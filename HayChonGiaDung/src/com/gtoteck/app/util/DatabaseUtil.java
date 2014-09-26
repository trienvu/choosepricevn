package com.gtoteck.app.util;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseUtil extends SQLiteAssetHelper {
	private static final String DATABASE_NAME = "haychongiachodung";
	private static final int DATABASE_VERSION = 3;

	public DatabaseUtil(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		// you can use an alternate constructor to specify a database location 
		// (such as a folder on the sd card)
		// you must ensure that this folder is available and you have permission
		// to write to it
		//super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

	}
}
