package com.encore.spring.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadDataVO {
	private String userName;
	
	private List<MultipartFile> file;

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
