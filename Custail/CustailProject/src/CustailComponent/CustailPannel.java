package CustailComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CustailPannel extends JPanel {
    public Color background_clr = Color.white;
    public Color Border_Clr= Color.white;
    int x,y,w,h;
    int radius =0;
    public CustailPannel(Color bg, Color bor,int rad){
        super();

        background_clr=bg;
        Border_Clr=bor;
        radius=rad;
        this.setLayout(null);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
    
    }
    public void paintComponent(Graphics g) {
        x=getX();
        y=getY();
        w=getWidth();
        h=getHeight();
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(this.background_clr);
        g2.fillRoundRect(0,0,w,h, radius, radius);
        g2.fillRoundRect(0,0,w,h, radius, radius);
    }
    protected void paintBorder(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(this.Border_Clr);
        // System.out.println("painting border");
        g2.drawRoundRect(0, 0, w, h, radius, radius);
        
    }

    
}
