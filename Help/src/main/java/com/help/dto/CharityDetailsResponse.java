/**
 * 
 */
package com.help.dto;

/**
 * @author chaitra.honnur
 *
 */
public class CharityDetailsResponse {

	private String StatusMessage;
	private Integer StatusCode;
	
	
	public String getStatusMessage() {
		return StatusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		StatusMessage = statusMessage;
	}
	public Integer getStatusCode() {
		return StatusCode;
	}
	public void setStatusCode(Integer statusCode) {
		StatusCode = statusCode;
	}
	public CharityDetailsResponse(String statusMessage, Integer statusCode) {
		super();
		StatusMessage = statusMessage;
		StatusCode = statusCode;
	}
	
	
	
}
