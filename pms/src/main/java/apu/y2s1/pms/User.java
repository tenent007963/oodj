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
    private static User instance;
    protected final String userID;
    protected final int intID;
    protected static String filename;
    protected String userName;
    protected String password;
    protected String role;
    protected DataAbstract db;
    
    private User(String sid, String role) {
        this.userID = sid;
        this.role = role;
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
    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User("U999","Student");
            JOptionPane.showMessageDialog(null,"Hint: Default user devid (UID U999) is used. Make sure to remove this in final release");
        }
        return instance;
    }
    
    public static synchronized void setInstance(String sid, String role){
        if (instance == null) {
                instance = new User(sid, role);
        }
    }

    private void setFilename(String fn){
        User.filename = fn;
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

    static public class Lecturer extends apu.y2s1.pms.User {
        private boolean is_first_marker;
        private boolean is_second_marker;
        private boolean is_supervisor;
        private final String regex = "(?i)^(true|yes|1)$";

        public Lecturer(String ID) {
            super(ID, "Admin");
            getAddiData(ID);
        }

        public final void getAddiData(String ID){
            String[] rawdata = db.getRow(intID);
            setIsFirstMarker(rawdata[4]);
            setIsSecondMarker(rawdata[5]);
            setIsSupervisor(rawdata[6]);
        }

        public boolean getIsFirstMarker() {
            return is_first_marker;
        }

        public boolean getIsSecondMarker() {
            return is_second_marker;
        }

        public boolean getIsSupervisor() {
            return is_supervisor;
        }

        private void setIsFirstMarker(String lowFirst) {
            this.is_first_marker = lowFirst.matches(this.regex);
        }

        private void setIsSecondMarker(String lowSecond) {
            this.is_second_marker = lowSecond.matches(this.regex);
        }

        private void setIsSupervisor(String lowThird) {
            this.is_supervisor = lowThird.matches(this.regex);
        }

        public void removePassword(){
            super.password = null;
        }
    }
    
    static public class Student extends apu.y2s1.pms.User { /*inherit user class*/
        private static Student instance;
        private String intake_code;
        private String assessment_assigned;

        private Student(String ID) {
            super(ID, "student"); /*explicitly invoke the constructor of the superclass User*/
            getAddiData();
        }

        public final void getAddiData(){
            String[] rawdata = db.getRow(intID);
            this.intake_code = rawdata[4];
            this.assessment_assigned = rawdata[5];
        }

        public static Student getInstance(String ID) {
            if (instance == null) {
                return instance;
            }
            return instance;
        }

        public String getIntakeCode() {
            return intake_code;
        }

        public String getAssessmentAssigned() {
            return assessment_assigned;
        }

        public void removePassword(){
            this.password = null;
        }
    }

}

