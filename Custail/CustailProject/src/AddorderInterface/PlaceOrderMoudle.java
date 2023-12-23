/* THis moudle will user to load all unit interface of custom and order moudle and worak as single unit
 * as place order moudle 
 * written by jyotiraditya mourya 4th dec 2023
 */
package AddorderInterface;

import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import CustailDriver.CustailApp;

/*  

 
*/
public class PlaceOrderMoudle extends Addorderutility {
    Setbutton setbtnobj;
    product_screen probj;
    Addcustmerscreen custobj;
    AddOrderEventMoudle userevent;
    ViewOrderMoudule vom;
    resetter res;
    mousetouchfeel mtf;
    // Border
    order_screen ordscreen;
    Addorderutility orderutility;
    JLabel NameLabel, WarnginLabel, NumberLabel, cust_id_label;
    JTextField Namefeild, NumberFeild, cust_idFeild;
    CustaiButton Submit, Next, Cancel, SetMeasurement, Addproduct, View_order, place_order, cancel_pro_screenbtn;
    /* Component for ordder screen */
    JLabel Order_id, Order_qty, Order_value, Order_date, name, custid, number;
    JTextField namefieldord, numberfeildord, custidfeildord, orderidfeild, order_qty_feild, order_value_feild,
            order_date_feild;
    /* Component for Product scren */
    JLabel product_Id_label, Protype_lable, Pro_swing_cost_label, Pro_cloth_Cost_label, Pro_toat_cost_label,
            pro_cust_id_label, pro_order_id_label;
    JTextField proidtf, protypetf, ProSwingCostTF, ProClothCostTF, ProTotalValueTF;
    JComboBox<String> type;
    String protype[] = { "Shirt", "Pant", "Trouser", "Coat", "Kurta", };
    Font custialfont_bold = new Font("Times New Roman", Font.BOLD, 16);
    Font custialfont_italic = new Font("Times New Roman", Font.ITALIC, 16);
    Border blacborder = BorderFactory.createLineBorder(Color.black, 1);
    Boolean cust_active = true, order_active = false, product_active = false, measure_set = false;

    public PlaceOrderMoudle(CustailApp capp) {

        this.capp = capp;
        Background_pannel = new CustailPannel(new Color(55, 55, 55), Color.BLACK, 20);
        Background_pannel.setBounds(700, 100, 550, 550);
        this.Background_pannel.setVisible(true);
        this.capp.mainScreen.add(Background_pannel);
        orderutility = new Addorderutility();
        setbtnobj = new Setbutton(this);
        custobj = new Addcustmerscreen(this);
        ordscreen = new order_screen(this);
        probj = new product_screen(this);
        userevent = new AddOrderEventMoudle(this, ordscreen, custobj, probj);
        this.Next.addActionListener(userevent);
        // vom=new ViewOrderMoudule(this);
    }

    public void setup_order_interface() {
        setbtnobj.setaddcustactive();
        setbtnobj.Setaddproductbtn();
        setbtnobj.setorderbtn();
        ordscreen.setuporderscreen();
        probj.setup_pro_screen();
        this.Addproductbtn.addActionListener(userevent);
        this.Addcustomerbtn.addActionListener(userevent);
        this.Addorderbtn.addActionListener(userevent);
        this.Submit.addActionListener(userevent);
        this.Namefeild.addKeyListener(userevent);
        this.NumberFeild.addKeyListener(userevent);
        this.ProSwingCostTF.addKeyListener(userevent);
        this.ProClothCostTF.addKeyListener(userevent);
        this.SetMeasurement.addActionListener(userevent);
        res = new resetter(this);
        mtf = new mousetouchfeel(this);
        this.Addproductbtn.addMouseListener(mtf);
        this.Addcustomerbtn.addMouseListener(mtf);
        this.Addorderbtn.addMouseListener(mtf);
        this.Submit.addMouseListener(mtf);
        this.SetMeasurement.addMouseListener(mtf);
        this.Background_pannel.addMouseListener(mtf);
        this.Addorderbtn.addMouseListener(mtf);
        this.Addproductbtn.addMouseListener(mtf);

    }
}
