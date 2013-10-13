package com.nityankhanna.androidutils.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nityan Khanna on 12/10/13.
 */
public abstract class DatabaseHelper extends SQLiteOpenHelper implements DatabaseErrorHandler {

	public static CreateTableHelper createTableHelper;

	public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);

		Column column = new Column("id", DataType.INTEGER);
		column.setConstraints("PRIMARY KEY AUTOINCREMENT");

		Column secondColumn = new Column("first_name", DataType.VARCHAR);
		secondColumn.setConstraints("NOT NULL");

		createTableHelper.addColumn(new Column("first_name", DataType.VARCHAR));

	}

	@Override
	public void onCreate(SQLiteDatabase database) {

	}

	public void open() {
		this.getWritableDatabase();
	}
}
