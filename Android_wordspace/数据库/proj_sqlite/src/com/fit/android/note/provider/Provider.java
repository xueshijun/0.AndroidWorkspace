package com.fit.android.note.provider;

import com.fit.android.entity.Notes;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Provider extends ContentProvider {

	public static final Uri prover_uri=Uri.parse("content://com.fit.android.note");
	
	private SQLiteDatabase db;
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		db.delete(Notes._NOTESTABLE, selection, selectionArgs);
		return 1;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		db.insert(Notes._NOTESTABLE,null, values);
		return null;
	}

	@Override
	public boolean onCreate() {
		db=getContext().openOrCreateDatabase(Notes._DBNAME,Context.MODE_PRIVATE,null);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		return db.query(Notes._NOTESTABLE,projection, selection, selectionArgs,null,null,sortOrder);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		db.update(Notes._NOTESTABLE,values, selection, selectionArgs);
		return 0;
	}

}
