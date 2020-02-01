package com.teammh.sona;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Score {
    private Calendar calDate;
    private int score;

    public Score(int score) {
        calDate = Calendar.getInstance();
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getDay() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d");
        return format.format(calDate.getTime());
    }

    public String getDateTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d ',' h:mm a");
        return calDate.getTime().toString();
    }

    protected Calendar getDateTime() {
        return calDate;
    }

    class sortByDateTime implements Comparator<Score> {
        public int compare(Score a, Score b) {
            return a.getDateTime().compareTo(b.getDateTime());
        }
    }
}
