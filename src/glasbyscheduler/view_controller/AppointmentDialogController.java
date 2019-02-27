/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.view_controller;
import glasbyscheduler.MainApp;
import glasbyscheduler.model.Appointment;
import glasbyscheduler.model.Customer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author aglasby
 */
public class AppointmentDialogController {
    @FXML
    private TableView<Customer> customerTable;
    
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    
    @FXML
    private TextField titleField;

    @FXML
    private TextField urlField;

    @FXML
    private TextField searchCustomerField;

    @FXML
    private DatePicker datePicker;
    
    @FXML
    private TextField locationField;
    
    @FXML
    private TextField contactField;

    @FXML
    private ComboBox<String> cbxDescription;

    @FXML
    private ComboBox<String> cbxStartHour;

    @FXML
    private ComboBox<String> cbxStartMinute;

    @FXML
    private ComboBox<String> cbxEndHour;

    @FXML
    private ComboBox<String> cbxEndMinute;

    private Stage dialogStage;
    private Stage calendarStage;

    private Customer customer;

    private Appointment appointment;

    private boolean saveClicked = false;

    private boolean modify = false;
    
     // Reference to the main application
    private MainApp mainApp;
    
    private MainController mainController;
    
    ObservableList<String> hourList = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00");
    ObservableList<String> minuteList = FXCollections.observableArrayList("00", "15", "30", "45");
    ObservableList<Appointment> tempAppointmentList = FXCollections.observableArrayList();
    ObservableList<Appointment> timeCheckList = FXCollections.observableArrayList();
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AppointmentDialogController(){
        
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */ 
    public void setMainApp(MainApp mainApp){
       this.mainApp = mainApp; 
    } 

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
    
    @FXML
    private void handleCalendarScreen(){
         try{
            // Load home screen from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/CalendarView.fxml"));
            AnchorPane mainView = (AnchorPane)loader.load();
            
             // Create the stage.
            Stage CalendarScreenStage = new Stage();
            CalendarScreenStage.setTitle("Calendar");
            CalendarScreenStage.initModality(Modality.WINDOW_MODAL);
            CalendarScreenStage.initOwner(calendarStage);
            
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
    
    /**
     * Sets the customer to be edited in the dialog.
     * 
     * @param appointment
     * @param customer
     */   
    public void setAppointment(Appointment appointment, Customer customer){
        this.appointment = appointment;
        this.customer = customer;
        titleField.setText(appointment.getTitle());
        urlField.setText(appointment.getUrl());        
             
        locationField.setText(appointment.getLocation());
        contactField.setText(appointment.getContact());
        if(MainController.isApptModified == true){
            modifyAppointment(appointment);
            customerTable.getSelectionModel().select(customer);
            cbxDescription.setValue(appointment.getDescription());
            cbxDescription.setItems(MainController.descriptionList);          
            String tempStartDate = appointment.getStart();
            String tempEndDate = appointment.getEnd();
            String pattern = "yyyy-MM-dd HH:mm:ss";  // pattern to use for combo boxes
            String pattern2 = "yyyy-MM-dd";  // pattern to use for the datepicker
            DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));   
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));
            ZonedDateTime currentZone = ZonedDateTime.now();            
            ZonedDateTime startDate = ZonedDateTime.parse(tempStartDate, Parser);            
            ZonedDateTime zoneTest = startDate.withZoneSameInstant(ZoneId.systemDefault());
            ZonedDateTime testSeoul = startDate.withZoneSameInstant(ZoneId.systemDefault());            
            ZonedDateTime endDate = ZonedDateTime.parse(tempEndDate, Parser);
            int startHour = startDate.getHour();
            int testHour = testSeoul.getHour();
            int startMinute = startDate.getMinute();
            int endHour = endDate.getHour();
            int endMinute = endDate.getMinute();
            
            String tempStartHour = Integer.toString(startHour);            
            if(tempStartHour.length() == 1){
                cbxStartHour.setValue("0" + tempStartHour);
            }else{
                cbxStartHour.setValue(Integer.toString(startHour));
            }            
            cbxStartHour.setItems(hourList);
            
            String tempStartMinute = Integer.toString(startMinute);
            if(tempStartMinute.length() == 1){
                cbxStartMinute.setValue("0" + tempStartMinute);
            }else{
                cbxStartMinute.setValue(Integer.toString(startMinute));
            }            
            cbxStartMinute.setItems(minuteList);            
           
            String tempEndHour = Integer.toString(endHour);
            if(tempEndHour.length() == 1){
                cbxEndHour.setValue("0" + tempEndHour);
            }else{
                cbxEndHour.setValue(Integer.toString(endHour));
            }            
            cbxEndHour.setItems(hourList);
            
            String tempEndMinute = Integer.toString(endMinute);
            if(tempEndMinute.length() == 1){
                cbxEndMinute.setValue("0" + tempEndMinute);
            }else{
                cbxEndMinute.setValue(Integer.toString(endMinute));
            }            
            cbxEndMinute.setItems(minuteList);
            
            LocalDateTime appointment1 = LocalDateTime.parse(tempStartDate, formatter);
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String newDate = appointment1.format(outputFormat);          
            LocalDate newDate2 = LocalDate.parse(newDate, outputFormat);
            datePicker.setValue(newDate2);            
        }else{
            // do the same as above for the zone - I need to add another if /else
            datePicker.setValue(appointment.getDate());  
            cbxDescription.setItems(MainController.descriptionList);
            cbxStartHour.setItems(hourList);
            cbxStartMinute.setItems(minuteList);
            cbxEndHour.setItems(hourList);
            cbxEndMinute.setItems(minuteList);
        }   
        initializeCustomerTable();
               
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isSaveClicked() {
        return saveClicked;
    }
    
