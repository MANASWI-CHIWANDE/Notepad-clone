package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
     About(){
         setBounds(450,100,600,500);

         setLayout(null);
         ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("Notepad/icons/windows-11.png"));
         //Image is awt class
         Image img2 = img1.getImage().getScaledInstance(300,60, Image.SCALE_DEFAULT);
         //JLabel is swing class

         ImageIcon img3 = new ImageIcon(img2);
         JLabel headerIcon = new JLabel(img3);
         headerIcon.setBounds(70,40,400,80);
         add(headerIcon);


         ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("Notepad/icons/Notepad_icon.png"));
         //Image is awt class
         Image img5 = img4.getImage().getScaledInstance(70,70, Image.SCALE_DEFAULT);
         //JLabel is swing class

         ImageIcon img6 = new ImageIcon(img5);
         JLabel icon2 = new JLabel(img6);
         icon2.setBounds(50,200,70,70);
         add(icon2);

         JLabel text = new JLabel("<html>Notepad<br> Version 0.1.0 <br>All rights reserved</html>");
         text.setBounds(150,150,500,200);
         text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
         add(text);


         JButton b1 = new JButton("OK");
         b1.setBounds(150,350,120,25);
         b1.addActionListener(this);
         add(b1);
         setVisible(true);
     }

     @Override
     public void actionPerformed(ActionEvent actionEvent){
        this.setVisible(false);
     }
    public static void main(String[] args) {
        new About();
    }
}
