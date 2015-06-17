package com.example.kevin.comicreminder;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevin.comicreminder.data.ComicContract;

/**
 * Created by Kevin on 6/8/2015.
 */
    public class ComicAdapter extends CursorAdapter {


    public static class ViewHolder {
        public final TextView nameView;
        public final TextView dateView;

        public ViewHolder(View view){
            nameView = (TextView) view.findViewById(R.id.list_item_name_textview);
            dateView= (TextView) view.findViewById(R.id.list_item_date_textview);

        }

    }

    public ComicAdapter(Context context,Cursor c, int flags) {
        super(context,c,flags);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        int viewType = getItemViewType(cursor.getPosition());

        String nameString = cursor.getString(Integer.parseInt(ComicContract.ComicEntry.COLUMN_COMIC_OBJECT));

        viewHolder.nameView.setText(nameString);




    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.comic_listview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }
}
