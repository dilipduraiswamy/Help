package com.help.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.help.dto.CharityDetails;

@Repository
public class HelpDaoImpl implements HelpDao {

	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Integer save(CharityDetails charityDetails) throws Exception {
		// TODO Auto-generated method stub
		mongoTemplate.save(charityDetails);
		return 1;
		
	}
	
	
}
