package com.bupt.turtleservice.model;

public class User {

	private String name = "";
	private String account = "";
	private String passwd = "";
	private String photo = "";
	private String birthday = "";
	private String studySchool = "";
	private String studyMajor = "";
	private String hometown = "";
	private boolean isSingle;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getStudySchool() {
		return studySchool;
	}
	public void setStudySchool(String studySchool) {
		this.studySchool = studySchool;
	}
	public String getStudyMajor() {
		return studyMajor;
	}
	public void setStudyMajor(String studyMajor) {
		this.studyMajor = studyMajor;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public boolean isSingle() {
		return isSingle;
	}
	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}
}
