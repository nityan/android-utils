package com.nityankhanna.androidutils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nityan Khanna on 05/12/13.
 */
public final class CreateTableHandler {

	public CreateTableHandler() {
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

	public String createForeignKeySyntax(Column column, String referenceTableName, Column referenceColumn) {
		return "FOREIGN KEY(" + column.getName() + ") REFERENCES " + referenceTableName + "(" + referenceColumn.getName() + ")";
	}
}
