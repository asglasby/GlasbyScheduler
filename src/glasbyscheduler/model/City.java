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
public class City {
    protected IntegerProperty cityId;
    protected StringProperty city;
    protected IntegerProperty countryId;

    public City(int cityId, String city, int countryId){
        this.cityId = new SimpleIntegerProperty(cityId);
        this.city = new SimpleStringProperty(city);
        this.countryId = new SimpleIntegerProperty(countryId);        
    }
    
    public City(){
        this(0, null, 0);        
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
    
    public String getCity(){
        return city.get();
    }
    
    public void setCity(String city){
        this.city = new SimpleStringProperty(city);
    }
    
    public StringProperty cityProperty(){
        return city;
    }
    
    
    
}
