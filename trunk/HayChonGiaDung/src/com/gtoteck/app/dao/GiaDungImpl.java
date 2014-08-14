package com.gtoteck.app.dao;

import android.content.Context;
import android.database.Cursor; 
import android.database.sqlite.SQLiteDatabase;

import com.gtoteck.app.util.DatabaseUtil; 
 

public class GiaDungImpl extends DatabaseUtil implements GiaDungDAO  {
	
	private static final String TABLE_NAME ="giadung";

	public GiaDungImpl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GiaDungEntity getGiaDungEntityByPosition(int index) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = getReadableDatabase();

		Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME
				+ " LIMIT " + index + ",1",
				null);

		cursor.moveToFirst(); 

		GiaDungEntity entity = new GiaDungEntity(cursor);
		 

		cursor.close();
		database.close();

		return entity;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		SQLiteDatabase database = getReadableDatabase();

		Cursor cursor = database.rawQuery("SELECT count(*) AS size FROM "
				+ TABLE_NAME ,null);
		cursor.moveToFirst();

		int size = cursor.getInt(0);

		cursor.close();
		database.close();

		return size; 
	}

	  
	
}
