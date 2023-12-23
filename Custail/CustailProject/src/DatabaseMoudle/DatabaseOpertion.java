// import UtilityModule.Order;
//This doucmetn will containn all method which are required to store and retrive data from database 
// Written by Jyotiradity Mourya aka Ruddarm Mourya
package DatabaseMoudle;

import java.sql.*;

// import CustailComponent.tryframe;
import CustailDriver.CustailApp;
import PaymentMoudle.PayCustomer;
import UtilityModule.Measurement;
import UtilityModule.Product;

public class DatabaseOpertion {
    CustailApp capp; // A custiapp object to access database conneciton and customer information
    public databaseutility DButility; // This will keep track of key data in database;
    PreparedStatement preparedquerry;
    Statement querry;

    public void setcapp(CustailApp cApp) {
        this.capp = cApp;
        DButility = new databaseutility();
        try {
            querry = capp.DBCon.createStatement();
        } catch (SQLException querry_ex) {
            System.out.println("Exception in querry creation " + querry_ex);
        }
        this.Data_base_utility();

    }

    // A final Method whic will use to place order in database;
    public boolean palceorder() {
        // boolean update_measure =meausre;
        boolean key = true;
        try {
            if (this.capp.customer.getisnewcut()) {
                if (!this.insert_cust_data()) {
                    key = false;
                }

            }
            if (!this.insert_cust_order_data()) {
                key = false;
            }
            if (!this.update_credit_debit_amt()) {
                key = false;
            }
            if (this.capp.customer.get_Is_Measure_update()) {
                Measurement temp = capp.customer.getCust_measurement();
                while (temp != null) {
                    if (temp.getIsnew()) {
                        if (!insert_cust_measurement_data(temp)) {

                            key = false;
                        }
                    }
                    temp = temp.getMnext();

                }
            }
            if (!this.insert_product_data(capp.customer.getheadproduct())) {
                key = false;
            }
            if (!updateutilit()) {
                key = false;
            }
            if (key) {

                capp.DBCon.commit();
                return key;

            } else {
                capp.DBCon.rollback();
                System.out.println(key);

                return key;
            }
        } catch (SQLException placeordere) {
            System.out.println("error in place order method " + placeordere);
            return false;
        }

    }

    public void comitcommand() {
        try {
            capp.DBCon.commit();

        } catch (SQLException ex) {
            System.out.println("Error while commiting");
        }
    }

    public void rollbacmehtod() {
        try {
            capp.DBCon.rollback();
        } catch (SQLException ex) {
            System.out.println("Error in roll back");
        }
    }

    // A method to check whether customer exist or not
    public boolean cust_exist(String Cnumber) {
        // System.out.println(Cnumber);
        PreparedStatement preparedquerry;
        ResultSet rs;
        try {
            preparedquerry = capp.DBCon.prepareStatement(
                    "SELECT COUNT(cust_id ) , cust_id, Cust_criedtamt, Cust_debit from customer WHERE cust_number =? GROUP by cust_id ");
            preparedquerry.setString(1, Cnumber);
            rs = preparedquerry.executeQuery();
            // rs.next();
            if (rs.next() == false) {
                System.out.println("return false");
                return false;
            } else {
                // DButility.setCust_id(rs.getString(2));
                capp.customer.setCid(rs.getString(2));
                capp.customer.setCreditamt(3);
                capp.customer.setDebitamt(4);
                return true;
            }

        } catch (SQLException sqx) {

            System.out.println("Error in custexist method " + sqx);
            return false;
        }
    }

    public boolean cust_existforPayment(String Cnumber, PayCustomer cust) {
        // System.out.println(Cnumber);
        PreparedStatement preparedquerry;
        ResultSet rs;
        try {
            preparedquerry = capp.DBCon.prepareStatement(
                    "SELECT *from customer WHERE cust_number =?");
            preparedquerry.setString(1, Cnumber);
            rs = preparedquerry.executeQuery();
            // rs.next();
            if (rs.next() == false) {
                System.out.println("return false");
                return false;
            } else {
                cust.setCustID(rs.getString(1));
                cust.setName(rs.getString(2));
                cust.setNumber(rs.getString(3));
                cust.setCreditamt(rs.getDouble(4));
                cust.setDebitamt(rs.getDouble(5));
                return true;
            }

        } catch (SQLException sqx) {

            System.out.println("Error in custexist method " + sqx);
            return false;
        }
    }

