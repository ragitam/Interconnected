package com.app.interconnected.Adapter;

public class Kegiatan {

    String namaKegiatan, namaPj;

    public Kegiatan(){}

    public Kegiatan(String namaKegiatan, String namaPj) {
        this.namaKegiatan = namaKegiatan;
        this.namaPj = namaPj;
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
}
