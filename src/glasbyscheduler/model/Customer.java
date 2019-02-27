/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author aglasby
 */
public class Customer {
    protected IntegerProperty customerId;
    protected StringProperty customerName;
    protected IntegerProperty customerAddressId;
    protected IntegerProperty addressId;
    protected IntegerProperty cityId;
    protected IntegerProperty countryId;
    protected StringProperty address1;
    protected StringProperty address2;
    protected StringProperty city;
    protected StringProperty state;
    protected StringProperty country;
    protected IntegerProperty zipCode;
    protected StringProperty customerPhone;
    
    public Customer(String customerName, String address1, String address2, String city, String state, String country, int zipCode, String customerPhone){
        this.customerName = new SimpleStringProperty(customerName);
        this.address1 = new SimpleStringProperty(address1);
        this.address2 = new SimpleStringProperty(address2);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.country = new SimpleStringProperty(country);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.customerPhone = new SimpleStringProperty(customerPhone);
    }
    
    public Customer(int customerId, String customerName, int addressId, String customerPhone, String address1, String address2, int cityId, String city, int zipCode, int countryId, String country ){
        this.customerId = new SimpleIntegerProperty(customerId);
        this.customerName = new SimpleStringProperty(customerName);
        this.addressId = new SimpleIntegerProperty(addressId);
        this.address1 = new SimpleStringProperty(address1);
        this.address2 = new SimpleStringProperty(address2);
        this.cityId = new SimpleIntegerProperty(cityId);
        this.city = new SimpleStringProperty(city);
        //this.state = new SimpleStringProperty(state);
        this.countryId = new SimpleIntegerProperty(countryId);
        this.country = new SimpleStringProperty(country);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.customerPhone = new SimpleStringProperty(customerPhone);
    }
    
    public Customer(){
        this(null, null, null, null, null, null, 0, null);
        
    }
    
    public Customer(String customerName){
        this.customerName = new SimpleStringProperty(customerName);
    }
    
    public Customer(String customerName, String customerPhone){
        this.customerName = new SimpleStringProperty(customerName);
        this.customerPhone = new SimpleStringProperty(customerPhone);
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
    
    public String getCustomerName(){
        return customerName.get();
    }
    
    public void setCustomerName(String customerName){
        this.customerName = new SimpleStringProperty(customerName);
    }
    
    public StringProperty customerNameProperty(){
        return customerName;
    } 
    
    public String getCustomerPhone(){
        return customerPhone.get();
    }
    
    public void setCustomerPhone(String customerPhone){
        this.customerPhone = new SimpleStringProperty(customerPhone);
    }
    
    public StringProperty customerPhoneProperty(){
        return customerPhone;
    } 
    
     public String getAddress1(){
        return address1.get();
    }
    
    public void setAddress1(String address1){
        this.address1 = new SimpleStringProperty(address1);
    }
    
    public StringProperty address1Property(){
        return address1;
    } 
    
     public String getAddress2(){
        return address2.get();
    }
    
    public void setAddress2(String address2){
        this.address2 = new SimpleStringProperty(address2);
    }
    
    public StringProperty address2Property(){
        return address2;
    } 
    
     public String getCity(){
        return city.get();
    }
    
    public void setCity(String city){
        this.city = new SimpleStringProperty(city);
    }
    
    public StringProperty cityProperty(){
        return city;
    }
    
     public String getState(){
        return state.get();
    }
    
    public void setState(String state){
        this.state = new SimpleStringProperty(state);
    }
    
    public StringProperty stateProperty(){
        return state;
    } 
    
     public String getCountry(){
        return country.get();
    }
    
    public void setCountry(String country){
        this.country = new SimpleStringProperty(country);
    }
    
    public StringProperty countryProperty(){
        return country;
    }
    
     public int getZipCode(){
        return zipCode.get();
    }
    
    public void setZipCode(int zipCode){
        this.zipCode = new SimpleIntegerProperty(zipCode);
    }
    
    public IntegerProperty zipCodeProperty() {
        return zipCode;
    }
    
    public int getAddressId(){
        return addressId.get();
    }
    
    public void setAddressId(int addressId){
        this.addressId = new SimpleIntegerProperty(addressId);
    }
    
    public IntegerProperty addressIdProperty() {
        return addressId;
    }
    
    public int getCityId(){
        return cityId.get();
    }
    
    public void setCityId(int cityId){
        this.cityId = new SimpleIntegerProperty(cityId);
    }
    
    public IntegerProperty cityIdProperty() {
        return cityId;
    } 
    
    public int getCountryId(){
        return countryId.get();
    }
    
    public void setCountryId(int countryId){
        this.countryId = new SimpleIntegerProperty(countryId);
    }
    
    public IntegerProperty countryIdProperty() {
        return countryId;
    } 
    
}