    /* THis method will use to find customer data for view customer deial moudle */
    public ResultSet cust_Exist_View(String Cnumber) {
        // System.out.println(Cnumber);
        ResultSet rs;
        try {
            preparedquerry = capp.DBCon.prepareStatement(
                    "SELECT *from customer WHERE cust_number =?");
            preparedquerry.setString(1, Cnumber);
            rs = preparedquerry.executeQuery();
            // rs.next();
            if (rs.next() == false) {
                System.out.println("return false");
                return null;
            } else {
                return rs;
            }

        } catch (SQLException sqx) {
            System.out.println("Error in custexist method " + sqx);
            return null;
        }
    }

    /* This Method will returen result set of for view customer module */
    public ResultSet getCust_order(String custId) {
        try {
            ResultSet rs;
            preparedquerry = capp.DBCon
                    .prepareStatement("Select *from cust_order where cust_id = ? ORDER BY order_Id DESC;");
            preparedquerry.setString(1, custId);
            rs = preparedquerry.executeQuery();
            if (rs.next() != false) {
                return rs;

            } else {
                return null;
            }

        } catch (SQLException getcustorder) {
            System.out.println("Error in Get Cust Order");
            return null;

        }

    }

    // this mehtod will set db utility such as order ,cust, pid, and mid etc
    public boolean Data_base_utility() {
        try {
            ResultSet rs;
            querry = capp.DBCon.createStatement();
            rs = querry.executeQuery("SELECT *from utility;");
            rs.next();
            DButility.setLast_cust_id(rs.getInt(2));
            DButility.setLast_order_id(rs.getInt(3));
            DButility.setLast_Measure_id(rs.getInt(5));
            DButility.setLast_pro_id(rs.getInt(4));
            // System.out.println("Cust id " + DButility.getLast_cust_id());
            // System.out.println("ord id " + DButility.getLast_order_id());
            // System.out.println("pro id " + DButility.getLast_pro_id());
            // System.out.println("mear id " + DButility.getLast_Measure_id());
            return true;

        } catch (SQLException utex) {
            System.out.println("Error in utiliy exception " + utex);
            return false;
        }

    }

    // A method to insert customer data in database
    public boolean insert_cust_data() {
        try {
            // INSERT into customer VALUE('123','cust_name','8',12.0, 12.0);
            String CID = "CID" + DButility.getLast_cust_id();
            capp.customer.setCid(CID);
            preparedquerry = capp.DBCon.prepareStatement("INSERT into customer VALUE(?,?,?, 0,0);");
            preparedquerry.setString(1, capp.customer.getCid());
            preparedquerry.setString(2, capp.customer.getName());
            preparedquerry.setString(3, capp.customer.getNumber());
            preparedquerry.executeUpdate();
            return true;

        } catch (SQLException insert_cust) {
            System.out.println("Error while inserting customer infor " + insert_cust);
            return false;
        }
    }

    // A Method to insert data of order in cust_order tables
    public boolean insert_cust_order_data() {
        // INSERT INTO cust_order
        // VALUES('oid1','CId111','2022-12-4',3,400.5,60.5,'Pending','2002-10,12');
        try {
            // String oid = "OID" + DButility.getLast_order_id();
            // capp.customer.setOrderid(oid);
            preparedquerry = capp.DBCon.prepareStatement("INSERT INTO cust_order VALUES(?,?,?,?,?,?,?,?);");
            preparedquerry.setString(1, capp.customer.getOrderid());
            preparedquerry.setString(2, capp.customer.getCid());
            preparedquerry.setString(3, capp.customer.getOrder_date());
            preparedquerry.setInt(4, capp.customer.getOrder_qty());
            preparedquerry.setDouble(5, capp.customer.getOrder_value());
            preparedquerry.setDouble(6, capp.customer.getOrder_paid_amt());
            preparedquerry.setString(7, capp.customer.getordstatus());
            preparedquerry.setString(8, capp.customer.getOrder_dev_Date());
            preparedquerry.executeUpdate();
            return true;

        } catch (SQLException insert_orde_ex) {
            System.out.println("Error while inseritn order data " + insert_orde_ex);
            return false;
        }

    }