    /**
     * Called when the user clicks save.
     */    
    @FXML
    private void handleSave(){
        timeCheckList.clear();
        if(validAppointmentData()){
            if(startAndEndValid()){
                if(isInputValid()){
                    Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();                    
                    this.customer = selectedCustomer;
                    appointment.setCustomerId(selectedCustomer.getCustomerId());
                    appointment.setTitle(titleField.getText());
                    appointment.setDescription(cbxDescription.getValue());
                    appointment.setLocation(locationField.getText());
                    appointment.setContact(contactField.getText());
                    appointment.setUrl(urlField.getText());
                    appointment.setStartHour(cbxStartHour.getValue());
                    appointment.setStartMinute(cbxStartMinute.getValue());
                    appointment.setEndHour(cbxEndHour.getValue());
                    appointment.setEndMinute(cbxEndMinute.getValue());
                    appointment.setDate(datePicker.getValue());

                    String start = appointment.getDate().toString() + " " + appointment.getStartHour() + ":" + appointment.getStartMinute();
                    String end = appointment.getDate().toString() + " " + appointment.getEndHour() + ":" + appointment.getEndMinute();
                    appointment.setStart(start);
                    appointment.setEnd(end);

                    saveClicked = true;
                    dialogStage.close();            
                }
            }        
        }else{
            dialogStage.show();
        }
    }
    
     /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    // This is a copy of the MainController appointmentList that will be used for modified appointments for evaluating overlapping timeframes
    private void modifyAppointment(Appointment appointment){
       tempAppointmentList.addAll(MainController.appointmentList);
        for (Appointment a : tempAppointmentList) {
            if(appointment.getAppointmentId() == a.getAppointmentId()){                
                tempAppointmentList.remove(a);
                break;
            }
        }
    }
    
    private boolean isInputValid(){
        if(verifyNoOverlaps()){
            if(insideBusinessHours()){
              return true;  
            }else{
                Alert businessHoursAlert = new Alert(Alert.AlertType.WARNING);            
                businessHoursAlert.initOwner(dialogStage);
                businessHoursAlert.setTitle("Appointment Ouside of business hours");
                businessHoursAlert.setHeaderText("Please schedule the appoinment betwee 9 and 17.");
                businessHoursAlert.setContentText("Please check the calendar for an available appointment hours.");            
                businessHoursAlert.showAndWait();
            return false;
            }
        }else{
            Alert ovlapsAlert = new Alert(Alert.AlertType.WARNING);            
            ovlapsAlert.initOwner(dialogStage);
            ovlapsAlert.setTitle("Appointment Overlap detected");
            ovlapsAlert.setHeaderText("The selected time overlaps with another appointment on the selected date.");
            ovlapsAlert.setContentText("Please check the calendar for an available appointment.");            
            ovlapsAlert.showAndWait();
            return false;
        }       
    }
    
