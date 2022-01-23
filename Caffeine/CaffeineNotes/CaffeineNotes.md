# 本地缓存介绍

缓存在日常开发中启动至关重要的作用，由于是存储在内存中，数据的读取速度是非常快的，能大量减少对数据库的访问，减少数据库的压力。

Redis 这种 NoSql 作为分布式缓存组件，能够提供多个服务间的缓存，但是需要网络开销，增加时耗。

本地缓存是直接从本地内存中读取，没有网络开销，例如秒杀系统或者数据量小的缓存等，比远程缓存更合适。

Caffeine 是基于 JAVA 8 的高性能缓存库。并且在 Spring5 (SpringBoot 2.x) 后，官方放弃了 Guava，而使用了性能更优秀的 Caffeine 作为默认缓存组件。

# SpringBoot 集成 Caffeine 两种方式

SpringBoot 有两种使用 Caffeine 作为缓存的方式：

- 方式一：直接引入 ==Caffeine 依赖==，然后使用 ==Caffeine 方法==实现缓存。
- 方式二：引入 ==Caffeine 和 Spring Cache 依赖==，使用 ==SpringCache 注解方法==实现缓存。



# 参考资料

[1] [SpringBoot 使用 Caffeine 本地缓存](https://blog.csdn.net/weixin_36380516/article/details/104454055)