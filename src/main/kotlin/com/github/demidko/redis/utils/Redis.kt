package com.github.demidko.redis.utils

import co.touchlab.stately.isolate.IsolateState
import org.redisson.api.RedissonClient
import org.redisson.config.Config

/**
 * Configure redis client
 * @param url connection string with login and password
 * @return config object
 */
public fun clientOf(url: String) = Config().apply {
  useSingleServer().apply {
    address = url
    val authorizationIdx = 9
    username = url
      .substring(authorizationIdx)
      .substringBefore(':')
    password = url
      .substring(authorizationIdx + username.length + 1)
      .substringBefore('@')
  }
}

/**
 * Returns Redis safe isolated map instance by name or local map.
 *
 * @param <K> type of key
 * @param <V> type of value
 * @param name - name of object
 * @return isolated map object
 */
public fun <K, V> RedissonClient.threadSafeMap(name: String) = IsolateState { getMap<K, V>(name) as MutableMap<K, V> }