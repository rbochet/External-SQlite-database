package fr.stackr.android.externaldb;

import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ExternalDBActivity extends Activity {
	private static final String TAG = "ExtDB";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView contentLog = (TextView) findViewById(R.id.content_log);

		// Create the database
		DataBaseHelper myDbHelper = new DataBaseHelper(
				this.getApplicationContext());
		myDbHelper = new DataBaseHelper(this);

		try {
			myDbHelper.createDataBase();
			contentLog.append("Database Created\n");
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		// Open the database
		try {

			myDbHelper.openDataBase();
			contentLog.append("Database Opened\n");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		// Get the readable version
		SQLiteDatabase db = myDbHelper.getReadableDatabase();
		contentLog.append("Get the readable database\n");

		// Make a select
		Cursor cur = db.rawQuery(
				"SELECT name FROM serval_developers ORDER BY name ASC;", null);

		cur.moveToPosition(0);
		Log.v(TAG, "Nb Col:" + cur.getColumnCount());
		Log.v(TAG, "Nb Records:" + cur.getCount());
		cur.close();
		contentLog.append("Select:\t" + cur.getColumnCount() + " cols, "
				+ cur.getCount() + " rows\n");

		// Make an insert
		ContentValues values = new ContentValues();
		values.put("name", "Serval");
		values.put("surname", "Cat");
		long servalCatID = db.insert("serval_developers", null, values);
		Log.v(TAG, "Serval Cat Inserted @: " + servalCatID);
		contentLog.append("Insert @ \t" + servalCatID + "\n");

		// Check insert
		cur = db.rawQuery(
				"SELECT name FROM serval_developers ORDER BY name ASC;", null);

		cur.moveToPosition(0);
		Log.v(TAG, "Nb Col:" + cur.getColumnCount());
		Log.v(TAG, "Nb Records:" + cur.getCount());
		cur.close();
		contentLog.append("Select:\t" + cur.getColumnCount() + " cols, "
				+ cur.getCount() + " rows\n");

		// dumb
		cur = db.rawQuery(
				"SELECT name, surname FROM serval_developers ORDER BY name ASC;",
				null);
		contentLog.append("\nDUMP\n");
		int i = 0;
		cur.moveToFirst();
		while (cur.isAfterLast() == false) {
			contentLog.append("(" + i++ + ")\t\t" + cur.getString(0) + "\t"
					+ cur.getString(1) + "\n");
			cur.moveToNext();
		}

		cur.moveToPosition(0);

		// Close
		myDbHelper.close();
		contentLog.append("Database closed.");

		// YEAH
	}
}