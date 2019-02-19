/**
 * 
 */
package com.help.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.help.dto.CharityDetails;

/**
 * @author chaitra.honnur
 *
 */
@Repository
public interface HelpRepo extends MongoRepository<CharityDetails, Long> {
	

}
