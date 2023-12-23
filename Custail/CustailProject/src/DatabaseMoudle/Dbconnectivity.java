
// A databse connectivity Doucment to connect mysql database custai with our softwar
//It will establih connection wtih databse and create required database and tables if rquired
//writen by Jyotiraditya  Mourya
package DatabaseMoudle;

import java.sql.*;


// import com.mysql.cj.protocol.Resultset;

import CustailDriver.CustailApp;

public class Dbconnectivity {
    

    public boolean TCMS_Database_Connector(CustailApp c) {
        String name, pswd;
        // = null;
        name = "jdbc:mysql://localhost:3306/";
        pswd = "ruddarmsql";
        try {
            c.DBCon = DriverManager.getConnection(name, "root", pswd);
            c.DBCon.setAutoCommit(false);

        } catch (SQLException ex) {
            System.out.println("Get conncetion error" + ex);
            return false;
        }

        return true;

    }

    // A method to create custail database
    public boolean tailcut_database_creator(CustailApp c, String Dname) {
        String name = "jdbc:mysql://localhost:3306/";
        String pswd = "ruddarmsql";
        Statement create_db_stement;
        name = name + Dname;
        try {
            c.DBCon = DriverManager.getConnection(name, "root", pswd);
            c.DBCon.setAutoCommit(false);
        } catch (SQLException ex) {
            // System.out.println("Got sql error "+ex);
            try {
                create_db_stement = c.DBCon.createStatement();
                create_db_stement.executeUpdate("Create DATABASE " + Dname);


            } catch (SQLException x) {
                // System.out.println("");
                return false;
            }
            return true;
        }
        return true;

    }

    public boolean tail_cust_database_table_creator_(CustailApp c) {
        if (c!=null){
            Dbconnectivity databaseobject=new Dbconnectivity();
            try{
                            Statement  str= c.DBCon.createStatement();
                            databaseobject.create_Cust_table(c.DBCon, str);
                            databaseobject.create_cust_order_table(c.DBCon, str);
                            databaseobject.Create_measurement_table(c.DBCon, str);
                            databaseobject.Create_Product_table(c.DBCon, str);
                            databaseobject.Create_Utilty_Table(c.DBCon, str);
                        

            }catch(SQLException ex){
                System.out.println(ex);
                return false;
            }

            return true;
        }
        else{
            return false;
        }
                
    }

    public void create_Cust_table(Connection dbc, Statement str) {
        ResultSet rs;

        try {
            DatabaseMetaData dmd = dbc.getMetaData();
            rs = dmd.getTables(null, null, "Customer", null);
            if (!rs.next()) {
                try {
                    // Customer Table creation
                    System.out.println("Creating customer table");
                    String cmd = "CREATE TABLE Customer (Cust_id VARCHAR(15) PRIMARY KEY," +
                            "Cust_name VARCHAR(20),	Cust_number VARCHAR(10),"
                            + "cust_criedtamt int DEFAULT 0,cust_debit int DEFAULT 0);";
                    str.execute(cmd);

                } catch (SQLException custex) {
                    System.out.println("Error in creating cusotmer table" + custex);
                }
            }
        } catch (SQLException custmertableex) {
            System.out.println("Custmer Table exception");
        }

    }

