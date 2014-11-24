package model;

import java.util.ArrayList;

public class Employee {
    private String Designation, Name;
    private int Phone_Number;
    private String file_no;

    public Employee() {
    }

    public Employee(String Name, String Designation, int Phone_Number, String file_no)
    {
        this.Name = Name;
        this.Designation = Designation;
        this.Phone_Number = Phone_Number;
        this.file_no = file_no;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        this.Designation = designation;
    }

    public int getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(int number) {
        this.Phone_Number = number;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String nof) {
        this.file_no = nof;
    }


}