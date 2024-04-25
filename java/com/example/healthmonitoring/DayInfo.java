package com.example.healthmonitoring;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DayInfo implements Serializable {

   // private Date date;
    private int day;
    private int month;
    private int year;
    private String whatDidIEatAndDrink;
    private boolean[] medicinesTaken;
    private int feelingRating;
    private String feelingDetail;
    private boolean[] sportsActivities;
    private int sleepRating;
    private boolean sleptWell;

    public DayInfo() {
        // Default constructor required for Firebase Realtime Database
    }

    public DayInfo(int day,int month, int year, String whatDidIEatAndDrink, boolean[] medicinesTaken, int feelingRating,
                   String feelingDetail,  boolean[] sportsActivities, int sleepRating, boolean sleptWell) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.whatDidIEatAndDrink = whatDidIEatAndDrink;
        this.medicinesTaken = medicinesTaken;
        this.feelingRating = feelingRating;
        this.feelingDetail = feelingDetail;
        this.sportsActivities = sportsActivities;
        this.sleepRating = sleepRating;
        this.sleptWell = sleptWell;
    }

    public String getDate(){
        return new String(day + "." + month + "." + year) ;
    }
    public int getDay(){return day;}
    public int getMonth(){return month;}
    public int getYear(){return year;}
    public String getWhatDidIEatAndDrink() {
        return whatDidIEatAndDrink;
    }

    public void setWhatDidIEatAndDrink(String whatDidIEatAndDrink) {
        this.whatDidIEatAndDrink = whatDidIEatAndDrink;
    }
    public  String getMedicinesTaken() {
        String toReturn = "";
        if(medicinesTaken[0] == true)
            toReturn += "Entyvio vial 300ml\t";
        if(medicinesTaken[1] == true)
            toReturn += "Raffassal 1gr\t";
        if(medicinesTaken[2] == true)
            toReturn += "Calcium\t";
        if(medicinesTaken[3] == true)
            toReturn += "Vitamin D";

        return toReturn;
    }

    public void setMedicinesTaken( boolean[] medicinesTaken) {
        this.medicinesTaken = medicinesTaken;
    }

    public int getFeelingRating() {
        return feelingRating;
    }

    public void setFeelingRating(int feelingRating) {
        this.feelingRating = feelingRating;
    }

    public String getFeelingDetail() {
        return feelingDetail;
    }

    public void setFeelingDetail(String feelingDetail) {
        this.feelingDetail = feelingDetail;
    }

    public  String getSportsActivities() {
        String toReturn = "";
        if(sportsActivities[0] == true)
            toReturn += "Nothing";
        else {
            if(sportsActivities[1] == true)
                toReturn += "Run\t";
            if (sportsActivities[2] == true)
                toReturn += "weight lifting\t";
            if (sportsActivities[3] == true)
                toReturn += "other activity";
        }
        return toReturn;
    }

    public void setSportsActivities( boolean[] sportsActivities) {
        this.sportsActivities = sportsActivities;
    }

    public int getSleepRating() {
        return sleepRating;
    }

    public void setSleepRating(int sleepRating) {
        this.sleepRating = sleepRating;
    }

    public boolean isSleptWell() {
        return sleptWell;
    }

    public void setSleptWell(boolean sleptWell) {
        this.sleptWell = sleptWell;
    }
}
