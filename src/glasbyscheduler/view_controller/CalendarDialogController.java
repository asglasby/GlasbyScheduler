/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.view_controller;
import glasbyscheduler.model.Appointment;
import glasbyscheduler.model.WeeklyCalendar;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author aglasby
 */
public class CalendarDialogController {
    @FXML
    private Label label;
    
    @FXML
    private Label label1;
    
    @FXML
    private Label labelMonthName;
    
    @FXML
    private TextArea calendarText1;
    
    @FXML
    private Label label2;
    
    @FXML
    private TextArea calendarText2;
        
    @FXML
    private Label label3;
    
    @FXML
    private TextArea calendarText3;
        
    @FXML
    private Label label4;
    
    @FXML
    private TextArea calendarText4;
        
    @FXML
    private Label label5;
    
    @FXML
    private TextArea calendarText5;
        
    @FXML
    private Label label6;
    
    @FXML
    private TextArea calendarText6;
        
    @FXML
    private Label label7;
    
    @FXML
    private TextArea calendarText7;
        
    @FXML
    private Label label8;
    
    @FXML
    private TextArea calendarText8;
        
    @FXML
    private Label label9;
    
    @FXML
    private TextArea calendarText9;
        
    @FXML
    private Label label10;
    
    @FXML
    private TextArea calendarText10;    
    
    @FXML
    private Label label11;
    
    @FXML
    private TextArea calendarText11;    
    
    @FXML
    private Label label12;
    
    @FXML
    private TextArea calendarText12;    
    
    @FXML
    private Label label13;
    
    @FXML
    private TextArea calendarText13;    
    
    @FXML
    private Label label14;
    
    @FXML
    private TextArea calendarText14;    
    
    @FXML
    private Label label15;
    
    @FXML
    private TextArea calendarText15;    
    
    @FXML
    private Label label16;
    
    @FXML
    private TextArea calendarText16;    
    
    @FXML
    private Label label17;
    
    @FXML
    private TextArea calendarText17;    
    
    @FXML
    private Label label18;
    
    @FXML
    private TextArea calendarText18;    
    
    @FXML
    private Label label19;
    
    @FXML
    private TextArea calendarText19;    
    
    @FXML
    private Label label20;
    
    @FXML
    private TextArea calendarText20;    
    
    @FXML
    private Label label21;
    
    @FXML
    private TextArea calendarText21;    
    
    @FXML
    private Label label22;
    
    @FXML
    private TextArea calendarText22;    
    
    @FXML
    private Label label23;
    
    @FXML
    private TextArea calendarText23;    
    
    @FXML
    private Label label24;
    
    @FXML
    private TextArea calendarText24;    
    
    @FXML
    private Label label25;
    
    @FXML
    private TextArea calendarText25;    
    
    @FXML
    private Label label26;
    
    @FXML
    private TextArea calendarText26;    
    
    @FXML
    private Label label27;
    
    @FXML
    private TextArea calendarText27;    
    
    @FXML
    private Label label28;
    
    @FXML
    private TextArea calendarText28;    
    
    @FXML
    private Label label29;
    
    @FXML
    private TextArea calendarText29;    
    
    @FXML
    private Label label30;
    
    @FXML
    private TextArea calendarText30;    
    
    @FXML
    private Label label31;
    
    @FXML
    private TextArea calendarText31;
    
    @FXML
    private TextArea calendarText32;
    
    @FXML
     private TextArea calendarText33;
    
    @FXML
    private TextArea calendarText34;
    
    @FXML
    private TextArea calendarText35;
    
    @FXML
    private TextArea calendarText36;
     
    @FXML
    private TextArea calendarText37;
      
    @FXML
    private TextArea calendarText38;
       
    @FXML
    private TextArea calendarText39;
        
    @FXML
    private TextArea calendarText40;
         
    @FXML
    private TextArea calendarText41;
    
    @FXML
    private TextArea calendarText42;
    
    @FXML
    private TextArea weeklyCalendar;
    
    int numDaysInMonth;
    
    @FXML
    private TableView<Appointment> calendarTable;

