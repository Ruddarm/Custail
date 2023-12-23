package AddorderInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
// import java.awt.event.Mou;;
import java.awt.event.KeyListener;
import java.time.LocalDate;

public class AddOrderEventMoudle implements ActionListener, KeyListener {
    PlaceOrderMoudle placeroder_mod;
    order_screen ord;
    Addcustmerscreen cust;
    product_screen pro;

    // Measurement_moudle Mom;
    AddOrderEventMoudle(PlaceOrderMoudle pom, order_screen ord, Addcustmerscreen cust, product_screen pro) {
        this.placeroder_mod = pom;
        this.ord = ord;
        this.cust = cust;
        this.pro = pro;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ord.order_warn_label.setVisible(false);
        if (e.getSource() == placeroder_mod.Addcustomerbtn) {
            placeroder_mod.cust_active = true;
            placeroder_mod.order_active = false;
            placeroder_mod.product_active = false;
            ord.Order_back_screen.setVisible(false);
            pro.pro_scree_back_pannel.setVisible(false);
            cust.custmerBackeScreen.setVisible(true);
            placeroder_mod.setbtnobj.activebutton();
            placeroder_mod.Background_pannel.remove(ord.Order_back_screen);
            placeroder_mod.Background_pannel.remove(pro.pro_scree_back_pannel);
            placeroder_mod.Background_pannel.add(cust.custmerBackeScreen);

            return;

        } else if (e.getSource() == placeroder_mod.Addorderbtn) {
            placeroder_mod.cust_active = false;
            placeroder_mod.order_active = true;
            placeroder_mod.product_active = false;
            pro.pro_scree_back_pannel.setVisible(false);
            cust.custmerBackeScreen.setVisible(false);
            ord.Order_back_screen.setVisible(true);
            placeroder_mod.setbtnobj.activebutton();
            placeroder_mod.Background_pannel.remove(cust.custmerBackeScreen);
            placeroder_mod.Background_pannel.remove(ord.Order_back_screen);
            placeroder_mod.Background_pannel.add(ord.Order_back_screen);
            return;

        } else if (e.getSource() == placeroder_mod.Addproductbtn) {
            placeroder_mod.cust_active = false;
            placeroder_mod.order_active = false;
            placeroder_mod.product_active = true;
            cust.custmerBackeScreen.setVisible(false);
            ord.Order_back_screen.setVisible(false);
            pro.pro_scree_back_pannel.setVisible(true);
            placeroder_mod.setbtnobj.activebutton();
            placeroder_mod.Background_pannel.remove(cust.custmerBackeScreen);
            placeroder_mod.Background_pannel.remove(ord.Order_back_screen);
            placeroder_mod.Background_pannel.add(pro.pro_scree_back_pannel);
            return;

        } else if (e.getSource() == placeroder_mod.Submit) {
            String Name, Number;
            Name = placeroder_mod.Namefeild.getText();
            Name.trim();
            if (Name.isBlank() || Name.isEmpty()) {
                cust.custscreen_warning_label.setText("Enter Name");
                cust.custscreen_warning_label.setVisible(true);
                return;
            }
            Number = placeroder_mod.NumberFeild.getText();
            Number.trim();
            if (Number.isBlank() || Number.isEmpty()) {
                cust.custscreen_warning_label.setText("Enter Number");
                cust.custscreen_warning_label.setVisible(true);
                return;
            }
            char num[] = Number.toCharArray();
            if (num.length != 10) {
                cust.custscreen_warning_label.setText("Enter Valid Number");
                cust.custscreen_warning_label.setVisible(true);
                return;
            }
            for (char c : num) {
                if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8'
                        && c != '9' && c != '0') {
                    cust.custscreen_warning_label.setText("Enter Valid Number");
                    cust.custscreen_warning_label.setVisible(true);
                    return;
                }
            }
            // now user had enter correct data;
            if (placeroder_mod.capp.DBopertion.cust_exist(Number)) {
                placeroder_mod.custidfeildord.setText(placeroder_mod.capp.customer.getCid());
            } else {
                int cid = placeroder_mod.capp.DBopertion.DButility.getLast_cust_id();
                cid++;
                String id = "CID" + cid;
                placeroder_mod.capp.DBopertion.DButility.setLast_cust_id(cid);
                placeroder_mod.capp.customer.setIsnewcust(true);
                placeroder_mod.custidfeildord.setText(id);
                placeroder_mod.capp.customer.setCid(id);
            }
            int loid = placeroder_mod.capp.DBopertion.DButility.getLast_order_id();
            String oid = "OID" + loid;
            loid++;
            placeroder_mod.capp.DBopertion.DButility.setLast_order_id(loid);
            placeroder_mod.numberfeildord.setText(Number);
            placeroder_mod.namefieldord.setText(Name);
            placeroder_mod.orderidfeild.setText(oid);
            placeroder_mod.order_date_feild.setText("" + LocalDate.now());
            placeroder_mod.capp.customer.setNumber(Number);
            placeroder_mod.capp.customer.setName(Name);
            placeroder_mod.capp.customer.setvalid(true);
            placeroder_mod.Addorderbtn.doClick();
            placeroder_mod.capp.customer.prepare_order(oid, "" + LocalDate.now());
            return;
        }
        if (e.getSource() == placeroder_mod.Next) {
            if (placeroder_mod.capp.customer.isvalid()) {
                placeroder_mod.pro_cust_id_label.setText("Customer ID : " + placeroder_mod.capp.customer.getCid());
                placeroder_mod.pro_order_id_label.setText("Order ID : " + placeroder_mod.capp.customer.getOrderid());
                String pid = "PRO" + placeroder_mod.capp.DBopertion.DButility.getLast_pro_id();
                placeroder_mod.proidtf.setText(pid);
                placeroder_mod.Addproductbtn.doClick();
                placeroder_mod.capp.customer.set_order_valid(true);
                placeroder_mod.capp.DBopertion.get_measurement();

                // placeroder_mod.capp.customer.prepare_order(pid, pid);
            } else {
                ord.order_warn_label.setText("Enter vaild Customer Detials");
                ord.order_warn_label.setVisible(true);
            }
        }
        if (e.getSource() == placeroder_mod.SetMeasurement) {
            Measurement_moudle mom = new Measurement_moudle(placeroder_mod);
            System.out.println(mom);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == placeroder_mod.Namefeild || e.getSource() == placeroder_mod.NumberFeild) {
            cust.custscreen_warning_label.setText("");
            cust.custscreen_warning_label.setVisible(false);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == placeroder_mod.ProSwingCostTF || e.getSource() == placeroder_mod.ProClothCostTF) {
            String sc, cc;
            sc = placeroder_mod.ProSwingCostTF.getText();
            cc = placeroder_mod.ProClothCostTF.getText();
            cc.trim();
            sc.trim();
            // if (sc.isEmpty()) {
            // placeroder_mod.ProTotalValueTF.setText("Rs " + cc);
            // return;
            // } else if (cc.isEmpty()) {
            // placeroder_mod.ProTotalValueTF.setText("Rs " + sc);
            // return;
            // } else {
            if (sc.isEmpty()) {
                placeroder_mod.ProTotalValueTF.setText("Rs " + cc);
                return;
            }

            if (cc.isEmpty()) {
                placeroder_mod.ProTotalValueTF.setText("Rs " + sc);

                return;
            }
            try {
                double totavalue = Double.parseDouble(sc) + Double.parseDouble(cc);
                placeroder_mod.ProTotalValueTF.setText("Rs " + totavalue);

            } catch (NumberFormatException ex) {
                System.out.println("Error in total value");
            }
            // }

        }
    }

}
