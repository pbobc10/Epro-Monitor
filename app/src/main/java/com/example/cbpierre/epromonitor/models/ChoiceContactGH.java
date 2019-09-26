package com.example.cbpierre.epromonitor.models;

public class ChoiceContactGH {
    private String nom_ratio;
    private Integer con_id;
    private boolean isChecked;

    public ChoiceContactGH() {
    }

    public String getNom_ratio() {
        return nom_ratio;
    }

    public void setNom_ratio(String nom_ratio) {
        this.nom_ratio = nom_ratio;
    }

    public Integer getCon_id() {
        return con_id;
    }

    public void setCon_id(Integer con_id) {
        this.con_id = con_id;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
