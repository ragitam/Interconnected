package com.app.interconnected.Adapter;

public class Kegiatan {

    String namaKegiatan, namaPj, namaOrg;

    public Kegiatan(){}

    public Kegiatan(String namaKegiatan, String namaPj, String namaOrg) {
        this.namaKegiatan = namaKegiatan;
        this.namaPj = namaPj;
        this.namaOrg = namaOrg;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getNamaPj() {
        return namaPj;
    }

    public void setNamaPj(String namaPj) {
        this.namaPj = namaPj;
    }

    public String getNamaOrg() {
        return namaOrg;
    }

    public void setNamaOrg(String namaOrg) {
        this.namaOrg = namaOrg;
    }
}
