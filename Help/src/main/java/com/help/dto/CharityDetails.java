package com.help.dto;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CharityDetails")
public class CharityDetails {

	private String name;
	
	private String phoneNumber;
	
	private String address;
	
	private ArrayList<String> images;
	
	private ArrayList<String> videos;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public ArrayList<String> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<String> videos) {
		this.videos = videos;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CharityDetails(String name, String phoneNumber, String address, ArrayList<String> images,
			ArrayList<String> videos, String description) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.images = images;
		this.videos = videos;
		this.description = description;
	}

	public CharityDetails() {
		super();
	}
	
	
	
}
