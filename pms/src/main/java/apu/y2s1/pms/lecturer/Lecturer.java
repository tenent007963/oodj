package apu.y2s1.pms.lecturer;
import apu.y2s1.pms.User;

public class Lecturer extends User {
    private boolean is_first_marker;
    private boolean is_second_marker;
    private boolean is_supervisor;
    private final String regex = "(?i)^(true|yes|1)$";
    
    public Lecturer(String ID) {
        super(ID, "Admin");
        getAddiData(ID);
    }

    public void getAddiData(String ID){
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
