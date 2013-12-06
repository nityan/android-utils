package com.nityankhanna.androidutils.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nityan Khanna on 12/10/13.
 */
public class DatabaseHandler extends SQLiteOpenHelper implements DatabaseErrorHandler {

	protected static SQLiteDatabase database;

	public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		DatabaseHandler.database = database;
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int i, int i2) {
	}

	@Override
	public void onCorruption(SQLiteDatabase database) {
		//database.execSQL("ROLLBACK");
		throw new DatabaseCorruptionException("The database " + getDatabaseName() + " has been corrupted.");
	}

	protected synchronized SQLiteDatabase openForRead() {

		if (database != null && database.isOpen()) {
			return database;
		}

		database = getReadableDatabase();

		return database;
	}

	protected synchronized SQLiteDatabase openForWrite() {

		if (database != null && database.isOpen()) {
			return database;
		}

		database = getWritableDatabase();

		return database;
	}

	public void createTable(String tableName, boolean dropTableIfExists, Column... columns) {

		if (dropTableIfExists) {

			openForWrite();
			database.execSQL("DROP TABLE IF EXISTS " + tableName);
			close();
		}

		String startSyntax = "CREATE TABLE " + tableName + "(";

		StringBuilder builder = new StringBuilder();

		builder.append(startSyntax);

		int index = 0;

		for (Column column : columns) {
			builder.append(column.toString());

			if (index != columns.length - 1) {
				builder.append(", ");
			}

			index++;
		}

		builder.append(")");

		openForWrite();

		database.execSQL(builder.toString());

		close();
	}

	public void updateTable(String tableName, String[] columns, Object[] newValues) {

		if (columns.length != newValues.length) {
			throw new IllegalArgumentException("The columns to update do not match the number of new values");
		}

		StringBuilder builder = new StringBuilder();

		builder.append("UPDATE ");
		builder.append(tableName);
		builder.append(" SET ");

		for (String s : columns) {

		}

		openForWrite();
	}

	public void dropTable(String tableName) {
		openForWrite();
		database.execSQL("DROP TABLE IF EXISTS" + tableName);
		close();
	}
}


