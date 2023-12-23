package CustailDriver;

import java.sql.Connection;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import AddorderInterface.PlaceOrderMoudle;
import CustailComponent.CustailFrame;
import CustilaMenu.MenuUtility;
import DashBoardMoudle.DashbordUtilit;
import java.awt.Image;
import PaymentMoudle.PaymentMoudleInterface;
import UpdateProduct.updateProductUtility;
import DatabaseMoudle.DatabaseOpertion;
import DatabaseMoudle.Dbconnectivity;
import FindCustomer.findCustomerUtility;
import Logincustail.loginutiliy;
import UtilityModule.Order;
import java.awt.Toolkit;

//THis is drive App of custail app
public class CustailApp {
    public Connection DBCon;
    public MenuUtility menu;
    public PlaceOrderMoudle order_interfac_obj;
    public DashbordUtilit Dashboard;
    public findCustomerUtility findCustomer;
    public updateProductUtility update_produtc;
    Image logo = Toolkit.getDefaultToolkit()
            .getImage("C:\\Users\\Ruddarm\\OneDrive\\Custail\\CustailProject\\src\\custtailImagelib\\Custailogo.jpg");
    Image img = Toolkit.getDefaultToolkit().getImage("CustailProject//src//custtailImagelib//desp.png");

    public CustailFrame mainScreen = new CustailFrame("CusTail");
    public Order customer = new Order();
    public Dbconnectivity DBconnect = new Dbconnectivity();
    public PaymentMoudleInterface paycusotmer;
    public DatabaseOpertion DBopertion = new DatabaseOpertion();
    public DailogBox dbbox;
    loginutiliy login;
    CustailApp custail;

    CustailApp() {
        mainScreen.image = img;
        mainScreen.setUndecorated(false);
        mainScreen.setIconImage(logo);
        mainScreen.setSize(1440, 1024);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setLayout(null);
        mainScreen.setContentPane(new JLabel(new ImageIcon(img)));
        dbbox = new DailogBox();
    }

    public  static void main(String[] args) throws Exception {
        /* Firs of all we going to setup Databbase related Moudle */
        CustailApp custail = new CustailApp();
        Semaphore sema=new Semaphore(0);
        synchronized(custail){
        if (!custail.DBconnect.TCMS_Database_Connector(custail)) {
            custail.dbbox.Setmessage("this is try");
            custail.dbbox.messageBox.setVisible(true);
        }
        // This will check whether custail database is in mysql database or not if not
        // then it will
        // create database custail if it get failed erro message will get printed an
        // system get exist
        if (!custail.DBconnect.tailcut_database_creator(custail, "Custail")) {
            custail.dbbox.Setmessage("CustTail Database not found");
            custail.dbbox.messageBox.setVisible(true);
            System.exit(-1);
        }
        if (!custail.DBconnect.tail_cust_database_table_creator_(custail)) {
            custail.dbbox.Setmessage("CustTail Tables not found in database");
            custail.dbbox.messageBox.setVisible(true);
        }
        // /* Here we will Setu Login Module */
        custail.DBopertion.setcapp(custail);
        custail.login = new loginutiliy(custail.DBopertion, custail.logo,sema);
        custail.login.setlogin(custail.login);
        // try {
        // wait();
        // } catch (InterruptedException ex) {
        // System.out.println("INterruption Error");
        // return;
        // }
        /*  */
        try {
            // custail.wait();
            sema.acquire();

        } catch (InterruptedException ex) {
            System.out.println("Error interruption  ");
        }
        System.out.println("code is here");
        if (custail.login.log_in) {
            custail.mainScreen.setVisible(true);
            custail.order_interfac_obj = new PlaceOrderMoudle(custail);
            custail.order_interfac_obj.setup_order_interface();
            custail.Dashboard = new DashbordUtilit(custail);
            custail.Dashboard.dashBoardrefersh();
            custail.menu = new MenuUtility(custail);
            custail.findCustomer = new findCustomerUtility(custail.DBopertion, custail.mainScreen);
        }

    }
}

}
