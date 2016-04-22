/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

public class User implements Serializable{

    public User() {
        userID=0;
        firstName="";
        lastName="";
        email="";
        addressOneField="";
        addressTwoField="";
        city="";
        state="";
        postCode="";
        country="";
        password="";
    }

public int userID;    
private String firstName;
private String lastName;
private String email;
private String address;
private String addressOneField;
private String addressTwoField;
private String city;
private String state;
private String postCode;
private String country;
private String password;
private String role;


	public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressOneField() {
        return addressOneField;
    }

    public String getAddressTwoField() {
        return addressTwoField;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }
    
    public String getPassword() {
        return password;
    }
        
    public String getRole() {
      	return role;
    }

    
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressOneField(String addressOneField) {
        this.addressOneField = addressOneField;
    }

    public void setAddressTwoField(String addressTwoField) {
        this.addressTwoField = addressTwoField;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
}
