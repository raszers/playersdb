package com.sport.entities;

public class Coach extends Person{
    private String coachStyle;

    public String getCoachStyle() {
        return coachStyle;
    }

    public void setCoachStyle(String coachStyle) {
        this.coachStyle = coachStyle;
    }

    @Override
    public String toString(){
        return getFirstName() + " " + getLastName();
    }
}
