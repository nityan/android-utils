package com.nityankhanna.androidutils.database;

/**
 * Created by Nityan Khanna on 12/10/13.
 */

/**
 * Represents a database column.
 */
public class Column {

	private String name;
	private DataType type;
	private String constraints;

	public Column() {
	}

	public Column(String name, DataType type) {
		this.name = name;
		this.type = type;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}
}