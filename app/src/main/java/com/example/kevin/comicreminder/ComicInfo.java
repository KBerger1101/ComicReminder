package com.example.kevin.comicreminder;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Kevin on 4/14/2015.
 */
public class ComicInfo  implements Serializable {
    private String name;
    private int releaseFreq;
    private int[] release;
    private int id;

    public Calendar cal = Calendar.getInstance();
    public ComicInfo(String comicName, int releaseType, int[] nextRelease) throws ParseException {
        name = comicName;
        releaseFreq= releaseType;
        release = nextRelease;
        calcReminderDate(release);

    }
    public ComicInfo(String comicName, int releaseType, int[] nextRelease, int databaseID) throws ParseException{
        name = comicName;
        releaseFreq = releaseType;
        release = nextRelease;
        id = databaseID;
        calcReminderDate(release);
    }

    public  void calcReminderDate(int[] dateToCalc) throws ParseException {
        String dateInString =""+ dateToCalc[2] + "-" + dateToCalc[1] + "-" + dateToCalc[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(sdf.parse(dateInString));

    }
    public void soundAlarmAndUpdateDate()
    {
        if (releaseFreq == 0){
            cal.add(Calendar.DATE,7);
        }
        else if (releaseFreq == 1) {
            cal.add(Calendar.DATE,14);
        }
        else if (releaseFreq==2){
            cal.add(Calendar.DATE,28);
        }

    }
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int getReleaseFreq()
    {
        return releaseFreq;
    }

    public String getName()
    {
        return name;
    }

    public int getID() { return id;}





}