    /* A method to insert measuremtn in database */
    public boolean insert_cust_measurement_data(Measurement measure) {
        // INSERT INTO measurement VALUES('MID1','CID111',4.4,23.3,42.3,.23,54.4,24,4);
        try {

            preparedquerry = capp.DBCon.prepareStatement("INSERT INTO measurement VALUES(?,?,?,?,?,?,?,?,?);");
            preparedquerry.setString(1, measure.getMid());
            preparedquerry.setString(2, capp.customer.getCid());
            preparedquerry.setString(3, measure.getName());
            preparedquerry.setDouble(4, measure.getLenegth());
            preparedquerry.setDouble(5, measure.getWaist());
            preparedquerry.setDouble(6, measure.getChest());
            preparedquerry.setDouble(7, measure.getWaist());
            preparedquerry.setDouble(8, measure.getArm_length());
            preparedquerry.setDouble(9, measure.getShoulder());
            preparedquerry.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(" Error in measuemtn insert method " + e);
            return false;
        }
    }

    public boolean update_measure(Measurement measure) {
        try {
            preparedquerry = capp.DBCon.prepareStatement("DELETE from measurement where measure_id =?;");
            preparedquerry.setString(1, capp.customer.getSelected_measure_Id());
            preparedquerry = capp.DBCon.prepareStatement("INSERT INTO measurement VALUES(?,?,?,?,?,?,?,?,?);");
            preparedquerry.setString(1, measure.getMid());
            preparedquerry.setString(2, capp.customer.getCid());
            preparedquerry.setString(3, measure.getName());
            preparedquerry.setDouble(4, measure.getLenegth());
            preparedquerry.setDouble(5, measure.getWaist());
            preparedquerry.setDouble(6, measure.getChest());
            preparedquerry.setDouble(7, measure.getWaist());
            preparedquerry.setDouble(8, measure.getArm_length());
            preparedquerry.setDouble(9, measure.getShoulder());
            preparedquerry.executeUpdate();
            return true;

        } catch (SQLException updatem) {
            System.out.println("Update measuremtn erro " + updatem);
            return false;
        }
    }

    public void get_measurement() {
        try {
            // Measurement node;
            preparedquerry = capp.DBCon.prepareStatement("SELECT *from measurement WHERE Cust_id=?;");
            preparedquerry.setString(1, capp.customer.getCid());
            System.out.println(capp.customer.getCid());
            ResultSet meaure = preparedquerry.executeQuery();
            while (meaure.next()) {
                capp.customer.Set_measurement(meaure);
            }
        } catch (SQLException getme) {

            System.out.println("Get meeasurement erro " + getme);

            // return false;
        }
    }

    public boolean update_credit_debit_amt() {
        double precredtamt;
        double predbitamt;
        precredtamt = capp.customer.getCreditamt() + capp.customer.getOrder_value();
        predbitamt = capp.customer.getDebitamt() + precredtamt - capp.customer.getOrder_paid_amt();
        try {
            preparedquerry = capp.DBCon
                    .prepareStatement("UPDATE customer set cust_criedtamt= ?, cust_debit =?  WHERE cust_id= ?;");
            preparedquerry.setDouble(1, precredtamt);
            preparedquerry.setDouble(2, predbitamt);
            preparedquerry.setString(3, capp.customer.getCid());
            preparedquerry.execute();
            return true;

        } catch (SQLException update_custmor_balance) {
            System.out.println("Erro in update credit debita amount " + update_custmor_balance);
        }
        return false;
    }

