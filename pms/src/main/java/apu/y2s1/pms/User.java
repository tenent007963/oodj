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
    protected static String filename;
    protected String userName;
    protected String password;
    protected String role;
    protected DataAbstract db;
    
    public User(String sid, String role) {
        this.userID = sid;
        this.role = role;
        System.out.println(role + ":" + this.role);
        switch(this.role.toLowerCase()) {
            case "student" -> setFilename("Students.txt");
            case "lecturer" -> setFilename("Lecturers.txt");
            case "admin" -> setFilename("Admins.txt");
            case "project manager" -> setFilename("ProjectManagers.txt");
            default -> System.out.println("Role empty. What is your role?");
        }
        db = new DataAbstract(User.filename);
        intID = db.getIndex(this.userID);
        getUserData();
    }

    // This object should be created upon successful login
    // seting default user for the sake of development
    public static User getInstance() {
        if (instance == null) {
            // using default user devid
            instance = new User("U999","Student");
            JOptionPane.showMessageDialog(null,"Hint: Default user devid (UID U999) is used. Make sure to remove this in final release");
       }
        return instance;
    }

    private void setFilename(String fn){
        System.out.println("Input"+fn);
        User.filename = fn;
        System.out.println("Final"+User.filename);
    }
    
    public final void getUserData(){
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
        return User.filename;
    }

    public boolean PwdCheck(String pwd) {
        return this.password.equals(pwd);
    }

}

