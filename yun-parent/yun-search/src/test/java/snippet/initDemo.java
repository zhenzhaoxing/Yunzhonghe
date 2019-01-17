package snippet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class initDemo {
	@Test
	public void sendMessage() throws Exception {
	
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-jms-consumer.xml");
		//等待
		System.in.read();
	
	}
}
