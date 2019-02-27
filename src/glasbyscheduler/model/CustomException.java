/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.model;

/**
 *
 * @author aglasby
 */

public class CustomException {
    
    public static class NameMissingException extends Exception{
        NameMissingException(String name){
            super(name);
        }
    }    
    
    public static class AddressMissingException extends Exception{
        AddressMissingException(String address1){
           super(address1);
       }
    }
    public static class Address2MissingException extends Exception{
        
        Address2MissingException(String address2){
            super(address2);
        }
    
    }
    
    public static class CountryMissingException extends Exception{        
        CountryMissingException(String country){
            super(country);
        }    
    }
    
    public static class CityMissingException extends Exception{        
        CityMissingException(String city){
            super(city);
        }    
    }
    
    public static class ZipCodeException extends Exception{        
        ZipCodeException(String zipcode){
            super(zipcode);
        }    
    }
    
    public static class PhoneNumberException extends Exception{        
        PhoneNumberException(String phoneNumber){
            super(phoneNumber);
        }    
    }
    
    
    
    
    
    public static void validateName(String name) throws NameMissingException{
        if(name == null){
            throw new NameMissingException("Please enter a name for the customer");
        }
    }
    
    public static void validateAddress(String address1) throws AddressMissingException{
        if(address1 == null){
            throw new AddressMissingException("Please enter an address for the customer");
        }
    }
    
    public static void validateAddress2(String address2) throws Address2MissingException{
        if(address2 == null){
            throw new Address2MissingException("Please enter NA if there is nothing for address2");
        }
    }
    
    public static void validateCountry(String country) throws CountryMissingException{
        if(country == null){
            throw new CountryMissingException("Please select a Country");
        }
    }
    
    public static void validateCity(String city) throws CityMissingException{
        if(city == null){
            throw new CityMissingException("Please select a City");
        }
    }
    
    public static void validateZipcode(String zipcode) throws ZipCodeException{
        if(zipcode == null || zipcode.equals("0")){
            throw new ZipCodeException("Please enter a number greater than 0 for the zip code");
        }else{
            try{
                Integer.parseInt(zipcode);
            }catch(NumberFormatException e){
                throw new ZipCodeException("Please enter a number for the zip code");
            }
        }        
    }
    
        public static void validatePhoneNumber(String phoneNumber) throws PhoneNumberException{
        if(phoneNumber == null){
            throw new PhoneNumberException("Please enter a phone number for the customer.");
        }
    }
    
}
