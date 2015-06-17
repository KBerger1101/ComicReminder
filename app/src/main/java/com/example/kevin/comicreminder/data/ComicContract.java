package com.example.kevin.comicreminder.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kevin on 5/18/2015.
 */
public class ComicContract {
    public static final String CONTENT_AUTHORITY = "com.kevin.ComicReminder.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);
    public static final String PATH_COMIC = "comics";

    public static final String DATE_FORMAT = "yyyMMdd";

    public static String getDbDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

public static final class ComicEntry implements BaseColumns {
    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMIC).build();

    public static final String CONTENT_TYPE =
            "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_COMIC;
    public static final String CONTENT_ITEM_TYPE =
            "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_COMIC;
    //Table Name
    public static final String TABLE_NAME= "comicList";



    //Column for Comic Object
    public static final String KEY_ID = "id";
    public static final String COLUMN_COMIC_OBJECT = "comic_object";
    //Column names
    public static final String[] COLUMNS = {KEY_ID, COLUMN_COMIC_OBJECT};

    public static Uri buildComicUri(long id){
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }



}
}
