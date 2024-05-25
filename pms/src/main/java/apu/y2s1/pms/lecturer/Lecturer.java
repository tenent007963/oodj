package apu.y2s1.pms.lecturer;
import apu.y2s1.pms.User;
import apu.y2s1.pms.DataAbstract;

public class Lecturer extends User {
    private static Lecturer instance;
    private boolean is_first_marker;
    private boolean is_second_marker;
    private boolean is_supervisor;
    private String full_name;
    //private final String regex = "(?i)^(true|yes|1)$";
    
    public Lecturer(String ID) {
        super(ID, "Lecturer");
        getAddiData(ID);
    }

    public final void getAddiData(String ID){
        this.full_name = db.getRow(this.intID)[1];
        DataAbstract db1 = new DataAbstract("Assessments.txt");
        setIsFirstMarker(db1.getCol(5));
        setIsSecondMarker(db1.getCol(4));
        setIsSupervisor(db1.getCol(3));
        removePassword();
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

    // used only after login to get the only instance
    public static Lecturer getInstance(){
        if (instance == null){
            return new Lecturer("T001");
        }
        return instance;
    }


    private void setIsFirstMarker(String[] rawFirst) {
        for (String s : rawFirst) {
            if (s.toLowerCase().matches(this.full_name.toLowerCase())) {
                this.is_first_marker = true;
                return;
            }
        }
    }

    private void setIsSecondMarker(String[] rawSecond) {
        for (String s : rawSecond) {
            if (s.toLowerCase().matches(this.full_name.toLowerCase())) {
                this.is_second_marker = true;
                return;
            }
        }
    }

    private void setIsSupervisor(String[] rawThird) {
        for (String s : rawThird) {
            if (s.toLowerCase().matches(this.full_name.toLowerCase())) {
                this.is_supervisor = true;
                return;
            }
        }
    }
    
    public void removePassword(){
        super.password = null;
    }
}
