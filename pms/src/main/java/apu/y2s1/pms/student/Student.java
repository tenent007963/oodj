package apu.y2s1.pms.student;
import apu.y2s1.pms.User;

public class Student extends User { /*inherit user class*/
    private String intake_code;
    private String assessment_assigned;
    
    public Student(String ID) {
        super(ID, "student"); /*inherit user class*/
        getAddiData();
    }

    public void getAddiData(){
        String[] rawdata = db.getRow(intID);
        this.intake_code = rawdata[4];
        this.assessment_assigned = rawdata[5];
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
