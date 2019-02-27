/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author aglasby
 */
public class Appointment {
    protected IntegerProperty appointmentId;    
    protected StringProperty description; // appointment description
    protected IntegerProperty customerId;   
    protected StringProperty title;
    protected StringProperty url;
    protected StringProperty location;
    protected StringProperty startHour;
    protected StringProperty endHour;
    protected StringProperty startMinute;
    protected StringProperty endMinute;
    protected StringProperty contact;
    protected LocalDate date;
    protected StringProperty start;
    protected StringProperty end;
   


    // Constructors
    public Appointment(int customerId, String title, String description, String location, String contact, String url, String startHour, String startMinute, String endHour, String endMinute, String start, String end){
        this.customerId = new SimpleIntegerProperty(customerId);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.contact = new SimpleStringProperty(contact);
        this.url = new SimpleStringProperty(url);
        this.startHour = new SimpleStringProperty(startHour);
        this.startMinute = new SimpleStringProperty(startMinute);
        this.endHour = new SimpleStringProperty(endHour);
        this.endMinute = new SimpleStringProperty(endMinute);
        this.start = new SimpleStringProperty(start);        
        this.end = new SimpleStringProperty(end);
    }
    
    public Appointment(int appointmentId, int customerId, String title, String description, String location, String contact, String url, String start, String end){
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.contact = new SimpleStringProperty(contact);
        this.url = new SimpleStringProperty(url);
        this.start = new SimpleStringProperty(start);        
        this.end = new SimpleStringProperty(end);
        
        
    }
    
    public Appointment(){
        this(0, null, null, null, null, null, null, null, null, null, null, null);
    }
    
    public Appointment(String description, int customerId){
        this.description = new SimpleStringProperty(description);
        this.customerId = new SimpleIntegerProperty(customerId);
    }
    
    public Appointment(String startHour, String startMinute){
        this.startHour = new SimpleStringProperty(startHour);
        this.startMinute = new SimpleStringProperty(startMinute);
        
    }

    public int getAppointmentId(){
        return appointmentId.get();
    }

    public void setAppointmentId(int appointmentId){
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
    }

    public IntegerProperty appointmentIdProperty() {
        return appointmentId;
    }   
    
    public String getDescription(){
        return description.get();
    }
    
    public void setDescription(String description){
        this.description = new SimpleStringProperty(description);
    }
    
    public StringProperty descriptionProperty(){
        return description;
    } 

    public int getCustomerId(){
        return customerId.get();
    }

    public void setCustomerId(int customerId){
        this.customerId = new SimpleIntegerProperty(customerId);
    }

    public IntegerProperty customerIdProperty() {
        return customerId;
    }

    public String getTitle(){
        return title.get();
    }
    
    public void setTitle(String title){
        this.title = new SimpleStringProperty(title);
    }
    
    public StringProperty titleProperty(){
        return title;
    } 
    
    public String getUrl(){
        return url.get();
    }
    
    public void setUrl(String url){
        this.url = new SimpleStringProperty(url);
    }
    
    public StringProperty urlProperty(){
        return url;
    }
    
    public String getLocation(){
        return location.get();
    }
    
    public void setLocation(String location){
        this.location = new SimpleStringProperty(location);
    }
    
    public StringProperty locationProperty(){
        return location;
    }
    
    public String getStartHour(){
        return startHour.get();
    }
    
    public void setStartHour(String startHour){
        this.startHour = new SimpleStringProperty(startHour);
    }
    
    public StringProperty startHourProperty(){
        return startHour;
    }
    
    public String getStartMinute(){
        return startMinute.get();
    }
    
    public void setStartMinute(String startMinute){
        this.startMinute = new SimpleStringProperty(startMinute);
    }
    
    public StringProperty startMinuteProperty(){
        return startMinute;
    }
    
    public String getEndMinute(){
        return endMinute.get();
    }
    
    public void setEndMinute(String endMinute){
        this.endMinute = new SimpleStringProperty(endMinute);
    }
    
    public StringProperty endMinuteProperty(){
        return endMinute;
    } 
    
    public String getEndHour(){
        return endHour.get();
    }
    
    public void setEndHour(String endHour){
        this.endHour = new SimpleStringProperty(endHour);
    }
    
    public StringProperty endHourProperty(){
        return endHour;
    }
    
    public String getContact(){
        return contact.get();
    }
    
    public void setContact(String contact){
        this.contact = new SimpleStringProperty(contact);
    }
    
    public StringProperty contactProperty(){
        return contact;
    } 
    
    public LocalDate getDate(){
        return date;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }
    
    public String getEnd(){
        return end.get();
    }
    
    public void setEnd(String end){
        this.end = new SimpleStringProperty(end);
    }
    
    public StringProperty endProperty(){
        return end;
    }
    
    public String getStart(){
        return start.get();
    }
    
    public void setStart(String start){
        this.start = new SimpleStringProperty(start);
    }
    
    public StringProperty startProperty(){
        return start;
    }
    
    
}
