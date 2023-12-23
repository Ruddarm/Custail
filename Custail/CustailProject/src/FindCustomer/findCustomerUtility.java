package FindCustomer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import DatabaseMoudle.DatabaseOpertion;

public class findCustomerUtility extends ShowCustomer implements MouseListener, ActionListener {

    CustailPannel Find_Back_Pannel;
    JTextField srch;
    CustaiButton Find;
    DatabaseOpertion DBO;
    JFrame mainFrame;
    ShowCustomer showDetials;
    JDialog StatusBox;
    JLabel msglable;

    public findCustomerUtility(DatabaseOpertion dbo, JFrame mf) {
        super(dbo);
        this.DBO = dbo;
        this.mainFrame = mf;
        Find_Back_Pannel = new CustailPannel(Color.WHITE, Color.WHITE, 15);
        Find_Back_Pannel.setBounds(100, 50, 500, 50);
        mainFrame.add(Find_Back_Pannel);
        srch = new JTextField();
        srch.setBounds(10, 7, 335, 35);
        srch.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 18));
        srch.setBorder(BorderFactory.createLineBorder(Color.white));
        Find_Back_Pannel.add(srch);
        Find = new CustaiButton(new Color(55, 55, 55), null, 15);
        Find.setText("Customer");
        Find.setBounds(347, 5, 150, 40);
        Find.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 18));
        Find_Back_Pannel.add(Find);
        Find.setForeground(Color.white);
        Find.addMouseListener(this);
        Find.addActionListener(this);
        StatusBox = new JDialog();
        StatusBox.setLayout(null);
        StatusBox.setTitle("Customer Not Found");
        StatusBox.setResizable(false);
        StatusBox.getContentPane().setBackground(new Color(55, 55, 55));
        StatusBox.setBounds(500, 200, 300, 150);
        msglable = new JLabel("Customer Not Found");
        msglable.setFont(new Font("Times New Roman ", Font.BOLD, 16));
        msglable.setForeground(Color.red);
        msglable.setBounds(50, 40, 200, 30);
        StatusBox.add(msglable);
        StatusBox.setVisible(false);
        Cust_Detials_Frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (e.getSource() == Cust_Detials_Frame) {
                    System.out.println("Window Closed");
                    srch.setText(null);

                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // if (e.getSource() == Order_table) {
        // // System.out.println("cell cliked");
        // }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == Find) {
            Find.background_clr = Color.BLACK;

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == Find) {
            Find.background_clr = new Color(55, 55, 55);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Find) {
            showDetials = new ShowCustomer(DBO);
            showDetials.Cust_Detials_Frame.addWindowListener(this);
            if (!showDetials.setCustomerDetails(srch.getText())) {
                StatusBox.setVisible(true);
                showDetials.Cust_Detials_Frame.dispose();

                return;
            } else {
                showDetials.Cust_Detials_Frame.setVisible(true);
                if (showDetials.setOrderTable(showDetials.Customer_ID.getText())) {

                }
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == showDetials.Cust_Detials_Frame) {
            // System.out.println("Window closed here");
            srch.setText("");

        }
    }

}
