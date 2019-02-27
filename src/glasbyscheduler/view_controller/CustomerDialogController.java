/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.view_controller;

import glasbyscheduler.MainApp;
import glasbyscheduler.model.CustomException.AddressMissingException;
import glasbyscheduler.model.Appointment;
import glasbyscheduler.model.City;
import glasbyscheduler.model.Country;
import glasbyscheduler.model.CustomException;
import glasbyscheduler.model.CustomException.Address2MissingException;
import glasbyscheduler.model.CustomException.CityMissingException;
import glasbyscheduler.model.CustomException.CountryMissingException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import glasbyscheduler.model.Customer;
import glasbyscheduler.model.CustomException.NameMissingException;
import glasbyscheduler.model.CustomException.PhoneNumberException;
import glasbyscheduler.model.CustomException.ZipCodeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author aglasby
 */
public class CustomerDialogController {
    
    @FXML
    private TextField customerNameField;
    
    @FXML
    private TextField address1Field;
    
    @FXML
    private TextField address2Field;
    
    @FXML
    private TextField zipCodeField;
    
    @FXML
    private TextField phoneField;
    
    @FXML
    private ComboBox<String> cbxCountry;
    
    @FXML
    private ComboBox<String> cbxCity;
    
    private Stage dialogStage;
    
    private Customer customer;
    
    private boolean saveClicked = false;
    
    private boolean modify = false;    
    
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
    
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
        customerNameField.setText(customer.getCustomerName());
        address1Field.setText(customer.getAddress1());
        address2Field.setText(customer.getAddress2());
        zipCodeField.setText(Integer.toString(customer.getZipCode()));
        phoneField.setText(customer.getCustomerPhone());        
        if(MainController.isModified == true){
            cbxCity.setValue(customer.getCity());
            cbxCity.setItems(MainController.cities);
            cbxCountry.setValue(customer.getCountry());
            cbxCountry.setItems(MainController.countries);            
        }else{
            cbxCity.setItems(MainController.cities);
            cbxCountry.setItems(MainController.countries);
        }
    }
    
    @FXML
    private void countryChoice(){
        if(cbxCountry.getValue().equals("USA")){
            ObservableList<String> usCityList = FXCollections.observableArrayList("Washington", "New York", "Los Angeles", "Chicago", "Houston", "Phoenix");
            cbxCity.setItems(usCityList);            
        }
        if(cbxCountry.getValue().equals("Japan")){
            ObservableList<String> japanCityList = FXCollections.observableArrayList("Tokyo", "Toyohashi", "Okazaki", "Toyota", "Akita");
            cbxCity.setItems(japanCityList);            
        }
        
        if(cbxCountry.getValue().equals("Australia")){
            ObservableList<String> australiaCityList = FXCollections.observableArrayList("Canberra", "Sydney", "Melbourne", "Brisbane", "Perth");
            cbxCity.setItems(australiaCityList);            
        }
        
        if(cbxCountry.getValue().equals("Russia")){
            ObservableList<String> russiaCityList = FXCollections.observableArrayList("Moscow", "Saint Petersburg", "Yeketerinburg", "Kazan", "Novosibirsk");
            cbxCity.setItems(russiaCityList);            
        }
        
        if(cbxCountry.getValue().equals("Britain")){
            ObservableList<String> britainCityList = FXCollections.observableArrayList("London", "Birmingham", "Manchester", "Glasgow", "Leeds", "Liverpool");
            cbxCity.setItems(britainCityList);            
        }
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
    private void handleSave() {
        if (isInputValid()) {
            customer.setCustomerName(customerNameField.getText());
            customer.setAddress1(address1Field.getText());
            customer.setAddress2(address2Field.getText());
            customer.setZipCode(Integer.parseInt(zipCodeField.getText()));
            String cityName = cbxCity.getValue();
            String countryName = cbxCountry.getValue();
            int cId = 0;
            int coId = 0;
            for (City city : MainController.cityList) {
                if(cityName.equals(city.getCity())){
                    cId = city.getCityId();
                }
            }
            
            customer.setCityId(cId);
            customer.setCity(cbxCity.getValue());
            for (Country country : MainController.countryList) {
                if(countryName.equals(country.getCountry())){
                    coId = country.getCountryId();
                }
            }
            customer.setCountry(cbxCountry.getValue());
            customer.setCountryId(coId);
            customer.setCustomerPhone(phoneField.getText());          

            saveClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
   private boolean isInputValid(){
       String errorMessage = "Exception occured:  ";
       try{
           CustomException.validateName(customerNameField.getText());
           CustomException.validateAddress(address1Field.getText());
           CustomException.validateAddress2(address2Field.getText());
           CustomException.validateCountry(cbxCountry.getValue());
           CustomException.validateCity(cbxCity.getValue());
           CustomException.validateZipcode(zipCodeField.getText());
           CustomException.validatePhoneNumber(phoneField.getText());
       }catch(NameMissingException | AddressMissingException | Address2MissingException | CountryMissingException | CityMissingException | ZipCodeException | PhoneNumberException e){
           errorMessage += e.getMessage();
           
            // Show the error message.
           Alert alert = new Alert(AlertType.ERROR);
           alert.initOwner(dialogStage);
           alert.setTitle("Invalid Field");
           alert.setHeaderText("Please correct invalid field");
           alert.setContentText(errorMessage);
           alert.showAndWait();
           return false;
       }

        return true;
   }   
    
}
