package apu.y2s1.pms.projects;
import apu.y2s1.pms.DataAbstract;
import apu.y2s1.pms.DataAbstract;

public class Assessment {
    private int assessmentID;
    private String assessment_name;
    private String assessment_type;
    private String[] assigned_first_markers;
    private String[] assigned_second_markers;
    private String[] assigned_supervisors;
    private String date_created;

    // constructor for specified ID, will auto get data from file
    public Assessment(int id) {
        this.assessmentID = id;
        DataAbstract data = new DataAbstract("assessments.txt");
        String[] row = data.getRow(id);
        this.assessment_name = row[1];
        this.assessment_type = row[2];
        this.assigned_first_markers = row[3].split(",");
        this.assigned_second_markers = row[4].split(",");
        this.assigned_supervisors = row[5].split(",");
        this.date_created = row[6];
    }

    // empty argument? no worries, it'll be fresh entry
    public Assessment() {
        this.assessmentID = 0;
        this.assessment_name = "";
        this.assessment_type = "";
        this.assigned_first_markers = new String[0];
        this.assigned_second_markers = new String[0];
        this.assigned_supervisors = new String[0];
        this.date_created = "Not available";
    }

    // display supervisor
    public String[] displaySupervisors() {
        return this.assigned_supervisors;
    }

    // diplay supervisee (are u sure this should be here?)
    public String[] displaySupervisees() {
        //return Student.getStudentsBySupervisor(this.assessmentID);
        return this.assigned_first_markers; //placeholder, do NOT use this as final
    }

    // the boring getters and setters section ===========
    public void setAssessmentID(int id) {
        this.assessmentID = id;
    }

    public int getAssessmentID() {
        return this.assessmentID;
    }

    public void setAssessmentName(String name) {
        this.assessment_name = name;
    }

    public String getAssessmentName()
    {
        return this.assessment_name;
    }

}