    @FXML
    private TableColumn<Appointment, String> sundayColumn;
    
    @FXML
    private TableColumn<Appointment, String> mondayColumn;
    
    @FXML
    private TableColumn<Appointment, String> tuesdayColumn;
    
    @FXML
    private TableColumn<Appointment, String> wednesdayColumn;
    
    @FXML
    private TableColumn<Appointment, String> thursdayColumn;
    
    @FXML
    private TableColumn<Appointment, String> fridayColumn;
    
    @FXML
    private TableColumn<Appointment, String> saturdayColumn;
  
    ZonedDateTime newDate = ZonedDateTime.now();
    private Stage dialogStage;
    
    @FXML
    ObservableList<Appointment> currentMonthAppointmentList = FXCollections.observableArrayList();
    
    @FXML
    ObservableList<Appointment> currentWeekAppointmentList = FXCollections.observableArrayList();
    
    @FXML
    ObservableList<WeeklyCalendar> daysToCompareList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    private int getNumDaysInMonth(){
        if(newDate.getMonth().toString() == "FEBRUARY"){
            if( newDate.getYear() % 4 == 0){
                numDaysInMonth = 29;
            }else{
                numDaysInMonth = 28;
            }
        }else{
            numDaysInMonth = newDate.getMonth().maxLength();
        }        
        return numDaysInMonth;
    }   
    
