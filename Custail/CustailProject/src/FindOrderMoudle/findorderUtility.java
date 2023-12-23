package FindOrderMoudle;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import DatabaseMoudle.DatabaseOpertion;
import UpdateProduct.updateProductUtility;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumnModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

public class findorderUtility implements TableColumnModelListener {
    DatabaseOpertion DBO;
    JScrollPane jp;
    String OrdId;
    JDialog orderFrame;
    ResultSet sResult;
    JTable Productable;

    public findorderUtility(String OrdID, DatabaseOpertion dbo) {
        this.OrdId = OrdID;
        this.DBO = dbo;
        orderFrame = new JDialog();
        orderFrame.setTitle("Product Frame");
        orderFrame.setBounds(300, 200, 700, 300);
        orderFrame.setLayout(null);
        orderFrame.getContentPane().setBackground(new Color(55, 55, 55));   
        orderFrame.setResizable(false);
        DefaultTableModel productmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        String tablehead[] = { "Pro ID", "MID", "Product", "Pro Status", "Swing Cost", "Cloth Cost", "Total Cost" };
        for (String string : tablehead) {
            productmodel.addColumn(string);
        }
        sResult = DBO.getProduct_table(OrdID);
        if (sResult != null) {
            try {
                do {
                    String rowdata[] = { sResult.getString(1), sResult.getString(4), sResult.getString(5),
                            sResult.getString(8), "" + sResult.getDouble(6), "" + sResult.getDouble(7),
                            "" + sResult.getDouble(9) };
                    productmodel.addRow(rowdata);

                } while (sResult.next() != false);
            } catch (SQLException ex) {
                System.out.println("Error in get product table method in find order moudle " + ex);
            }
        }
        Productable=new JTable(productmodel);
        Productable.setBackground(Color.white);
        Productable.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        Productable.setRowSelectionAllowed(false);
        Productable.setColumnSelectionAllowed(false);
        Productable.setCellSelectionEnabled(true);
        Productable.removeEditor();
        Productable.setLayout(null);
        TableColumnModel colummodel=Productable.getColumnModel();
        colummodel.addColumnModelListener(this);
        jp = new JScrollPane(Productable);
        jp.setBounds(0, 0, 700, 300);
        orderFrame.add(jp);
        orderFrame.setVisible(true);

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
            int rwo = Productable.getSelectedRow();
            if (rwo != -1&&Productable.getSelectedColumn()==0) {
                String Proid = "" + Productable.getValueAt(rwo, 0);
                System.out.println(Proid);
                // P findord = new findorderUtility(Proid, DBO);
                updateProductUtility upro=new updateProductUtility(Proid, DBO);
                upro.donothing();
                Productable.clearSelection();
            }
        }
    }
    public void donothing(){

    }

}
