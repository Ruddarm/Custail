package AddorderInterface;

import java.awt.Color;

// import javax.swing.ImageIcon;

import CustailComponent.CustaiButton;

public class Setbutton {
    PlaceOrderMoudle add_order_utiltiy;
    Color bgclr = new Color(55, 55, 55);
    Color activeclr = new Color(111, 107, 107);
    // ImageIcon adduser=new
    // ImageIcon("C:\\Users\\Ruddarm\\Downloads\\Custail\\CustailProject\\src\\custtailImagelib\\add-user.png");

    public Setbutton(PlaceOrderMoudle utility) {
        this.add_order_utiltiy = utility;
    }

    public void setaddcustactive() {
        add_order_utiltiy.Addcustomerbtn = new CustaiButton(activeclr, activeclr, 10);
        this.add_order_utiltiy.Addcustomerbtn.painted_Clr = Color.white;
        // add_order_utiltiy.Addcustomerbtn.setIcon(adduser);
        add_order_utiltiy.Addcustomerbtn.setText("ADD");
        add_order_utiltiy.Addcustomerbtn.setForeground(Color.GREEN);
        add_order_utiltiy.Addcustomerbtn.setBounds(700, 60, 100, 40);
        // add_order_utiltiy.Addcustomerbtn.setForeground(Color.white);
        add_order_utiltiy.capp.mainScreen.add(add_order_utiltiy.Addcustomerbtn);
    }

    public void setorderbtn() {
        add_order_utiltiy.Addorderbtn = new CustaiButton(bgclr, activeclr, 10);
        this.add_order_utiltiy.Addorderbtn.painted_Clr = Color.white;
        add_order_utiltiy.Addorderbtn.setText("Order");
        add_order_utiltiy.Addorderbtn.setBounds(800, 70, 100, 30);
        add_order_utiltiy.Addorderbtn.setForeground(Color.white);
        add_order_utiltiy.capp.mainScreen.add(add_order_utiltiy.Addorderbtn);
    }

    public void Setaddproductbtn() {
        add_order_utiltiy.Addproductbtn = new CustaiButton(bgclr, activeclr, 10);
        this.add_order_utiltiy.Addproductbtn.painted_Clr = Color.white;
        add_order_utiltiy.Addproductbtn.setText("Product");
        add_order_utiltiy.Addproductbtn.setBounds(900, 70, 100, 30);
        add_order_utiltiy.Addproductbtn.setForeground(Color.white);
        add_order_utiltiy.capp.mainScreen.add(add_order_utiltiy.Addproductbtn);
    }

    public void activebutton() {
        if (this.add_order_utiltiy.cust_active) {
            add_order_utiltiy.Addcustomerbtn.background_clr = this.activeclr;
            add_order_utiltiy.Addcustomerbtn.setBounds(700, 60, 100, 40);
            add_order_utiltiy.Addcustomerbtn.setForeground(Color.GREEN);
            add_order_utiltiy.Addorderbtn.background_clr = this.bgclr;
            add_order_utiltiy.Addorderbtn.setForeground(Color.white);
            add_order_utiltiy.Addorderbtn.setBounds(800, 70, 100, 30);
            add_order_utiltiy.Addproductbtn.background_clr = this.bgclr;
            add_order_utiltiy.Addproductbtn.setBounds(900, 70, 100, 30);
            add_order_utiltiy.Addproductbtn.setForeground(Color.white);

        } else if (this.add_order_utiltiy.order_active) {
            add_order_utiltiy.Addcustomerbtn.background_clr = this.bgclr;
            add_order_utiltiy.Addcustomerbtn.setBounds(700, 70, 100, 30);
            add_order_utiltiy.Addcustomerbtn.setForeground(Color.WHITE);
            add_order_utiltiy.Addorderbtn.background_clr = this.activeclr;
            add_order_utiltiy.Addorderbtn.setBounds(800, 60, 100, 40);
            add_order_utiltiy.Addorderbtn.setForeground(Color.GREEN);
            add_order_utiltiy.Addproductbtn.background_clr = this.bgclr;
            add_order_utiltiy.Addproductbtn.setBounds(900, 70, 100, 30);
            add_order_utiltiy.Addproductbtn.setForeground(Color.white);
        } else if (this.add_order_utiltiy.product_active) {
            add_order_utiltiy.Addcustomerbtn.background_clr = this.bgclr;
            add_order_utiltiy.Addcustomerbtn.setForeground(Color.WHITE);
            add_order_utiltiy.Addcustomerbtn.setBounds(700, 70, 100, 30);
            add_order_utiltiy.Addorderbtn.background_clr = this.bgclr;
            add_order_utiltiy.Addorderbtn.setForeground(Color.WHITE);
            add_order_utiltiy.Addorderbtn.setBounds(800, 70, 100, 30);
            add_order_utiltiy.Addproductbtn.background_clr = this.activeclr;
            add_order_utiltiy.Addproductbtn.setForeground(Color.GREEN);
            add_order_utiltiy.Addproductbtn.setBounds(900, 60, 100, 40);
        }
    }
}