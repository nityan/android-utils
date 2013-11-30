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

	/**
	 * Initializes a new instance of a Column.
	 */
	public Column() {
	}

	/**
	 * Initializes a new instance of a Column with a name and type.
	 * @param name The name.
	 * @param type The type.
	 */
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