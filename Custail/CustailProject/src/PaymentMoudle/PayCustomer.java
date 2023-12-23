package PaymentMoudle;


public class PayCustomer extends OrderList {
    private String custID, Name, Number;
    private double creditamt, Debitamt, selected_ord_Value, selected_ord_paid_amt, selected_ord_balance_amt;
    private OrderList orderHead, orderTemp;
    selectedOrder selectedOrderListHead, orderListTemp;

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public void setOrderHead(OrderList orderHead) {
        this.orderHead = orderHead;
    }

    public void setOrderTemp(OrderList orderTemp) {
        this.orderTemp = orderTemp;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }

    public String getCustID() {
        return custID;
    }

    public OrderList getOrderHead() {
        return orderHead;
    }

    public OrderList getOrderTemp() {
        return orderTemp;
    }

    public double getCreditamt() {
        return creditamt;
    }

    public void setCreditamt(double creditamt) {
        System.out.println(creditamt);
        this.creditamt = creditamt;
    }

    public double getDebitamt() {
        return Debitamt;
    }

    public void setDebitamt(double debitamt) {
        Debitamt = debitamt;
    }

    public void setSelected_ord_Value(double selected_ord_Value) {

        this.selected_ord_Value = selected_ord_Value;
    }

    public void setSelected_ord_paid_amt(double selected_ord_paid_amt) {
        this.selected_ord_paid_amt = selected_ord_paid_amt;
    }

    public void setSelected_ord_balance_amt(double selected_ord_balance_amt) {
        this.selected_ord_balance_amt = selected_ord_balance_amt;
    }

    public double getSelected_ord_Value() {
        System.out.println("Order Selected amt is " + selected_ord_Value);

        return selected_ord_Value;
    }

    public double getSelected_ord_paid_amt() {
        return selected_ord_paid_amt;
    }

    public double getSelected_ord_balance_amt() {
        return selected_ord_balance_amt;
    }

    public void Insert_order_list(String ordID, String ordDate, String ordStaus, int qty, double orderValue,
            double orderPaidAmt) {
        if (getOrderHead() == null) {
            // System.out.println("Setting head");
            OrderList headnode = new OrderList();
            headnode.setOrde_next(null);
            headnode.setOrderID(ordID);
            headnode.setOrderDate(ordDate);
            headnode.setOrder_status(ordStaus);
            headnode.setOrderValue(orderValue);
            headnode.setOrderPaidAmt(orderPaidAmt);
            headnode.setOrd_qty(qty);
            orderHead = headnode;
            orderTemp = orderHead;
        } else {
            OrderList newnode = new OrderList();
            newnode.setOrde_next(null);
            newnode.setOrderID(ordID);
            newnode.setOrderDate(ordDate);
            newnode.setOrder_status(ordStaus);
            newnode.setOrde_next(null);
            newnode.setOrderValue(orderValue);
            newnode.setOrderPaidAmt(orderPaidAmt);
            newnode.setOrd_qty(qty);
            orderTemp.setOrde_next(newnode);
            orderTemp = newnode;
        }
    }

    public void create_Selceted_orderdata_list(String ord_id, double value, double paidamount, double Balance) {
        if (selectedOrderListHead == null) {
            selectedOrder headorder = new selectedOrder();
            headorder.Order_ID = ord_id;
            headorder.ordervalue = value;
            headorder.paidamount = paidamount;
            headorder.balanceamt = Balance = value - paidamount;
            headorder.orderNext = null;
            selected_ord_Value = selected_ord_Value + value;
            selected_ord_paid_amt = paidamount + selected_ord_paid_amt;
            selected_ord_balance_amt = selected_ord_balance_amt + headorder.balanceamt;
            selectedOrderListHead = headorder;
            orderListTemp = selectedOrderListHead;

        } else {
            selectedOrder nextnode = new selectedOrder();
            nextnode.Order_ID = ord_id;
            nextnode.ordervalue = value;
            nextnode.paidamount = paidamount;
            nextnode.balanceamt = Balance;
            nextnode.orderNext = null;
            selected_ord_Value = selected_ord_Value + value;
            selected_ord_paid_amt = paidamount + selected_ord_paid_amt;
            selected_ord_balance_amt = selected_ord_balance_amt + nextnode.balanceamt;
            orderListTemp.orderNext = nextnode;
            orderListTemp = nextnode;
        }

    }

    public void delete_selected_order_data_list_head(String Ord_ID) {
        if (selectedOrderListHead != null) {
            if (selectedOrderListHead.Order_ID.equals(Ord_ID)) {
                selectedOrder temp;
                temp = selectedOrderListHead;
                selected_ord_Value = selected_ord_Value - temp.ordervalue;
                selected_ord_balance_amt = selected_ord_balance_amt - temp.balanceamt;
                selected_ord_paid_amt = selected_ord_paid_amt - temp.paidamount;
                selectedOrderListHead = temp.orderNext;
                return;
            }
            return;
        }
    }

    public void delte_at_orderId_data_list(String orderID) {
        if (selectedOrderListHead != null) {
            selectedOrder pre = selectedOrderListHead;
            selectedOrder temp = selectedOrderListHead.orderNext;
            while (temp.orderNext != null) {
                // pre = temp;
                if (temp.Order_ID.equals(orderID)) {
                    selected_ord_Value = selected_ord_Value - temp.ordervalue;
                    selected_ord_balance_amt = selected_ord_balance_amt - temp.balanceamt;
                    selected_ord_paid_amt = selected_ord_paid_amt - temp.paidamount;
                    pre.orderNext = temp.orderNext;
                    return;
                }
                pre = temp;
                temp = temp.orderNext;
            }
            if (temp.Order_ID.equals(orderID)) {
                selected_ord_Value = selected_ord_Value - temp.ordervalue;
                selected_ord_balance_amt = selected_ord_balance_amt - temp.balanceamt;
                selected_ord_paid_amt = selected_ord_paid_amt - temp.paidamount;
                pre.orderNext = null;

            }

        }
    }
}
