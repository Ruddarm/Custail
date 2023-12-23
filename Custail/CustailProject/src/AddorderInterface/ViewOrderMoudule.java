package AddorderInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import UtilityModule.Product;
public class ViewOrderMoudule {
    JDialog View_order_Frame;
    CustailPannel Back_pannel, CustBack_pannel, Date_panne;
    PlaceOrderMoudle pom;
    CustaiButton Place_order, Cancel;
    DefaultTableModel DTM, DateDTM;
    JScrollPane jp, DateJP;
    JTableHeader TH;
    Font devdatefont = new Font("Times New Roman", Font.CENTER_BASELINE, 12);
    Product prtemp;
    JComboBox<String> DayBox, YearBox, MonthBox;
    JTable Order_table;
    Font viewordfont = new Font("Times New Roman", Font.BOLD, 16);
    JLabel CustomerName, CustomerNumber, Customer_ID, CustomerPng, Payment;
    JTextField ord_val, ord_qty, Advane_payment;
    static String date[] = new String[35], Year[] = new String[35],
            Month[] = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };;
    ImageIcon im = new ImageIcon(
            "C:\\Users\\Ruddarm\\Downloads\\Custail\\CustailProject\\src\\custtailImagelib\\customer.png");

    public ViewOrderMoudule(PlaceOrderMoudle pom) {
        // Jsvg
        ViewOrderMoudule.datepicker();
        this.pom = pom;
        View_order_Frame = new JDialog();
        View_order_Frame.setTitle("CutsTail Order");
        View_order_Frame.setLayout(null);
        View_order_Frame.getContentPane().setBackground(new Color(55, 55, 55));
        View_order_Frame.setResizable(false);
        View_order_Frame.setBounds(350, 100, 700, 500);
        View_order_Frame.setVisible(true);
        CustBack_pannel = new CustailPannel(new Color(87, 85, 85), Color.WHITE, 10);
        CustBack_pannel.setBounds(30, 10, 630, 145);
        View_order_Frame.add(CustBack_pannel);
        CustomerPng = new JLabel();
        CustomerPng.setIcon(im);
        // CustomerPng.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        // RenderingHints.VALUE_ANTIALIAS_ON);
        CustomerPng.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
        CustomerPng.setBackground(new Color(55, 55, 55));
        CustomerPng.setBounds(30, 10, 130, 130);
        CustomerName = new JLabel(pom.capp.customer.getName());
        CustomerNumber = new JLabel(pom.capp.customer.getNumber());
        Customer_ID = new JLabel(pom.capp.customer.getCid());
        CustBack_pannel.add(CustomerPng);
        CustomerName.setFont(viewordfont);
        CustomerName.setForeground(Color.white);
        CustomerName.setBounds(170, 40, 150, 30);
        CustomerNumber.setFont(viewordfont);
        CustomerNumber.setForeground(Color.white);
        CustomerNumber.setBounds(170, 70, 150, 30);
        Customer_ID.setFont(viewordfont);
        Customer_ID.setForeground(Color.white);
        Customer_ID.setBounds(170, 10, 150, 30);
        CustBack_pannel.add(CustomerName);
        CustBack_pannel.add(Customer_ID);
        CustBack_pannel.add(CustomerNumber);
        Back_pannel = new CustailPannel(new Color(225, 225, 225), Color.WHITE, 20);
        Back_pannel.setBounds(30, 160, 630, 240);
        Back_pannel.setLayout(null);
        View_order_Frame.add(Back_pannel);
        DTM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        String Column_Head[] = { "Pro ID", "Pro Type", "Swing Cost", "Cloth Cost", "Total Cost", "Measure ID" };
        // DTM.addColumn(Column_Head[]);
        for (String string : Column_Head) {
            DTM.addColumn(string);
        }
        prtemp = pom.capp.customer.getheadproduct();
        while (prtemp != null) {
            String data[] = { prtemp.getProid(), prtemp.getProductType(), "" + prtemp.getSwingcost(),
                    "" + prtemp.getClothcost(), "" + prtemp.getTotal_Cost(), "" + prtemp.getMid() };
            DTM.addRow(data);
            prtemp = prtemp.getPrnext();
        }
        Order_table = new JTable(DTM);
        Order_table.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        Order_table.setRowSelectionAllowed(false);
        Order_table.setColumnSelectionAllowed(false);
        Order_table.setCellSelectionEnabled(true);
        Order_table.removeEditor();
        Order_table.setLayout(null);
        TH = Order_table.getTableHeader();
        TH.setBackground(new Color(247, 236, 211));
        TH.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 16));
        jp = new JScrollPane(Order_table);
        jp.setBounds(10, 10, 600, 150);
        Back_pannel.add(jp);
        Payment = new JLabel("Paid Amount :");
        ord_qty = new JTextField("Qty :" + pom.capp.customer.getOrder_qty());
        ord_val = new JTextField("Total Rs:" + pom.capp.customer.getOrder_value());
        Advane_payment = new JTextField("" + pom.capp.customer.getOrder_paid_amt());
        ord_val.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ord_qty.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ord_qty.setBounds(10, 180, 150, 30);
        ord_val.setBounds(180, 180, 150, 30);
        Payment.setBounds(350, 180, 100, 30);
        Advane_payment.setBounds(450, 180, 150, 30);
        Advane_payment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        Payment.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        ord_qty.setBackground(Color.white);
        ord_val.setBackground(Color.white);
        ord_qty.setEditable(false);
        ord_val.setEditable(false);
        Back_pannel.add(Payment);
        Back_pannel.add(ord_qty);
        Back_pannel.add(ord_val);
        Back_pannel.add(Advane_payment);
        Place_order = new CustaiButton(Color.green, Color.black, 10);
        Place_order.setText("Place Order");
        Place_order.setBounds(480, 410, 150, 30);
        Place_order.painted_Clr = Color.WHITE;
        View_order_Frame.add(Place_order);
        Cancel = new CustaiButton(Color.RED, Color.black, 10);
        Cancel.setText("Cancel");
        Cancel.setBounds(340, 410, 100, 30);
        Cancel.painted_Clr = Color.WHITE;
        View_order_Frame.add(Cancel);
        Cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                pom.cancel_pro_screenbtn.doClick();
                View_order_Frame.dispose();
            }
            
        });
        DayBox = new JComboBox<String>(ViewOrderMoudule.date);
        YearBox = new JComboBox<String>(ViewOrderMoudule.Year);
        MonthBox = new JComboBox<String>(ViewOrderMoudule.Month);
        DayBox.setFont(devdatefont);
        YearBox.setFont(devdatefont);
        MonthBox.setFont(devdatefont);
        DayBox.setFocusable(false);
        YearBox.setFocusable(false);
        MonthBox.setFocusable(false);
        DayBox.setSelectedIndex(1);
        DayBox.setBounds(110, 410, 50, 30);
        YearBox.setBounds(210, 410, 50, 30);
        MonthBox.setBounds(160, 410, 50, 30);
        View_order_Frame.add(DayBox);
        View_order_Frame.add(YearBox);
        View_order_Frame.add(MonthBox);
        Place_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // throw new UnsupportedOperationException("Unimplemented method
                // 'actionPerformed'");
                if (pom.capp.customer.isvalid() && pom.capp.customer.is_ordeer_valid()
                        && pom.capp.customer.getPro_valid()) {
                    double paidamt = 0.0;
                    String devdate;
                    try {
                        paidamt = Double.parseDouble(Advane_payment.getText());
                        devdate = "" + YearBox.getSelectedItem();
                        for (int i=0;i< 12 ;i++) {
                            if(Month[i].equals(""+MonthBox.getSelectedItem())){
                                i++;
                                devdate=devdate+"-"+i;
                                break;
                            }
                        }
                        devdate = devdate + "-" + DayBox.getSelectedItem();
                        System.out.println(devdate);
                        pom.capp.customer.setOrder_paid_amt(paidamt);
                        pom.capp.customer.setOrder_dev_Date(devdate);
                        if (pom.capp.DBopertion.palceorder()) {
                            pom.capp.dbbox.Setmessage("Order Place Confirmed", 1);
                            pom.cancel_pro_screenbtn.doClick();
                            View_order_Frame.dispose();
                            pom.capp.Dashboard.dashBoardrefersh();
                        } else {
                            pom.capp.dbbox.Setmessage("Error while Placing Order", 0);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Error while converting number in paid amt");
                    }
                }
            }
        });
    }

    public static void datepicker() {
        int crntYear = 2023;
        for (int i = 1; i < 31; i++) {
            date[i] = new String();
            date[i] = "" + i;
        }
        for (int i = 0; i <= 10; i++) {
            Year[i] = new String();
            Year[i] = "" + crntYear;
            crntYear++;
        }
    }
}
