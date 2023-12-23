package AddorderInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;;

public class product_screen {
    PlaceOrderMoudle pom;
    ImageIcon img;
    CustailPannel pro_scree_back_pannel, pro_Screen_data_pannel, product_white_pannel;
    JLabel warn;

    product_screen(PlaceOrderMoudle pMoudle) {
        this.pom = pMoudle;
        pro_scree_back_pannel = new CustailPannel(new Color(210, 210, 210), Color.WHITE, 20);
        pro_scree_back_pannel.setBounds(40, 50, 470, 450);
        pro_scree_back_pannel.setVisible(false);
        this.pom.Background_pannel.add(pro_scree_back_pannel);
        product_white_pannel = new CustailPannel(new Color(240, 240, 240), Color.white, 10);
        product_white_pannel.setBounds(15, 15, 440, 420);
        pro_scree_back_pannel.add(product_white_pannel);

        /* Setting customer data screen */
        pro_Screen_data_pannel = new CustailPannel(Color.white, Color.WHITE, 20);
        pro_Screen_data_pannel.setBounds(10, 30, 420, 310);
        /* Setting cutomer */
        product_white_pannel.add(pro_Screen_data_pannel);
        img = new ImageIcon(
            "C:\\Users\\Ruddarm\\Downloads\\Custail\\CustailProject\\src\\custtailImagelib\\measure.png");

    }

