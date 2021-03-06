package com.github.demidko.redis.utils

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class RedisKtTest {

  @Test
  fun `configs should works correctly`() =
    clientOf("rediss://root:23449cdf4934fd@crack-do-user-randombd.jb.ondigitalocean.com:37081")
      .useSingleServer()
      .run {
        assertThat(username, equalTo("root"))
        assertThat(password, equalTo("23449cdf4934fd"))
      }
}