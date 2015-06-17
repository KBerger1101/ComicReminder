package com.example.kevin.comicreminder.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kevin.comicreminder.ComicInfo;
import com.example.kevin.comicreminder.data.ComicContract.ComicEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kevin on 5/18/2015.
 */
public class ComicDbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "comics.db";
    public ComicDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create a table to hold each comic's title string, int date, and int releaseFreq
        final String SQL_CREATE_TABLE= "CREATE TABLE " + ComicEntry.TABLE_NAME + "(" +
                ComicEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ComicEntry.COLUMN_COMIC_OBJECT + " TEXT NOT NULL"+
                ");";
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ComicEntry.TABLE_NAME);;
        onCreate(db);
    }


     public void addComic(ComicInfo comic){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        String comicString = gson.toJson(comic);

        values.put(ComicEntry.COLUMN_COMIC_OBJECT,comicString);

        db.insert(ComicEntry.TABLE_NAME, null, values);

        db.close();
    }

    public List<ComicInfo> getComic(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(ComicEntry.TABLE_NAME, ComicEntry.COLUMNS,
                        " id = ?",
                        new String[] { String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);

        //if we get results
        if (cursor != null)
            cursor.moveToFirst();

        //build new book Object after de serializing
        Type listType = new TypeToken<ArrayList<ComicInfo>>() {
        }.getType();

        List<ComicInfo>  comicInfos = new Gson().fromJson(String.valueOf(cursor), listType);
     return comicInfos;
    }





    public List<ComicInfo> getAllComics(){
        List<ComicInfo> comicInfos = new LinkedList<ComicInfo>();

        String query = "SELECT * FROM " + ComicEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ComicInfo comic= null;

        if (cursor.moveToFirst()){
            //build new book Object after de serializing
            Type listType = new TypeToken<ArrayList<ComicInfo>>() {
            }.getType();
            do {


                comic = new Gson().fromJson(String.valueOf(cursor), listType);



                comicInfos.add(comic);
            } while (cursor.moveToNext());
        }
        db.close();
        return comicInfos;

    }


     public int update(ComicInfo comic) {
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         String comicText = new Gson().toJson(comic);
         values.put("comic", comicText);

         int i = db.update(ComicEntry.TABLE_NAME,
                 values,
                 ComicEntry.KEY_ID + " = ?",
                 new String[]{String.valueOf(comic.getID())});
         //

         db.close();
         return i;
     }
         //


    public void deleteComic(ComicInfo comic) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(ComicEntry.TABLE_NAME,
                ComicEntry.KEY_ID + " = ?",
                new String[]{String.valueOf(comic.getID())});

        db.close();
    }

}

