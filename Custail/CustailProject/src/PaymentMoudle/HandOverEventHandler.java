package PaymentMoudle;

import java.awt.Color;
import java.awt.event.*;

public class HandOverEventHandler implements ActionListener, MouseListener {
    Handover hov;

    HandOverEventHandler(Handover hov) {
        this.hov = hov;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == hov.HandOver) {
            hov.HandOver.background_clr = Color.green;
            hov.HandOver.setForeground(Color.BLACK);

        } else if (e.getSource() == hov.Cancel) {
            hov.Cancel.background_clr = Color.RED;
            hov.Cancel.setForeground(Color.BLACK);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == hov.HandOver) {
            hov.HandOver.background_clr = Color.lightGray;
            hov.HandOver.setForeground(Color.BLACK);

        } else if (e.getSource() == hov.Cancel) {
            hov.Cancel.background_clr = Color.BLACK;
            hov.Cancel.setForeground(Color.WHITE);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == hov.HandOver) {
            if (hov.paycust.selectedOrderListHead != null) {
                double paidamt = Double.parseDouble(hov.PaidAmt.getText());
                double tempDebitAMt = hov.paycust.getDebitamt() - paidamt;
                selectedOrder temp;
                temp = hov.paycust.selectedOrderListHead;
                boolean key = true;
                while (temp != null) {
                    if (paidamt >=0) {
                        if (temp.balanceamt <= paidamt) {
                            paidamt = paidamt - temp.balanceamt;
                            temp.paidamount = temp.paidamount + temp.balanceamt;
                            temp.balanceamt = temp.ordervalue - temp.paidamount;
                            if (!hov.HandOverDb.Order_HandOver_ToCustomer(true, temp.Order_ID, temp.paidamount)) {
                                key = false;
                            }
                        } else if (paidamt < temp.balanceamt) {
                            temp.balanceamt = temp.balanceamt - paidamt;
                            temp.paidamount = temp.paidamount + paidamt;
                            paidamt = 0;
                            if (!hov.HandOverDb.Order_HandOver_ToCustomer(false, temp.Order_ID, temp.paidamount)) {
                                key = false;
                            }

                        }

                    } else {
                        if (!hov.HandOverDb.Order_HandOver_ToCustomer(false, temp.Order_ID, temp.paidamount)) {
                            key = false;
                        }
                    }
                    temp = temp.orderNext;

                }
                if (key) {
                    if (hov.HandOverDb.update_custmer_payment(hov.paycust.getCustID(), tempDebitAMt)) {
                        hov.HandOverDb.comitcommand();
                        hov.Dash_utiliyt.dashBoardrefersh();
                        PaymentMoudleInterface.SetStatusMessage("Order Hand Over", 1, hov.msgBox);
                        hov.msgBox.setVisible(true);
                        hov.Handover_frame.dispose();
                    } else {
                        PaymentMoudleInterface.SetStatusMessage("Error Please try again", 1, hov.msgBox);
                        hov.HandOverDb.rollbacmehtod();
                        hov.msgBox.setVisible(true);
                    }
                }

            }

        } else if (e.getSource() == hov.Cancel) {
            hov.Handover_frame.dispose();
        }
    }

}
