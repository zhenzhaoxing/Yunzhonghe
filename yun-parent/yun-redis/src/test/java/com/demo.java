package com;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class demo {
        public static void main(String[] args) {
			/*Jedis je = new Jedis("192.168.229.129", 7001);
			 String result = je.set("address", "海淀");
			 String string = je.get("address");
			 System.out.println(string);*/
        	//集群版
        	HashSet<HostAndPort> set = new HashSet<HostAndPort>();
        	set.add(new HostAndPort("192.168.229.129",
        			7001));
        	set.add(new HostAndPort("192.168.229.129",
        			7002));
        	set.add(new HostAndPort("192.168.229.129",
        			7003));
        	set.add(new HostAndPort("192.168.229.129",
        			7004));
        	set.add(new HostAndPort("192.168.229.129",
        			7005));
        	set.add(new HostAndPort("192.168.229.129",
        			7006));
        	
        	
        	JedisCluster clu = new JedisCluster(set);
        	String string = clu.get("address");
        	System.out.println(string);
        	Long long1 = clu.append("zhen", "兆星");
        	System.out.println(long1);
        	Boolean exists = clu.exists("name");
        	System.out.println(exists);
        	Long del = clu.del("id");
        	System.out.println(del);
		}
}
