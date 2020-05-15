package com.example.myapplication.data.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgrammeModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("date_debut")
    @Expose
    private String dateDebut;
    @SerializedName("date_fin")
    @Expose
    private String dateFin;
    @SerializedName("duree")
    @Expose
    private Integer duree;
    @SerializedName("maladie")
    @Expose
    private String maladie;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    private String user;


    public ProgrammeModel(String dateDebut, String dateFin, Integer duree, String maladie,String user) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.maladie = maladie;
        this.user = user ;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}