// This class will handel all even performed in payment moudle interface ///
/* Written by jyotiraditya Mourya on 14 dec 2023 */

package PaymentMoudle;

import java.awt.Color;
import java.awt.event.*;

public class PaymentEventhandler extends WindowAdapter implements ActionListener, MouseListener {
    PaymentMoudleInterface pom;
    Handover hand_over;

    public PaymentEventhandler(PaymentMoudleInterface pom) {
        this.pom = pom;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pom.find) {
            if (pom.DBO.cust_existforPayment(pom.numbertofind.getText(), pom.customer)) {
                pom.setcustomerpannel();
                pom.CustBack_pannel.remove(pom.findcutomer);
                pom.CustBack_pannel.repaint();
                // System.out.println(pom.customer.getCustID());
                pom.DBO.Find_order_using_CID(pom.customer.getCustID(), pom.customer);
                pom.addordertotable();
            }else{
                pom.Status_message.setVisible(true);
                PaymentMoudleInterface.SetStatusMessage("Customer Not Found", 2,pom.Status_message);
                // pom.Pay_Order_frame.add(pom.Status_message);
            }

        } else if (e.getSource() == pom.PayNow) {
            hand_over = new Handover(pom.customer, pom.DBO, pom.dashboard,pom.Status_message);
        } else if (e.getSource() == pom.Pay_Order_frame) {
            // System.out.println("Action performed");

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == pom.Order_table) {
            if (pom.Order_table.getSelectedColumn() == 0) {
                // System.out.println(pom.Order_table.getValueAt(pom.Order_table.getSelectedRow(),
                // 0));
                if (Boolean.valueOf("" + pom.Order_table.getValueAt(pom.Order_table.getSelectedRow(), 0))) {
                    pom.Order_table.setValueAt(false, pom.Order_table.getSelectedRow(), 0);
                    int row = pom.Order_table.getSelectedRow();
                    pom.customer.delete_selected_order_data_list_head("" + pom.Order_table.getValueAt(row, 1));
                    pom.customer.delte_at_orderId_data_list("" + pom.Order_table.getValueAt(row, 1));
                    pom.ord_val.setText("₹ " + pom.customer.getSelected_ord_Value());
                    pom.balanceamt.setText("₹ " + pom.customer.getSelected_ord_balance_amt());
                    pom.Advane_payment.setText("₹ " + pom.customer.getSelected_ord_paid_amt());
                } else {
                    int row = pom.Order_table.getSelectedRow();
                    pom.Order_table.setValueAt(true, pom.Order_table.getSelectedRow(), 0);
                    pom.customer.create_Selceted_orderdata_list("" + pom.Order_table.getValueAt(row, 1),
                            Double.parseDouble("" + pom.Order_table.getValueAt(row, 4)),
                            Double.parseDouble("" + pom.Order_table.getValueAt(row, 5)),
                            Double.parseDouble("" + pom.Order_table.getValueAt(row, 6)));
                    pom.ord_val.setText("₹ " + pom.customer.getSelected_ord_Value());
                    pom.balanceamt.setText("₹ " + pom.customer.getSelected_ord_balance_amt());
                    pom.Advane_payment.setText("₹ " + pom.customer.getSelected_ord_paid_amt());

                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == pom.PayNow) {
            pom.PayNow.background_clr = Color.green;
            pom.PayNow.setForeground(Color.black);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == pom.PayNow) {
            pom.PayNow.background_clr = Color.BLACK;
            pom.PayNow.setForeground(Color.GREEN);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        /* Order Table */
        if (e.getSource() == pom.Pay_Order_frame) {
            System.out.println("Windo closed");
            pom.customer = null;
            // pom=null;

        }

    }

}
