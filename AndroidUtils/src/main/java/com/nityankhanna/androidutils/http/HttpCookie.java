package com.nityankhanna.androidutils.http;

import org.apache.http.cookie.Cookie;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Nityan Khanna on 10/11/13.
 */
public class HttpCookie implements Cookie {

	private String comment;
	private String domain;
	private Date expiryDate;
	private boolean isSecure;
	private String name;
	private String path;
	private String value;
	private int version;

	public HttpCookie() {
		super();
	}

	public HttpCookie(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public HttpCookie(String name, String value, boolean isSecure) {
		super();
		this.name = name;
		this.value = value;
		this.isSecure = isSecure;
	}

	public HttpCookie(String name, String value, Date expiryDate) {
		super();
		this.name = name;
		this.value = value;
		this.expiryDate = expiryDate;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String getCommentURL() {
		return null;
	}

	@Override
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public boolean isPersistent() {
		return (null != expiryDate);
	}

	@Override
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {

		if (domain != null) {
			this.domain = domain.toLowerCase(Locale.ENGLISH);
		} else {
			this.domain = null;
		}
	}

	@Override
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int[] getPorts() {
		throw new UnsupportedOperationException("This method has not been implemented yet.");
	}

	@Override
	public boolean isSecure() {
		return isSecure;
	}

	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}

	@Override
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public boolean isExpired(Date date) {

		if (date == null) {
			throw new IllegalArgumentException("Date may not be null");
		}

		return (expiryDate != null && expiryDate.getTime() <= date.getTime());
	}

	public void setExpired(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return String.format("name: %s, value: %s, comment: %s, expiryDate: %s, isPersistent: %s, domain: %s, path: %s, secure: %s, version: %s",
				name, value, comment, expiryDate, isPersistent(), domain, path, isSecure, version);
	}
}