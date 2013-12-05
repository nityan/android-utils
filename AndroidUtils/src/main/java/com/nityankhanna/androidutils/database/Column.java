package com.nityankhanna.androidutils.database;

/**
 * Created by Nityan Khanna on 12/10/13.
 */

import java.util.ArrayList;

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
	 * Initializes a new instance of a Column with a name.
	 *
	 * @param name The name.
	 */
	public Column(String name) {
		this.name = name;
	}

	/**
	 * Initializes a new instance of a Column with a name and type.
	 *
	 * @param name The name.
	 * @param type The type.
	 */
	public Column(String name, DataType type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Initializes a new instance of a Column with a name, type and constraints.
	 *
	 * @param name        The name.
	 * @param type        The type.
	 * @param constraints The constraints of the column.
	 */
	public Column(String name, DataType type, String constraints) {
		this.name = name;
		this.type = type;
		this.constraints = constraints;
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

	@Override
	public String toString() {
		return name + " " + type + " " + constraints;
	}
}