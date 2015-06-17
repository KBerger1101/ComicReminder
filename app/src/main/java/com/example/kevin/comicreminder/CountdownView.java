package com.example.kevin.comicreminder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.kevin.comicreminder.data.ComicDbHelper;

import java.util.ArrayList;

/**
 * Created by Kevin on 4/13/2015.
 */


public class CountdownView extends ActionBarActivity{
    private ComicAdapter mComicAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    static ArrayList<ComicInfo> countdownList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComicDbHelper dbHelper = new ComicDbHelper(this);
        CountdownView.countdownList = (ArrayList<ComicInfo>) dbHelper.getAllComics();
        mComicAdapter = new ComicAdapter(this,,0);
        setContentView(R.layout.countdown);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

