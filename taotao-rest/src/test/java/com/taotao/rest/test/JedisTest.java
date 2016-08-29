package com.taotao.rest.test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	/**
	 * 单机版jedis测试
	 */
	@Test
	public void testJedisSingle() {
		Jedis jedis = new Jedis("", 6379);
		jedis.set("name", "whling");
		String name = jedis.get("name");

		System.out.println(name);
		jedis.close();
	}

	/**
	 * jedis连接池测试
	 */
	@Test
	public void testJedisPool() {
		JedisPool jedisPool = new JedisPool("192.168.1.132", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("name", "wohenni");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
		jedisPool.close();
	}

	/**
	 * 集群版测试
	 * 
	 */
	@Test
	public void testJedisCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.132", 7001));
		nodes.add(new HostAndPort("192.168.1.132", 7002));
		nodes.add(new HostAndPort("192.168.1.132", 7003));
		nodes.add(new HostAndPort("192.168.1.132", 7004));
		nodes.add(new HostAndPort("192.168.1.132", 7005));
		nodes.add(new HostAndPort("192.168.1.132", 7006));

		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("name", "maolin");
		String name = cluster.get("name");
		System.out.println(name);
		try {
			cluster.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * jedis整合spring测试(单机)
	 */
	@Test
	public void testJedisAndSpring() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisPool jedisPool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = jedisPool.getResource();
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
	}

	/**
	 * jedis整合spring测试(集群版)
	 * @throws IOException 
	 */
	@Test
	public void testJedisAndSpringCluster() throws IOException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster) applicationContext.getBean("redisClient");
		String name = cluster.get("name");
		System.out.println(name);
		cluster.close();
	}
}
