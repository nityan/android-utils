package com.nityankhanna.androidutils;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nityankhanna.androidutils.database.Column;
import com.nityankhanna.androidutils.database.ConstraintType;
import com.nityankhanna.androidutils.database.CreateTableHandler;
import com.nityankhanna.androidutils.database.DataType;
import com.nityankhanna.androidutils.database.DatabaseHandler;
import com.nityankhanna.androidutils.security.EncryptionManager;
import com.nityankhanna.androidutils.system.FileManager;
import com.nityankhanna.androidutils.ui.TextViewField;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * Created by Nityan Khanna on 22/10/13.
 */
public class MainActivity extends Activity implements View.OnClickListener {

	private static EncryptionManager encryptionManager;
	private String temp;
	private String temp2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		encryptionManager = EncryptionManager.getInstance();

		Button button = (Button) findViewById(R.id.button);
		Button button2 = (Button) findViewById(R.id.button2);

		DatabaseHandler databaseHandler = new DatabaseHandler(this, "thefeed", null, 1);

		Column firstColumn = new Column("id", DataType.TEXT, "PRIMARY KEY");
		Column secondColumn = new Column("name", DataType.TEXT, "NOT NULL UNIQUE");

		databaseHandler.createTable("users", true, firstColumn, secondColumn);
		databaseHandler.createTable("roles", true, firstColumn, secondColumn);

		Column userid = new Column("UserId", DataType.TEXT, ", FOREIGN KEY(UserId) REFERENCES users(id)");
		Column usersInRoles = new Column("RoleId", DataType.TEXT, ", FOREIGN KEY(RoleId) REFERENCES roles(id)");

		databaseHandler.createTable("usersInRoles", true, userid, usersInRoles);

		button.setOnClickListener(this);
		button2.setOnClickListener(this);

		TextViewField textViewField = (TextViewField) findViewById(R.id.text_view_field);

		Log.d("DEBUG", textViewField.getTextAsString());
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {

			case R.id.button:

				try {
					temp = new String(encryptionManager.encryptData("password", "Nityan".getBytes()));
					Log.e("DEBUG", temp);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}

				break;

			case R.id.button2:
				try {
					temp2 = new String(encryptionManager.decryptData("password", temp.getBytes()));

					Log.e("DEBUG", temp2);
				} catch (Exception ex) {
					FileManager.logException(this, ex);
				}

				break;

			default:
				break;
		}
	}
}
