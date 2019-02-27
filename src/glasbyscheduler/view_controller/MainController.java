/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.view_controller;

import glasbyscheduler.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import glasbyscheduler.model.Customer;
import glasbyscheduler.model.Appointment;
import glasbyscheduler.model.WeeklyCalendar;
import glasbyscheduler.model.City;
import glasbyscheduler.model.Country;
import glasbyscheduler.utilities.DbConnection;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author aglasby
 */
public class MainController {
    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneColumn;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, String> appointmentTypeColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;


    @FXML
    private TableView<Appointment> calendarTable;

    @FXML
    private TableColumn<Appointment, String> sundayColumn;


    @FXML
    private TableColumn<Appointment, String> mondayColumn;

    @FXML
    private TableColumn<WeeklyCalendar, String> tuesdayColumn;

    @FXML
    private TableColumn<WeeklyCalendar, String> wednesdayColumn;

    @FXML
    private TableColumn<WeeklyCalendar, String> thursdayColumn;

    @FXML
    private TableColumn<WeeklyCalendar, String> fridayColumn;

    @FXML
    private TableColumn<WeeklyCalendar, String> saturdayColumn;

    @FXML 
    private Pane calendarPane;

    @FXML
    private TextArea calendarText;

    public static boolean isModified = false;
    public static boolean isApptModified = false;

    private String loginHeader = "Please enter your username and password to continue \n";
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String activityLogFile = "src/files/loginActivity.txt", item;

    @FXML
    public static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    public static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    
    @FXML
    public static ObservableList<Appointment> allAppointmentList = FXCollections.observableArrayList();
    
    @FXML
    public static ObservableList<String> consultantList = FXCollections.observableArrayList();

    @FXML
    ObservableList<Appointment> calendarList = FXCollections.observableArrayList();

    @FXML
    ObservableList<String> logFile = FXCollections.observableArrayList();
    
    @FXML
    public static ObservableList<City> cityList = FXCollections.observableArrayList();
    
    @FXML
    public static ObservableList<String> cities = FXCollections.observableArrayList();
    
    @FXML
    public static ObservableList<String> countries = FXCollections.observableArrayList();
    
    @FXML
    public static ObservableList<Country> countryList = FXCollections.observableArrayList();
    
    @FXML
    public static ObservableList<String> descriptionList = FXCollections.observableArrayList();

