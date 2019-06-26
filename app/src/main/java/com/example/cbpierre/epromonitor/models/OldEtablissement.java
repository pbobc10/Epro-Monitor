package com.example.cbpierre.epromonitor.models;

import com.google.gson.annotations.Expose;

public class OldEtablissement {
    @Expose
    private Integer contact_id;
    @Expose
    private Integer etab_id;

    public OldEtablissement() {
    }

    public OldEtablissement(Integer contact_id, Integer etab_id) {
        this.contact_id = contact_id;
        this.etab_id = etab_id;
    }

    public Integer getContact_id() {
        return contact_id;
    }

    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    public Integer getEtab_id() {
        return etab_id;
    }

    public void setEtab_id(Integer etab_id) {
        this.etab_id = etab_id;
    }
}
