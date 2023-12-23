package UtilityModule;


public class Product {
    private String Proid;
    private String ProductType;
    private String Mid;
    private double clothcost;
    private double swingcost;
    private String product_staus;
    private double total_Cost;
    private Product prnext;
    public String getMid() {
        return Mid;
    }
    public void setMid(String mid) {
        Mid = mid;
    }
    public String getProid() {
        return Proid;
    }

    public void setProid(String Proid) {
        this.Proid = Proid;

    }

    // Getter and Setter methods for ProductType
    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    // Getter and Setter methods for clothcost
    public double getClothcost() {
        return clothcost;
    }

    public void setClothcost(double clothcost) {
        this.clothcost = clothcost;
    }

    // Getter and Setter methods for swingcost
    public double getSwingcost() {
        return swingcost;
    }

    public void setSwingcost(double swingcost) {
        this.swingcost = swingcost;
    }

    // Getter and Setter methods for product_staus
    public String getProduct_staus() {
        return product_staus;
    }

    public void setProduct_staus(String product_staus) {
        this.product_staus = product_staus;
    }

    // Getter and Setter methods for total_Cost
    public double getTotal_Cost() {
        return total_Cost;
    }

    public void setTotal_Cost(double total_Cost) {
        this.total_Cost = total_Cost;
    }
    public Product getPrnext() {
        return this.prnext;
    }
    public void setPrnext(Product prnext) {
        this.prnext = prnext;
    }

    public void preapre_product(String pro_id,String pro_type, double clothc,double swingc,double tcost,String m_id){
            setProid(pro_id);
            setProductType(pro_type);
            setClothcost(clothc);
            setSwingcost(swingc);
            setTotal_Cost(tcost);
            setMid(m_id);
    }   

}
