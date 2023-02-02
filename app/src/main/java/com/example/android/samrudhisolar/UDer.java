package com.example.android.samrudhisolar;

public class UDer {
    String mail,pass,cnf_pass;

    public UDer(String mail, String pass, String cnf_pass) {
        this.mail=mail;
        this.pass=pass;
        this.cnf_pass=cnf_pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCnf_pass() {
        return cnf_pass;
    }

    public void setCnf_pass(String cnf_pass) {
        this.cnf_pass = cnf_pass;
    }
}
