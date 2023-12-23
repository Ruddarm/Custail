package UtilityModule;

import java.sql.ResultSet;
import java.sql.SQLException;


// This fill include a class for data of custmer.
//Written by ruddarm Mourya On 1st Dec 2023.
// 
public class Customer {
    private String cid; //to store custmoer id
    private String Name; //to store name
    private String Number; // to store number of customer
    private String Selected_measure_Id;
    private boolean Valid =false,isMeasureUpdate =false,isnewcust=false;//it will indicate stus
    private double Creditamt;
    private double debitamt;
    private Measurement cust_measurement=null;
    private Measurement Temp_measurement;
    public void setIsnewcust(boolean isnewcust) {
        this.isnewcust = isnewcust;
    }
    public boolean getisnewcut(){
        return isnewcust;
    }
    public void set_Measure_update(boolean isMeasureUpdate) {
        this.isMeasureUpdate = isMeasureUpdate;
    }
    public  boolean get_Is_Measure_update(){
        return  isMeasureUpdate;
    }
    public void setCid(String cid) { 
        //a method to set customer id
        this.cid = cid;
    }
    public String getSelected_measure_Id() {
        return Selected_measure_Id;
    }
    public void setSelected_measure_Id(String selected_measure_Id) {
        Selected_measure_Id = selected_measure_Id;
    }
    public void setTemp_measurement(Measurement temp_measurement) {
        Temp_measurement = temp_measurement;
    }
    public void setCust_measurement(Measurement cust_measurement) {
        this.cust_measurement = cust_measurement;
    }
    public Measurement getCust_measurement() {
        return cust_measurement;
    }
    public Measurement getTemp_measurement() {
        return Temp_measurement;
    }
    // a method to se name
    public void setName(String name) {
        Name = name;
    }
    // a method to set number
    public void setNumber(String number) {
        Number = number;
    }
    // a method to set staus
    public void setvalid(boolean Valid) {
        this.Valid = Valid;
    }
    // a set method for credit amount
    public void setCreditamt(double creditamt) {
        Creditamt = creditamt;
    }
    //a set method for debit amount
    public void setDebitamt(double debitamt) {
        this.debitamt = debitamt;
    }
    //now get method
    public String getCid() {
        System.out.println(cid);
        return cid;
    }
    //a get method for name
    public String getName() {
        return Name;
    }
    // a get method for number
    public String getNumber() {
        return Number;
    }
    // a get method for staus
    public boolean isvalid(){
        return Valid;
    }
    // a get mthod for credit amoutn
    public double getCreditamt() {
        return Creditamt;
    }
    // a get method for debit amount
    public double getDebitamt() {
        return debitamt;
    }
    public void setcustdata( String Cust_name, String Cust_number){
        // setCid(cust_id);
        setName(Cust_name);
        setNumber(Cust_number);
    }
    public void cust_rest(){
        setcustdata( null, null);
        setCreditamt(0);
        setDebitamt(0);
        setvalid(false);
        Temp_measurement = cust_measurement=null;
        

    }
    public void Set_measurement(ResultSet rs){
            if(cust_measurement==null){
                Measurement node_one = new Measurement();
                try{
                                    node_one.setMid(rs.getString(1));
                                    node_one.setName(rs.getString(3));
                                    //length waist chest
                                    node_one.setLenegth(rs.getDouble(4));
                                    node_one.setWaist(rs.getDouble(5));
                                    node_one.setChest(rs.getDouble(6));
                                    //wrist armlength shoulder
                                    node_one.setLenegth(rs.getDouble(7));
                                    node_one.setArm_length(rs.getDouble(8));
                                    node_one.setShoulder(rs.getDouble(9));
                                    node_one.setMnext(null);
                                    cust_measurement=node_one;
                                    Temp_measurement=cust_measurement;

                }catch(SQLException set_me){
                    System.out.println("error in set_measurement "+set_me);
                }
            }else{
                Measurement nextnode = new Measurement();
                try{
                                    nextnode.setMid(rs.getString(1));
                                    nextnode.setName(rs.getString(3));
                                    // nextnode waist chest
                                    nextnode.setLenegth(rs.getDouble(4));
                                    nextnode.setWaist(rs.getDouble(5));
                                    nextnode.setChest(rs.getDouble(6));
                                    // nextnodearmlength shoulder
                                    nextnode.setLenegth(rs.getDouble(7));
                                    nextnode.setArm_length(rs.getDouble(8));
                                    nextnode.setShoulder(rs.getDouble(9));
                                    nextnode.setMnext(null);
                                    Temp_measurement.setMnext(nextnode);
                                    Temp_measurement=nextnode;    

                }catch(SQLException set_me){
                    System.out.println("error in set_measurement "+set_me);
                }
            }
    }
    
}

