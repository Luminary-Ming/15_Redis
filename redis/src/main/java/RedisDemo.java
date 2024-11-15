
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDemo {
    public static void main(String[] args) {
        demo02();
    }

    public static void demo01() {
        Jedis jedis = new Jedis("192.168.200.133", 6379);
        jedis.auth("leesin");
        jedis.set("username", "yasuo");
        jedis.close();
    }

    public static void demo02() {
        // 创建链接 Redis 的连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(10);
        // 设置最小空闲连接数
        config.setMaxIdle(2);
        // 设置获取连接时的最大等待毫秒数（如果设置为阻塞时 BlockWhenExhausted），如果超时就抛异常，默认-1表示永不超时
        config.setMaxWaitMillis(1000);
        JedisPool jedisPool = new JedisPool(config, "192.168.200.133", 6379, 1000, "leesin");
        // 通过连接池获取 jedis 对象
        Jedis jedis = jedisPool.getResource();
        jedis.zadd("users", 3, "zhangsan3");
        jedis.zadd("users", 8, "zhangsan8");
        jedis.zadd("users", 7, "zzangsan7");
        jedis.zadd("users", 10, "zahngsan10");
        jedis.zadd("users", 5, "zhangsan5");
        jedis.zadd("users", 1, "zhangsan1");
        jedis.close();
    }
}
