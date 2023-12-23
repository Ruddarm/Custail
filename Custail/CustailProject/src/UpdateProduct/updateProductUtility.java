package UpdateProduct;

import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import DatabaseMoudle.DatabaseOpertion;

// import javax.swing.J;;
public class updateProductUtility extends MouseAdapter implements ActionListener {
    String ProID, ORDID;
    DatabaseOpertion DBO;
    ResultSet proDetials;
    JDialog product_screenDialog, msgBox;
    CustaiButton update;
    JLabel CustIDlable, OrdIDlable, PROIDLable, ProTypeLable, ProSwingLable, ProClothLable, ProTotalCostLable,
            ProStatus, ProMID;
    JTextField CustIDtf, OrderIDtf, PROIDTF, ProTypeTF, ProSwingTF, ProClothTF, ProTotalCostTF, PROMID;
    CustailPannel productpannel;
    Font productfont = new Font("Times New Roman", Font.CENTER_BASELINE, 16);
    String status[] = { "PENDING", "CUTTING", "SWING", "FINISHING", "COMPLETED" };
    JComboBox<String> prostatBox = new JComboBox<>(status);

    public updateProductUtility(String Proid, DatabaseOpertion dbo) {
        this.ProID = Proid;
        this.DBO = dbo;
        // this.proDetials=rs;
        updateproductdriver();

    }

    public void updateproductdriver() {

        if (!checkProductExist()) {
            System.out.println("return");
            return;
        }
        // System.out.println("Code is here");
        product_screenDialog = new JDialog();
        product_screenDialog.setLayout(null);
        product_screenDialog.setTitle("CusTail Product");
        product_screenDialog.getContentPane().setBackground(new Color(55, 55, 55));
        product_screenDialog.setBounds(400, 100, 550, 450);
        product_screenDialog.setResizable(false);
        product_screenDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        product_screenDialog.setVisible(true);
        productpannel = new CustailPannel(Color.WHITE, Color.black, 15);
        productpannel.setLayout(null);
        productpannel.setBounds(25, 20, 490, 380);
        product_screenDialog.add(productpannel);
        /* Setting custmer ID */
        CustIDlable = new JLabel("Customer ID : ");
        CustIDlable.setBounds(90, 30, 150, 30);
        CustIDlable.setFont(productfont);
        CustIDtf = new JTextField();
        CustIDtf.setBounds(250, 30, 150, 30);
        CustIDtf.setEditable(false);
        CustIDtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productpannel.add(CustIDlable);
        productpannel.add(CustIDtf);
        /* Setting Order Id */
        OrdIDlable = new JLabel("Order ID : ");
        OrdIDlable.setBounds(90, 65, 150, 30);
        OrdIDlable.setFont(productfont);
        OrderIDtf = new JTextField();
        OrderIDtf.setBounds(250, 65, 150, 30);
        OrderIDtf.setEditable(false);
        OrderIDtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productpannel.add(OrdIDlable);
        productpannel.add(OrderIDtf);
        /* setting Product ID */
        PROIDLable = new JLabel("Prouct ID : ");
        PROIDLable.setBounds(90, 100, 150, 30);
        PROIDLable.setFont(productfont);
        PROIDTF = new JTextField();
        PROIDTF.setBounds(250, 100, 150, 30);
        PROIDTF.setEditable(false);
        PROIDTF.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productpannel.add(PROIDLable);
        productpannel.add(PROIDTF);
        /* Product Type */
        ProTypeLable = new JLabel("Product : ");
        ProTypeLable.setBounds(90, 135, 150, 30);
        ProTypeLable.setFont(productfont);
        ProTypeTF = new JTextField();
        ProTypeTF.setBounds(250, 135, 150, 30);
        ProTypeTF.setEditable(false);
        ProTypeTF.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productpannel.add(ProTypeLable);
        productpannel.add(ProTypeTF);
        /* Prodcut status */
        ProStatus = new JLabel("Status :");
        ProStatus.setBounds(90, 170, 150, 30);
        ProStatus.setFont(productfont);
        prostatBox.setBounds(250, 170, 150, 30);
        prostatBox.setEditable(false);
        prostatBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productpannel.add(prostatBox);
        productpannel.add(ProStatus);
        /* Setting product cost */
        ProSwingLable = new JLabel("Swing Cost :");
        ProSwingLable.setBounds(90, 205, 150, 30);
        ProSwingLable.setFont(productfont);
        ProSwingTF = new JTextField();
        ProSwingTF.setBounds(250, 205, 150, 30);
        ProSwingTF.setEditable(false);
        ProSwingTF.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productpannel.add(ProSwingLable);
        productpannel.add(ProSwingTF);

        ProClothLable = new JLabel("Cloth Cost :");
        ProClothLable.setBounds(90, 240, 150, 30);
        ProClothLable.setFont(productfont);
        ProClothTF = new JTextField();
        ProClothTF.setBounds(250, 240, 150, 30);
        ProClothTF.setEditable(false);
        ProClothTF.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productpannel.add(ProClothLable);
        productpannel.add(ProClothTF);
        /* Setting Update button */
        update = new CustaiButton(Color.BLACK, null, 20);
        update.setText("Update");
        update.setForeground(Color.white);
        update.setBounds(170, 300, 150, 40);
        productpannel.add(update);
        update.addMouseListener(this);
        // update.addMouseListener(this);
        update.addActionListener(this);
        setpruductDetials();

    }

