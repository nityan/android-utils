package com.nityankhanna.androidutils.database;

/**
 * Created by Nityan Khanna on 12/10/13.
 */
public enum ConstraintType {

	/**
	 * Ensures that the value in a column meets a specific condition
	 */
	CHECK,

	/**
	 * Specifies a default value when specified none for this column
	 */
	DEFAULT,

	/**
	 * Ensure the referential integrity of the data in one table to match values in another table.
	 */
	FOREIGN_KEY,

	/**
	 * Indicates that a column cannot store NULL value.
	 */
	NOT_NULL,

	/**
	 * A combination of a NOT NULL and UNIQUE.
	 * Ensures that a column (or combination of two or more columns) have an unique identity which helps to find a particular record in a table more easily and quickly.
	 */
	PRIMARY_KEY,

	/**
	 * A combination of a NOT NULL and UNIQUE. with autoincrement.
	 * Ensures that a column (or combination of two or more columns) have an unique identity which helps to find a particular record in a table more easily and quickly.
	 */
	PRIMARY_KEY_AUTO_INCREMENT,

	/**
	 * Ensures that each row for a column must have a unique value.
	 */
	UNIQUE;

	private ConstraintType() {
	}

	@Override
	public String toString() {

		String value;

		switch (this) {

			case CHECK:
				value = " CHECK ";
				break;

			case DEFAULT:
				value = " DEFAULT ";
				break;

			case FOREIGN_KEY:
				value = " FOREIGN KEY ";
				break;

			case NOT_NULL:
				value = " NOT NULL ";
				break;

			case PRIMARY_KEY:
				value = " PRIMARY KEY ";
				break;

			case PRIMARY_KEY_AUTO_INCREMENT:
				value = " PRIMARY KEY AUTO INCREMENT ";
				break;

			case UNIQUE:
				value = " UNIQUE ";
				break;

			default:
				value = null;
				break;
		}

		return value;
	}
}
