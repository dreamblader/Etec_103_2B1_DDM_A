package br.com.etec.ddm_a.model;


import java.util.Calendar;

public class CustomTime {

    private int hour;
    private int minutes;
    private int seconds;

    public CustomTime(int hour, int minutes, int seconds){
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public CustomTime(int hour, int minutes){
        this(hour,minutes,0);
    }

    public CustomTime(){
        this(0,0,0);
    }

    // HOUR
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    // MINUTES
    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    // SECONDS
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    //DIFF

    public CustomTime setFromCurrentDiff(){

            Calendar current = Calendar.getInstance();
            int currentHour = current.get(Calendar.HOUR_OF_DAY);
            int currentMinute = current.get(Calendar.MINUTE);
            CustomTime calendarTime = new CustomTime(currentHour, currentMinute);
            return setFromDiff(calendarTime);
    }

    public CustomTime setFromDiff(CustomTime time){
        int diffMinute = minutes - time.getMinutes();
        int diffHour = 0;

        while(diffMinute < 0){
            diffMinute += 60;
            diffHour -= 1;
        }

        diffHour += hour - time.getHour();

        while(diffHour < 0){
            diffHour += 24;
        }

        return new CustomTime(diffHour, diffMinute);
    }

    //MISC


    @Override
    public String toString() {
        return paddingTime(this.hour)+":"+paddingTime(this.minutes);
    }

    private String paddingTime(int time){
        String timeString = "";

        if(time < 10){
            timeString = "0"+time;
        } else {
            timeString = String.valueOf(time);
        }

        return timeString;
    }
}
