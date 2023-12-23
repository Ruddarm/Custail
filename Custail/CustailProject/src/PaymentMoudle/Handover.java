package PaymentMoudle;

import CustailComponent.CustaiButton;

import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustailComponent.CustailPannel;
import DashBoardMoudle.DashbordUtilit;
import DatabaseMoudle.DatabaseOpertion;
import UtilityModule.Measurement;

public class Handover {
    HandOverEventHandler hanoveeventobj;
    JDialog Handover_frame;
    CustailPannel Back_pannel;
    PayCustomer paycust;
    CustaiButton Cancel, HandOver;
    JLabel paying_amount_lable;
    DatabaseOpertion HandOverDb;
    DefaultTableModel DTM;
    JScrollPane jp;
    DashbordUtilit Dash_utiliyt;
    JTextField TotalAmt, PaidAmt, BalanceAmt, Amount;
    JTableHeader TH;
    JDialog msgBox;
    JTable Selected_order_Table;
    Measurement AddMeasure_Head, add_Temp;
    DefaultTableCellRenderer tablerender;
    int highlightrow[], highlightcolum[];
    int track = 0;
    String column[] = { "Order ID", "Total", "Paid", "Balance" };

    Handover(PayCustomer paycust,DatabaseOpertion dbo, DashbordUtilit dash_utilit,JDialog msgbox) {
        this.HandOverDb=dbo;
        this.paycust = paycust;
        this.Dash_utiliyt=dash_utilit;
        this.msgBox=msgbox;
        Handover_frame = new JDialog();
        Handover_frame.setTitle("Hand Over Order");
        Handover_frame.setLayout(null);
        Handover_frame.getContentPane().setBackground(new Color(55, 55, 55));
        Handover_frame.setResizable(false);
        Handover_frame.setBounds(350, 100, 700, 400);
        Handover_frame.setVisible(true);
        Back_pannel = new CustailPannel(new Color(225, 225, 225), Color.WHITE, 20);
        Back_pannel.setBounds(30, 20, 630, 320);
        Back_pannel.setLayout(null);
        Handover_frame.add(Back_pannel);
        DTM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        for (String string : column) {
            DTM.addColumn(string);
        }
        if (paycust.selectedOrderListHead != null) {
            selectedOrder temp = paycust.selectedOrderListHead;
            while (temp != null) {
                String data[] = { temp.Order_ID, "" + temp.ordervalue, "" + temp.paidamount, "" + temp.balanceamt };
                DTM.addRow(data);
                temp = temp.orderNext;
            }
        }
        Selected_order_Table = new JTable(DTM);
        Selected_order_Table.setBackground(Color.white);
        Selected_order_Table.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        Selected_order_Table.setRowSelectionAllowed(false);
        Selected_order_Table.setColumnSelectionAllowed(false);
        Selected_order_Table.setCellSelectionEnabled(true);
        Selected_order_Table.removeEditor();
        Selected_order_Table.setLayout(null);
        jp = new JScrollPane(Selected_order_Table);
        jp.setBounds(10, 20, 600, 100);
        jp.setBackground(Color.white);
        Back_pannel.add(jp);
        Handover_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /* Arranging INput area */
        TotalAmt = new JTextField("Total : Rs " + paycust.getSelected_ord_Value());
        PaidAmt = new JTextField("Paid : Rs " + paycust.getSelected_ord_paid_amt());
        BalanceAmt = new JTextField("Balance : Rs " + paycust.getSelected_ord_balance_amt());
        TotalAmt.setFont(new Font("Times new Roman ", Font.CENTER_BASELINE, 12));
        TotalAmt.setEditable(false);
        TotalAmt.setBackground(Color.white);
        TotalAmt.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        TotalAmt.setBounds(60, 125, 150, 30);
        PaidAmt.setFont(new Font("Times new Roman ", Font.CENTER_BASELINE, 12));
        PaidAmt.setEditable(false);
        PaidAmt.setBackground(Color.white);
        PaidAmt.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        PaidAmt.setBounds(230, 125, 150, 30);
        BalanceAmt.setFont(new Font("Times new Roman ", Font.CENTER_BASELINE, 12));
        BalanceAmt.setEditable(false);
        BalanceAmt.setBackground(Color.white);
        BalanceAmt.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));
        BalanceAmt.setBounds(400, 125, 150, 30);
        Back_pannel.add(TotalAmt);
        Back_pannel.add(PaidAmt);
        Back_pannel.add(BalanceAmt);
        PaidAmt=new JTextField();
        paying_amount_lable=new JLabel("Amount :");
        // paying_amount_lable.setText(null);
        paying_amount_lable.setFont(new Font("Times New Roman", Font.LAYOUT_RIGHT_TO_LEFT, 14));
        paying_amount_lable.setBounds(185, 180, 100, 30);

        PaidAmt.setBackground(Color.WHITE);
        PaidAmt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        PaidAmt.setBounds(295, 180, 150, 30);
        Back_pannel.add(paying_amount_lable);
        Back_pannel.add(PaidAmt);
        HandOver=new CustaiButton(Color.WHITE, null, 15);
        HandOver.setText("Hand Over");
        HandOver.setBounds(320, 250, 150, 40);
        HandOver.painted_Clr=Color.black;
        Back_pannel.add(HandOver);
        Cancel=new CustaiButton(Color.BLACK, null, 15);
        Cancel.setForeground(Color.WHITE);
        Cancel.setText("Cancel");
        Cancel.setBounds(160, 250, 150, 40);
        Cancel.painted_Clr=Color.black;
        Back_pannel.add(Cancel);
        hanoveeventobj=new HandOverEventHandler(this);
        Cancel.addMouseListener(hanoveeventobj);
        Cancel.addActionListener(hanoveeventobj);
        HandOver.addMouseListener(hanoveeventobj);
        HandOver.addActionListener(hanoveeventobj);
    }

}
