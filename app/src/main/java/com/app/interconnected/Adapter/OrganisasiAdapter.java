package com.app.interconnected.Adapter;


public class OrganisasiAdapter {
    private String username, emailUser, fullnameUser, orgName;

    public OrganisasiAdapter() {
    }

    public OrganisasiAdapter(String username, String emailUser, String fullnameUser, String orgName) {
        this.username = username;
        this.emailUser = emailUser;
        this.fullnameUser = fullnameUser;
        this.orgName = orgName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getFullnameUser() {
        return fullnameUser;
    }

    public void setFullnameUser(String fullnameUser) {
        this.fullnameUser = fullnameUser;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
