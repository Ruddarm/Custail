package AddorderInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mousetouchfeel implements MouseListener {
    Color unactve = new Color(57, 57, 57);
    Color activepannel = new Color(37, 37, 37);
    PlaceOrderMoudle pom;
    mousetouchfeel(PlaceOrderMoudle pom) {
        this.pom = pom;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseClicked'");
        if (e.getSource() == pom.Submit) {
            pom.Submit.setBackground(Color.white);
            // System.out.println(" Cod ein submti");

            pom.Submit.repaint();
        } else if (e.getSource() == pom.Next) {
            pom.Submit.setBackground(Color.RED);
            pom.Next.repaint();

        } else if (e.getSource() == pom.SetMeasurement) {
            pom.SetMeasurement.setBackground(Color.RED);
            pom.SetMeasurement.repaint();
            // System.out.println(" Cod is here");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == pom.Submit) {
            pom.Submit.setBackground(Color.white);

        } else if (e.getSource() == pom.Next) {
            pom.Submit.setBackground(Color.RED);

        } else if (e.getSource() == pom.SetMeasurement) {
            pom.SetMeasurement.setBackground(Color.RED);
            System.out.println(" Cod is here");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == pom.Submit) {
            pom.Submit.setBackground(Color.white);

        } else if (e.getSource() == pom.Next) {
            System.out.println(" Code is hre");
            pom.Submit.setBackground(Color.RED);

        } else if (e.getSource() == pom.SetMeasurement) {
            pom.SetMeasurement.setBackground(Color.RED);
            System.out.println(" Cod is here");

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == pom.Background_pannel) {
            pom.Background_pannel.background_clr = activepannel;
            pom.Background_pannel.Border_Clr = Color.white;
            pom.Background_pannel.repaint();
            return;
        }
        if (e.getSource() == pom.Submit) {
            pom.Submit.background_clr = activepannel;
            pom.Submit.painted_Clr = Color.white;
            pom.Submit.setForeground(Color.WHITE);
            return;
        }
        if (e.getSource() == pom.Addcustomerbtn) {
            pom.Addcustomerbtn.mouseclr = activepannel;
            pom.Addcustomerbtn.painted_Clr = Color.WHITE;
            pom.Addcustomerbtn.setForeground(Color.BLACK);
        } else if (e.getSource() == pom.Addorderbtn) {
            pom.Addorderbtn.mouseclr = activepannel;
            pom.Addorderbtn.painted_Clr = Color.WHITE;
            pom.Addorderbtn.setForeground(Color.BLACK);

        } else if (e.getSource() == pom.Addproductbtn) {
            pom.Addproductbtn.mouseclr = activepannel;
            pom.Addproductbtn.painted_Clr = Color.WHITE;
            pom.Addproductbtn.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == pom.Background_pannel) {
            pom.Background_pannel.background_clr = unactve;
            pom.Background_pannel.Border_Clr = Color.BLACK;
            pom.Background_pannel.repaint();

        } else if (e.getSource() == pom.Submit) {
            pom.Submit.background_clr = Color.GREEN;
            pom.Submit.painted_Clr = Color.WHITE;
            pom.Submit.setForeground(Color.BLACK);

        } else if (e.getSource() == pom.Addcustomerbtn) {
            pom.Addcustomerbtn.mouseclr = pom.Addcustomerbtn.background_clr;
            pom.Addcustomerbtn.painted_Clr = Color.WHITE;
            pom.Addcustomerbtn.setForeground(Color.WHITE);

        } else if (e.getSource() == pom.Addorderbtn) {
            pom.Addorderbtn.mouseclr = activepannel;
            pom.Addorderbtn.painted_Clr = Color.WHITE;
            pom.Addorderbtn.setForeground(Color.WHITE);

        } else if (e.getSource() == pom.Addproductbtn) {
            pom.Addproductbtn.mouseclr = activepannel;
            pom.Addproductbtn.painted_Clr = Color.WHITE;
            pom.Addproductbtn.setForeground(Color.WHITE);
        }

    }

}
