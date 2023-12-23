package UtilityModule;
//A Measuremnet class to store measurements of customer
//Written by Jyotiradtiya Mourya
public class Measurement {
    private double lengeth;
    private String Mid;
    private String Name;
    private boolean isnew=false;
    private double  lenegth, 	Waist, 	Chest,	wrist,	Arm_length,	Shoulder;
    private Measurement mnext;
    
    public void setIsnew(boolean isnew) {
        this.isnew = isnew;
    }
    public boolean getIsnew(){
        return isnew;
    }
    public void setMnext(Measurement mnext) {
        this.mnext = mnext;
    }
    public Measurement getMnext() {
        return mnext;
    }
    public void setArm_length(double arm_length) {
        Arm_length = arm_length;
    }
    public void setChest(double chest) {
        Chest = chest;
    }public void setLenegth(double lenegth) {
        this.lenegth = lenegth;
    }public void setLengeth(double lengeth) {
        this.lengeth = lengeth;
    }public void setMid(String mid) {
        Mid = mid;
    }public void setName(String name) {
        Name = name;
    }public void setShoulder(double shoulder) {
        Shoulder = shoulder;
    }public void setWaist(double waist) {
        Waist = waist;
    }public void setWrist(double wrist) {
        this.wrist = wrist;
    }
    public double getArm_length() {
        return Arm_length;
    }public double getChest() {
        return Chest;
    }public double getLenegth() {
        return lenegth;
    }public double getLengeth() {
        return lengeth;
    }public String getMid() {
        return Mid;
    }public String getName() {
        return Name;
    }public double getShoulder() {
        return Shoulder;
    }public double getWaist() {
        return Waist;
    }public double getWrist() {
        return wrist;
    }
}
