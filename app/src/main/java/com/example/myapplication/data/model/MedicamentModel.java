package com.example.myapplication.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




    public class MedicamentModel {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("date_debut")
        @Expose
        private String dateDebut;
        @SerializedName("date_fin")
        @Expose
        private String dateFin;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("duree")
        @Expose
        private Integer duree;
        @SerializedName("user")
        @Expose
        private String user;
        @SerializedName("programme")
        @Expose
        private String programme;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getDuree() {
            return duree;
        }

        public void setDuree(Integer duree) {
            this.duree = duree;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getProgramme() {
            return programme;
        }

        public void setProgramme(String programme) {
            this.programme = programme;
        }

        public MedicamentModel(String id, String dateDebut, String dateFin, String name, Integer duree, String user, String programme) {
            this.id = id;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.name = name;
            this.duree = duree;
            this.user = user;
            this.programme = programme;
        }
        public MedicamentModel( String dateDebut, String dateFin, String name, Integer duree, String user, String programme) {
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.name = name;
            this.duree = duree;
            this.user = user;
            this.programme = programme;
        }
    }


