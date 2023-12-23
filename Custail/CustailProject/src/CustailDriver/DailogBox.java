package CustailDriver;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

//A dailog box for informing message to user 
//writen by ruddarm mourya
public class DailogBox {
    // A dailog
    JDialog messageBox;
    // this will use to print message in dailog box
    JLabel messagLabel;


    public DailogBox() {
        // Insitalizing dailog box
        messageBox = new JDialog();
        messageBox.setLayout(null);
        messageBox.setTitle("CusTail Error");
        messageBox.setBounds(400, 200, 500, 300);
        messageBox.getContentPane().setBackground(new Color(55, 55, 55));

    }

    public void Setmessage(String text) {
        // setting message in j dailog box
        messagLabel = new JLabel(text);
        messagLabel.setBounds(100, 100, 400, 40);
        messagLabel.setForeground(Color.red);
        messagLabel.setFont(new Font("F", Font.ITALIC, 12));
        messageBox.add(messagLabel);

    }

    /* A set message when order sucesfull or unsucceful */
    public void Setmessage(String text, int status) {
        // setting message in j dailog box
        messagLabel = new JLabel(text);
        messagLabel.setBounds(200, 100, 200, 40);
        if (status == 1) {
            messagLabel.setForeground(Color.GREEN);
            messagLabel.setVisible(true);

        } else {
            messagLabel.setForeground(Color.red);

        }
        messagLabel.setFont(new Font("F", Font.ITALIC, 12));
        messageBox.add(messagLabel);
        messageBox.setVisible(true);

    }
    
}
