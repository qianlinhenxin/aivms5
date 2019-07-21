package com.qlhx.service.logger.realize.main;

import com.mongodb.client.FindIterable;
import com.qlhx.service.logger.realize.util.MgTemplate;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Serializable;

@EnableEurekaClient
@ServletComponentScan
@MapperScan("com.qlhx.dao")
@SpringBootApplication
@EnableTransactionManagement
public class ServiceLoggerApplication {

	 @Autowired
     MgTemplate mgTemplate;
	 
	 @Autowired
	 private MongoTemplate mongoTemplate;
	 
	public static void main(String[] args) {
		SpringApplication.run(ServiceLoggerApplication.class, args);
		
		
		
	}
	
	@Bean
	public void ss()
	{
		
		System.out.println("===========");
		Document document = new Document();
//		Map m = new HashMap();
//		m.put("bb", 11);
//		m.put("cc", 22);
//        document.putAll(m);
		A a = new A();
		a.setName("大海66");
		a.setSex("男66");
		mongoTemplate.insert(a,"ldh");
//		document.putAll(BSON.applyEncodingHooks(arg0));
//		mgTemplate.getCollection("ldh").insertOne(Document.parse(JSON.toJSONString(a)));
		BsonDocument book = new BsonDocument();
		book.append("name", new BsonString("大海"));
		FindIterable<Document>  it = mgTemplate.getCollection("ldh").find(book);
		Document dd= it.first();
		System.out.println("========="+dd.get("sex"));
//		while (it.hasMoreElements()) {
//			type type = (type) it.nextElement();
		
	}
	
	class A implements Serializable
	{
		private String name;
		private String sex;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
	}

}
