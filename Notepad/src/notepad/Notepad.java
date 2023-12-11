package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * @author Manaswi_Chiwande
 */
public class Notepad extends JFrame implements ActionListener{
    JTextArea area;
    String text;
    Notepad(){
        
        setTitle("Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("Notepad/icons/Notepad_icon.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);
        
        JMenuBar menuBar = new JMenuBar();//menubar
        menuBar.setBackground(Color.WHITE);

        JMenu file = new JMenu("File");//file menu
        file.setFont(new Font("AERIAL",Font.BOLD,18));
        
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        
        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        
        
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        
        
        menuBar.add(file);

        JMenu edit = new JMenu("Edit");//file menu
        edit.setFont(new Font("AERIAL",Font.BOLD,18));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));

        JMenuItem select_all = new JMenuItem("Select ALL");
        select_all.addActionListener(this);
        select_all.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));


        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(select_all);


        menuBar.add(edit);


        JMenu helpmenu= new JMenu("Help");//file menu
        helpmenu.setFont(new Font("AERIAL",Font.BOLD,18));

        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));

        helpmenu.add(help);
        menuBar.add(helpmenu);

        setJMenuBar(menuBar);


        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);


        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //make setVisible at end as it reflect all changes we have made above it
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New")){
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");

            chooser.addChoosableFileFilter(restrict);

            int action = chooser.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION)return;

            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader,null);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        else if(ae.getActionCommand().equals("Save")){
            JFileChooser save_as = new JFileChooser();
            save_as.setApproveButtonText("Save");

            int action = save_as.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION)return;

            File filename = new File(save_as.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile= new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste")){
            area.insert(text,area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Select All")){
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("About")){
            new About().setVisible(true);
        }

    }
    public static void main(String[] args) {
        // TODO code application logic here
        new Notepad();
    }
    
}
