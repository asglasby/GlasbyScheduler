/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.view_controller;
import glasbyscheduler.MainApp;
import glasbyscheduler.model.Appointment;
import glasbyscheduler.model.Customer;
import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author aglasby
 */
public class ReportsDialogController {
    @FXML
    private TextArea loginActivity;
    
    @FXML
    private TextArea consultantSchedule;
    
    @FXML
    private TextArea descriptionCount;
    
    private MainApp mainApp;
    
    private Stage dialogStage;
    
    ObservableList<String> monthList = FXCollections.observableArrayList("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
    
    public ReportsDialogController(){
        
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */ 
    public void setMainApp(MainApp mainApp){
       this.mainApp = mainApp;

       //loadCalendarData();    
    }
    
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
    
    public void showLoginActivity(){
        String filename = "src/files/loginActivity.txt", item;
        File file = new File(filename);
        
        try {
            Scanner inputFile = new Scanner(file);
            loginActivity.setText("Username\tLogin Result\tDate and Time");
            while(inputFile.hasNext()){                
                item = inputFile.nextLine();                
                loginActivity.appendText("\n" + item);                
            } 
            inputFile.close();
        } catch (Exception e) {
        }        
    }
    
    public void showConsultantSchedule(){
       printConsultantSchedule();
    }
    
    private void printConsultantSchedule(){
        for (String c : MainController.consultantList) {
            consultantSchedule.appendText("\n" + c);
            for (Appointment a : MainController.allAppointmentList) {
                if(a.getContact().equals(c)){
                    consultantSchedule.appendText("\n" + "Customer ID: " + a.getCustomerId() + " Meeting Type " + a.getTitle() + " Location "  + a.getLocation() + " Start Time"  + a.getStart() + " End Time"  + a.getEnd());
                }
            }
            consultantSchedule.appendText("\n\n");
        }        
    }
    
    public void showAppointmentTypeCount(){
        descriptionCount.setText("\n Appointment Counts \n");
        for (String m : monthList) {
            descriptionCount.appendText("\n" + m + "\n");
            for (String d : MainController.descriptionList) {
                descriptionCount.appendText("\n" + d + "\n");
            int count = 0;
                for (Appointment a : MainController.allAppointmentList) {
                    String tempStartDate = a.getStart();
                    String pattern = "yyyy-MM-dd HH:mm:ss";
                    DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));
                    ZonedDateTime startDate = ZonedDateTime.parse(tempStartDate, Parser);
                    String apptMonth = startDate.getMonth().toString();
                    if(a.getDescription().equals(d)){
                        if(apptMonth.equals(m)){
                            count++;
                        }
                    }
                }
                descriptionCount.appendText("Total: " + count + "\n\n");
            }
        }
    }
    
    public void showCustomerAppointmentCount(){
        descriptionCount.setText("Customer ID: \t Customer Name ");
        for (Customer c : MainController.customerList) {
            descriptionCount.appendText("\n" + c.getCustomerId() + " " + c.getCustomerName() + "\n");
            int count = 0;
            for (Appointment a : MainController.allAppointmentList) {
                if(a.getCustomerId() == c.getCustomerId()){
                    descriptionCount.appendText("\n" + a.getAppointmentId() + " " + a.getTitle() + " " + a.getDescription() + "\n");
                    count++;
                }            
            }
            descriptionCount.appendText("Total: " + count + "\n\n");
        }        
    }    
}
