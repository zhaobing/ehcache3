package org.ehcache.demos.zhaob;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class ProgrammaticConfigurationDemo {
  public static void main(String[] args) {
    test1();
  }

  public static void test1() {
    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
      .withCache("preConfigured",
        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
      .build();
    cacheManager.init();

    Cache<Long, String> preConfigured =
      cacheManager.getCache("preConfigured", Long.class, String.class);


    preConfigured.put(0L, "abc");

    Cache<Long, String> myCache = cacheManager.createCache("myCache",
      CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)));

    myCache.put(1L, "da one!");
    String value = myCache.get(1L);
    System.out.println(value);


    String v2 = preConfigured.get(0L);
    System.out.println(v2);

    cacheManager.removeCache("preConfigured");


    String v3 = preConfigured.get(0L);
    System.out.println(v3);

    cacheManager.close();
  }


}