    public void create_cust_order_table(Connection dbc, Statement str) {
        ResultSet rs;
        try {
            DatabaseMetaData dmd = dbc.getMetaData();
            rs = dmd.getTables(null, null, "Cust_order", null);
            rs.next();
            if (!rs.next()) {
                try {
                    System.out.println("Creating Custorder table");
                    String cmd = "CREATE TABLE Cust_order (Order_ID varchar(15) PRIMARY KEY," +
                            "cust_id varchar(15)," +
                            "order_date date ," +
                            "order_qty int," +
                            "order_value FLOAT," +
                            "order_paid_amt FLOAT," +
                            "order_status VARCHAR(10)," +
                            "order_dev_date DATE, FOREIGN KEY (Cust_ID) REFERENCES customer(Cust_ID)" +
                            ");";
                    str.executeUpdate(cmd);
                } catch (SQLException orderex) {
                    System.out.println("Erro in creating order table " + orderex);
                    // if any error occur
                    // key = -1;
                }
            }
        } catch (SQLException ex) {

        }
    }
    public void Create_measurement_table(Connection dbc, Statement str) {
        ResultSet rs;
        try {
            DatabaseMetaData dmd = dbc.getMetaData();
            rs = dmd.getTables(null, null, "Cust_order", null);
            rs.next();
            if (!rs.next()) {
                try {
                        String cmd = "CREATE TABLE Measurement (" +
                                "    Measure_ID VARCHAR(15) Primary key," +
                                "    Cust_id VARCHAR(15), -- Match the data type with the 'customer' table\n" +
                                "    cust_name VARCHAR(20)," +
                                "    length FLOAT," +
                                "    Waist FLOAT," +
                                "    Chest FLOAT," +
                                "    wrist FLOAT," +
                                "    arm_length FLOAT," +
                                "    shoulder FLOAT," +
                                "    FOREIGN KEY (Cust_id) REFERENCES customer(Cust_id)" +
                                ");";
                        str.executeUpdate(cmd);
                    } catch (SQLException measuretablex) {
                        System.out.println("Error while creating table measuremtn ");

                    }
            }
        } catch (SQLException ex) {

        }
    }
     public void Create_Utilty_Table(Connection dbc, Statement str) {
        ResultSet rs;
        try {
            DatabaseMetaData dmd = dbc.getMetaData();
            rs = dmd.getTables(null, null, "Cust_order", null);
            rs.next();
            if (!rs.next()) {
                try {
                        String cmd = "CREATE TABLE Utility (" +
                                "    Ut_index INT PRIMARY KEY," +
                                "    Last_Cid INT," +
                                "    Last_oid INT," +
                                "    Last_pid INT," +
                                "    Last_Measure_Id INT," +
                                "    pass VARCHAR(255)," +
                                "    User_name VARCHAR(50)" +
                                ");";
                                
                        str.executeUpdate(cmd);
                        cmd="INSERT INTO utility VALUES(1,111,1,1,1,null,null)";
                        str.executeQuery(cmd);
                        System.out.println("heh");
                    } catch (SQLException utlitiytableexcepton) {
                        System.out.println("Error while creating utility table " + utlitiytableexcepton);
                        // key = -1;

                    }
            }
        } catch (SQLException ex) {

        }
    }
     public void Create_Product_table(Connection dbc, Statement str) {
        ResultSet rs;
        try {
            DatabaseMetaData dmd = dbc.getMetaData();
            rs = dmd.getTables(null, null, "Cust_order", null);
            rs.next();
            if (!rs.next()) {
                try {
                        // System.out.println("Code is here");
                        // querry to create table product
                        String cmd = "CREATE TABLE Product (" +
                                "    Product_ID Varchar(15) PRIMARY KEY ," +
                                "    Cust_ID VARCHAR(15)," +
                                "    Order_ID VARCHAR(15)," +
                                "    Measurement_ID VARCHAR(15)," +
                                "    Product_Type VARCHAR(50)," +
                                "    Cloth_Cost FLOAT," +
                                "    Sewing_Cost FLOAT," +
                                "    Product_Status VARCHAR(20)," +
                                "    Total_Cost FLOAT," +
                                "    FOREIGN KEY (Cust_ID) REFERENCES customer(Cust_ID)," +
                                "    FOREIGN KEY (Order_ID) REFERENCES cust_order(Order_ID)," +
                                "    FOREIGN KEY (Measurement_ID) REFERENCES Measurement(Measure_ID)" +
                                ");";
                        str.executeUpdate(cmd);

                    } catch (SQLException produtabelexception) {
                        System.out.println("Error while creating table product" + produtabelexception);
                        // key = -1;
                    }
            }

        } catch (SQLException ex) {

        }
    }
}
