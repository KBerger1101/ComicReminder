package com.example.kevin.comicreminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

import static android.app.DatePickerDialog.OnDateSetListener;

public class DatePickerFragment extends DialogFragment
        implements OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (ComicInformationActivity)getActivity(), year, month, day);
    }
    public void onDateSet(DatePicker view, int newYear, int newMonth, int newDay) {


    }






}
