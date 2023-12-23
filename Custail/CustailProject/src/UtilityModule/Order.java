package UtilityModule;
//A order class whic will stor data of order it is extend class of customer


//written by ruddarm mourya

public class Order extends Customer {
    private String orderid;
    private String order_date; // To store order date
    private int order_qty=0; // to store number of product in one order
    private double order_value; // To store total amount of each order;
    private double order_paid_amt; // it will sotre advance paid by customer at a time of placing order
    private String order_Status;
    private String order_dev_Date; // it will store deilvry date of a order
    private Product Head_product, Temp_product;
    private boolean order_valid=false, Pro_valid=false; // indciate starus wehter order is vaild or not
    private Order custOrderHead, OredtTemp;    // a get method to get orderid
    public String getOrderid() {
        return orderid;
    }

    public void setPro_valid(boolean pro_valid) {
        Pro_valid = pro_valid;
    }
    public boolean getPro_valid(){
        return Pro_valid;
    }

    public void setTemp_product(Product temp_product) {
        Temp_product = temp_product;
    }

    public Product getTemp_product() {
        return Temp_product;
    }

    // a get method to get order date
    public String getOrder_date() {
        return order_date;
    }

    // a get method to get order qty
    public int getOrder_qty() {
        return order_qty;
    }

    // method to get order value
    public double getOrder_value() {
        return order_value;
    }

    // method to get paid amoutn
    public double getOrder_paid_amt() {
        return order_paid_amt;
    }

    // method to get order staus
    public String getOrder_dev_Date() {
        return order_dev_Date;
    }

    // method to get order staus
    public String getordstatus() {
        return order_Status;
    }

    // method to set orderid
    public void setOrderid(String order_id) {
        // System.out.println("order d aregu" +order_id);
        this.orderid = order_id;
    }

    public void setOrder_value(double order_value) {

        this.order_value = order_value;
    }

    public void setOrder_Status(String order_Status) {
        this.order_Status = order_Status;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_paid_amt(double order_paid_amt) {
        this.order_paid_amt = order_paid_amt;
    }

    public void setOrder_dev_Date(String order_dev_Date) {
        this.order_dev_Date = order_dev_Date;
    }

    public void setOrder_qty(int order_qty) {
        this.order_qty = order_qty;
    }

    public void set_order_valid(Boolean valid) {
        this.order_valid = valid;
    }

    public boolean is_ordeer_valid() {
        return this.order_valid;
    }

    public Product getheadproduct() {
        return this.Head_product;
    }
    public Order getCustOrderHead() {
        return custOrderHead;
    }
    public void setCustOrderHead(Order custOrderHead) {
        this.custOrderHead = custOrderHead;
    }
    public Order getOredtTemp() {
        return OredtTemp;
    }
    public void setOredtTemp(Order oredtTemp) {
        OredtTemp = oredtTemp;
    }
    // A method to set order value
    public boolean prepare_order(String Order_id, String order_date) {
        if (isvalid()) {
            setOrderid(Order_id);
            setOrder_date(order_date);
            setOrder_qty(0);
            setOrder_value(0);
            setOrder_paid_amt(0);
            setOrder_Status("PENDING");
            setOrder_dev_Date("order_dev_Date");
            set_order_valid(true);
            return true;
        } else {
            set_order_valid(false);
            return false;
        }
    }
    // A method to setp prdutoc implemt using linked list logic
    public void setproduct(String pro_id, String pro_type, double clothc, double swingc, double tcost, String mid) {
        if (is_ordeer_valid()) {
            double totalvalue;
            if (getOrder_qty() < 1) {
                // this.Head_product
                Product nodeone = new Product();
                nodeone.preapre_product(pro_id, pro_type, clothc, swingc, tcost,mid);
                nodeone.setPrnext(null);
                this.Head_product = nodeone;
                // System.out.println("Node in is "+nodeone);
                // System.out.println("head node is "+getheadproduct());
                Temp_product = Head_product;
                setOrder_qty(1);
                totalvalue=getOrder_value()+swingc+clothc;
                setOrder_value(totalvalue);
                setPro_valid(true);
            } else {
                Product nextNode = new Product();
                nextNode.preapre_product(pro_id, pro_type, clothc, swingc, tcost, mid);
                Temp_product.setPrnext(nextNode);
                nextNode.setPrnext(null);
                Temp_product = nextNode;
                int qty;
                double val;
                qty = getOrder_qty();
                val = getOrder_value();
                qty++;
                setOrder_qty(qty);
                val = getOrder_value()+swingc + clothc;
                setOrder_value(val);
            }
        }
    }
    //order _restter 
    public void order_reset(){
        prepare_order(null, null);
    }
    //product restter()
    public void pro_rest(){

        this.Head_product=this.Temp_product=null;
        setOrder_qty(0);
        setOrder_value(0);
        setOrder_paid_amt(0);
        set_order_valid(false);
        
    }
    
}
