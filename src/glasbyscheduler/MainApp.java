/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler;

import glasbyscheduler.model.Appointment;
import glasbyscheduler.model.Customer;
import glasbyscheduler.view_controller.AppointmentDialogController;
import glasbyscheduler.view_controller.CalendarDialogController;
import glasbyscheduler.view_controller.CustomerDialogController;
import glasbyscheduler.view_controller.MainController;
import glasbyscheduler.view_controller.ReportsDialogController;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author aglasby
 */
public class MainApp extends Application {
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Scheduler");        
        showHomeScreen();
    }
    
    public void showHomeScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/HomeScreen.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
            //Show the scene 
            Scene scene = new Scene(mainView);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        // Give the controller access to the main app.
        MainController controller = loader.getController();
        controller.setMainApp(this);
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

    public void showMainScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/MainScreen.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
            // Create the stage.
            Stage mainScreenStage = new Stage();
            mainScreenStage.setTitle("Scheduler Main Screen");
            mainScreenStage.initModality(Modality.WINDOW_MODAL);
            mainScreenStage.initOwner(primaryStage);
            
            //Show the scene 
            Scene scene = new Scene(mainView);
            mainScreenStage.setScene(scene);
            mainScreenStage.show();
            
            MainController controller = loader.getController();
            controller.setMainApp(this);
            controller.initializeMainScreenColumns();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showCustomerScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/CustomerView.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
            // Create the stage.
            Stage customerScreenStage = new Stage();
            customerScreenStage.setTitle("Customers");
            customerScreenStage.initModality(Modality.WINDOW_MODAL);
            customerScreenStage.initOwner(primaryStage);
            
            //Show the scene 
            Scene scene = new Scene(mainView);
            customerScreenStage.setScene(scene);
            customerScreenStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showAddCustomerDialog(Customer customer){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/CustomerView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Customer");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CustomerDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCustomer(customer);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void showAppointmentScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/AppointmentView.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
            // Create the stage.
            Stage AppointmentScreenStage = new Stage();
            AppointmentScreenStage.setTitle("Appointments");
            AppointmentScreenStage.initModality(Modality.WINDOW_MODAL);
            AppointmentScreenStage.initOwner(primaryStage);
            
            //Show the scene 
            Scene scene = new Scene(mainView);
            AppointmentScreenStage.setScene(scene);
            AppointmentScreenStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showAddAppointmentDialog(Appointment appointment, Customer customer){
         try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/AppointmentView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Appointment");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AppointmentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAppointment(appointment, customer);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void showCalendarScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/CalendarView.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
             // Create the stage.
            Stage CalendarScreenStage = new Stage();
            CalendarScreenStage.setTitle("Calendar");
            CalendarScreenStage.initModality(Modality.WINDOW_MODAL);
            CalendarScreenStage.initOwner(primaryStage);
            
            //Show the scene 
            Scene scene = new Scene(mainView);
            CalendarScreenStage.setScene(scene);
            
            // Set the Date into the controller.
            CalendarDialogController controller = loader.getController();
            controller.setDialogStage(CalendarScreenStage);
            controller.initializeCalendar();            
            CalendarScreenStage.show();            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showWeeklyCalendarScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/WeeklyCalendarView.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
             // Create the stage.
            Stage CalendarScreenStage = new Stage();
            CalendarScreenStage.setTitle("Weekly Calendar");
            CalendarScreenStage.initModality(Modality.WINDOW_MODAL);
            CalendarScreenStage.initOwner(primaryStage);
            
            //Show the scene 
            Scene scene = new Scene(mainView);
            CalendarScreenStage.setScene(scene);
            
            // Set the Date into the controller.
            CalendarDialogController controller = loader.getController();
            controller.setDialogStage(CalendarScreenStage);
            controller.initializeWeeklyCalendar();  
            
            CalendarScreenStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
        
    public void showReportsScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/ReportsView.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
            // Create the stage.
            Stage ReportsScreenStage = new Stage();
            ReportsScreenStage.setTitle("Calendar");
            ReportsScreenStage.initModality(Modality.WINDOW_MODAL);
            ReportsScreenStage.initOwner(primaryStage);
            
            //Show the scene 
            Scene scene = new Scene(mainView);
            ReportsScreenStage.setScene(scene);
            ReportsScreenStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showLoginActivityScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/LoginActivityView.fxml"));
            AnchorPane page = (AnchorPane)loader.load();   
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login Activity");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ReportsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.showLoginActivity();
            
            dialogStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showConsultantScheduleScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/ConsultantScheduleView.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Consultant Schedules");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ReportsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.showConsultantSchedule();
            
            dialogStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
     public void showAppointmentCountScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/AppointmentCountView.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Appointment Type Counts");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ReportsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.showAppointmentTypeCount();
            
            dialogStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
     
      public void showCustomerAppointmentCountScreen(){
        try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/AppointmentCountView.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Customer Appointment Counts");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ReportsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.showCustomerAppointmentCount();
            
            dialogStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
