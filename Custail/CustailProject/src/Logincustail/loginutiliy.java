// // A login class to setup login for software
// Written by Jyotiradity Mourya
package Logincustail;

// 
import java.awt.Color;
import java.awt.Font;

// 
// import javax.swing.JTextfeild;;
import javax.swing.*;


// 
import CustailComponent.CustailPannel;
// 
import java.sql.*;
import java.util.concurrent.Semaphore;
import java.awt.event.*;
import DatabaseMoudle.DatabaseOpertion;
import java.awt.Image;
import java.awt.Toolkit;

public class loginutiliy implements ActionListener {
    public JDialog loginframe;
    public JButton Loginbutton, SetupButton;
    Font logFont = new Font("Times New Roman", Font.BOLD, 16);
    public JLabel usernamLabel, paswwordlabel, warninglabel, logoJLabel;
    Image mainlogo = Toolkit.getDefaultToolkit()
            .getImage("C:\\Users\\Ruddarm\\OneDrive\\Custail\\CustailProject\\src\\custtailImagelib\\loginlog.png");
    CustailPannel backPannel = new CustailPannel(Color.red, null, 0);
    public JTextField user;
    public JPasswordField password;
    public ResultSet rs;
    public DatabaseOpertion dbobj;
    public boolean log_in = false;
    public Semaphore semaphore;
    public loginutiliy(DatabaseOpertion db, Image logoimg,Semaphore sema) {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                dbobj = db;
                semaphore=sema;
                loginframe = new JDialog();
                loginframe.setLayout(null);
                loginframe.setIconImage(logoimg);
                loginframe.setBounds(400, 150, 550, 400);
                loginframe.getContentPane().setBackground(Color.white);
                loginframe.setResizable(false);
                loginframe.setVisible(true);
                // this.backPannel.setBounds(0, 0, 600, 500);
                // loginframe.add(backPannel);
                // backPannel.setLayout(null);
            }
        });

    }

    public void setlogin(loginutiliy obj) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ImageIcon logicon = new ImageIcon(mainlogo);
                obj.logoJLabel = new JLabel(logicon);
                obj.logoJLabel.setBounds(195, 0, 138, 138);
                obj.warninglabel = new JLabel();
                obj.warninglabel.setBounds(150, 130, 250, 30);
                obj.warninglabel.setForeground(Color.RED);
                obj.usernamLabel = new JLabel("    User Name  :");
                obj.paswwordlabel = new JLabel("    Password   :");
                obj.usernamLabel.setBounds(100, 160, 150, 30);
                obj.paswwordlabel.setBounds(100, 195, 150, 30);
                obj.user = new JTextField();
                obj.user.setBounds(260, 160, 150, 30);
                obj.password = new JPasswordField();
                obj.password.setBounds(260, 195, 150, 30);
                obj.Loginbutton = new JButton();
                obj.SetupButton = new JButton();
                obj.Loginbutton.setText("LOGIN");
                obj.SetupButton.setText("SETUP");
                obj.Loginbutton.setBounds(200, 250, 150, 30);
                obj.SetupButton.setBounds(200, 250, 150, 30);
                obj.Loginbutton.setForeground(Color.white);
                obj.Loginbutton.setBackground(new Color(55, 55, 55));
                obj.Loginbutton.setFont(logFont);
                obj.logoJLabel.setFont(logFont);
                obj.usernamLabel.setFont(logFont);
                obj.warninglabel.setFont(logFont);
                obj.paswwordlabel.setFont(logFont);
                obj.user.setFont(logFont);
                obj.password.setFont(logFont);
                obj.loginframe.add(logoJLabel);
                obj.loginframe.add(usernamLabel);
                obj.loginframe.add(warninglabel);
                obj.loginframe.add(paswwordlabel);
                obj.loginframe.add(user);
                obj.loginframe.add(password);
                obj.Loginbutton.addActionListener(obj);

                obj.SetupButton.addActionListener(obj);

                obj.rs = dbobj.is_user_password_setup();
                if (rs == null) {
                    loginframe.add(SetupButton);
                } else {
                    loginframe.add(Loginbutton);
                }

            }
        });

    }

    public void addlistener() {

    }

    // Action implemtnion
    @Override
    public void actionPerformed(ActionEvent e) {
        warninglabel.setVisible(false);
        if (e.getSource() == this.SetupButton) {
            String username, passwrodString;
            username = user.getText();
            char pswd[] = password.getPassword();
            passwrodString = pswd.toString();
            System.out.println(passwrodString);
            passwrodString.split(passwrodString);
            if (username.isBlank()) {
                warninglabel.setText("Enter Username");
                warninglabel.setVisible(true);
            }
            if (passwrodString.isBlank()) {
                warninglabel.setText("Enter Password");
                warninglabel.setVisible(true);
            } else {
                if (dbobj.set_user_data(username, passwrodString)) {
                    warninglabel.setText("Set UP sucesfull please restart");
                    System.out.println("Setup");
                    warninglabel.setVisible(true);
                }
            }

        }
        if (e.getSource() == this.Loginbutton) {
            // System.out.println(Thread.currentThread());
            synchronized(this){
            warninglabel.setVisible(false);
            String username, passwrodString;
            username = user.getText();
            char[] pswd = password.getPassword();
            passwrodString="";
            for (char c : pswd) {
                passwrodString=passwrodString+c;
            }
            username.split(username);
            username.split(passwrodString);
            passwrodString.split(passwrodString);
            System.out.println(passwrodString);
            try {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                if (username.equals(rs.getString(1)) && passwrodString.equals(rs.getString(2))) {
                    log_in=true;
                    loginframe.dispose();
                    semaphore.release();
                    
                } else {
                    warninglabel.setText("Wrong Password or User name ");
                    warninglabel.setVisible(true);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
}
