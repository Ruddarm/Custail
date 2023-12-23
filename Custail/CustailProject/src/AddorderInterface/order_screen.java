package AddorderInterface;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import java.awt.Color;
import java.awt.Font;

public class order_screen {
    CustailPannel Order_back_screen, order_data_screen;
    PlaceOrderMoudle ordermodule;
    JLabel order_warn_label;

    public order_screen(PlaceOrderMoudle om) {
        this.ordermodule = om;
        Order_back_screen = new CustailPannel(new Color(210, 210, 210), Color.WHITE, 20);
        Order_back_screen.setBounds(60, 50, 430, 450);
        // Order_back_screen.
        this.ordermodule.Background_pannel.add(Order_back_screen);
        /* Setting customer data screen */
        order_data_screen = new CustailPannel(Color.white, Color.BLACK, 10);
        order_data_screen.setBounds(25, 30, 380, 300);
        Order_back_screen.add(order_data_screen);
        this.ordermodule.Next= new CustaiButton(Color.green, null, 15);
        this.ordermodule.Cancel=new CustaiButton(Color.RED, null, 15);
        Order_back_screen.setVisible(false);
    }

    public void setuporderscreen() {
        this.order_warn_label=new JLabel("Warning");
        this.order_warn_label.setVisible(false);
        this.ordermodule.custid = new JLabel("Customer ID :");
        this.ordermodule.Order_id = new JLabel("Order ID    : ");
        this.ordermodule.name = new JLabel("Name         : ");
        this.ordermodule.number = new JLabel("Number      : ");
        this.ordermodule.Order_qty = new JLabel("Order Qty   : ");
        this.ordermodule.Order_value = new JLabel("Order Value : ");
        this.ordermodule.Order_date = new JLabel("Order Date: : ");
        this.ordermodule.orderidfeild = new JTextField("ORD13XX");
        this.order_warn_label.setForeground(Color.red);
        this.order_warn_label.setFont(this.ordermodule.custialfont_italic);
        this.ordermodule.custid.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.Order_id.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.name.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.number.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.Order_qty.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.Order_value.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.Order_date.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.custidfeildord = new JTextField();
        this.ordermodule.namefieldord = new JTextField();
        this.ordermodule.numberfeildord = new JTextField();
        this.ordermodule.order_qty_feild = new JTextField();
        this.ordermodule.order_value_feild = new JTextField();
        this.ordermodule.order_date_feild = new JTextField();
        this.ordermodule.orderidfeild.setEditable(false);
        this.ordermodule.custidfeildord.setEditable(false);
        this.ordermodule.namefieldord.setEditable(false);
        this.ordermodule.numberfeildord.setEditable(false);
        this.ordermodule.order_qty_feild.setEditable(false);
        this.ordermodule.order_value_feild.setEditable(false);
        this.ordermodule.order_date_feild.setEditable(false);
        this.ordermodule.orderidfeild.setFocusable(false);
        this.ordermodule.custidfeildord.setFocusable(false);
        this.ordermodule.namefieldord.setFocusable(false);
        this.ordermodule.numberfeildord.setFocusable(false);
        this.ordermodule.order_qty_feild.setFocusable(false);
        this.ordermodule.order_value_feild.setFocusable(false);
        this.ordermodule.order_date_feild.setFocusable(false);
        this.ordermodule.orderidfeild.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ordermodule.custidfeildord.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ordermodule.namefieldord.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ordermodule.numberfeildord.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ordermodule.order_qty_feild.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ordermodule.order_value_feild.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ordermodule.order_date_feild.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.order_warn_label.setBounds(100, 20, 200, 20);
        this.ordermodule.custid.setBounds(30, 50, 100, 30);
        this.ordermodule.custidfeildord.setBounds(200, 50, 150, 30);
        this.ordermodule.Order_id.setBounds(30, 85, 100, 30);
        this.ordermodule.orderidfeild.setBounds(200, 85, 150, 30);
        this.ordermodule.name.setBounds(30, 120, 100, 30);
        this.ordermodule.namefieldord.setBounds(200, 120, 150, 30);
        this.ordermodule.number.setBounds(30, 155, 100, 30);
        this.ordermodule.numberfeildord.setBounds(200, 155, 150, 30);
        this.ordermodule.Order_qty.setBounds(30, 190, 100, 30);
        this.ordermodule.order_qty_feild.setBounds(200, 190, 150, 30);
        this.ordermodule.Order_value.setBounds(30, 225, 150, 30);
        this.ordermodule.order_value_feild.setBounds(200, 225, 150, 30);
        this.ordermodule.Order_date.setBounds(30, 260, 100, 30);
        this.ordermodule.order_date_feild.setBounds(200, 260, 150, 30);
        order_data_screen.add(order_warn_label);
        order_data_screen.add(this.ordermodule.custid);
        order_data_screen.add(this.ordermodule.custidfeildord);
        order_data_screen.add(this.ordermodule.orderidfeild);
        order_data_screen.add(this.ordermodule.Order_id);
        order_data_screen.add(this.ordermodule.name);
        order_data_screen.add(this.ordermodule.namefieldord);
        order_data_screen.add(this.ordermodule.numberfeildord);
        order_data_screen.add(this.ordermodule.number);
        order_data_screen.add(this.ordermodule.Order_qty);
        order_data_screen.add(this.ordermodule.order_qty_feild);
        order_data_screen.add(this.ordermodule.order_value_feild);
        order_data_screen.add(this.ordermodule.Order_value);
        order_data_screen.add(this.ordermodule.order_date_feild);
        order_data_screen.add(this.ordermodule.Order_date);
        this.ordermodule.Next.setBounds(250, 350, 100, 30);
        this.ordermodule.Next.setText("NEXT");
        this.ordermodule.Cancel.setBounds(100, 350, 100, 30);
        this.ordermodule.Cancel.setText("CANCEL");
        Order_back_screen.add(this.ordermodule.Cancel);
        Order_back_screen.add(this.ordermodule.Next);

    }

}
