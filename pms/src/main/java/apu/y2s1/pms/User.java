/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu.y2s1.pms;

import javax.swing.JOptionPane;

/**
 *
 * @author Jeslyn
 */
public class User {
    protected static User instance;
    protected final String userID;
    protected final int intID;
    protected String userName;
    protected String password;
    protected String role;
    protected String filename;
    protected DataAbstract db;
    
    public User(String sid, String role) {
        this.userID = sid;
        this.role = role;
        switch(this.role) {
            case "Student":
                this.filename = "Students.txt";
                break;
            case "Lecturer":
                this.filename = "Lecturers.txt";
                break;
            case "Admin":
                this.filename = "Admins.txt";
                break;
            case "Project Manager":
                this.filename = "ProjectManagers.txt";
                break;
            default:
                break;
        }
        db = new DataAbstract(this.filename);
        intID = db.getIndex(this.userID);
        getUserData();
    }

    // This object should be created upon successful login
    // seting default user for the sake of development
    public static User getInstance() {
        if (instance == null) {
            // using default user devid
            instance = new User("U999","student");
            JOptionPane.showMessageDialog(null,"Hint: Default user devid (UID U999) is used. Make sure to remove this in final release");
       }
        return instance;
    }
    
    public void getUserData(){
        String[] rawdata = db.getRow(this.intID);
        if(this.userID.equalsIgnoreCase(rawdata[0])){
            this.userName = rawdata[1];
            this.password = rawdata[2];
        }
    }
    
    public String getUserID() {
        return userID;
    }

    public String getRole() {
        return this.role;
    }

    public String getDBFile() {
        return this.filename;
    
    }

    public boolean PwdCheck(String pwd) {
        if (this.password.equals(pwd)) {
            return true;
        } else {
            return false;
        }
    }

}

