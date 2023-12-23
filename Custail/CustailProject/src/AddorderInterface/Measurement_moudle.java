package AddorderInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.lang.Object;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import CustailComponent.CustaiButton;
import CustailComponent.CustailPannel;
import UtilityModule.Measurement;

public class Measurement_moudle {
    JDialog Measurement_Frame;
    CustailPannel Back_pannel;
    PlaceOrderMoudle pom;
    CustaiButton Add_measurement, Save_measurement;
    JLabel Warning;
    DefaultTableModel DTM;
    JScrollPane jp;
    JTableHeader TH;
    JTable Measurement_Table;
    Measurement AddMeasure_Head, add_Temp;
    DefaultTableCellRenderer tablerender;
    int highlightrow[], highlightcolum[];
    int track = 0;

    public Measurement_moudle(PlaceOrderMoudle pom) {
        this.pom = pom;
        Measurement_Frame = new JDialog();
        Measurement_Frame.setTitle("CusTail Measurement");
        Measurement_Frame.setLayout(null);
        Measurement_Frame.getContentPane().setBackground(new Color(55, 55, 55));
        Measurement_Frame.setResizable(false);
        Measurement_Frame.setBounds(350, 100, 700, 500);
        Measurement_Frame.setVisible(true);
        Back_pannel = new CustailPannel(new Color(225, 225, 225), Color.WHITE, 20);
        Back_pannel.setBounds(30, 40, 630, 320);
        Back_pannel.setLayout(null);
        Measurement_Frame.add(Back_pannel);
        DTM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return row == DTM.getRowCount() - 1;
            };
        };
        String header[] = { "MID", "Name", "lenegth", "Waist", "Chest", "wrist", "Arm length", "Shoulder" };
        for (String string : header) {
            DTM.addColumn(string);
        }
        // String Data[][] = new String[8][];
        // pom.capp.customer.setCust_measurement(null);
        Measurement Temp = pom.capp.customer.getCust_measurement();
        while (Temp != null) {
            String data[] = { Temp.getMid(), Temp.getName(), "" + Temp.getLenegth(), "" + Temp.getWaist(),
                    "" + Temp.getChest(), "" + Temp.getWrist(), "" + Temp.getArm_length(), "" + Temp.getShoulder() };
                    // System.out.println(Temp.getName());
            DTM.addRow(data);
            Temp = Temp.getMnext();
        }
        Measurement_Table = new JTable(DTM);
        Measurement_Table.setBackground(Color.white);
        Measurement_Table.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        Measurement_Table.setRowSelectionAllowed(false);
        Measurement_Table.setColumnSelectionAllowed(false);
        Measurement_Table.setCellSelectionEnabled(true);
        Measurement_Table.removeEditor();
        Measurement_Table.setLayout(null);
        jp = new JScrollPane(Measurement_Table);
        jp.setBounds(10, 30, 600, 200);
        jp.setBackground(Color.white);
        Back_pannel.add(jp);
        Measurement_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Add_measurement = new CustaiButton(new Color(55, 55, 55), null, 10);
        Add_measurement.setBounds(350, 230, 100, 30);
        Add_measurement.setFont(pom.custialfont_bold);
        Add_measurement.setForeground(Color.WHITE);
        Add_measurement.setText("ADD");
        Add_measurement.painted_Clr = Color.WHITE;
        Back_pannel.add(Add_measurement);
        Save_measurement = new CustaiButton(new Color(0, 0, 155), null, 10);
        Save_measurement.setBounds(500, 230, 100, 30);
        Save_measurement.setFont(pom.custialfont_bold);
        Save_measurement.setForeground(Color.WHITE);
        Save_measurement.setText("SELECT");
        Save_measurement.painted_Clr = Color.WHITE;
        Back_pannel.add(Save_measurement);
        tablerender = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                // System.out.println(row);
                // System.out.println(column);
                // System.out.println(highlightrow);
                // System.out.println(highlightcolum);
                System.out.println(row + " " + column);

                for (int i = 0; i < highlightrow.length; i++) {
                    // System.out.println("row is" + highlightrow[i]);
                    // System.out.println("co is " + highlightcolum[i]);
                    // System.out.print(column);

                    if (highlightrow[i] == row && highlightcolum[i] == column && column != 0) {
                        cellComponent.setBackground(Color.red);
                        return cellComponent;
                    } // System.out.println("Inse esle");

                }
                cellComponent.setBackground(Measurement_Table.getBackground());
                return cellComponent;

            };
        };
        Add_measurement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AddMeasure_Head == null) {
                    Measurement AddMeasure = new Measurement();
                    AddMeasure.setMnext(null);
                    AddMeasure.setIsnew(true);
                    AddMeasure_Head = AddMeasure;
                    pom.capp.customer.set_Measure_update(true);
                    String str[] = new String[8];
                    int mid = pom.capp.DBopertion.DButility.getLast_Measure_id();
                    str[0] = "MID" + mid;
                    mid++;
                    pom.capp.DBopertion.DButility.setLast_Measure_id(mid);
                    AddMeasure.setMid(str[0]);
                    DTM.addRow(str);
                    DTM.addTableModelListener(new TableModelListener() {
                        @Override
                        public void tableChanged(TableModelEvent e) {
                            // System.out.println("COde is here");
                            int lrow = e.getLastRow();
                            if (e.getColumn() == 1) {
                                AddMeasure.setName("" + DTM.getValueAt(e.getLastRow(), 1));
                                // System.out.println(AddMeasure.getName());
                                return;
                            } else if (e.getColumn() == 2) {
                                AddMeasure.setLenegth(Double.parseDouble("" + DTM.getValueAt(lrow, 2)));
                                // double d=
                            } else if (e.getColumn() == 3) {
                                AddMeasure.setWaist(Double.parseDouble("" + DTM.getValueAt(lrow, 3)));

                            } else if (e.getColumn() == 4) {
                                AddMeasure.setChest(Double.parseDouble("" + DTM.getValueAt(lrow, 4)));

                            } else if (e.getColumn() == 5) {
                                AddMeasure.setWrist(Double.parseDouble("" + DTM.getValueAt(lrow, 5)));

                            } else if (e.getColumn() == 6) {
                                AddMeasure.setArm_length(Double.parseDouble("" + DTM.getValueAt(lrow, 6)));

                            } else if (e.getColumn() == 7) {
                                AddMeasure.setShoulder(Double.parseDouble("" + DTM.getValueAt(lrow, 7)));
                            }
                        }

                    });
                } else {
                    add_Temp = AddMeasure_Head;
                    Measurement AddMeasure = new Measurement();
                    AddMeasure.setMnext(null);
                    AddMeasure.setIsnew(true);
                    add_Temp.setMnext(AddMeasure);
                    add_Temp = AddMeasure;
                    String str[] = new String[8];
                    int mid = pom.capp.DBopertion.DButility.getLast_Measure_id();
                    str[0] = "MID" + mid;
                    mid++;
                    pom.capp.DBopertion.DButility.setLast_Measure_id(mid);
                    AddMeasure.setMid(str[0]);
                    DTM.addRow(str);
                    DTM.addTableModelListener(new TableModelListener() {

                        @Override
                        public void tableChanged(TableModelEvent e) {
                            System.out.println("COde is here");
                            // System.out.println("COde is here");
                            int lrow = e.getLastRow();
                            if (e.getColumn() == 1) {
                                AddMeasure.setName("" + DTM.getValueAt(e.getLastRow(), 1));
                                System.out.println(AddMeasure.getName());
                                return;
                            } else if (e.getColumn() == 2) {
                                AddMeasure.setLenegth(Double.parseDouble("" + DTM.getValueAt(lrow, 2)));
                                // double d=
                            } else if (e.getColumn() == 3) {
                                AddMeasure.setWaist(Double.parseDouble("" + DTM.getValueAt(lrow, 3)));

                            } else if (e.getColumn() == 4) {
                                AddMeasure.setChest(Double.parseDouble("" + DTM.getValueAt(lrow, 4)));

                            } else if (e.getColumn() == 5) {
                                AddMeasure.setWrist(Double.parseDouble("" + DTM.getValueAt(lrow, 5)));

                            } else if (e.getColumn() == 6) {
                                AddMeasure.setArm_length(Double.parseDouble("" + DTM.getValueAt(lrow, 6)));

                            } else if (e.getColumn() == 7) {
                                AddMeasure.setShoulder(Double.parseDouble("" + DTM.getValueAt(lrow, 7)));
                            }
                        }

                    });
                }
            }
            // AddMeasure = new Measurement();
        });
        Warning = new JLabel();
        Warning.setBounds(200, 10, 150, 20);
        Warning.setFont(pom.custialfont_italic);
        Warning.setForeground(Color.red);
        Warning.setVisible(false);
        Back_pannel.add(Warning);
        Save_measurement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Warning.setVisible(false);
                if (Measurement_Table.getSelectedColumn() != 0) {
                    Warning.setText("Select Measurement");
                    Warning.setVisible(true);
                } else {
                    highlightcolum = new int[50];
                    highlightrow = new int[50];
                    boolean key = true;
                    int i = Measurement_Table.getSelectedRow();
                    for (int j = 0; j < Measurement_Table.getColumnCount(); j++) {
                        if (null == Measurement_Table.getValueAt(i, j)) {
                            // Measurement_Table.setGridColor(Color.RED);
                            key = false;
                            highlightrow[track] = i;
                            highlightcolum[track] = j;
                            Measurement_Table.setDefaultRenderer(Object.class, tablerender);
                            Warning.setText("Enter Data Properly");
                            Warning.setVisible(true);
                            track++;
                        }
                    }
                    if (!key) {
                        return;
                    }
                    Measurement Temp = null;
                    if (pom.capp.customer.getCust_measurement() == null) {
                        pom.capp.customer.setCust_measurement(AddMeasure_Head);
                        // Temp=AddMeasure_Head;
                    } else {
                        Temp = pom.capp.customer.getCust_measurement();

                        while (Temp.getMnext() != null) {
                            System.out.println("Stuck here");
                            Temp = Temp.getMnext();
                        }
                        Temp.setMnext(AddMeasure_Head);

                    }
                    // AddMeasure_Head.setMid("" + Measurement_Table.getValueAt(Measurement_Table.getSelectedRow(), 0));
                    pom.capp.customer.setSelected_measure_Id(""+Measurement_Table.getValueAt(Measurement_Table.getSelectedRow(),0));
                    System.out.println(pom.capp.customer.getSelected_measure_Id());
                    Measurement_Frame.dispose();
                    pom.measure_set = true;
                }
            }

        });
    }
}
