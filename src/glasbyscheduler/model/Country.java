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
public class Country {
    
    protected StringProperty country;
    protected IntegerProperty countryId;
    
    public Country(int countryId, String country ){
        this.countryId = new SimpleIntegerProperty(countryId);
        this.country = new SimpleStringProperty(country);               
    }
    
    public Country(){
        this(0, null);        
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
    
    public String getCountry(){
        return country.get();
    }
    
    public void setCountry(String country){
        this.country = new SimpleStringProperty(country);
    }
    
    public StringProperty countryProperty(){
        return country;
    } 
    
}