    public void setpruductDetials() {
        try {
            do {
                PROIDTF.setText(proDetials.getString(1));
                OrderIDtf.setText(proDetials.getString(3));
                ORDID = OrderIDtf.getText();
                CustIDtf.setText(proDetials.getString(2));
                ProTypeTF.setText(proDetials.getString(5));
                ProSwingTF.setText("" + proDetials.getDouble(6));
                ProClothTF.setText("" + proDetials.getDouble(7));
                for (String string : status) {
                    if (string.equals(proDetials.getString(8))) {
                        prostatBox.setSelectedItem(string);
                        break;
                    }
                }

            } while (proDetials.next() != false);
        } catch (SQLException setpro) {
            System.out.println("Error in set product detial in update product moudle " + setpro);
        }
    }

    public boolean checkProductExist() {
        proDetials = DBO.getProductData(ProID);
        if (proDetials == null) {
            msgBoxsetup("Product Not found", 1);
            return false;
        }
        return true;

    }

    public void msgBoxsetup(String msg, int key) {
        msgBox = new JDialog();
        msgBox.setLayout(null);
        msgBox.getContentPane().setBackground(new Color(55, 55, 55));
        msgBox.setBounds(500, 200, 300, 150);
        msgBox.setFont(productfont);
        JLabel msglable = new JLabel(msg);
        msglable.setBounds(95, 40, 150, 30);
        if (key == 1) {
            msglable.setForeground(Color.red);
            msgBox.add(msglable);

        } else if (key == 2) {
            msglable.setForeground(Color.green);
            msgBox.add(msglable);
        }
        msgBox.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            if (DBO.updateProduct_Status("" + prostatBox.getSelectedItem(), ProID)) {

                // DBO.comitcommand();
                // product_screenDialog.dispose();
                if (DBO.UpdateOrderStatus(ORDID)) {
                    msgBoxsetup("Update Succesful", 2);
                    DBO.comitcommand();
                    product_screenDialog.dispose();
                }else {
                    DBO.rollbacmehtod();
                    msgBoxsetup("Erro while updating ", 1);
                }
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == update) {
            update.background_clr = Color.WHITE;
            update.painted_Clr = Color.BLACK;
            update.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == update) {
            update.background_clr = Color.BLACK;
            update.painted_Clr = Color.BLACK;
            update.setForeground(Color.WHITE);
        }

    }
    public void donothing(){

    }
}
