package com.example.myapplication.data.model;

import java.util.ArrayList;

public class AddPriseAndUpdateProgramModel {


    private ProgrammeModel program;
    private ArrayList<prisePostModel> prises;

    public AddPriseAndUpdateProgramModel(ProgrammeModel program, ArrayList<prisePostModel> prises) {
        this.program = program;
        this.prises = prises;
    }

    public ProgrammeModel getProgram() {
        return program;
    }

    public void setProgram(ProgrammeModel program) {
        this.program = program;
    }

    public ArrayList<prisePostModel> getPrises() {
        return prises;
    }

    public void setPrises(ArrayList<prisePostModel> prises) {
        this.prises = prises;
    }
}
