package DatabaseMoudle;


public class databaseutility {
    private String Cust_id,Order_id,Measure_id,Prod_id; //Get existing id
    private int  Last_cust_id, Last_order_id, Last_Measure_id, Last_pro_id; // variable to store last id utlity
    public String getCust_id() {
        return Cust_id;
    }
    public int getLast_Measure_id() {
        return Last_Measure_id;
    }
    public int getLast_cust_id() {
        return Last_cust_id;
    } 
    public int getLast_order_id() {

        return Last_order_id;
    }
    public int getLast_pro_id() {
        return Last_pro_id;
    } 
    public String getMeasure_id() {
        return Measure_id;
    } 
    public String getOrder_id() {
        return Order_id;
    } 
    public String getProd_id() {
        return Prod_id;
    }
    public void setCust_id(String cust_id) {
        Cust_id = cust_id;
    }
    public void setLast_Measure_id(int last_Measure_id) {
        Last_Measure_id = last_Measure_id;
    }public void setLast_cust_id(int last_cust_id) {
        Last_cust_id = last_cust_id;
    }public void setLast_order_id(int last_order_id) {
        this.Last_order_id = last_order_id;
    }public void setLast_pro_id(int last_pro_id) {
        Last_pro_id = last_pro_id;
    }public void setMeasure_id(String measure_id) {
        Measure_id = measure_id;
    }public void setOrder_id(String order_id) {
        Order_id = order_id;
    }public void setProd_id(String prod_id) {
        Prod_id = prod_id;
    }
    // public void  updateutility(){
    //     int cid=getLast_cust_id();
    //     int oid =getLast_order_id();
    //     cid++;oid++;

    // }
}
