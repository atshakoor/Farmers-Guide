package com.example.raceme.farmersguide;

/**
 * Created by Raceme on 22/03/2017.
 */

public class Costs {

    private int esticost;
    private int actucost;
    private String estiPL;
    private String actuPL;

    private String year;
    private String crop;

    public Costs(){

    }




    public Costs(int esticost, int actucost, String estiPL, String actuPL){
        this.esticost= esticost;
        this.actucost=actucost;
        this.estiPL=estiPL;
        this.actuPL= actuPL;
    }

    public int getEsticost() {
        return esticost;
    }

    public void setEsticost(int esticost) {
        this.esticost = esticost;
    }

    public int getActucost() {
        return actucost;
    }

    public void setActucost(int actucost) {
        this.actucost = actucost;
    }

    public String getEstiPL() {
        return estiPL;
    }

    public void setEstiPL(String estiPL) {
        this.estiPL = estiPL;
    }

    public String getActuPL() {
        return actuPL;
    }

    public void setActuPL(String actuPL) {
        this.actuPL = actuPL;
    }


    public String getYear() {
        return year;
    }

    public String setYear(String year) {
        this.year = year;
        return null;
    }

    public String getCrop() {
        return crop;
    }

    public String setCrop(String crop) {
        this.crop = crop;
        return crop;
    }
}
