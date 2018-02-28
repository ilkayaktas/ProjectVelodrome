package com.ilkayaktas.projectname.model.app;

/**
 * Created by ilkay on 01/07/2017.
 */

public class Dummy {
    public String name;
    public String surname;

    public Dummy(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
