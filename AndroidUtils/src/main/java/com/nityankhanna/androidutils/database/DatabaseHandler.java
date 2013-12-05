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
}


