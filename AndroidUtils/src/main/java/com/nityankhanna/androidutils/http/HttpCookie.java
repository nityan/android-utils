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
	}

	public HttpCookie(String name, String value) {
		this.name = name;
		this.value = value;
		this.comment = "";
		this.domain = "";
		this.expiryDate = null;
		this.path = "";
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

	/**
	 * This method is not used and always returns null.
	 *
	 * @return Returns null.
	 */
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
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (!(object instanceof HttpCookie)) {
			return false;
		}

		HttpCookie cookie = (HttpCookie) object;

		return name.equals(cookie.name) && value.equals(cookie.value)
				&& comment.equals(cookie.comment) && domain.equals(cookie.domain)
				&& expiryDate.equals(cookie.expiryDate) && path.equals(cookie.path)
				&& isSecure == cookie.isSecure && version == cookie.version;
	}

	@Override
	public int hashCode() {

		int result = 17;

		result = 31 * result + name.hashCode();
		result = 31 * result + value.hashCode();
		result = 31 * result + comment.hashCode();
		result = 31 * result + domain.hashCode();
		result = 31 * result + expiryDate.hashCode();
		result = 31 * result + path.hashCode();
		result = 31 * result + (isSecure ? 1 : 0);
		result = 31 * result + version;

		return result;
	}

	@Override
	public String toString() {
		return "HttpCookie{" +
				"comment='" + comment + '\'' +
				", domain='" + domain + '\'' +
				", expiryDate=" + expiryDate +
				", isSecure=" + isSecure +
				", name='" + name + '\'' +
				", path='" + path + '\'' +
				", value='" + value + '\'' +
				", version=" + version +
				'}';
	}
}