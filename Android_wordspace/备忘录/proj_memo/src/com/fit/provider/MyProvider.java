package com.fit.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.fit.entity.Memo;

public class MyProvider extends ContentProvider {
	public static final Uri prover_uri=Uri.parse("content://com.fit.android.memo");
	private SQLiteDatabase db;
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		db.delete(Memo.TABLENAME, selection, selectionArgs);
		return 1;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public boolean onCreate() {
		db = getContext().openOrCreateDatabase(Memo.DBNAME,Context.MODE_PRIVATE,null);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		System.out.println(db==null);
		return db.query(Memo.TABLENAME, projection, selection, selectionArgs,null, null, sortOrder);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
