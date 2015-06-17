package com.example.kevin.comicreminder;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.kevin.comicreminder.data.ComicDbHelper;

import java.text.ParseException;


/**
 * Created by Kevin on 4/13/2015.
 */
public class ComicInformationActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

private String comicName;
private int releaseType;
    public String dateString;
    public int[] date = new int[3];
    private boolean upDateView= false;

    public ComicInformationActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info);
    }




    public void saveComic(View v) throws ParseException {
        comicName = String.valueOf(R.id.comic_title_textBox);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.freq_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                if (checkedRadioButton.getId() == R.id.weekly_radiobutton)
                    releaseType = 0;
                if (checkedRadioButton.getId() == R.id.biweekly_radiobutton)
                    releaseType = 1;
                if (checkedRadioButton.getId() == R.id.monthly_radiobutton)
                    releaseType = 2;
                if (checkedRadioButton.getId() == R.id.one_time_radiobutton)
                    releaseType = 3;

            }
        });

        ComicInfo mComic = new ComicInfo(comicName, releaseType, date);
        storeComic(mComic);
        this.finish();


    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");


    }

    public void storeComic(ComicInfo newComic) {
        CountdownView.countdownList.add(newComic);
        ComicDbHelper db = new ComicDbHelper(this);
        db.addComic(newComic);
        db.close();


    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //do some stuff for example write on log and update TextField on activity
        Log.w("DatePicker", "Date = " + year);
        date[0] = day;
        date[1]= month;
        date[2] = year;
        dateString = "" + (date[1]+1) + "/" + date[0] + "/" + date[2];

        ((TextView) findViewById(R.id.date_text)).setText(dateString);
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
    @Override
    public void onStop() {
        super.onStop();

    }
}