    public boolean startAndEndValid(){
        String tempStartDate = datePicker.getValue().toString() + " " + cbxStartHour.getValue() + ":" + cbxStartMinute.getValue();
        String tempEndDate = datePicker.getValue().toString() + " " + cbxEndHour.getValue() + ":" + cbxEndMinute.getValue();
        
        String pattern = "yyyy-MM-dd HH:mm";
	DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));
        ZonedDateTime startDate = ZonedDateTime.parse(tempStartDate, Parser);
        ZonedDateTime endDate = ZonedDateTime.parse(tempEndDate, Parser);  
        
        if(endDate.getHour() >= startDate.getHour()){
            if(endDate.getHour() == startDate.getHour()){
                if(endDate.getMinute() > startDate.getMinute()){
                    return true;
                }
            }
            return true;
        }else{
            Alert endTimeAlert = new Alert(Alert.AlertType.WARNING);            
            endTimeAlert.initOwner(dialogStage);
            endTimeAlert.setTitle("Please correct the appointment end time");
            endTimeAlert.setHeaderText("The appointment end time must be after the appointment start time.");
            endTimeAlert.setContentText("Please correct the appointment time.");            
            endTimeAlert.showAndWait();
            return false;
        }        
    }
    
    public void initializeCustomerTable(){
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());        
        customerTable.setItems(MainController.customerList);
    }
    
    private boolean verifyNoOverlaps(){
        boolean noOverlaps = true;
        LocalDate compareDate = datePicker.getValue();  // the date we are considering scheduling
        int compareStartHour = Integer.parseInt(cbxStartHour.getValue());
        int compareStartMinute = Integer.parseInt(cbxStartMinute.getValue());
        int compareEndHour = Integer.parseInt(cbxEndHour.getValue());
        int compareEndMinute = Integer.parseInt(cbxEndMinute.getValue());
         ObservableList<Appointment> overlapsAppointmentList = FXCollections.observableArrayList();
        if(MainController.isApptModified == true){
            overlapsAppointmentList.addAll(tempAppointmentList);
        }else{
            overlapsAppointmentList.addAll(MainController.appointmentList);
        }
        for (Appointment appointment : overlapsAppointmentList) {
            String tempStartDate = appointment.getStart();
            String tempEndDate = appointment.getEnd();
            String pattern = "yyyy-MM-dd HH:mm:ss";  // pattern to use for combo boxes
            DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York")); 
            ZonedDateTime startDate = ZonedDateTime.parse(tempStartDate, Parser);
            ZonedDateTime endDate = ZonedDateTime.parse(tempEndDate, Parser);
            
            if(compareDate.equals(startDate.toLocalDate())){
                timesToSegments();
                if(startDate.getHour() == endDate.getHour()){
                    boolean overlapCheck = sameStartEndHour(compareStartHour, compareStartMinute, compareEndHour, compareEndMinute, startDate, endDate, noOverlaps);
                    if(overlapCheck == false){
                        noOverlaps = false;
                        return noOverlaps;
                    }
                }else{
                    ZonedDateTime tempDate = endDate;  

                    while(tempDate.getHour() != startDate.getHour() || tempDate.getMinute() != startDate.getMinute()){
                        if(compareStartHour == tempDate.getHour() && compareStartMinute == tempDate.getMinute()){
                            noOverlaps = false;
                            return noOverlaps;
                        }                    
                        if(compareEndHour == tempDate.getHour() && compareEndMinute == tempDate.getMinute()){
                            noOverlaps = false;
                            return noOverlaps;
                        }
                        
                        for (Appointment a : timeCheckList) {
                            int s = Integer.parseInt(a.getStartHour());
                            int e = Integer.parseInt(a.getStartMinute());
                            if(tempDate.getHour() == s && tempDate.getMinute() == e){
                                noOverlaps = false;
                                return noOverlaps;
                            }
                        }
                        tempDate = tempDate.minusMinutes(15);                        
                    }
                }                
            }
        }        
        return noOverlaps;
    }
    
    private boolean insideBusinessHours(){
        if(Integer.parseInt(cbxStartHour.getValue()) >= 9 && Integer.parseInt(cbxStartHour.getValue()) <= 17 ){
            if(Integer.parseInt(cbxStartHour.getValue()) == 17){
                if(Integer.parseInt(cbxStartMinute.getValue()) == 0){
                    
                }
            }            
            if(Integer.parseInt(cbxEndHour.getValue()) >= 9 && Integer.parseInt(cbxEndHour.getValue()) <= 17 ){
                if(Integer.parseInt(cbxEndHour.getValue()) == 17){
                    if(Integer.parseInt(cbxEndMinute.getValue()) == 0){

                    }
                }
            }
            if(Integer.parseInt(cbxEndHour.getValue()) >= Integer.parseInt(cbxStartHour.getValue())){
                if(Integer.parseInt(cbxEndHour.getValue()) == Integer.parseInt(cbxStartHour.getValue())){
                    if(Integer.parseInt(cbxEndMinute.getValue()) > Integer.parseInt(cbxStartMinute.getValue())){

                    }
                }
            }
            return true;
        }else{
            return false;
        }       
    }
    
    private boolean validAppointmentData(){
        StringBuilder errorMessage = new StringBuilder();
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if(titleField.getText() == null){
            errorMessage.append(" Please enter a title for your Appointment. ");
        }
        if(urlField.getText() == null){
            errorMessage.append(" Please enter a url or type 'none' for your Appointment. ");
        }
        if(locationField.getText() == null){
            errorMessage.append(" Please enter a location for your Appointment. ");
        }
        if(contactField.getText() == null){
            errorMessage.append(" Please enter a contact for your Appointment. ");
        }
        if(datePicker.getValue() == null){
            errorMessage.append(" Please select a date for your Appointment. ");
        }
        if(cbxDescription.getValue() == null){
            errorMessage.append(" Please select a Description for your Appointment. ");
        }
        if(cbxStartHour.getValue() == null){
            errorMessage.append(" Please select a Start Hour for your Appointment. ");
        }
        if(cbxStartMinute.getValue() == null){
            errorMessage.append(" Please select a Start Minute for your Appointment. ");
        }
        if(cbxEndHour.getValue() == null){
            errorMessage.append(" Please select a End Hour for your Appointment. ");
        }
        if(cbxEndMinute.getValue() == null){
            errorMessage.append(" Please select a Start Minute for your Appointment. ");
        }
        if(selectedCustomer == null){
            errorMessage.append(" Please select a Customer for your Appointment. ");
        }
        if(errorMessage.length() == 0){            
            return true;            
        }else{            
            Alert appointmentAlert = new Alert(Alert.AlertType.WARNING);            
            appointmentAlert.initOwner(dialogStage);
            appointmentAlert.setTitle("Appointment Information Missing");
            appointmentAlert.setHeaderText("Please make sure all appointment fields are complete.");
            appointmentAlert.setContentText("Please correct the following: " + errorMessage);            
            appointmentAlert.showAndWait();
            return false;
        }        
    }
    
    private boolean sameStartEndHour(int compareStartHour, int compareStartMinute, int compareEndHour, int compareEndMinute, ZonedDateTime startDate, ZonedDateTime endDate, boolean noOverlaps ){
        ZonedDateTime tempDate = endDate;                 
        while(tempDate.getMinute() != startDate.getMinute()){
            if(compareStartHour == tempDate.getHour() && compareStartMinute == tempDate.getMinute()){
                noOverlaps = false;
                return noOverlaps;
            }                    
            if(compareEndHour == tempDate.getHour() && compareEndMinute == tempDate.getMinute()){
                noOverlaps = false;
                return noOverlaps;
            }
            for (Appointment a : timeCheckList) {
                int s = Integer.parseInt(a.getStartHour());
                int e = Integer.parseInt(a.getStartMinute());
                if(tempDate.getHour() == s && tempDate.getMinute() == e){
                    noOverlaps = false;
                    return noOverlaps;
                }
            }
            tempDate = tempDate.minusMinutes(15);
        }
        return noOverlaps;
    }
    
    private void timesToSegments(){
        String tempStartDate = datePicker.getValue().toString() + " " + cbxStartHour.getValue() + ":" + cbxStartMinute.getValue();
        String tempEndDate = datePicker.getValue().toString() + " " + cbxEndHour.getValue() + ":" + cbxEndMinute.getValue();
        String pattern = "yyyy-MM-dd HH:mm";
	DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));
        ZonedDateTime pStartDate = ZonedDateTime.parse(tempStartDate, Parser);
        ZonedDateTime pEndDate = ZonedDateTime.parse(tempEndDate, Parser);
        ZonedDateTime tempStart = pEndDate;
        timeCheckList.add(new Appointment(Integer.toString(pEndDate.getHour()), Integer.toString(pEndDate.getMinute())));
        timeCheckList.add(new Appointment(Integer.toString(pStartDate.getHour()), Integer.toString(pStartDate.getMinute())));        
        
        while(tempStart.getHour() != pStartDate.getHour() || tempStart.getMinute() != pStartDate.getMinute()){
            String myStart = Integer.toString(tempStart.getHour());
            String myEnd = Integer.toString(tempStart.getMinute());
            Appointment temp = new Appointment(myStart, myEnd);
            timeCheckList.add(temp);
            tempStart = tempStart.minusMinutes(15);
        }       
    }    
}