    public void setup_pro_screen() {
        this.warn = new JLabel();
        // this.warn.setText("This is warnng");
        this.warn.setFont(pom.custialfont_italic);
        this.warn.setVisible(false);
        this.warn.setForeground(Color.RED);
        this.warn.setBounds(150, 10, 150, 30);
        this.pro_Screen_data_pannel.add(this.warn);
        this.pom.pro_cust_id_label = new JLabel("Customer ID :");
        this.pom.pro_order_id_label = new JLabel("Order ID :");
        this.pom.pro_cust_id_label.setFont(new Font("Time New Roman", Font.ITALIC, 14));
        this.pom.pro_order_id_label.setFont(new Font("Time New Roman", Font.ITALIC, 14));
        this.pom.pro_cust_id_label.setBounds(10, 5, 250, 20);
        this.pom.pro_order_id_label.setBounds(270, 5, 250, 20);
        /* Product data */
        this.pom.product_Id_label = new JLabel("Product ID     : ");
        this.pom.Protype_lable = new JLabel("Product Type  : ");
        this.pom.Pro_swing_cost_label = new JLabel("Swing Cost     : ");
        this.pom.Pro_cloth_Cost_label = new JLabel("Cloth Cost     : ");
        this.pom.Pro_toat_cost_label = new JLabel("Total Cost     : ");
        this.product_white_pannel.add(this.pom.pro_cust_id_label);
        this.product_white_pannel.add(this.pom.pro_order_id_label);
        this.pom.type = new JComboBox<String>(this.pom.protype);
        this.pom.type.setFont(this.pom.custialfont_bold);
        // this.pom.product_Id_label
        this.pom.product_Id_label.setFont(this.pom.custialfont_bold);
        this.pom.Pro_swing_cost_label.setFont(this.pom.custialfont_bold);
        this.pom.Pro_cloth_Cost_label.setFont(this.pom.custialfont_bold);
        this.pom.Pro_toat_cost_label.setFont(this.pom.custialfont_bold);
        this.pom.Protype_lable.setFont(this.pom.custialfont_bold);
        this.pom.product_Id_label.setBounds(20, 50, 150, 30);
        this.pom.Protype_lable.setBounds(20, 85, 150, 30);
        this.pom.Pro_swing_cost_label.setBounds(20, 120, 150, 30);
        this.pom.Pro_cloth_Cost_label.setBounds(20, 155, 150, 30);
        this.pom.Pro_toat_cost_label.setBounds(20, 185, 150, 30);
        this.pro_Screen_data_pannel.add(this.pom.product_Id_label);
        this.pro_Screen_data_pannel.add(this.pom.Pro_swing_cost_label);
        this.pro_Screen_data_pannel.add(this.pom.Pro_cloth_Cost_label);
        this.pro_Screen_data_pannel.add(this.pom.Protype_lable);
        this.pro_Screen_data_pannel.add(this.pom.Pro_toat_cost_label);
        /* Prodcut data Jtextfeild */
        this.pom.proidtf = new JTextField("PIDXX169");
        this.pom.proidtf.setEditable(false);
        this.pom.ProSwingCostTF = new JTextField();
        this.pom.ProClothCostTF = new JTextField();
        this.pom.ProTotalValueTF = new JTextField();
        this.pom.proidtf.setFont(this.pom.custialfont_italic);
        this.pom.proidtf.setFont(this.pom.custialfont_italic);
        this.pom.ProSwingCostTF.setFont(this.pom.custialfont_italic);
        this.pom.ProClothCostTF.setFont(this.pom.custialfont_italic);
        this.pom.ProTotalValueTF.setFont(this.pom.custialfont_italic);
        this.pom.proidtf.setBounds(200, 50, 150, 30);
        this.pom.type.setBounds(200, 85, 150, 30);
        this.pom.ProSwingCostTF.setBounds(200, 120, 150, 30);
        this.pom.ProClothCostTF.setBounds(200, 155, 150, 30);
        this.pom.ProTotalValueTF.setBounds(200, 190, 150, 30);
        this.pom.ProTotalValueTF.setEditable(false);
        this.pom.proidtf.setBorder(this.pom.blacborder);
        this.pom.proidtf.setBorder(this.pom.blacborder);
        this.pom.ProSwingCostTF.setBorder(this.pom.blacborder);
        this.pom.ProClothCostTF.setBorder(this.pom.blacborder);
        this.pom.ProTotalValueTF.setBorder(this.pom.blacborder);
        // this.pro_Screen_data_pannel.add(this.pom.protypetf);
        this.pro_Screen_data_pannel.add(this.pom.proidtf);
        this.pro_Screen_data_pannel.add(this.pom.type);
        this.pro_Screen_data_pannel.add(this.pom.ProTotalValueTF);
        this.pro_Screen_data_pannel.add(this.pom.ProSwingCostTF);
        this.pro_Screen_data_pannel.add(this.pom.ProClothCostTF);
        /* Setup buttn for product Screen */
        this.pom.SetMeasurement = new CustaiButton(new Color(240, 240 , 240), null, 2);
        // this.pom.SetMeasurement.setText("MESUREMENT");
        this.pom.SetMeasurement.setIcon(img);
        this.pom.SetMeasurement.setFont(this.pom.custialfont_italic);
        this.pom.SetMeasurement.painted_Clr = Color.BLACK;
        this.pom.SetMeasurement.setBounds(200, 240, 150, 30);
        this.pro_Screen_data_pannel.add(this.pom.SetMeasurement);
        this.pom.Addproduct = new CustaiButton(Color.BLUE, Color.black, 20);
        /* Other funciton button */
        // Cancel buttong
        this.pom.cancel_pro_screenbtn = new CustaiButton(Color.RED, null, 10);
        this.pom.cancel_pro_screenbtn.setText("Cancel");
        this.pom.cancel_pro_screenbtn.setForeground(Color.white);
        this.pom.cancel_pro_screenbtn.setFont(this.pom.custialfont_bold);
        this.pom.cancel_pro_screenbtn.painted_Clr = Color.black;
        this.pom.cancel_pro_screenbtn.setBounds(50, 360, 100, 30);
        this.product_white_pannel.add(this.pom.cancel_pro_screenbtn);
        // Add Product Button
        this.pom.Addproduct = new CustaiButton(Color.BLUE, null, 10);
        this.pom.Addproduct.setText("Add");
        this.pom.Addproduct.setForeground(Color.white);
        this.pom.Addproduct.setFont(this.pom.custialfont_bold);
        this.pom.Addproduct.painted_Clr = Color.black;
        this.pom.Addproduct.setBounds(170, 360, 100, 30);
        this.product_white_pannel.add(this.pom.Addproduct);
        // Adding action eventt in add product butto
        this.pom.Addproduct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setVisible(false);
                if (pom.measure_set) {
                    if (!pom.ProTotalValueTF.getText().trim().isEmpty()) {
                        try {
                            pom.capp.customer.setproduct(pom.proidtf.getText(), "" + pom.type.getSelectedItem(),
                                    Double.parseDouble("" + pom.ProSwingCostTF.getText()),
                                    Double.parseDouble("" + pom.ProClothCostTF.getText()),
                                    Double.parseDouble("" + pom.ProTotalValueTF.getText(3,
                                            +pom.ProTotalValueTF.getText().length() - 3)),
                                    pom.capp.customer.getSelected_measure_Id());
                                    pom.order_qty_feild.setText(""+pom.capp.customer.getOrder_qty());
                                    pom.order_value_feild.setText("Rs "+pom.capp.customer.getOrder_value());

                        } catch (BadLocationException ex) {
                            System.out.println("Error in total value " + ex);
                        }
                        int i = pom.capp.DBopertion.DButility.getLast_pro_id();
                        i++;
                        pom.capp.DBopertion.DButility.setLast_pro_id(i);
                        pom.proidtf.setText("PID" + i);
                        pom.capp.customer.setSelected_measure_Id(null);
                        pom.measure_set = false;
                        pom.ProTotalValueTF.setText("RS 0");
                        pom.ProClothCostTF.setText("0");
                        pom.ProSwingCostTF.setText("0");
                    } else {
                        warn.setText("Enter Total Cost");
                        warn.setVisible(true);
                    }

                } else {
                    warn.setText("Set Measurement");
                    warn.setVisible(true);
                }
                // throw new UnsupportedOperationException("Unimplemented method
                // 'actionPerformed'");
            }

        });
        // View Order Button
        this.pom.View_order = new CustaiButton(new Color(55, 55, 55), null, 10);
        this.pom.View_order.setText("View");
        this.pom.View_order.setForeground(Color.white);
        this.pom.View_order.setFont(this.pom.custialfont_bold);
        this.pom.View_order.painted_Clr = Color.black;
        this.pom.View_order.setBounds(280, 360, 100, 30);
        this.product_white_pannel.add(this.pom.View_order);
        this.pom.View_order.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pom.vom=new ViewOrderMoudule(pom);
            }
            
        });
    }
}