    public boolean update_custmer_payment(String Cust_ID, double debitamt) {
        try {
            preparedquerry = capp.DBCon.prepareStatement("UPDATE customer set  cust_debit =?  WHERE cust_id= ?");
            preparedquerry.setDouble(1, debitamt);
            preparedquerry.setString(2, Cust_ID);
            preparedquerry.execute();
            return true;
        } catch (SQLException updatcustpay) {
            System.out.println("Error while updating customer payment  debit");
            return false;
        }
    }

    /* A method to insert product in database */
    public boolean insert_product_data(Product pro) {
        try {
            // INSERT INTO product
            // VALUE('pro_id','cust_id','order_id','Measur_id','Product_Type','cloth
            // cost','swing-cost','prodcut_status','toatl_cost')
            preparedquerry = capp.DBCon.prepareStatement("INSERT INTO product VALUE(?,?,?,?,?,?,?,?,?)");
            while (pro != null) {
                // String pid = "PID" + DButility.getProd_id();
                // pro.setProid(pid);
                preparedquerry.setString(1, pro.getProid());
                preparedquerry.setString(2, capp.customer.getCid());
                preparedquerry.setString(3, capp.customer.getOrderid());
                preparedquerry.setString(4, pro.getMid());
                preparedquerry.setString(5, pro.getProductType());
                preparedquerry.setDouble(6, pro.getClothcost());
                preparedquerry.setDouble(7, pro.getSwingcost());
                preparedquerry.setString(8, "Pending");
                preparedquerry.setDouble(9, pro.getTotal_Cost());
                preparedquerry.executeUpdate();
                pro = pro.getPrnext();
            }
            return true;
        } catch (SQLException insert_product) {
            System.out.println("Error in  insert_prdocut data " + insert_product);
            return false;
        }
    }

    /* A s method to chech whter user had setup hi pasword or not */
    public ResultSet is_user_password_setup() {
        try {
            preparedquerry = capp.DBCon.prepareStatement("SELECT user_name,pass from utility;");
            ResultSet rs = preparedquerry.executeQuery();
            rs.next();
            if (rs.getString(1) == null || rs.getString(2) == null) {
                return null;
            }
            // System.out.println(rs.get);
            return rs;

        } catch (SQLException setup) {
            System.out.println("Setup exception");
            return null;
        }
    }
    // A method to setup user and password

    public boolean set_user_data(String user, String pass) {
        try {
            preparedquerry = capp.DBCon.prepareStatement("UPDATE  utility SET pass =?, user_name= ? where ut_index=1;");
            preparedquerry.setString(1, pass);
            preparedquerry.setString(2, user);
            preparedquerry.executeUpdate();
            capp.DBCon.commit();
            return true;
        } catch (SQLException user_setup) {
            System.out.println("Error in user setup " + user_setup);
            return false;
        }

    }

    /*
     * A update uility metho is to upade utilityf cust tail database such as last
     * cust id order and product id
     */
    public boolean updateutilit() {
        try {
            preparedquerry = capp.DBCon.prepareStatement(
                    "UPDATE utility set last_cid=?, last_oid=?, last_measure_Id =?, Last_pid=? WHERE ut_index=1;");
            preparedquerry.setInt(1, DButility.getLast_cust_id());
            preparedquerry.setInt(2, DButility.getLast_order_id());
            preparedquerry.setInt(3, DButility.getLast_Measure_id());
            preparedquerry.setInt(4, DButility.getLast_pro_id());
            preparedquerry.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error in updateutilit " + ex);
            return false;
        }

    }

    /* A gettoalt order method is for dashboard to get the count of total order */
    public int gettotalorder() {
        try {
            preparedquerry = capp.DBCon.prepareStatement("SELECT count(order_id) from cust_order;");
            ResultSet rs = preparedquerry.executeQuery();
            while (rs.next() != false) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException ex) {
            System.out.println("get toal order " + ex);
            return -1;

        }
    }

