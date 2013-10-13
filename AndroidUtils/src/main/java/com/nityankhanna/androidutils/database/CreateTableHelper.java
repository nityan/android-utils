package com.nityankhanna.androidutils.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nityan Khanna on 12/10/13.
 */
public class CreateTableHelper {

	private String tableName;
	private List<Column> columns;

	public CreateTableHelper(String tableName) {
		this.tableName = tableName;
		columns = new ArrayList<Column>();
	}

	public void addColumn(Column column) {
		columns.add(column);
	}

	public void createTable() {

		int counter = 0;
		int columnCount = columns.size();
		String syntax = "CREATE TABLE " + tableName + "(";

		while (counter < columnCount) {
			syntax = columns.get(columnCount).getName() + ", " + columns.get(columnCount).getType() + ", " + columns.get(columnCount).getConstraints();
		}


	}

	public List<Column> getColumns() {
		return columns;
	}

	public void removeColumn(String name) {

		int index = 0;

		for (Column column : columns) {
			index++;
			if (column.getName().equals(name)) {
				break;
			}
		}

		columns.remove(index);
	}

	private String createColumnSyntax() {

		String name;
		String type;
		List<ConstraintType> constraints;

		StringBuilder builder = new StringBuilder();

		for (Column column : columns) {
			builder.append(column.getName());
			builder.append(column.getType());

			if (column.hasConstraints()) {

			}
		}
	}


}
