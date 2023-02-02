package com.example.android.samrudhisolar.model;

public class Dealers {

    String userFirstName;
    String userLastName;
    String userAddress;
    String userPinCode;
    String userMobileNo;
    String userEmail;
    String userPass;
    String userConPass;
    String userValidationCode;

    public Dealers(){

    }

    public Dealers(String firstName, String lastName, String address, String pinCode, String mobileNo, String email, String pass,
                   String conPass, String validationCode){
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.userAddress = address;
        this.userPinCode = pinCode;
        this.userMobileNo = mobileNo;
        this.userEmail = email;
        this.userPass = pass;
        this.userConPass = conPass;
        this.userValidationCode = validationCode;
    }

//    public Dealers(String userFirstName, String userLastName) {
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
//    }
//
//    public Dealers(String userAddress, String userPinCode) {
//        this.userAddress = userAddress;
//        this.userPinCode = userPinCode;
//    }
//
//    public Dealers(String userMobileNo, String userEmail) {
//        this.userMobileNo = userMobileNo;
//        this.userEmail = userEmail;
//    }
//
//    public Dealers(String userPass, String userConPass) {
//        this.userPass = userPass;
//        this.userConPass = userConPass;
//    }
//
//    public Dealers(String userValidationCode) {
//        this.userValidationCode = userValidationCode;
//    }


    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPinCode() {
        return userPinCode;
    }

    public void setUserPinCode(String userPinCode) {
        this.userPinCode = userPinCode;
    }

    public String getUserMobileNo() {
        return userMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserConPass() {
        return userConPass;
    }

    public void setUserConPass(String userConPass) {
        this.userConPass = userConPass;
    }

    public String getUserValidationCode() {
        return userValidationCode;
    }

    public void setUserValidationCode(String userValidationCode) {
        this.userValidationCode = userValidationCode;
    }
}