    public void initializeCalendar(){        
          
        String dayOfWeek = newDate.withDayOfMonth(1).getDayOfWeek().toString();
        
        labelMonthName.setText(newDate.getMonth().toString());
        int dayCount = getNumDaysInMonth();     
        
        int count = 0;
        String[] dayOfWeekLabels = new String[7];
        String[] daysOfWeek = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        String[] calendarText = new String[31];
        String[] dayOfMonthArray = new String[42];
        int startIndexPosition = 0;
        
        for (int i = 0; i < daysOfWeek.length; i++) {               
            if(dayOfWeek.equals(daysOfWeek[i])){
                count = i;               
                startIndexPosition = i;               

            }else{                
            }        
        }        
        for (int j = 1; j <= dayCount; j++) {            
            String numToText = Integer.toString(j);
            dayOfMonthArray[startIndexPosition] = numToText;
            startIndexPosition ++;           
        }
        
        
        int firstWeek = 0;
        for (int i = 1; i <= 31; i++) {
            count++;
            if(count == 7 ){
                count = 0;                    
            }
            if(firstWeek <= 6){
                dayOfWeekLabels[count] = daysOfWeek[count];
            }
        }
        
         for (Appointment appointment : MainController.appointmentList) {
            String appointmentMonth = appointment.getStart();
            String tempDate = appointment.getStart();            
            String pattern = "yyyy-MM-dd HH:mm:ss";
            DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));            
            ZonedDateTime checkDate = ZonedDateTime.parse(tempDate, Parser);
            ZonedDateTime systemTimeOffset = checkDate.withZoneSameInstant(ZoneId.systemDefault());
            ZoneOffset checkOffset = checkDate.getOffset();
            if(checkDate.getMonth() == newDate.getMonth()){
                currentMonthAppointmentList.add(appointment);                
                String dayToCompare = Integer.toString(checkDate.getDayOfMonth()); 
                for (int i = 0; i < dayOfMonthArray.length - 1; i++) {
                    if(dayOfMonthArray[i] != null){
                        if(dayOfMonthArray[i].length() > 1){
                            if(dayOfMonthArray[i].substring(1, 2).equals("1")||dayOfMonthArray[i].substring(1, 2).equals("2")||dayOfMonthArray[i].substring(1, 2).equals("3")||dayOfMonthArray[i].substring(1, 2).equals("4")||dayOfMonthArray[i].substring(1, 2).equals("5")||dayOfMonthArray[i].substring(1, 2).equals("6")||dayOfMonthArray[i].substring(1, 2).equals("7")||dayOfMonthArray[i].substring(1, 2).equals("8")||dayOfMonthArray[i].substring(1, 2).equals("9")||dayOfMonthArray[i].substring(1, 2).equals("0")){
                                String firstTwoNums = dayOfMonthArray[i].substring(0, 2);
                                if(firstTwoNums.equals(dayToCompare)){
                                    ZonedDateTime convertedStart = checkDate.withZoneSameInstant(ZoneId.systemDefault());
                                    ZonedDateTime tempEnd = ZonedDateTime.parse(appointment.getEnd(), Parser);
                                    ZonedDateTime convertedEnd = tempEnd.withZoneSameInstant(ZoneId.systemDefault());
                                    dayOfMonthArray[i] += "\n" + appointment.getDescription() + "\n" + convertedStart.toLocalTime() + "\n" + convertedEnd.toLocalTime() + "\n---------------------";
                                    
                                }                            
                            }else{
                                String firstNum = dayOfMonthArray[i].substring(0, 1);
                                if(firstNum.equals(dayToCompare)){
                                    ZonedDateTime convertedStart = checkDate.withZoneSameInstant(ZoneId.systemDefault());
                                    ZonedDateTime tempEnd = ZonedDateTime.parse(appointment.getEnd(), Parser);
                                    ZonedDateTime convertedEnd = tempEnd.withZoneSameInstant(ZoneId.systemDefault());
                                    dayOfMonthArray[i] += "\n" + appointment.getDescription() + "\n" + convertedStart.toLocalTime() + "\n" + convertedEnd.toLocalTime()+ "\n---------------------";   
                                   
                                }
                            }
                        }else{                            
                            String firstNum = dayOfMonthArray[i].substring(0, 1);
                            if(firstNum.equals(dayToCompare)){
                                ZonedDateTime convertedStart = checkDate.withZoneSameInstant(ZoneId.systemDefault());
                                ZonedDateTime tempEnd = ZonedDateTime.parse(appointment.getEnd(), Parser);
                                ZonedDateTime convertedEnd = tempEnd.withZoneSameInstant(ZoneId.systemDefault());
                                dayOfMonthArray[i] += "\n" + appointment.getDescription() + "\n" + convertedStart.toLocalTime() + "\n" + convertedEnd.toLocalTime()+ "\n---------------------";   
                                
                            }
                        } 
                    }                    
                }                
            }
        }

        // TODO
        label1.setText(dayOfWeekLabels[0]);
        label2.setText(dayOfWeekLabels[1]);
        label3.setText(dayOfWeekLabels[2]);
        label4.setText(dayOfWeekLabels[3]);
        label5.setText(dayOfWeekLabels[4]);
        label6.setText(dayOfWeekLabels[5]);
        label7.setText(dayOfWeekLabels[6]);
        calendarText1.setText(dayOfMonthArray[0]);
        calendarText2.setText(dayOfMonthArray[1]);
        calendarText3.setText(dayOfMonthArray[2]);
        calendarText4.setText(dayOfMonthArray[3]);
        calendarText5.setText(dayOfMonthArray[4]);
        calendarText6.setText(dayOfMonthArray[5]);
        calendarText7.setText(dayOfMonthArray[6]);
        calendarText8.setText(dayOfMonthArray[7]);
        calendarText9.setText(dayOfMonthArray[8]);
        calendarText10.setText(dayOfMonthArray[9]);
        calendarText11.setText(dayOfMonthArray[10]);
        calendarText12.setText(dayOfMonthArray[11]);
        calendarText13.setText(dayOfMonthArray[12]);
        calendarText14.setText(dayOfMonthArray[13]);
        calendarText15.setText(dayOfMonthArray[14]);
        calendarText16.setText(dayOfMonthArray[15]);
        calendarText17.setText(dayOfMonthArray[16]);
        calendarText18.setText(dayOfMonthArray[17]);
        calendarText19.setText(dayOfMonthArray[18]);
        calendarText20.setText(dayOfMonthArray[19]);
        calendarText21.setText(dayOfMonthArray[20]);
        calendarText22.setText(dayOfMonthArray[21]);
        calendarText23.setText(dayOfMonthArray[22]);
        calendarText24.setText(dayOfMonthArray[23]);
        calendarText25.setText(dayOfMonthArray[24]);
        calendarText26.setText(dayOfMonthArray[25]);
        calendarText27.setText(dayOfMonthArray[26]);
        calendarText28.setText(dayOfMonthArray[27]);
        calendarText29.setText(dayOfMonthArray[28]);
        calendarText30.setText(dayOfMonthArray[29]);
        calendarText31.setText(dayOfMonthArray[30]);
        calendarText32.setText(dayOfMonthArray[31]);
        calendarText33.setText(dayOfMonthArray[32]);
        calendarText34.setText(dayOfMonthArray[33]);
        calendarText35.setText(dayOfMonthArray[34]);
        calendarText36.setText(dayOfMonthArray[35]);
        calendarText37.setText(dayOfMonthArray[36]);
        calendarText38.setText(dayOfMonthArray[37]);
        calendarText39.setText(dayOfMonthArray[38]);
        calendarText40.setText(dayOfMonthArray[39]);
        calendarText41.setText(dayOfMonthArray[40]);
        calendarText42.setText(dayOfMonthArray[41]);   
    }

    public void initializeWeeklyCalendar(){
        addWeekAppointmentsToList();        
    }
    
    // add all appointment for the current week to the list.
    private void addWeekAppointmentsToList(){
        daysToCompare();
        splitWeekList();
        printWeeklyAppointments();
    }
    
    private void daysToCompare(){
        LocalDate today = LocalDate.now();
        String currentDayOfWeek = today.getDayOfWeek().toString();
        int currentDayOfMonth = today.getDayOfMonth();
        int startDay = currentDayOfMonth;
        Calendar calToday = Calendar.getInstance();
        calToday.add(Calendar.DAY_OF_MONTH, 17);
        
        
        
        // If today's Day of week is Sunday we need to add 1 calendar Day to the day and then pull out the Month and Day of Month to compare later. 
        if(currentDayOfWeek.equals("SUNDAY")){
           // startDay++;
            LocalDate myStartDay = today;
            for (int i = 1; i <= 7; i++) {
                LocalDate dayToAdd = myStartDay.plusDays(i);
                String dayOfWeek = dayToAdd.getDayOfWeek().toString();
                int dayOfMonth = dayToAdd.getDayOfMonth();
                String sDayOfMonth = Integer.toString(dayOfMonth);
                String month = dayToAdd.getMonth().toString();                
                daysToCompareList.add(new WeeklyCalendar(dayOfWeek, month, sDayOfMonth));
            }           
        }
        if(currentDayOfWeek.equals("MONDAY")){
            LocalDate myStartDay = today.minusDays(2);;
            for (int i = 1; i <= 7; i++) {
                LocalDate dayToAdd = myStartDay.plusDays(i);
                String dayOfWeek = dayToAdd.getDayOfWeek().toString();
                int dayOfMonth = dayToAdd.getDayOfMonth();
                String sDayOfMonth = Integer.toString(dayOfMonth);
                String month = dayToAdd.getMonth().toString();                
                daysToCompareList.add(new WeeklyCalendar(dayOfWeek, month, sDayOfMonth));
            }          
                  
        }
        if(currentDayOfWeek.equals("TUESDAY")){
            LocalDate myStartDay = today.minusDays(3);
            for (int i = 1; i <= 7; i++) {
                LocalDate dayToAdd = myStartDay.plusDays(i);
                String dayOfWeek = dayToAdd.getDayOfWeek().toString();
                int dayOfMonth = dayToAdd.getDayOfMonth();
                String sDayOfMonth = Integer.toString(dayOfMonth);
                String month = dayToAdd.getMonth().toString();                
                daysToCompareList.add(new WeeklyCalendar(dayOfWeek, month, sDayOfMonth));
            }                   
        }
        
        if(currentDayOfWeek.equals("WEDNESDAY")){
            LocalDate myStartDay = today.minusDays(4);
            for (int i = 1; i <= 7; i++) {
                LocalDate dayToAdd = myStartDay.plusDays(i);
                String dayOfWeek = dayToAdd.getDayOfWeek().toString();
                int dayOfMonth = dayToAdd.getDayOfMonth();
                String sDayOfMonth = Integer.toString(dayOfMonth);
                String month = dayToAdd.getMonth().toString();                
                daysToCompareList.add(new WeeklyCalendar(dayOfWeek, month, sDayOfMonth));
            }                  
        }
        
        if(currentDayOfWeek.equals("THURSDAY")){
            LocalDate myStartDay = today.minusDays(5);
            for (int i = 1; i <= 7; i++) {
                LocalDate dayToAdd = myStartDay.plusDays(i);
                String dayOfWeek = dayToAdd.getDayOfWeek().toString();
                int dayOfMonth = dayToAdd.getDayOfMonth();
                String sDayOfMonth = Integer.toString(dayOfMonth);
                String month = dayToAdd.getMonth().toString();                
                daysToCompareList.add(new WeeklyCalendar(dayOfWeek, month, sDayOfMonth));
            }                
        }
        
        if(currentDayOfWeek.equals("FRIDAY")){
            LocalDate myStartDay = today.minusDays(6);            
            for (int i = 1; i <= 7; i++) {
                LocalDate dayToAdd = myStartDay.plusDays(i);
                String dayOfWeek = dayToAdd.getDayOfWeek().toString();
                int dayOfMonth = dayToAdd.getDayOfMonth();
                String sDayOfMonth = Integer.toString(dayOfMonth);
                String month = dayToAdd.getMonth().toString();                
                daysToCompareList.add(new WeeklyCalendar(dayOfWeek, month, sDayOfMonth));
            }          
        }
        if(currentDayOfWeek.equals("SATURDAY")){
            LocalDate myStartDay = today.minusDays(7);
            for (int i = 1; i <= 7; i++) {                
                LocalDate dayToAdd = myStartDay.plusDays(i);
                String dayOfWeek = dayToAdd.getDayOfWeek().toString();
                int dayOfMonth = dayToAdd.getDayOfMonth();
                String sDayOfMonth = Integer.toString(dayOfMonth);
                String month = dayToAdd.getMonth().toString();                
                daysToCompareList.add(new WeeklyCalendar(dayOfWeek, month, sDayOfMonth));
            }           
        }        
    }
    
    private void splitWeekList(){
        for (Appointment appointment : MainController.appointmentList) {
            String tempStartDate = appointment.getStart();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));
            ZonedDateTime startDate = ZonedDateTime.parse(tempStartDate, Parser);
            String apptMonth = startDate.getMonth().toString();
            
            for (WeeklyCalendar cal : daysToCompareList) {
                if(apptMonth.equals(cal.getMonth())&& startDate.getDayOfMonth() == Integer.parseInt(cal.getDayOfMonth())){
                    currentWeekAppointmentList.add(appointment);
                }
            }
        }
    }
    
    private void printWeeklyAppointments(){
        weeklyCalendar.setText("Weekly Appointments");        
        for (WeeklyCalendar day : daysToCompareList) {
            weeklyCalendar.appendText("\n" + day.getDayOfWeek() + " " + day.getMonth() + " - " + day.getDayOfMonth() + "\n");
            for (Appointment appointment : currentWeekAppointmentList) {
                String tempStartDate = appointment.getStart();
                String pattern = "yyyy-MM-dd HH:mm:ss";
                DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));
                ZonedDateTime startDate = ZonedDateTime.parse(tempStartDate, Parser);
                String apptMonth = startDate.getMonth().toString();                
                if(apptMonth.equals(day.getMonth())&& startDate.getDayOfMonth() == Integer.parseInt(day.getDayOfMonth())){                                       
                    weeklyCalendar.appendText("\n Title: " + appointment.getTitle() + " Contact: " + appointment.getContact() + " Location: " + appointment.getLocation() + " Description: " + appointment.getDescription() + " Start: " + appointment.getStart() + " End: " + appointment.getEnd() + "\n");
                }
            }
        }        
    }
    
}
