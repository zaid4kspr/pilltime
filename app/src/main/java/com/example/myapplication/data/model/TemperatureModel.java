package com.example.myapplication.data.model;

public class TemperatureModel {

   private  Float degres;
    private  String date;
    private  String ref_p;
    private String user;
    private String note;


    public TemperatureModel(Float degres, String date, String ref_p, String user, String note) {
        this.degres = degres;
        this.date = date;
        this.ref_p = ref_p;
        this.user = user;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getDegres() {
        return degres;
    }

    public void setDegres(Float degres) {
        this.degres = degres;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRef_p() {
        return ref_p;
    }

    public void setRef_p(String ref_p) {
        this.ref_p = ref_p;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
