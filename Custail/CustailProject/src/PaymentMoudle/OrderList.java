package PaymentMoudle;

public class OrderList {
 private String OrderID;
 int ord_qty;
 private String OrderDate,Order_status;
 private double orderValue,OrderPaidAmt,remamt;
 private OrderList orde_next;;
 public String getOrderDate() {
     return OrderDate;
 }
 public int getOrd_qty() {
     return ord_qty;
 }
 public void setOrd_qty(int ord_qty) {
     this.ord_qty = ord_qty;
 }
 public String getOrderID() {
     return OrderID;
 }
 public double getOrderPaidAmt() {
     return OrderPaidAmt;
 }public double getOrderValue() {
     return orderValue;
 }public String getOrder_status() {
     return Order_status;
 }public double getRemamt() {
     return remamt;
 }public void setOrderDate(String orderDate) {
     OrderDate = orderDate;
 }public void setOrderID(String orderID) {
     OrderID = orderID;
 }
 public void setOrderPaidAmt(double orderPaidAmt) {
     OrderPaidAmt = orderPaidAmt;
 }public void setOrderValue(double orderValue) {
     this.orderValue = orderValue;
 }
 public void setOrde_next(OrderList orde_next) {
     this.orde_next = orde_next;
 }
 public OrderList getOrde_next() {
     return orde_next;
 }
 public void setOrder_status(String order_status) {
     Order_status = order_status;
 } 

}
