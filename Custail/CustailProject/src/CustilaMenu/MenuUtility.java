package CustilaMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JTextField;

// import com.mysql.cj.x.protobuf.MysqlxCrud.ColumnOrBuilder;

import CustailComponent.CustaiButton;
import javax.swing.BorderFactory;
import CustailComponent.CustailPannel;
import CustailDriver.CustailApp;
import PaymentMoudle.PaymentMoudleInterface;
import UpdateProduct.updateProductUtility;
public class MenuUtility implements MouseListener, ActionListener {
    CustailApp cApp;

    CustailPannel menupannel,backPannelFortextfeild;
    JTextField proidTf;
    Color actvie = new Color(37, 37, 37), unacitve = new Color(57, 57, 57), darkcolor = new Color(27, 27, 27);
    // Font menufont=new Font()
    Font menufont = new Font("Times New Roman ", Font.BOLD, 14);
    CustaiButton paymetbtn, updatebtn;


    public MenuUtility(CustailApp cApp) {
        this.cApp = cApp;
        menupannel = new CustailPannel(new Color(97, 97, 97), Color.BLACK, 15);
        menupannel.setBounds(100, 500, 500, 150);
        cApp.mainScreen.add(menupannel);
        menupannel.addMouseListener(this);
        paymetbtn = new CustaiButton(Color.GREEN, Color.white, 10);
        paymetbtn.setText("PAYMENT");
        paymetbtn.setBounds(10, 55, 150, 40);
        paymetbtn.setForeground(Color.BLACK);
        menupannel.add(paymetbtn);
        backPannelFortextfeild=new CustailPannel(Color.white, Color.WHITE, 15);
        backPannelFortextfeild.setBounds(170, 55, 300, 40);
        proidTf=new JTextField();
        proidTf.setBorder(BorderFactory.createLineBorder(Color.white));    
        proidTf.setBounds(5, 5, 145, 30);
        proidTf.setFont(menufont);;
        backPannelFortextfeild.add(proidTf);
        
        // findordbtn = new CustaiButton(Color.WHITE, null, 10);
        // findordbtn.setBounds(170, 55, 150, 40);
        // findordbtn.setText("FIND ORDER");
        updatebtn = new CustaiButton(Color.BLACK, null, 15);
        updatebtn.setBounds(150, 2, 148, 36);
        updatebtn.setForeground(Color.white);
        updatebtn.setText("UPDATE ORDER");
        // findordbtn.setFont(menufont);
        updatebtn.setFont(menufont);
        // findordbtn.setFont(menufont);
        // menupannel.add(findordbtn);
        backPannelFortextfeild.add(updatebtn);
        menupannel.add(backPannelFortextfeild);
        paymetbtn.addMouseListener(this);
        updatebtn.addMouseListener(this);
        // findordbtn.addMouseListener(this);
        paymetbtn.addActionListener(this);
        updatebtn.addActionListener(this);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseEntered'");
        if (e.getSource() == menupannel) {
            menupannel.Border_Clr = Color.white;
            menupannel.background_clr = actvie;
            menupannel.repaint();
            // System.out.println("Mouse enter");
        }
        if (e.getSource() == paymetbtn) {
            paymetbtn.background_clr = darkcolor;
            paymetbtn.painted_Clr = Color.WHITE;
            paymetbtn.setForeground(Color.white);

        }
        
        if (e.getSource() == updatebtn) {
            updatebtn.background_clr = Color.DARK_GRAY;
            updatebtn.painted_Clr = Color.white;
            updatebtn.setForeground(Color.white);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // 'mouseExited'");
        if (e.getSource() == menupannel) {
            menupannel.background_clr = unacitve;
            menupannel.Border_Clr = Color.BLACK;
            menupannel.repaint();
            // System.out.println("Mouse enter");

        }
        if (e.getSource() == paymetbtn) {
            paymetbtn.background_clr = Color.GREEN;
            paymetbtn.painted_Clr = Color.BLACK;
            paymetbtn.setForeground(Color.BLACK);

        }
        
        if (e.getSource() == updatebtn) {
            updatebtn.background_clr = Color.BLACK;
            updatebtn.painted_Clr = Color.BLACK;
            updatebtn.setForeground(Color.WHITE);

        }

        // if(e.getSour)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==paymetbtn){
            cApp.paycusotmer=new PaymentMoudleInterface(cApp.DBopertion,cApp.Dashboard);

        }else if(e.getSource()==updatebtn){
            cApp.update_produtc=new updateProductUtility(proidTf.getText(), cApp.DBopertion);
            
        }
        
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
