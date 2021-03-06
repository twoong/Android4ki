package com.twoong.android4ki.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {



    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher;

    public static final int ALL = 1;
    public static final int ITEM = 2;
    public static final String AUTHORITY = "com.twoong.android4ki.provider";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.twoong.android4ki.provider." + FeedReaderContract.FeedEntry.TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.twoong.android4ki.provider." + FeedReaderContract.FeedEntry.TABLE_NAME;

    public static final Uri URI= Uri.parse("content://" + AUTHORITY + "/" + FeedReaderContract.FeedEntry.TABLE_NAME);

    static{
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, FeedReaderContract.FeedEntry.TABLE_NAME, ALL);
        sUriMatcher.addURI(AUTHORITY, FeedReaderContract.FeedEntry.TABLE_NAME + "/#", ITEM);
    }

    private FeedReaderDbHelper mDbHelper;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        switch (sUriMatcher.match(uri)){
            case ALL:
                break;
            case ITEM:
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            case UriMatcher.NO_MATCH:
                return 0;
            //throw new UnsupportedOperationException("Not yet implemented");
        }

        int deletedRow = mDbHelper.getWritableDatabase().delete(FeedReaderContract.FeedEntry.TABLE_NAME,
                selection,
                selectionArgs);

        //데이터 삭제됨을 통지
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedRow;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)){
            case ALL:
                return CONTENT_TYPE;
            case ITEM:
                return CONTENT_ITEM_TYPE;
            default:
                return null;
                //throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = mDbHelper.getWritableDatabase().insert(FeedReaderContract.FeedEntry.TABLE_NAME,
                null,
                values);
        if(rowId != -1) {
            Uri returnUri = ContentUris.withAppendedId(URI, rowId);

            //데이터 추가됨을 통지
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = FeedReaderDbHelper.getInstance(getContext());
        return mDbHelper != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (sUriMatcher.match(uri)){
            case ALL:
                break;
            case ITEM:
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            case UriMatcher.NO_MATCH:
                return null;
        }
        Cursor cursor = mDbHelper.getReadableDatabase().query(FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        // 데이터 변경이 감지되도록 resolver 에 알림
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (sUriMatcher.match(uri)){
            case ALL:
                break;
            case ITEM:
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            case UriMatcher.NO_MATCH:
                return 0;
        }
        int updatedRow = mDbHelper.getWritableDatabase().update(FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(updatedRow > 0){
            //데이터 변경됨을 통지
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updatedRow;
    }
}
