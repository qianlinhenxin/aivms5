package com.qlhx.service.logger.realize.util;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


@Component
public class MgTemplate {
	
	static{
		System.out.println("11111111111111");
	}
	
	@Autowired
    private MongoTemplate mongoTemplate;
 
    public MongoCollection<Document> getCollection(String collectionName) {
    	
        return mongoTemplate.getCollection(collectionName);
    }


}
