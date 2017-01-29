package com.techpalle.contenetproviderexample;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private MyHelper myHelper;

    //URIto table Mapping Logic
    private static UriMatcher uriMatcher = new UriMatcher(-1);
    static {
        uriMatcher.addURI("com.techpalle.B_34", "studentTable", 1);
    }
    //END OF MAPPING

    public class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table student(_id integer primary key, name text, sub text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case 1:
                // Means client is asking to insert into student table
                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                sqLiteDatabase.insert("student",null, values);
                break;
            default:
                //Means invalid table
                break;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        myHelper = new MyHelper(getContext(), "tech.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case 1:
                //Client is requesting to read student table
                Cursor c = null;
                SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
                c = sqLiteDatabase.query("student", null, null, null, null, null, null);
                return c;
            default:
                //means invalid table name
                break;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
