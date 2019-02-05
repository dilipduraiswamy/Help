package com.help.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelpDaoImpl implements HelpDao {

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
}
