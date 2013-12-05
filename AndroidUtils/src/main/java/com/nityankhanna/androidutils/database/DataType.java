package com.nityankhanna.androidutils.database;

/**
 * Created by Nityan Khanna on 12/10/13.
 */
public enum DataType {
	ARRAY,
	BINARY,
	BOOLEAN,
	CHARACTER,
	DATE,
	DECIMAL,
	DOUBLE,
	FLOAT,
	INTEGER,
	INTERVAL,
	MULTISET,
	NUMERIC,
	REAL,
	SMALL_INT,
	TEXT,
	TIME,
	TIMESTAMP,
	VARBINARY,
	VARCHAR,
	XML;

	private DataType() {
	}

	@Override
	public String toString() {

		String value;

		switch (this) {

			case ARRAY:
				value = "ARRAY ";
				break;

			case BINARY:
				value = "BINARY ";
				break;

			case BOOLEAN:
				value = "BOOLEAN ";
				break;

			case CHARACTER:
				value = "CHARACTER ";
				break;

			case DATE:
				value = "DATE ";
				break;

			case DECIMAL:
				value = "DECIMAL ";
				break;

			case DOUBLE:
				value = "DOUBLE PRECISION ";
				break;

			case FLOAT:
				value = "FLOAT ";
				break;

			case INTEGER:
				value = "INTEGER ";
				break;

			case INTERVAL:
				value = "INTERVAL ";
				break;

			case MULTISET:
				value = "MULTISET ";
				break;

			case NUMERIC:
				value = "NUMERIC ";
				break;

			case REAL:
				value = "REAL ";
				break;

			case SMALL_INT:
				value = "SMALL INT ";
				break;

			case TEXT:
				value = "TEXT ";
				break;

			case TIME:
				value = "TIME ";
				break;

			case TIMESTAMP:
				value = "TIMESTAMP ";
				break;

			case VARBINARY:
				value = "VARBINARY ";
				break;

			case VARCHAR:
				value = "VARCHAR ";
				break;

			case XML:
				value = "XML ";
				break;

			default:
				value = null;
				break;
		}

		return value;
	}

	/*
	@Override
	public String toString() {

		String value;

		switch (this) {

			case ARRAY:
				value = " ARRAY ";
				break;

			case BINARY:
				value = " BINARY ";
				break;

			case BOOLEAN:
				value = " BOOLEAN ";
				break;

			case CHARACTER:
				value = " CHARACTER ";
				break;

			case DATE:
				value = " DATE ";
				break;

			case DECIMAL:
				value = " DECIMAL ";
				break;

			case DOUBLE:
				value = " DOUBLE PRECISION ";
				break;

			case FLOAT:
				value = " FLOAT ";
				break;

			case INTEGER:
				value = " INTEGER ";
				break;

			case INTERVAL:
				value = " INTERVAL ";
				break;

			case MULTISET:
				value = " MULTISET ";
				break;

			case NUMERIC:
				value = " NUMERIC ";
				break;

			case REAL:
				value = " REAL ";
				break;

			case SMALL_INT:
				value = " SMALL INT ";
				break;

			case TEXT:
				value = " TEXT ";
				break;

			case TIME:
				value = " TIME ";
				break;

			case TIMESTAMP:
				value = " TIMESTAMP ";
				break;

			case VARBINARY:
				value = " VARBINARY ";
				break;

			case VARCHAR:
				value = " VARCHAR ";
				break;

			case XML:
				value = " XML ";
				break;

			default:
				value = null;
				break;
		}

		return value;
	}
	 */
}
