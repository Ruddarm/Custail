package AddorderInterface;

import UtilityModule.Order;
import java.awt.event.*;

public class resetter implements ActionListener{
    PlaceOrderMoudle pom;
    resetter(PlaceOrderMoudle pom){
        this.pom=pom;
        pom.cancel_pro_screenbtn.addActionListener(this);
        pom.Cancel.addActionListener(this);
        // pom.vom.Cancel.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==pom.cancel_pro_screenbtn||e.getSource()==pom.Cancel) {
            pom.capp.customer=new Order();
            pom.namefieldord.setText("");
            pom.numberfeildord.setText("");
            pom.Namefeild.setText("");
            pom.NumberFeild.setText("");
            pom.order_qty_feild.setText("");
            pom.order_value_feild.setText("");
            pom.proidtf.setText("PRO"+pom.capp.DBopertion.DButility.getLast_pro_id());
            pom.orderidfeild.setText("ORD"+pom.capp.DBopertion.DButility.getLast_order_id()); 
            pom.cust_idFeild.setText("CID"+pom.capp.DBopertion.DButility.getLast_cust_id());
            pom.custidfeildord.setText("CID"+pom.capp.DBopertion.DButility.getLast_cust_id()); 
            pom.pro_cust_id_label.setText("Customer ID :");
            pom.pro_order_id_label.setText("Order ID: ");
            pom.ProSwingCostTF.setText("0");
            pom.ProClothCostTF.setText("0");
            pom.ProTotalValueTF.setText("0");
            pom.Addcustomerbtn.doClick();
                      // pom.namefieldord.setFont("");
            
        }

    }
}
