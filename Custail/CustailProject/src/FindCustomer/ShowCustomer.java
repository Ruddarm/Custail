package FindCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import java.awt.*;
import CustailComponent.CustailPannel;
import DatabaseMoudle.DatabaseOpertion;
import FindOrderMoudle.findorderUtility;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumnModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;;
public class ShowCustomer extends WindowAdapter  implements TableColumnModelListener {
    DatabaseOpertion DBO;
    JDialog Cust_Detials_Frame, Status_message;
    CustailPannel Back_pannel, CustBack_pannel, findcutomer;
    DefaultTableModel DTM;
    JScrollPane jp;

    ResultSet DatabseResult;
    JTableHeader TH;
    JTable Order_table;
    Font viewordfont = new Font("Times New Roman", Font.CENTER_BASELINE, 16);
    Font totalfont = new Font("Times New Roman", Font.CENTER_BASELINE, 14);
    JLabel CustomerName, CustomerNumber, Customer_ID, CustomerPng, cred_amt, debit_amt;
    JTextField ord_val, balanceamt, Advane_payment, numbertofind;

    String paymenttableHeadder[] = { "Order ID", "Order Date", "Order Status", "Order Qty", "Order Value",
            "Amount Paid", "Balance" };
    // OrderTable=new JTable();
    ImageIcon im = new ImageIcon(
            "C:\\Users\\Ruddarm\\Downloads\\Custail\\CustailProject\\src\\custtailImagelib\\customer.png");

    public ShowCustomer(DatabaseOpertion Database_operation) {
        DBO = Database_operation;
        Cust_Detials_Frame = new JDialog();
        // Cust_Detials_Frame.setVisible(false);
        Cust_Detials_Frame.setTitle("CutsTail Customer");
        Cust_Detials_Frame.setLayout(null);
        Cust_Detials_Frame.getContentPane().setBackground(new Color(55, 55, 55));
        Cust_Detials_Frame.setResizable(false);
        Cust_Detials_Frame.setBounds(300, 100, 800, 550);
        Cust_Detials_Frame.setVisible(false);
        Cust_Detials_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Status_message = new JDialog();
        Status_message.getContentPane().setBackground(new Color(55, 55, 55));
        Status_message.setBounds(500, 250, 300, 100);
        Status_message.setLayout(null);
        CustBack_pannel = new CustailPannel(new Color(87, 85, 85), Color.WHITE, 10);
        CustBack_pannel.setBounds(75, 50, 650, 145);
        Cust_Detials_Frame.add(CustBack_pannel);
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
        CustBack_pannel.add(CustomerName);
        CustBack_pannel.add(Customer_ID);
        CustBack_pannel.add(CustomerNumber);
        CustBack_pannel.add(CustomerPng);
        CustBack_pannel.add(cred_amt);
        CustBack_pannel.add(debit_amt);
        Back_pannel = new CustailPannel(new Color(225, 225, 225), Color.WHITE, 20);
        Back_pannel.setBounds(75, 210, 650, 240);
        Back_pannel.setLayout(null);
        Cust_Detials_Frame.add(Back_pannel);
        // setCustomerDetails();
        DTM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            };
        };
        for (String string : paymenttableHeadder) {
            DTM.addColumn(string);

        }
        Order_table = new JTable(DTM);
        Order_table.setLayout(null);
        Order_table.setBackground(Color.white);
        Order_table.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        Order_table.setRowSelectionAllowed(false);
        Order_table.setColumnSelectionAllowed(false);
        Order_table.setCellSelectionEnabled(true);
        Order_table.removeEditor();
        Order_table.setLayout(null);
        Order_table.requestFocus();

        Order_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Order_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SwingUtilities.invokeLater(() -> {
            Order_table.requestFocus();
        });

        TableColumnModel colummodel = Order_table.getColumnModel();
        colummodel.addColumnModelListener(this);
        jp = new JScrollPane(Order_table);
        jp.setBounds(5, 10, 640, 200);
        Back_pannel.add(jp);
        
    }

    public boolean setCustomerDetails(String CustomerNum) {
        ResultSet Cust_Details = DBO.cust_Exist_View(CustomerNum);
        if (Cust_Details == null) {
            return false;
        }
        try {
            do {
                this.Customer_ID.setText(Cust_Details.getString(1));
                this.CustomerName.setText(Cust_Details.getString(2));
                this.CustomerNumber.setText(Cust_Details.getString(3));
                this.cred_amt.setText("Rs " + Cust_Details.getDouble(4));
                this.debit_amt.setText("Rs " + Cust_Details.getDouble(5));
                Cust_Details.next();
                return true;
            } while (Cust_Details.next() != false);
        } catch (SQLException setCustDetail) {
            System.out.println("Error while setting customer Details ");
            return false;
        }
    }

    public boolean setOrderTable(String Cid) {
        ResultSet Order_detial = DBO.getCust_order(Cid);
        if (Order_detial != null) {
            try {
                do {
                    double deb = Order_detial.getDouble(5);
                    double cred = Order_detial.getDouble(6);
                    String data[] = { Order_detial.getString(1), Order_detial.getString(3), Order_detial.getString(7),
                            "" + Order_detial.getInt(4), "" + deb,
                            "" + cred, "" + (deb - cred) };
                    DTM.addRow(data);

                } while (Order_detial.next() != false);
                Order_table.getColumnModel().getColumn(0).setPreferredWidth(30);
                return true;
            } catch (SQLException ex) {
                System.out.println(" Error in  Set Order Table " + ex);
                return false;
            }
        }
        return false;
    }

    @Override
    public void columnAdded(TableColumnModelEvent e) {
    }

    @Override
    public void columnRemoved(TableColumnModelEvent e) {
    }

    @Override
    public void columnMoved(TableColumnModelEvent e) {
    }

    @Override
    public void columnMarginChanged(ChangeEvent e) {
    }

    @Override
    public void columnSelectionChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            System.out.println("selected");
            int rwo = Order_table.getSelectedRow();
            if (rwo != -1&&Order_table.getSelectedColumn()==0) {
                String Proid = "" + Order_table.getValueAt(rwo, 0);
                System.out.println(Proid);
                findorderUtility findord = new findorderUtility(Proid, DBO);
                findord.donothing();
                Order_table.clearSelection();
            }

        }

    }
    @Override
    public void windowClosing(WindowEvent e) {
        // // TODO Auto-generated method stub
        // super.windowClosing(e);
    }
}