    // Reference to the main application
    private MainApp mainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainController(){
        
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
        
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */    
    @FXML
    private void initialize() {  
      
    }

    public void initializeMainScreenColumns(){
        // Initialize the customer table 
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        customerPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().customerPhoneProperty());
        
        // Intialize the appointment table
        appointmentTypeColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());        
        customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        loadCustomerData();
        loadAppointmentData();
        getCityData();
        getCountryData();
        getAllCityData();
        getAllCountryData();
        getAppointmentDescriptionData();
        getAllAppointmentData();
        getConsultantData();
        checkCurrentAppointment();        
    }  

    
    @FXML
    private void handleCustomerScreen(){
        mainApp.showCustomerScreen();
    }
    
    @FXML
    private void handleAppointmentScreen(){
        mainApp.showAppointmentScreen();
    }
    
    @FXML
    private void handleCalendarScreen(){
        mainApp.showCalendarScreen();
    }
    
    @FXML
    private void handleWeeklyCalendarScreen(){
        mainApp.showWeeklyCalendarScreen();
    }
    
    @FXML
    private void handleReportsScreen(){
        mainApp.showReportsScreen();
    }
    
    @FXML
    private void handleCustmerAppointmentScreen(){
        mainApp.showCustomerAppointmentCountScreen();
    }
    
    @FXML
    private void handleLogin(){
//        mainApp.showLoginScreen();
        // Create the custom dialog.
        getLocale();
        
        Dialog<Pair<String, String>> loginDialog = new Dialog<>();
        loginDialog.setTitle("Scheduler Login");
        loginDialog.setHeaderText(loginHeader);
        
        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        loginDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        
        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = loginDialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        loginDialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        loginDialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = loginDialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            try {
                LocalDateTime date = LocalDateTime.now();
                FileWriter fileWriter = new FileWriter(activityLogFile, true);
                PrintWriter outFile = new PrintWriter(fileWriter);
                if(username.getText().equals("test") && password.getText().equals("password")){
                    item = username.getText() + "\tLogin Successful\t" + date.toString();
                    logFile.add(username.getText() + " login successfull");

                    
                    //fileWriter.append(item);
                    outFile.println(item);
                    outFile.close();


                    //Close Home Screen                
                    mainApp.getPrimaryStage().close();
                    mainApp.showMainScreen();
                }else{
                    item = username.getText() + "\tLogin Failed\t" + date.toString();
                    logFile.add(username.getText() + "login failed");
                    runLoginErrorDialog();
                   
                   
                   outFile.println(item);
                   outFile.close();

                }
            } catch (Exception e) {
            }
        });
    }
    
    private void runLoginErrorDialog(){
        String localeErrorTitle = "";
        String localeErrorHeaderText = "";
        String localeErrorContentText = "";
        
        if(Locale.getDefault().equals(Locale.US) || Locale.getDefault().equals(Locale.ENGLISH)){ 
            Locale locale = new Locale("es", "ES");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);        
            localeErrorTitle += rb.getString("loginErrorTitle"); 
            localeErrorHeaderText += rb.getString("loginErrorHeaderText");
            localeErrorContentText += rb.getString("loginErrorContentText");
        }
        
        if(Locale.getDefault().equals(Locale.GERMAN)|| Locale.getDefault().equals(Locale.GERMANY)){
            Locale locale = new Locale("de", "DE");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            localeErrorTitle += rb.getString("loginErrorTitle"); 
            localeErrorHeaderText += rb.getString("loginErrorHeaderText");
            localeErrorContentText += rb.getString("loginErrorContentText");         
        }

        if(Locale.getDefault().equals(Locale.FRANCE)|| Locale.getDefault().equals(Locale.FRENCH)){
            Locale locale = new Locale("fr", "FR");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            localeErrorTitle += rb.getString("loginErrorTitle"); 
            localeErrorHeaderText += rb.getString("loginErrorHeaderText");
            localeErrorContentText += rb.getString("loginErrorContentText");          
        }
        
        if(Locale.getDefault().equals(Locale.JAPAN)|| Locale.getDefault().equals(Locale.JAPANESE)){
            Locale locale = new Locale("ja", "JA");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            localeErrorTitle += rb.getString("loginErrorTitle"); 
            localeErrorHeaderText += rb.getString("loginErrorHeaderText");
            localeErrorContentText += rb.getString("loginErrorContentText");           
        }
        
        if(Locale.getDefault().equals(Locale.KOREA)|| Locale.getDefault().equals(Locale.KOREAN)){
            Locale locale = new Locale("ko", "KO");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            localeErrorTitle += rb.getString("loginErrorTitle"); 
            localeErrorHeaderText += rb.getString("loginErrorHeaderText");
            localeErrorContentText += rb.getString("loginErrorContentText");          
        }
        
        if(Locale.getDefault().equals(Locale.CHINA)|| Locale.getDefault().equals(Locale.CHINESE)){
            Locale locale = new Locale("zh", "ZH");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            localeErrorTitle += rb.getString("loginErrorTitle"); 
            localeErrorHeaderText += rb.getString("loginErrorHeaderText");
            localeErrorContentText += rb.getString("loginErrorContentText");          
        }      
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Login Error\t -----" + localeErrorTitle);
        alert.setHeaderText("The username or password entered is incorrect.\n" + localeErrorHeaderText);
        alert.setContentText("Please re-enter your information.\n" + localeErrorContentText);
        alert.showAndWait();        
    }
    
    @FXML
    private void getLocale(){        
        if(Locale.getDefault().equals(Locale.US) || Locale.getDefault().equals(Locale.ENGLISH)){ 
            Locale locale = new Locale("es", "ES");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);        
            loginHeader += rb.getString("login");           
        }
        
        if(Locale.getDefault().equals(Locale.GERMAN)|| Locale.getDefault().equals(Locale.GERMANY)){
            Locale locale = new Locale("de", "DE");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            loginHeader += rb.getString("login");            
        }

        if(Locale.getDefault().equals(Locale.FRANCE)|| Locale.getDefault().equals(Locale.FRENCH)){
            Locale locale = new Locale("fr", "FR");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            loginHeader += rb.getString("login");            
        }
        
        if(Locale.getDefault().equals(Locale.JAPAN)|| Locale.getDefault().equals(Locale.JAPANESE)){
            Locale locale = new Locale("ja", "JA");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            loginHeader += rb.getString("login");            
        }
        
        if(Locale.getDefault().equals(Locale.KOREA)|| Locale.getDefault().equals(Locale.KOREAN)){
            Locale locale = new Locale("ko", "KO");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            loginHeader += rb.getString("login");            
        }
        
        if(Locale.getDefault().equals(Locale.CHINA)|| Locale.getDefault().equals(Locale.CHINESE)){
            Locale locale = new Locale("zh", "ZH");
            ResourceBundle rb = ResourceBundle.getBundle("language_files/Language", locale);
            loginHeader += rb.getString("login");            
        }      
    }
    
    private void loadCustomerData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();
            rs = st.executeQuery("SELECT customer.customerid, customer.customerName, customer.addressId, address.phone, address.address, address.address2, address.cityId, address.postalCode, city.city, city.countryId, country.country FROM customer JOIN address ON customer.addressId = address.addressId JOIN city ON city.cityId = address.cityId JOIN country ON city.countryId = country.countryId");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){
                String customerName = rs.getString("customerName");
                String phone = rs.getString("phone");
                int customerId = rs.getInt("customerid");
                int addressId = rs.getInt("addressId");
                String address1 = rs.getString("address");
                String address2 = rs.getString("address2");
                int cityId = rs.getInt("cityId");
                int postalCode = rs.getInt("postalCode");
                String city = rs.getString("city");
                int countryId = rs.getInt("countryId");
                String country = rs.getString("country");
                Customer tr = new Customer(customerId, customerName, addressId, phone, address1, address2, cityId, city, postalCode, countryId, country);
                customerList.add(tr);
            }
            customerTable.setItems(customerList);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void loadAppointmentData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT appointmentid, customerId, title, description, location, contact, url, start, end FROM appointment WHERE createdBy = 'test'");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){
                int appointmentId = rs.getInt("appointmentid");                
                int customerId = rs.getInt("customerId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");
                String url = rs.getString("url");
                String start = rs.getString("start");
                String end = rs.getString("end");
               
                Appointment tr = new Appointment(appointmentId, customerId, title, description, location, contact, url, start, end);
                appointmentList.add(tr);
            }
            appointmentTable.setItems(appointmentList);            
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        private void getAllAppointmentData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT appointmentid, customerId, title, description, location, contact, url, start, end FROM appointment ");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){
                int appointmentId = rs.getInt("appointmentid");                
                int customerId = rs.getInt("customerId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");
                String url = rs.getString("url");
                String start = rs.getString("start");
                String end = rs.getString("end");
               
                Appointment tr = new Appointment(appointmentId, customerId, title, description, location, contact, url, start, end);
                allAppointmentList.add(tr);
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getAppointmentDescriptionData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT DISTINCT description FROM appointment");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){
                String description = rs.getString("description");                
                descriptionList.add(description);
            }                       
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getConsultantData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT DISTINCT contact FROM appointment");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){
                String consultant = rs.getString("contact");                
                consultantList.add(consultant);
            }                       
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getAllCityData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT cityId, city, countryId FROM city");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){
                int cityId = rs.getInt("cityId");
                String city = rs.getString("city");
                int countryId = rs.getInt("countryId");               
                City tr = new City(cityId, city, countryId);
                cityList.add(tr);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void getCityData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT city FROM city");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){                
                String city = rs.getString("city");                
                cities.add(city);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void getAllCountryData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT countryId, country FROM country");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){ 
                int countryId = rs.getInt("countryId");
                String country = rs.getString("country");               
                Country tr = new Country(countryId, country);
                countryList.add(tr);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        
    private void getCountryData(){
        try {
            Connection db = DbConnection.getConnection();
            Statement st = db.createStatement();        
            rs = st.executeQuery("SELECT country FROM country");
            rs.beforeFirst(); // resets cursor
            while(rs.next()){                
                String country = rs.getString("country");
                countries.add(country);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }  

    @FXML
    private void handleAddCustomer(){
        isModified = false;
        Customer tempCustomer = new Customer();
        boolean saveClicked = mainApp.showAddCustomerDialog(tempCustomer);
        if(saveClicked){
            customerList.add(tempCustomer);
            customerTable.refresh();
            String addressId = insertAddressDB(tempCustomer.getAddress1(), tempCustomer.getAddress2(), tempCustomer.getCityId(), tempCustomer.getZipCode(), tempCustomer.getCustomerPhone());
            int addressID = Integer.parseInt(addressId);
            insertCustomerDB(tempCustomer.getCustomerName(), addressID);
            customerList.clear();
            loadCustomerData(); 
        }
    }
    
    @FXML
    private void handleEditCustomer(){
        isModified = true;
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null){
            boolean saveClicked = mainApp.showAddCustomerDialog(selectedCustomer);
            if(saveClicked){            
            customerTable.refresh();
            updateCustomerDB(selectedCustomer.getCustomerId(), selectedCustomer.getCustomerName());
            updateAddressDB(selectedCustomer.getAddressId(), selectedCustomer.getAddress1(), selectedCustomer.getAddress2(), selectedCustomer.getCityId(), selectedCustomer.getZipCode(), selectedCustomer.getCustomerPhone());
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }        
    } 
    
    @FXML
    private void handleDeleteCustomer(){
        Customer customer = customerTable.getSelectionModel().getSelectedItem();
        int selectedCustomer = customerTable.getSelectionModel().getSelectedIndex();
        if(selectedCustomer >= 0){
            Alert alert = new Alert(AlertType.CONFIRMATION);            
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Please confirm that you want to delete this item.");
            alert.setContentText("Are you sure you want to Delete this item?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                customerTable.getItems().remove(selectedCustomer);            
                deleteCustomerDB(customer.getCustomerId());
                deleteAddressDB(customer.getAddressId());
                checkCustApp(customer.getCustomerId());
                customerList.clear();
                loadCustomerData(); 
            }else{            
              alert.close();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");

            alert.showAndWait();
        }       
    }
    
    private String insertCustomerDB(String customerName, int addressId){
        //int addressId = Integer.parseInt(addressID);
        String customerId = null;
        String sql = "INSERT INTO customer(customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES(?, ?, 1, now(), 'test', now(), 'test')";
         try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setInt(2, addressId);            
            ps.execute();                 
            ps = db.prepareStatement("SELECT LAST_INSERT_ID() FROM customer");  // retrieve newly assigned customer id
            rs = ps.executeQuery();
            rs.next(); // only one record, so no need for a loop.
            customerId = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return customerId;        
    }
    
    private String insertAddressDB(String address, String address2, int cityId, int postalCode, String phone){
        String addressId = null;
        String sql = "INSERT INTO address(address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES(?,?, ?, ?, ?, now(), 'test', now(), 'test')";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setString(1, address);
            ps.setString(2, address2);
            ps.setInt(3, cityId);            
            ps.setInt(4, postalCode);
            ps.setString(5, phone);
            ps.execute();
            ps = db.prepareStatement("SELECT LAST_INSERT_ID() FROM address");// retrieve newly assigned address id
            rs = ps.executeQuery();
            rs.next(); // only one record, so no need for a loop.
            addressId = rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return addressId;
    }
    
    private void updateCustomerDB(int customerId, String customerName){
        String custId = Integer.toString(customerId);
        
        String sql = "UPDATE customer SET customerName = ?, lastUpdate = now(), lastUpdateBy = 'test' WHERE customerId = ?";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, custId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void updateAddressDB(int addressId, String address, String address2, int cityId, int postalCode, String phone){
        String addId = Integer.toString(addressId);
        String cId = Integer.toString(cityId);
        String pc = Integer.toString(postalCode);
        
        String sql = "UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode = ?, phone = ?, lastUpdate = now(), lastUpdateBy = 'test' WHERE addressId = ? ";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setString(1, address);
            ps.setString(2, address2);
            ps.setString(3, cId);
            ps.setString(4, pc);
            ps.setString(5, phone);
            ps.setString(6, addId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
   
    private void deleteCustomerDB(int customerId){
        String custId = Integer.toString(customerId);        
        String sql = "DELETE FROM customer WHERE customerId = ?";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setString(1, custId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void deleteAddressDB(int addressId){
        String addId = Integer.toString(addressId);        
        String sql = "DELETE FROM address WHERE addressId = ?";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setString(1, addId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
  
    @FXML
    private void handleAddAppointment(){
        isApptModified = false;
        Appointment tempAppointment = new Appointment();
        Customer tempCustomer = new Customer();
        boolean saveClicked = mainApp.showAddAppointmentDialog(tempAppointment, tempCustomer);
        if(saveClicked){            
            insertAppointmentDB(tempAppointment.getCustomerId(), tempAppointment.getTitle(), tempAppointment.getDescription(), tempAppointment.getLocation(), tempAppointment.getContact(), tempAppointment.getUrl(), tempAppointment.getStart(), tempAppointment.getEnd());
            appointmentList.clear();
            loadAppointmentData();
        }
    }
    
    private String insertAppointmentDB(int customerId, String title, String description, String location, String contact, String url, String start, String end ){
        String appointmentId = null;
        String sql = "INSERT INTO appointment(customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES(?, ?, ?, ?, ?, ?, ?, ?,  now(), 'test', now(), 'test')";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, location);
            ps.setString(5, contact);
            ps.setString(6, url);
            ps.setString(7, start);            
            ps.setString(8, end);
            ps.execute();
            ps = db.prepareStatement("SELECT LAST_INSERT_ID() FROM appointment");// retrieve newly assigned address id
            rs = ps.executeQuery();
            rs.next(); // only one record, so no need for a loop.
            appointmentId = rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return appointmentId;        
    }
    
    @FXML
    private void handleModifyAppointment(){
        isApptModified = true;
        if(appointmentTable.getSelectionModel().getSelectedItem() != null){
            Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
            Customer selectedCustomer = new Customer();
            int customerId = selectedAppointment.getCustomerId();
            // find the customer id and highlight that customer in the customerTable
            for (Customer customer : customerList) {
                if(customer.getCustomerId() == customerId){
                    selectedCustomer = customer;
                    break;
                }
            }        
            boolean saveClicked = mainApp.showAddAppointmentDialog(selectedAppointment, selectedCustomer);
            if(saveClicked){
                appointmentTable.refresh();
                String start = selectedAppointment.getDate().toString() + " " + selectedAppointment.getStartHour() + ":" + selectedAppointment.getStartMinute();
                String end = selectedAppointment.getDate().toString() + " " + selectedAppointment.getEndHour() + ":" + selectedAppointment.getEndMinute();

                updateAppointmentDB(selectedAppointment.getAppointmentId(), selectedAppointment.getCustomerId(), selectedAppointment.getTitle(), selectedAppointment.getDescription(), selectedAppointment.getLocation(), selectedAppointment.getContact(), selectedAppointment.getUrl(), start, end);
                appointmentList.clear();
                loadAppointmentData();
            }            
        }else{
            // Nothing selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Appointment Selected");
            alert.setContentText("Please select an appointment to be modified.");
            alert.showAndWait();            
        }       
    }
    
    @FXML
    private void handleDeleteAppointment(){
        Appointment appointment = appointmentTable.getSelectionModel().getSelectedItem();
        int selectedAppointment = appointmentTable.getSelectionModel().getSelectedIndex();
        if(selectedAppointment >= 0){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Please confirm that you want to delete this item.");
            alert.setContentText("Are you sure you want to Delete this item?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                appointmentTable.getItems().remove(selectedAppointment);
            // appointmentTable.refresh();
                deleteAppointmentDB(appointment.getAppointmentId());                
            }else{
                alert.close();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }        
    }
    
    private void deleteAppointmentDB(int appointmentId){
        String apptId = Integer.toString(appointmentId);
        String sql = "DELETE FROM appointment WHERE appointmentId = ?";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
            ps.setString(1, apptId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void updateAppointmentDB(int appointmentId, int customerId, String title, String description, String location, String contact, String url, String start, String end){
        String apptId = Integer.toString(appointmentId);
        
        String sql = "UPDATE appointment SET customerId = ?, title = ?, description = ?, location = ?, contact = ?, url = ?, start = ?, end = ?, lastUpdate = now(), lastUpdateBy = 'test' WHERE appointmentid = ?";
        try {
            Connection db = DbConnection.getConnection();
            ps = db.prepareStatement(sql);
             ps.setInt(1, customerId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, location);
            ps.setString(5, contact);
            ps.setString(6, url);
            ps.setString(7, start);            
            ps.setString(8, end);
            ps.setString(9, apptId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @FXML
    private void handleLoginActivity(){
        mainApp.showLoginActivityScreen();
    }
    
    @FXML
    private void handleConsultantScheduleScreen(){
        mainApp.showConsultantScheduleScreen();
    }
    
    @FXML
    private void handleAppointmentCountScreen(){
        mainApp.showAppointmentCountScreen();
    }
    
    private void checkCustApp(int custId){
        for (Appointment a : allAppointmentList) {
            if(a.getCustomerId() == custId){
                deleteAppointmentDB(a.getAppointmentId());
                appointmentList.clear();
                loadAppointmentData();                
            }
        }
    }
    
    private void checkCurrentAppointment(){
        ZonedDateTime currentTime = ZonedDateTime.now();
        outerloop:
        for (Appointment a : MainController.allAppointmentList) {            
            String tempStartDate = a.getStart();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/New_York"));
            ZonedDateTime startDate = ZonedDateTime.parse(tempStartDate, Parser);
            ZonedDateTime tempTime;
            for (int i = 0; i < 15; i++) {                
                tempTime = currentTime.plusMinutes(i);
                if(tempTime.getHour() == startDate.getHour() && tempTime.getMinute() == startDate.getMinute()){                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Appointment Starting Soon");
                    alert.setHeaderText("Apppointment Starting Soon");
                    alert.setContentText("You have an appointment starting at: " + startDate.getHour() + ":" + startDate.getMinute());
                    alert.show();
                    break outerloop;
                }
            }
            
        }
    }
}