    /* A method to calcuate the number of order to pending */
    public int gettoatlpendingOrder() {
        try {
            preparedquerry = capp.DBCon.prepareStatement(
                    "SELECT count(order_id) from cust_order where order_status!='Hand Over' && order_status!='COMPLETE';");
            ResultSet rs = preparedquerry.executeQuery();
            while (rs.next() != false) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException ex) {
            System.out.println("get toal order " + ex);
            return -1;

        }
    }

    /*
     * A method for dashboard to get the count of order which is completed but not
     * diliverd yet
     */
    public int getorder_compelete() {
        try {
            preparedquerry = capp.DBCon
                    .prepareStatement("SELECT COUNT(order_Id) FROM cust_order where order_status ='COMPLETE';");
            ResultSet rs = preparedquerry.executeQuery();
            while (rs.next() != false) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException ex) {
            System.out.println("get toal order " + ex);
            return -1;

        }
    }

    /*
     * A get total customer method for dashboard to get the count of total customer
     */
    public int get_total_customer() {
        try {
            preparedquerry = capp.DBCon.prepareStatement("Select count(cust_id) from customer");
            ResultSet rs = preparedquerry.executeQuery();
            while (rs.next() != false) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException totalcustomer) {
            System.out.println("Eror in method get total customer " + totalcustomer);
            return -1;
        }
    }

    /*
     * A handover to customer is method to get the count of order which is fullfiled
     */
    public int handovertocustomer() {
        try {
            preparedquerry = capp.DBCon
                    .prepareStatement("select count(order_Id) from  cust_order where Order_status='HAND OVER'");
            // preparedquerry.setString(1, OrdID);
            ResultSet rs = preparedquerry.executeQuery();
            // return ;
            while (rs.next() != false) {
                // System.out.println("rs "+rs.getInt(1));
                return rs.getInt(1);
            }
            return -1;

        } catch (SQLException handover) {
            System.out.println("Erro in handover to cusomter method " + handover);
            return -1;
        }
    }

    /* A find metho to find the order using cutomer ID */
    public boolean Find_order_using_CID(String Cid, PayCustomer custo) {
        try {
            // System.out.println(Cid);
            preparedquerry = capp.DBCon
                    .prepareStatement(
                            "SELECT *from cust_order WHERE cust_id = ? && (order_status='COMPLETE' or order_status='Not Paid') ORDER BY  Order_id DESC;");
            preparedquerry.setString(1, Cid);
            ResultSet rs = preparedquerry.executeQuery();
            while (rs.next() != false) {
                custo.Insert_order_list(rs.getString(1), rs.getString(3), rs.getString(6), rs.getInt(4),
                        rs.getDouble(5), rs.getDouble(6));
            }
            return true;

        } catch (SQLException findord) {
            System.out.println("Error in Find order using cid " + findord);
            return false;

        }
    }
    /*
     * A method to upadte that order is given diliver to the customer and payment
     * may or may not received
     */

    public boolean Order_HandOver_ToCustomer(boolean isPaymentCompleted, String OrderID, double Paid_Value) {

        try {
            if (isPaymentCompleted) {
                preparedquerry = capp.DBCon.prepareStatement(
                        "UPDATE cust_order set order_status ='Hand Over', order_paid_amt = ? WHERE Order_id = ?;");
                preparedquerry.setDouble(1, Paid_Value);
                preparedquerry.setString(2, OrderID);
                preparedquerry.execute();
                return true;

            } else {
                preparedquerry = capp.DBCon.prepareStatement(
                        "UPDATE cust_order set order_status ='NOT PAID', order_paid_amt = ? WHERE Order_id = ?;");
                preparedquerry.setDouble(1, Paid_Value);
                preparedquerry.setString(2, OrderID);
                preparedquerry.execute();
                return true;

            }
        } catch (SQLException Order_hand_Over_TOcustomer) {
            System.out.println("Error in Order_hand_Over_To customer");
            return false;
        }
    }

