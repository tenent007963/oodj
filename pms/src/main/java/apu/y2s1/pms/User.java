/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu.y2s1.pms;

/**
 *
 * @author Jeslyn
 */
class User {
    private static User instance;
    private String userID;
    private String role;
    private String filename;
    private DataAbstract db;
    
    public User(String ID, String role) {
        this.role = role;
        switch(this.role) {
            case "student":
                this.filename = "Students.txt";
                break;
            case "lecturer":
                this.filename = "Lecturers.txt";
                break;
            case "admin":
                this.filename = "Admins.txt";
                break;
            case "pm":
                this.filename = "ProjectManagers.txt";
                break;
            default:
                break;
        }
        db = new DataAbstract(this.filename);
    }
    

    // This object should be created upon successful login
    // seting default user for the sake of development
    public static User getInstance() {
        if (instance == null) {
            // using default user devid
            instance = new User("U999","student");
       }
        return instance;
    }
    
    public void getUserData(String ID){
        int intID = db.getIndex(userID);
        String[] rawdata = db.getRow(intID);
        setUserID(rawdata[0]);
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }

}

class Lecturer extends User {
    private String is_first_marker;
    private String is_second_marker;
    private String is_supervisor;
    
    public Lecturer(String ID) {
        super(ID, "admin");
        getUserData(ID);
    }

    public static void main(String ID){
        Lecturer user = new Lecturer(ID);
    }

    @Override
    public void getUserData(String ID){
        int intID = db.getIndex(userID);
        String[] rawdata = db.getRow(intID);
        setUserID(rawdata[0]);
        setIsFirstMarker(rawdata[1]);
        setIsSecondMarker(rawdata[2]);
        setIsSupervisor(rawdata[3]);
    }

    public String getIsFirstMarker() {
        return is_first_marker;
    }

    public void setIsFirstMarker(String is_first_marker) {
        this.is_first_marker = is_first_marker;
    }

    public String getIsSecondMarker() {
        return is_second_marker;
    }

    public void setIsSecondMarker(String is_second_marker) {
        this.is_second_marker = is_second_marker;
    }

    public String getIsSupervisor() {
        return is_supervisor;
    }

    public void setIsSupervisor(String is_supervisor) {
        this.is_supervisor = is_supervisor;
    }
}

class Student extends User {
    private String intake_code;
    private String assessment_assigned;
    
    public Student(String ID) {
        super(ID, "student");
        getUserData(ID);
    }

    public static void main(String ID){
        Student user = new Student(ID);
    }

    @Override
    public void getUserData(String ID){
        int intID = db.getIndex(userID);
        String[] rawdata = db.getRow(intID);
        setUserID(rawdata[0]);
        setIntakeCode(rawdata[1]);
        setAssessmentAssigned(rawdata[2]);
    }

    public String getIntakeCode() {
        return intake_code;
    }

    public void setIntakeCode(String intake_code) {
        this.intake_code = intake_code;
    }

    public String getAssessmentAssigned() {
        return assessment_assigned;
    }

    public void setAssessmentAssigned(String assessment_assigned) {
        this.assessment_assigned = assessment_assigned;
    }
}
