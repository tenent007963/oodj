package apu.y2s1.pms;
import apu.y2s1.pms.DataAbstract;

public class Assessment {
    private int assessmentID;
    private String assessment_name;
    private String assessment_type;
    private String[] assigned_second_markers;
    private String[] assigned_first_markers;
    private String[] assigned_supervisors;

    // constructor for specified ID, will auto get data from file
    public Assessment(int id) {
        this.assessmentID = id;
        DataAbstract data = new DataAbstract("assessments.txt");
        String[] row = data.getRow(id);
        this.assessment_name = row[1];
        this.assessment_type = row[2];
        this.assigned_second_markers = row[3].split(",");
        this.assigned_first_markers = row[4].split(",");
        this.assigned_supervisors = row[5].split(",");
    }

    // empty argument? no worries, it'll be fresh
    public Assessment() {
        this.assessmentID = 0;
        this.assessment_name = "";
        this.assessment_type = "";
        this.assigned_second_markers = new String[0];
        this.assigned_first_markers = new String[0];
        this.assigned_supervisors = new String[0];
    }

    // display supervisor
    public String[] getSupervisors() {
        return this.assigned_supervisors;
    }

    // diplay supervisee (are u sure this should be here?)
    public String[] getSupervisees() {
        return this.assigned_first_markers;
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