    /* A method to get product data using pro duct id */
    public ResultSet getProductData(String proid) {
        try {
            ResultSet rs;
            preparedquerry = capp.DBCon.prepareStatement("SELECT *from product WHERE product_Id=?;");
            preparedquerry.setString(1, proid);
            rs = preparedquerry.executeQuery();
            if (rs.next() != false) {
                return rs;
            }
            return null;
        } catch (SQLException getprodata) {
            System.out.println("Error in get Product Data " + getprodata);
            return null;
        }

    }
    public ResultSet getProUsingOID(String oid) {
        try {
            ResultSet rs;
            preparedquerry = capp.DBCon.prepareStatement("SELECT *from product WHERE Order_ID=?;");
            preparedquerry.setString(1, oid);
            rs = preparedquerry.executeQuery();
            if (rs.next() != false) {
                return rs;
            }
            return null;
        } catch (SQLException getprodata) {
            System.out.println("Error in get Product Data " + getprodata);
            return null;
        }

    }
    /* A method to update product status */
    public boolean updateProduct_Status(String Status, String pid) {
        try {
            preparedquerry = capp.DBCon.prepareStatement("UPDATE product set product_status=? WHERE product_id=?");
            preparedquerry.setString(1, Status);
            preparedquerry.setString(2, pid);
            preparedquerry.executeUpdate();
            return true;
        } catch (SQLException updatepro) {
            System.out.println("Error in update product status method " + updatepro);
            return false;
        }

    }

    public ResultSet getProduct_table(String OrdId) {
        try {
            preparedquerry = capp.DBCon.prepareStatement("Select *from Product where Order_ID = ?");
            preparedquerry.setString(1, OrdId);
            ResultSet rs = preparedquerry.executeQuery();
            if (rs.next() != false) {
                return rs;

            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error in get product table using order id " + ex);
            return null;
        }
    }

    /* A Method to update Order Status */
    public boolean UpdateOrderStatus(String Oid) {
        System.out.println(Oid);
        ResultSet rs = getProUsingOID(Oid);
        boolean key = true;
        if (rs == null) {
            System.out.println( "rs is null");
            return true;
        }
        try {
            do {
                System.out.println(rs.getString(8));
                if (!rs.getString(8).equals("COMPLETED")) {
                    System.out.println("false");
                    key = false;
                }
            } while (rs.next() != false);
            if (!key) {
                return true;
            } else {
                preparedquerry = capp.DBCon.prepareStatement("Update cust_order set order_status = 'COMPLETE' where Order_id = ?");
                preparedquerry.setString(1, Oid);
                preparedquerry.executeUpdate();
                capp.Dashboard.dashBoardrefersh();
                return true;
            }
            // return key;
        } catch (SQLException ex) {
            System.out.println("Error update Order Status " + ex);
            return false;
        }

    }
}

/* Testing of Data operation */
// Customer wroking
// cid method wroing
// utitliy morking
// Testign Product linked list
// custail.customer.setCid("CID111");
// custail.customer.setSelected_measure_Id("MID1");
// custail.customer.setproduct("P12", "thi", 80, 90, 100);
// custail.customer.setproduct("P13", "hi", 280, 290, 120);
// custail.customer.setproduct("P14", "i", 380, 390, 130);
// // Product temp = custail.customer.getheadproduct();
// // System.out.println("HEad produtct "+custail.customer.getheadproduct());
// // System.out.println(temp);
// // while (temp != null) {
// // System.out.println(temp.getProid());
// // System.out.println(temp.getProductType());
// // System.out.println(temp.getClothcost());
// // System.out.println(temp.getSwingcost());
// // System.out.println(temp.getTotal_Cost());
// // temp = temp.getPrnext();

// // }
// //Testing Measument linked list
// // // custail.DBopertion
// // custail.customer.setCid("CID111");
// // custail.DBopertion.get_measurement();
// // Measurement temp= custail.customer.getCust_measurement();
// // while (temp!=null) {
// // System.out.println(temp.getMid());
// // System.out.println(temp.getName());
// // temp=temp.getMnext();
// // }
// //Testing Product insert
// custail.DBopertion.insert_product_data(custail.customer.getheadproduct());
