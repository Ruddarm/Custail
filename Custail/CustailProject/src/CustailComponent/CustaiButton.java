    /* A custom button fro custial using Jbutton  */

package CustailComponent;

import javax.swing.JButton;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
public class CustaiButton extends JButton {
    public Color background_clr;
    public Color Active_clr,mouseclr;
    public Color painted_Clr=Color.BLACK;
    public int radius;
    public int x,y,w,h;
    Image btnimage;
    public CustaiButton(Color bg,Color active,int rad){
        this.Active_clr=active;
        this.background_clr=bg;
        this.painted_Clr=bg;
        this.radius=rad;
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    }
    protected void paintComponent(Graphics var1) {
        Graphics2D var2 = (Graphics2D)var1;
        mouseclr=background_clr;
        var2.setColor(this.mouseclr);
        var2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.radius, this.radius);
        var2.setColor(this.mouseclr);
        var2.fillRoundRect(1, 1, this.getWidth() - 1, this.getHeight() - 1, this.radius, this.radius);
        super.paintComponent(var1);
     }
  
     protected void paintBorder(Graphics var1) {
        Graphics2D var2 = (Graphics2D)var1;
        var2.setColor(this.painted_Clr);
        var2.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), this.radius,this.radius);
     }

}
