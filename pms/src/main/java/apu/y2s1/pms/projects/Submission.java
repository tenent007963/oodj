package apu.y2s1.pms.projects;

import apu.y2s1.pms.DataAbstract;

public class Submission {
    private int submissionID;
    private String submission_status;
    private String submission_result;
    private String submission_date;
    private String marking_feedback;
    private String submission_1st_marked;
    private String submission_2nd_marked;
    private String presentation_DateTime;
    private String presentation_slot_accepted;
    private String student_TP;

    // constructor for specified ID, will auto get data from file
    public Submission(int id) {
        this.submissionID = id;
        DataAbstract data = new DataAbstract("submissions.txt");
        String[] row = data.getRow(id);
        this.submission_status = row[1];
        this.submission_result = row[2];
        this.submission_date = row[3];
        this.marking_feedback = row[4];
        this.submission_1st_marked = row[5];
        this.submission_2nd_marked = row[6];
        this.presentation_DateTime = row[7];
        this.presentation_slot_accepted = row[8];
        this.student_TP = row[9];
    }

    // constructor for creating a new empty submission
    public Submission() {
        this.submissionID = 0;
        this.submission_status = "";
        this.submission_result = "";
        this.submission_date = "";
        this.marking_feedback = "";
        this.submission_1st_marked = "";
        this.submission_2nd_marked = "";
        this.presentation_DateTime = "";
        this.presentation_slot_accepted = "";
        this.student_TP = "";
    }
    
    // getting all the details for specific assessment
    public String[] viewDetails() {
        String[] details = new String[10];
        details[0] = Integer.toString(this.submissionID);
        details[1] = this.submission_status;
        details[2] = this.submission_result;
        details[3] = this.submission_date;
        details[4] = this.marking_feedback;
        details[5] = this.submission_1st_marked;
        details[6] = this.submission_2nd_marked;
        details[7] = this.presentation_DateTime;
        details[8] = this.presentation_slot_accepted;
        details[9] = this.student_TP;
        return details;
    }

    // getting only basic info of specific assessment for student
    public String[] viewInfo() {
        String[] basicInfo = new String[6];
        basicInfo[0] = Integer.toString(this.submissionID);
        basicInfo[1] = this.submission_status;
        basicInfo[2] = this.submission_result;
        basicInfo[3] = this.submission_date;
        basicInfo[4] = this.presentation_DateTime;
        basicInfo[5] = this.presentation_slot_accepted;
        return basicInfo;
    }

    // boring getters and setters section ==========
    public void setSubmissionID(int id) {
        this.submissionID = id;
    }

    public int getSubmissionID() {
        return this.submissionID;
    }

    public void setStatus(String status) {
        this.submission_status = status;
    }

    public String getStatus() {
        return this.submission_status;
    }

    public void setResult(String result) {
        this.submission_result = result;
    }

    public String getResult() {
        return this.submission_result;
    }

}
