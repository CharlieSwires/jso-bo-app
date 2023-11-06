package com.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import restful.Encryption;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@Document(collection="Entries")
public class MongoBean {
	final static String publicKey;
	final static String privateKey;
	static {
		publicKey=System.getenv("PUBLIC_KEY");
		privateKey=System.getenv("PRIVATE_KEY");
	}
	public MongoBean() {
	}

	//in the clear
	@Id
	private String id;
	private String firstname;
	private String surname;

	//encrypted
	private String title;
	private String extension;
	private String aofs;
	private String station;
	private String relevantDay;
	private String relevantTime;
	private String callDay;
	private String callTime;
	private String releaseExtension;
	private String releaseStation;
	private String releaseCallDay;
	private String releaseCallTime;
	private String held;
	private String arrestComments;
	private String team;
	private String region;
	private String suspectedCourt;
	private String courtOutcome;
	private String courtComments;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return (title != null && !title.isEmpty()? Encryption.decrypt(privateKey, title):null);
	}
	public void setTitle(String title) {
		this.title = (title != null && !title.isEmpty()? Encryption.encrypt(publicKey, title):null);
	}
	public String getFirstname() {
		return (firstname != null && !firstname.isEmpty()? firstname:null);
	}
	public void setFirstname(String firstname) {
		this.firstname = (firstname != null && !firstname.isEmpty()? firstname:null);
	}
	public String getSurname() {
		return (surname != null && !surname.isEmpty()? surname:null);
	}
	public void setSurname(String surname) {
		this.surname = (surname != null && !surname.isEmpty()? surname:null);
	}


	public String getExtension() {
		return (extension != null && !extension.isEmpty()? Encryption.decrypt(privateKey, extension):null);
	}
	public void setExtension(String extension) {
		this.extension = (extension != null && !extension.isEmpty()? Encryption.encrypt(publicKey, extension): null);
	}
	public String getAofs() {
		return (aofs != null && !aofs.isEmpty()? Encryption.decrypt(privateKey, aofs):null);
	}
	public void setAofs(String aofs) {
		this.aofs = (aofs != null && !aofs.isEmpty()? Encryption.encrypt(publicKey, aofs): null);
	}
	public String getStation() {
		return (station != null && !station.isEmpty()? Encryption.decrypt(privateKey, station):null);
	}
	public void setStation(String station) {
		this.station = (station != null && !station.isEmpty()? Encryption.encrypt(publicKey, station): null);
	}
	public String getRelevantDay() {
		return (relevantDay != null && !relevantDay.isEmpty()? Encryption.decrypt(privateKey, relevantDay):null);
	}
	public void setRelevantDay(String relevantDay) {
		this.relevantDay = (relevantDay != null && !relevantDay.isEmpty()? Encryption.encrypt(publicKey, relevantDay): null);
	}
	public String getRelevantTime() {
		return (relevantTime != null && !relevantTime.isEmpty()? Encryption.decrypt(privateKey, relevantTime):null);
	}
	public void setRelevantTime(String relevantTime) {
		this.relevantTime = (relevantTime != null && !relevantTime.isEmpty()? Encryption.encrypt(publicKey, relevantTime): null);
	}
	public String getCallDay() {
		return (callDay != null && !callDay.isEmpty()? Encryption.decrypt(privateKey, callDay):null);
	}
	public void setCallDay(String callDay) {
		this.callDay = (callDay != null && !callDay.isEmpty()? Encryption.encrypt(publicKey, callDay): null);
	}
	public String getCallTime() {
		return (callTime != null && !callTime.isEmpty()? Encryption.decrypt(privateKey, callTime):null);
	}
	public void setCallTime(String callTime) {
		this.callTime = (callTime != null && !callTime.isEmpty()? Encryption.encrypt(publicKey, callTime): null);
	}
	public String getReleaseExtension() {
		return (releaseExtension != null && !releaseExtension.isEmpty()? Encryption.decrypt(privateKey, releaseExtension):null);
	}
	public void setReleaseExtension(String releaseExtension) {
		this.releaseExtension = (releaseExtension != null && !releaseExtension.isEmpty()? Encryption.encrypt(publicKey, releaseExtension): null);
	}
	public String getReleaseStation() {
		return (releaseStation != null && !releaseStation.isEmpty()? Encryption.decrypt(privateKey, releaseStation):null);
	}
	public void setReleaseStation(String releaseStation) {
		this.releaseStation = (releaseStation != null && !releaseStation.isEmpty()? Encryption.encrypt(publicKey, releaseStation): null);
	}
	public String getReleaseCallDay() {
		return (releaseCallDay != null && !releaseCallDay.isEmpty()? Encryption.decrypt(privateKey, releaseCallDay):null);
	}
	public void setReleaseCallDay(String releaseCallDay) {
		this.releaseCallDay = (releaseCallDay != null && !releaseCallDay.isEmpty()? Encryption.encrypt(publicKey, releaseCallDay): null);
	}
	public String getReleaseCallTime() {
		return (releaseCallTime != null && !releaseCallTime.isEmpty()? Encryption.decrypt(privateKey, releaseCallTime):null);
	}
	public void setReleaseCallTime(String releaseCallTime) {
		this.releaseCallTime = (releaseCallTime != null && !releaseCallTime.isEmpty()? Encryption.encrypt(publicKey, releaseCallTime): null);
	}
	public String getHeld() {
		return (held != null && !held.isEmpty()? Encryption.decrypt(privateKey, held):null);
	}
	public void setHeld(String held) {
		this.held = (held != null && !held.isEmpty()? Encryption.encrypt(publicKey, held): null);
	}
	public String getTeam() {
		return (team != null && !team.isEmpty()? Encryption.decrypt(privateKey, team):null);
	}
	public void setTeam(String team) {
		this.team = (team != null && !team.isEmpty()? Encryption.encrypt(publicKey, team): null);
	}
	public String getRegion() {
		return (region != null && !region.isEmpty()? Encryption.decrypt(privateKey, region):null);
	}
	public void setRegion(String region) {
		this.region = (region != null && !region.isEmpty()? Encryption.encrypt(publicKey, region): null);
	}
	public String getSuspectedCourt() {
		return (suspectedCourt != null && !suspectedCourt.isEmpty()? Encryption.decrypt(privateKey, suspectedCourt):null);
	}
	public void setSuspectedCourt(String suspectedCourt) {
		this.suspectedCourt = (suspectedCourt != null && !suspectedCourt.isEmpty()? Encryption.encrypt(publicKey, suspectedCourt): null);
	}
	public String getCourtOutcome() {
		return (courtOutcome != null && !courtOutcome.isEmpty()? Encryption.decrypt(privateKey, courtOutcome):null);
	}
	public void setCourtOutcome(String courtOutcome) {
		this.courtOutcome = (courtOutcome != null && !courtOutcome.isEmpty()? Encryption.encrypt(publicKey, courtOutcome): null);
	}
	public String getCourtComments() {
		return (courtComments != null && !courtComments.isEmpty()? Encryption.decrypt(privateKey, courtComments):null);
	}
	public void setCourtComments(String courtComments) {
		this.courtComments = (courtComments != null && !courtComments.isEmpty()? Encryption.encrypt(publicKey, courtComments): null);
	}
	public String getArrestComments() {
		return (arrestComments != null && !arrestComments.isEmpty()? Encryption.decrypt(privateKey, arrestComments):null);
	}
	public void setArrestComments(String arrestComments) {
		this.arrestComments = (arrestComments != null && !arrestComments.isEmpty()? Encryption.encrypt(publicKey, arrestComments): null);
	}

}
