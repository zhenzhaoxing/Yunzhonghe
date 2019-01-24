package com.star.redis.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.star.redis.dao.JedisDao;

import redis.clients.jedis.JedisCluster;
@Service
public class JedisDaoImpl implements JedisDao {

	@Resource
	private  JedisCluster jedisClients;
	@Override
	public Boolean exists(String key) {
		return jedisClients.exists(key);
	}

	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		return jedisClients.del(key);
	}

	@Override
	public String get(String key) {
		return jedisClients.get(key);
	}

	@Override
	public String set(String key, String value) {
		return jedisClients.set(key, value);
	}

	@Override
	public Long expire(String key, int seconds) {
     return jedisClients.expire(key, seconds);

	}

}
