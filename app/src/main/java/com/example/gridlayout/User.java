package com.example.gridlayout;

import android.widget.ImageView;

import java.util.List;

public class User {

    private int studentID;
    private String name;
    private String photo;
    private String affiliation;
    private List<Reservation> reservations;

    public User(int studentID, String name, String photo, String affiliation, List<Reservation> reservations) {
        this.studentID = studentID;
        this.name = name;
        this.photo = photo;
        this.affiliation = affiliation;
        this.reservations = reservations;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

}