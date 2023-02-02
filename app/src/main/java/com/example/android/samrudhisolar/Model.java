package com.example.android.samrudhisolar;

public class Model {
    private String namecu;
    private String emailcu;
    private String dealerid,dealername,solarcapacity,systemsearialno,dateofinstall;

    public String getDealerid() {
        return dealerid;
    }

    public void setDealerid(String dealerid) {
        this.dealerid = dealerid;
    }

    public String getDealername() {
        return dealername;
    }

    public void setDealername(String dealername) {
        this.dealername = dealername;
    }

    public String getSolarcapacity() {
        return solarcapacity;
    }

    public void setSolarcapacity(String solarcapacity) {
        this.solarcapacity = solarcapacity;
    }

    public String getSystemsearialno() {
        return systemsearialno;
    }

    public void setSystemsearialno(String systemsearialno) {
        this.systemsearialno = systemsearialno;
    }

    public String getDateofinstall() {
        return dateofinstall;
    }

    public void setDateofinstall(String dateofinstall) {
        this.dateofinstall = dateofinstall;
    }

    public Model(String dealerid, String dealername, String namecu, String emailcu, String addresscu, String phonecu, String imagecu, String solarcapacity, String systemserialno, String dateofinstall) {
        this.namecu=namecu;
        this.emailcu=emailcu;
        this.addresscu=addresscu;
        this.phonecu=phonecu;
        this.imagecu=imagecu;
        this.dealerid=dealerid;
        this.dealername=dealername;
        this.solarcapacity=solarcapacity;
        this.systemsearialno=systemserialno;
        this.dateofinstall=dateofinstall;
    }

    public String getNamecu() {
        return namecu;
    }

    public void setNamecu(String namecu) {
        this.namecu = namecu;
    }

    public String getEmailcu() {
        return emailcu;
    }

    public void setEmailcu(String emailcu) {
        this.emailcu = emailcu;
    }

    public String getAddresscu() {
        return addresscu;
    }

    public void setAddresscu(String addresscu) {
        this.addresscu = addresscu;
    }

    public String getPhonecu() {
        return phonecu;
    }

    public void setPhonecu(String phonecu) {
        this.phonecu = phonecu;
    }

    public String getImagecu() {
        return imagecu;
    }

    public void setImagecu(String imagecu) {
        this.imagecu = imagecu;
    }

    private String addresscu;
    private String phonecu;
    private String imagecu;

}
