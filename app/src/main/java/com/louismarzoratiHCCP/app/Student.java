package com.louismarzoratiHCCP.app;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**********************************************************************
 * The following class instantiates a student and the data that
 * makes up a student
 *
 * @author Louis Marzorati
 * @author Daniel Kelch

 * @version 12/04/13
 **********************************************************************/

public class Student implements Comparable<Student>, Serializable  {

    /**This represents the name of the student**/
    private String name;

    /**This represents the G number of student**/
    private String unit;

    /**This represents the GPA of student**/
    private String pass;

    private String email;

    private Calendar tDate;

    private String complex;

    private String passType;

    /******************************************************************
     * This is the student constructor, and checks for valid input for
     * the different parameters.
     *
     * @param name the name of the student
     * @param //gNumber the Gnumber of the student
     * @param //gpa the GPA of a student
     *****************************************************************/
    public Student(String name, String unit, String email, String pass, Calendar tDate,
                   String complex, String passType) {

        super();
        this.name = name;
        this.unit = unit;
        this.pass = pass;
        this.email = email;
        this.tDate = tDate;
        this.complex = complex;
        this.passType = passType;
    }

    public String getComplex(){return complex;}

    public void setComplex(String complex){ this.complex=complex; }

    public void setPassType(String passType){this.passType=passType;}

    public String getPassType(){return passType;}

    public Calendar getDate(){ return tDate; }

    public void Calendar(Calendar tDate){ this.tDate = tDate; }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    /******************************************************************
     * Method that returns the student name
     * @return name of the student
     *****************************************************************/
    public String getName() {
        return name;
    }

    /******************************************************************
     * Method that sets the student name
     * @param name The name of the student
     *****************************************************************/
    public void setName(String name) {
        this.name = name;
    }

    /******************************************************************
     * Method that returns the student gNumber
     * @return The GNumber of the student
     *****************************************************************/
    public String getUnit() {
        return unit;
    }

    /******************************************************************
     * Method that sets the student G number
     * @param //the GNumber of the student
     *****************************************************************/
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /******************************************************************
     * Method that returns the students GPA
     * @return the GPA of the student
     *****************************************************************/
    public String getPass() {
        return pass;
    }

    /******************************************************************
     * Method /that sets the students GPA
     * @param //the GPA of the student
     *****************************************************************/
    public void setPass(String pass) {
        this.pass = pass;
    }

    /******************************************************************
     * Method that returns a string of all the data for a student
     * @return A formatted string of the student
     *****************************************************************/
    public String toString(){
        return name + " " + unit + " " + pass + " ";
    }

    /******************************************************************
     *  Method that returns 1 if its the same student, 0 if not
     * @return Will return 1 if its the same student, 0 if not
     *****************************************************************/
    public int compareTo(Student data) {
        if(pass == data.pass && unit == data.unit && name == data.name)
            return 1;
        else
            return 0;
    }
}

