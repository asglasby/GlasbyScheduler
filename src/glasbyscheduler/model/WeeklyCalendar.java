/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author aglasby
 */
public class WeeklyCalendar {
    protected StringProperty sunday;
    protected StringProperty monday;
    protected StringProperty tuesday;
    protected StringProperty wednesday;
    protected StringProperty thursday;
    protected StringProperty friday;
    protected StringProperty saturday;    
    protected StringProperty dayOfMonth;
    protected StringProperty month;
    protected StringProperty dayOfWeek;
    
    public WeeklyCalendar(String s, String m, String t, String w, String th, String f, String sa){
        this.sunday = new SimpleStringProperty(s);
        this.monday = new SimpleStringProperty(m);
        this.tuesday = new SimpleStringProperty(t);
        this.wednesday = new SimpleStringProperty(w);
        this.thursday = new SimpleStringProperty(th);
        this.friday = new SimpleStringProperty(f);
        this.saturday = new SimpleStringProperty(sa);        
    }

    public WeeklyCalendar(String dayOfWeek, String month, String dayOfMonth){
        this.month = new SimpleStringProperty(month);
        this.dayOfMonth = new SimpleStringProperty(dayOfMonth);
        this.dayOfWeek = new SimpleStringProperty(dayOfWeek);
    }
    
    public String getSunday(){
        return sunday.get();
    }
    
    public void setSunday(String sunday){
        this.sunday = new SimpleStringProperty(sunday);
    }
    
    public StringProperty sundayProperty(){
        return sunday;
    }
    
        public String getMonday(){
        return monday.get();
    }
    
    public void setMonday(String monday){
        this.monday = new SimpleStringProperty(monday);
    }
    
    public StringProperty mondayProperty(){
        return monday;
    }
    
        public String getTuesday(){
        return tuesday.get();
    }
    
    public void setTuesday(String tuesday){
        this.tuesday = new SimpleStringProperty(tuesday);
    }
    
    public StringProperty tuesdayProperty(){
        return tuesday;
    }
    
        public String getWednesday(){
        return wednesday.get();
    }
    
    public void setWednesday(String wednesday){
        this.wednesday = new SimpleStringProperty(wednesday);
    }
    
    public StringProperty wednesdayProperty(){
        return wednesday;
    }
    
        public String getThursday(){
        return thursday.get();
    }
    
    public void setThursday(String thursday){
        this.thursday = new SimpleStringProperty(thursday);
    }
    
    public StringProperty thursdayProperty(){
        return thursday;
    }
    
        public String getFriday(){
        return friday.get();
    }
    
    public void setFriday(String friday){
        this.friday = new SimpleStringProperty(friday);
    }
    
    public StringProperty fridayProperty(){
        return friday;
    }
    
    public String getSaturday(){
        return saturday.get();
    }
    
    public void setSaturday(String saturday){
        this.saturday = new SimpleStringProperty(saturday);
    }
    
    public StringProperty saturdayProperty(){
        return saturday;
    }
    
     public String getMonth(){
        return month.get();
    }
    
    public void setMonth(String month){
        this.month = new SimpleStringProperty(month);
    }
    
    public StringProperty monthProperty(){
        return month;
    }
    
    public String getDayOfMonth(){
        return dayOfMonth.get();
    }
    
    public void setDayOfMonth(String dayOfMonth){
        this.dayOfMonth = new SimpleStringProperty(dayOfMonth);
    }
    
    public StringProperty dayOfMonthProperty(){
        return dayOfMonth;
    }
    
    
    public String getDayOfWeek(){
        return dayOfWeek.get();
    }
    
    public void setDayOfWeek(String dayOfWeek){
        this.dayOfWeek = new SimpleStringProperty(dayOfWeek);
    }
    
    public StringProperty dayOfWeekProperty(){
        return dayOfWeek;
    }
    
    
    
}
