/* THis class will load an interface to enter customer data
 * writen by rudarm mourya on 4th Dec 2023
 */

package AddorderInterface;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
public class Addcustmerscreen {
    PlaceOrderMoudle ordermodule;
    CustailPannel custmerBackeScreen, Custmer_data_screen;
    public JLabel custscreen_warning_label;

    Addcustmerscreen(PlaceOrderMoudle obMoudle) {
        this.ordermodule = obMoudle;
        custmerBackeScreen = new CustailPannel(new Color(210, 210, 210), Color.BLACK, 20);
        custmerBackeScreen.setBounds(60, 50, 430, 450);
        this.ordermodule.Background_pannel.add(custmerBackeScreen);
        /* Setting customer data screen */
        Custmer_data_screen = new CustailPannel(Color.white, Color.BLACK, 10);
        Custmer_data_screen.setBounds(25, 50, 380, 200);
        custmerBackeScreen.add(Custmer_data_screen);
        // Setting input Area for customer data
        this.custscreen_warning_label=new JLabel("Warrnng");
        this.ordermodule.NameLabel = new JLabel("Enter Name     :");
        this.ordermodule.NumberLabel = new JLabel("Enter Number   :");
        this.ordermodule.cust_id_label = new JLabel("Customer ID   :");
        this.ordermodule.cust_idFeild = new JTextField("CID123XXX");
        this.ordermodule.cust_idFeild.setEditable(false);
        this.ordermodule.NumberFeild = new JTextField();
        this.ordermodule.Namefeild = new JTextField();
        this.custscreen_warning_label.setBounds(100, 10, 150, 30);
        this.ordermodule.cust_id_label.setBounds(30, 50, 150, 30);
        this.ordermodule.cust_idFeild.setBounds(200, 50, 150, 30);
        this.ordermodule.NameLabel.setBounds(30, 85, 150, 30);
        this.ordermodule.Namefeild.setBounds(200, 85, 150, 30);
        this.ordermodule.NumberLabel.setBounds(30, 120, 150, 30);
        this.ordermodule.NumberFeild.setBounds(200, 120, 150, 30);
        this.custscreen_warning_label.setForeground(Color.red);
        this.custscreen_warning_label.setFont(new Font("Times New Roman", Font.ITALIC, 16));
        this.custscreen_warning_label.setVisible(false);
        this.ordermodule.NameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.cust_id_label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.ordermodule.NumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        Custmer_data_screen.add(this.custscreen_warning_label);
        Custmer_data_screen.add(this.ordermodule.cust_id_label);
        Custmer_data_screen.add(this.ordermodule.cust_idFeild);
        Custmer_data_screen.add(this.ordermodule.NameLabel);
        Custmer_data_screen.add(this.ordermodule.Namefeild );
        Custmer_data_screen.add(this.ordermodule.NumberLabel);
        Custmer_data_screen.add(this.ordermodule.NumberFeild);
        this.ordermodule.Submit=new CustaiButton(Color.GREEN, null, 17);
        this.ordermodule.Submit.painted_Clr=Color.BLACK;
        this.ordermodule.Submit.setText("SUBMIT");
        this.ordermodule.Submit.setBounds(140,280,150,40);
        custmerBackeScreen.add(this.ordermodule.Submit);
        // this.ordermodule.Background_pannel.remove(custmerBackeScreen);
        // custmerBackeScreen.setVisible(false);
        
    }

}
