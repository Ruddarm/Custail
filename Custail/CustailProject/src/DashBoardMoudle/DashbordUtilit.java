package DashBoardMoudle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import CustailDriver.CustailApp;
// import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class DashbordUtilit {
    CustailApp cApp;
    CustailPannel DashBoardPannel, custpan, ordpan, handoverpand;
    // Font
    Font Dashboardfont = new Font("Times new Roman", Font.CENTER_BASELINE, 14);
    // Border
    Border dashboradborder = BorderFactory.createLineBorder(Color.white);
    ImageIcon Customerimg, Orderimg, Handoverimg;
    CustaiButton customer_lable, OrderLable, Hande_OVerLable;
    JProgressBar custbar, ordBar, handoverbar;
    JLabel cutlable, ord, hand;
    Color panbg = new Color(97, 97, 97);
    ProgressBarUI ui;
    int total_order, toal_handover, total_completed;

    public DashbordUtilit(CustailApp cApp) {

        this.cApp = cApp;
        DashBoardPannel = new CustailPannel(new Color(57, 57, 57), Color.white, 15);
        DashBoardPannel.setBounds(100, 150, 500, 320);
        DashBoardPannel.setLayout(null);
        cApp.mainScreen.add(DashBoardPannel);
        custpan = new CustailPannel(panbg, Color.white, 20);
        custpan.setBounds(30, 40, 440, 75);
        ordpan = new CustailPannel(panbg, Color.WHITE, 20);
        ordpan.setBounds(30, 120, 440, 75);
        handoverpand = new CustailPannel(panbg, Color.white, 20);
        handoverpand.setBounds(30, 200, 440, 75);
        DashBoardPannel.add(custpan);
        DashBoardPannel.add(ordpan);
        DashBoardPannel.add(handoverpand);
        Customerimg = new ImageIcon("C:\\Users\\Ruddarm\\Downloads\\Custail\\CustailProject\\src\\custtailImagelib\\n" + //
                "ew-account.png");
        Orderimg = new ImageIcon(
                "C:\\Users\\Ruddarm\\Downloads\\Custail\\CustailProject\\src\\custtailImagelib\\booking.png");
        Handoverimg = new ImageIcon("CustailProject\\src\\custtailImagelib\\delivery.png");
        customer_lable = new CustaiButton(Color.white, null, 10);
        OrderLable = new CustaiButton(Color.white, null, 10);
        Hande_OVerLable = new CustaiButton(Color.white, null, 10);
        customer_lable.setBackground(Color.white);
        customer_lable.painted_Clr = Color.black;
        OrderLable.setBackground(Color.WHITE);
        Hande_OVerLable.setBackground(Color.white);
        customer_lable.setBorder(dashboradborder);
        customer_lable.setBounds(5, 5, 65, 65);
        OrderLable.setBounds(5, 5, 65, 65);
        Hande_OVerLable.setBounds(5, 5, 65, 65);
        Hande_OVerLable.setIcon(Handoverimg);
        customer_lable.setIcon(Customerimg);
        OrderLable.setIcon(Orderimg);
        custpan.add(customer_lable);
        ordpan.add(OrderLable);
        handoverpand.add(Hande_OVerLable);
        custbar = new JProgressBar(0, 100);
        ordBar = new JProgressBar(0, total_order);
        handoverbar = new JProgressBar(0, total_completed);
        custbar.setBackground(Color.WHITE);
        ordBar.setBackground(Color.white);
        handoverbar.setBackground(Color.white);
        custbar.setBorderPainted(false);
        custbar.setFocusable(false);
        custbar.setValue(10);
        custbar.setUI(new BasicProgressBarUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                int fil = (custbar.getMaximum() * custbar.getValue()) / 100;
                // super.paint(g, c);
                Graphics2D g2 = (Graphics2D) g;
                // g.drawRoundRect(custbar.getX(), custbar.getY(), custbar.getWidth(),
                // custbar.getHeight(),50,50 );
                g2.setColor(Color.green);
                g2.fillRect(0, 0, fil, 15);
            }

        });
        ordBar.setUI(new ProgressBarUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                int fil = (ordBar.getValue() * 100);
                double res = (double) fil / ordBar.getMaximum();
                fil = (int) res;
                Graphics2D g2 = (Graphics2D) g;
                // System.out.println("FIl is " + fil);

                g2.setColor(Color.green);
                g2.fillRect(0, 0, ((ordBar.getWidth() * fil) / 100), 15);
            }
        });
        handoverbar.setUI(new ProgressBarUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                int fil = (handoverbar.getValue()*100);
                double res = (double) fil / handoverbar.getMaximum();
                // res = res * 100;
                fil = (int) res;

                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.green);
                g2.fillRect(0, 0, ((handoverbar.getWidth() * fil) / 100), 15);
            }
        });
        custbar.setBounds(75, 40, 300, 12);
        ordBar.setBounds(75, 40, 300, 12);
        handoverbar.setBounds(75, 40, 300, 12);
        custpan.add(custbar);
        ordpan.add(ordBar);
        handoverpand.add(handoverbar);
        cutlable = new JLabel();
        System.out.println(14 * cutlable.getText().length());
        ord = new JLabel();
        hand = new JLabel();
        cutlable.setFont(Dashboardfont);
        ord.setFont(Dashboardfont);
        hand.setFont(Dashboardfont);
        cutlable.setForeground(Color.white);
        ord.setForeground(Color.white);
        hand.setForeground(Color.WHITE);
        custpan.add(cutlable);
        ordpan.add(ord);
        handoverpand.add(hand);

    }

    public void  dashBoardrefersh() {
        this.ordBar.setMaximum(cApp.DBopertion.gettoatlpendingOrder());
        this.ordBar.setValue(cApp.DBopertion.getorder_compelete());
        this.custbar.setMaximum(100);
        this.custbar.setValue(cApp.DBopertion.get_total_customer());
        this.handoverbar.setMaximum(cApp.DBopertion.gettotalorder());
        this.handoverbar.setValue(cApp.DBopertion.handovertocustomer());
        System.out.println(handoverbar.getValue());
        this.cutlable.setText("" + custbar.getValue() + "/" + custbar.getMaximum());
        this.ord.setText("" + ordBar.getValue() + "/" + ordBar.getMaximum());
        this.hand.setText("" + handoverbar.getValue() + "/" + handoverbar.getMaximum());
        cutlable.setBounds((375 - (8 * cutlable.getText().length())), 20, 100, 20);
        ord.setBounds((375 - (8 * ord.getText().length())), 20, 100, 20);
        hand.setBounds((375 - (8 * hand.getText().length())), 20, 100, 20);
        this.ordBar.repaint();
        this.custbar.repaint();
        this.handoverbar.repaint();

    }

}
