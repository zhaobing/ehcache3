package org.ehcache.demos.zhaob

import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder

object TestK {
  @JvmStatic
  fun main(args: Array<String>) {
    val cacheManagerBuilder = CacheManagerBuilder.newCacheManagerBuilder()
    val cacheManager = cacheManagerBuilder.build()
    cacheManager.init()

    val cacheCfg = CacheConfigurationBuilder.newCacheConfigurationBuilder(1L.javaClass,
      String::class.java, ResourcePoolsBuilder.heap(10))
    val cache = cacheManager.createCache("test-cache", cacheCfg)

    cache.put(1L, "测试值1")
    val value = cache.get(1)
    println(value)
  }
}
