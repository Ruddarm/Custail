package PaymentMoudle;

import DatabaseMoudle.DatabaseOpertion;
import java.sql.Connection;
import java.awt.*;
import AddorderInterface.PlaceOrderMoudle;
import AddorderInterface.ViewOrderMoudule;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import DashBoardMoudle.DashbordUtilit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PaymentMoudleInterface {
    Connection DBc;
    DatabaseOpertion DBO;
    PayCustomer customer;
    JDialog Pay_Order_frame, Status_message;
    DashbordUtilit dashboard;
    CustailPannel Back_pannel, CustBack_pannel, findcutomer;
    PlaceOrderMoudle pom;
    CustaiButton find, Pay, Hand_Over;
    DefaultTableModel DTM;
    JScrollPane jp;
    PaymentEventhandler PayEvent;
    CustaiButton PayNow;
    JTableHeader TH;
    JTable Order_table;
    Font viewordfont = new Font("Times New Roman", Font.CENTER_BASELINE, 16);
    Font totalfont = new Font("Times New Roman", Font.CENTER_BASELINE, 14);
    JLabel CustomerName, CustomerNumber, Customer_ID, CustomerPng, cred_amt, debit_amt;
    JTextField ord_val, balanceamt, Advane_payment, numbertofind;
    String paymenttableHeadder[] = { "", "Order ID", "Order Date", "Qty", "Order Value", "Amount Paid", "Balance" };
    // OrderTable=new JTable();
    ImageIcon im = new ImageIcon(
            "C:\\Users\\Ruddarm\\Downloads\\Custail\\CustailProject\\src\\custtailImagelib\\customer.png");

    public PaymentMoudleInterface(DatabaseOpertion Database_operation, DashbordUtilit dash) {
        DBO = Database_operation;
        this.dashboard = dash;
        customer = new PayCustomer();
        ViewOrderMoudule.datepicker();
        Pay_Order_frame = new JDialog();
        Pay_Order_frame.setTitle("CutsTail Order");
        PayEvent = new PaymentEventhandler(this);
        Pay_Order_frame.setLayout(null);
        Pay_Order_frame.getContentPane().setBackground(new Color(55, 55, 55));
        Pay_Order_frame.setResizable(false);
        Pay_Order_frame.setBounds(300, 50, 800, 600);
        Pay_Order_frame.setVisible(true);
        Pay_Order_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Status_message = new JDialog();
        Status_message.getContentPane().setBackground(new Color(55, 55, 55));
        Status_message.setBounds(500, 250, 300, 100);
        Status_message.setLayout(null);
        CustBack_pannel = new CustailPannel(new Color(87, 85, 85), Color.WHITE, 10);
        CustBack_pannel.setBounds(75, 50, 650, 145);
        Pay_Order_frame.add(CustBack_pannel);
        CustomerPng = new JLabel();
        CustomerPng.setIcon(im);
        CustomerPng.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        CustomerPng.setBackground(new Color(55, 55, 55));
        CustomerPng.setBounds(30, 10, 130, 130);
        CustomerName = new JLabel();
        CustomerNumber = new JLabel();
        Customer_ID = new JLabel();
        cred_amt = new JLabel();
        debit_amt = new JLabel();
        Customer_ID.setFont(viewordfont);
        Customer_ID.setForeground(Color.white);
        Customer_ID.setBounds(170, 10, 150, 30);
        CustomerName.setBounds(170, 40, 150, 30);
        CustomerName.setFont(viewordfont);
        CustomerName.setForeground(Color.white);
        CustomerNumber.setForeground(Color.white);
        CustomerNumber.setBounds(170, 70, 150, 30);
        CustomerNumber.setFont(viewordfont);
        cred_amt.setFont(viewordfont);
        cred_amt.setForeground(Color.GREEN);
        cred_amt.setBounds(170, 110, 150, 30);
        debit_amt.setFont(viewordfont);
        debit_amt.setForeground(Color.RED);
        debit_amt.setBounds(310, 110, 150, 30);
        numbertofind = new JTextField();
        numbertofind.setBounds(5, 5, 100, 30);
        numbertofind.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        numbertofind.setFont(new Font("Times New Roman", Font.BOLD, 16));
        findcutomer = new CustailPannel(Color.WHITE, Color.black, 15);
        findcutomer.setBounds(250, 52, 250, 40);
        findcutomer.add(numbertofind);
        find = new CustaiButton(new Color(37, 37, 37), null, 15);
        find.setBounds(145, 5, 100, 30);
        find.setText("FIND");
        find.addActionListener(PayEvent);
        find.setForeground(Color.WHITE);
        findcutomer.add(find);
        CustBack_pannel.add(findcutomer);
        CustBack_pannel.add(CustomerName);
        CustBack_pannel.add(Customer_ID);
        CustBack_pannel.add(CustomerNumber);
        CustBack_pannel.add(CustomerPng);
        CustBack_pannel.add(cred_amt);
        CustBack_pannel.add(debit_amt);
        Back_pannel = new CustailPannel(new Color(225, 225, 225), Color.WHITE, 20);
        Back_pannel.setBounds(75, 210, 650, 240);
        Back_pannel.setLayout(null);
        Pay_Order_frame.add(Back_pannel);
        DTM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Boolean.class;
                    // break;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Double.class;
                    case 5:
                        return Double.class;
                    case 6:
                        return Double.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        for (String string : paymenttableHeadder) {
            DTM.addColumn(string);
        }
        Order_table = new JTable(DTM);
        Order_table.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        Order_table.setRowSelectionAllowed(false);
        Order_table.setColumnSelectionAllowed(false);
        Order_table.setCellSelectionEnabled(true);
        Order_table.removeEditor();
        Order_table.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        Order_table.setLayout(null);
        TH = Order_table.getTableHeader();
        TH.setBackground(new Color(247, 236, 211));
        TH.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        jp = new JScrollPane(Order_table);
        jp.setBounds(25, 10, 600, 150);
        Back_pannel.add(jp);
        ord_val = new JTextField("Total : ₹ ");
        Advane_payment = new JTextField("Paid : ₹ ");
        balanceamt = new JTextField("Balance : ₹ ");
        balanceamt.setEditable(false);
        ord_val.setEditable(false);
        Advane_payment.setEditable(false);
        ord_val.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        Advane_payment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        balanceamt.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        ord_val.setFont(totalfont);
        Advane_payment.setFont(totalfont);
        balanceamt.setFont(totalfont);
        Advane_payment.setForeground(Color.GREEN);
        balanceamt.setForeground(Color.red);
        ord_val.setBounds(70, 165, 150, 30);
        Advane_payment.setBounds(240, 165, 150, 30);
        balanceamt.setBounds(410, 165, 150, 30);
        Back_pannel.add(Advane_payment);
        Back_pannel.add(ord_val);
        Back_pannel.add(balanceamt);
        PayNow = new CustaiButton(Color.BLACK, null, 15);
        PayNow.setForeground(Color.GREEN);
        PayNow.painted_Clr = Color.WHITE;
        PayNow.setText("PAY");
        PayNow.setBounds(570, 470, 150, 40);
        PayNow.addActionListener(PayEvent);
        PayNow.addMouseListener(PayEvent);
        Pay_Order_frame.add(PayNow);
    }

    public void setcustomerpannel() {
        CustomerName.setText(customer.getName());
        CustomerNumber.setText(customer.getNumber());
        Customer_ID.setText(customer.getCustID());
        cred_amt.setText("₹ " + customer.getCreditamt());
        debit_amt.setText("₹ " + (customer.getDebitamt()));
    }

    public void addordertotable() {
        OrderList temp = customer.getOrderHead();
        int i = 0;
        while (temp != null) {
            DTM.addRow(new java.lang.Object[0]);
            DTM.setValueAt(false, i, 0);
            System.out.println("DTM IS " + DTM);
            DTM.setValueAt(temp.getOrderID(), i, 1);
            DTM.setValueAt(temp.getOrderDate(), i, 2);
            DTM.setValueAt(temp.getOrd_qty(), i, 3);
            DTM.setValueAt(temp.getOrderValue(), i, 4);
            DTM.setValueAt(temp.getOrderPaidAmt(), i, 5);
            DTM.setValueAt(((temp.getOrderValue() - temp.getOrderPaidAmt())), i, 6);
            temp = temp.getOrde_next();
            i++;
        }
        Order_table.getColumnModel().getColumn(0).setPreferredWidth(10);
        Order_table.addMouseListener(PayEvent);
        Pay_Order_frame.addWindowListener(PayEvent);

    }

    public static void SetStatusMessage(String msgg, int Stats, JDialog jd) {
        JLabel msg = new JLabel(msgg);
        msg.setBounds(75, 10, 150, 30);
        msg.setVisible(false);
        if (Stats == 1) {
            msg.setVisible(true);
            msg.setForeground(Color.GREEN);
            jd.add(msg);
            return;
        } else if (Stats == 2) {
            msg.setForeground(Color.RED);
            jd.add(msg);
            // msg.setBounds(75, 10, 150, 30);

            msg.setVisible(true);
            return;
        }

    }

}
