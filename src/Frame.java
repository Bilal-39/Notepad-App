import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class App extends JFrame implements ActionListener, ItemListener {
    //----------------------------MenuItem Definition------------------------------------
    JMenuBar menu = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem New = new JMenuItem("New");
    JMenuItem Open = new JMenuItem("Open");
    JMenuItem SaveAs = new JMenuItem("Save");
    JMenuItem Exit = new JMenuItem("Exit");
    
    JMenu Edit = new JMenu("Edit");
    JMenuItem selectAll = new JMenuItem("Select All");
    JMenuItem Copy = new JMenuItem("Copy");
    JMenuItem Cut = new JMenuItem("Cut");
    JMenuItem Paste = new JMenuItem("Paste");
    JMenuItem wrapText = new JMenuItem("Wrap Text");
    JMenuItem Edit_Font = new JMenuItem("Edit Font");
    

    JMenu Help = new JMenu("Help");
    JMenuItem About_ =new JMenuItem("About");
    //----------------------------MenuItem Definition------------------------------------

    JTextArea text = new JTextArea();
    JScrollPane scrrollpane = new JScrollPane(text);

    //-------------------------JDialog Components----------------------------------------
    JDialog fontA = new JDialog(this,"JDialog");
    JLabel sizeLabel = new JLabel("Font Size");
    String[] s1 = {"10","12","14","16","18","20","22","24"};
    @SuppressWarnings("rawtypes") 
    JComboBox size = new JComboBox<>(s1);
    JLabel familyLabel = new JLabel("Font Family"); 
    String[] s2 = {"Arial","Arial Black","TimesNewRoman","Consolas"}; 
    @SuppressWarnings("rawtypes") 
    JComboBox fontFamily = new JComboBox<>(s2);
    //-------------------------JDialog Components----------------------------------------
    
    App(){
      //---------------------------------Adding MenuItems to menubar-----------------------------------------
      file.add(New);
      file.add(Open);
      file.add(SaveAs);
      file.add(Exit);

      Edit.add(selectAll);
      Edit.add(Copy);
      Edit.add(Cut);
      Edit.add(Paste);
      Edit.add(wrapText);
      Edit.add(Edit_Font);

      Help.add(About_);

      menu.add(file);
      menu.add(Edit);
      menu.add(Help);
      setJMenuBar(menu);
      //-------------------------------Adding MenuItems to menubar------------------------------------------

     

      Font fon = new Font("Arial",Font.PLAIN,18);  

      //------------------------------Text Area-------------------------------------
      text.setBounds(20,30,440,350);
      text.setFont(fon);
      scrrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      add(scrrollpane);
    //------------------------------Text Area-----------------------------------------

    //-----------------------------Jdialog creation and adding components to it----------
     fontA.setSize(300,300);
     fontA.setLocation(300,400);
     fontA.setLayout(null);
     fontA.setVisible(false);
     
     //font size label
     fontA.add(sizeLabel);
     sizeLabel.setBounds(20,15,80,20);

     //font size combobox
     fontA.add(size);
     size.setBounds(100,15,50,20);
     size.setSelectedItem("18");
     size.setFocusable(false);

     //font family label
     fontA.add(familyLabel);
     familyLabel.setBounds(20,45,80,20);

     //font family combo box
     fontA.add(fontFamily);
     fontFamily.setBounds(100,45,105,20);
     fontFamily.setFocusable(false);

    //-----------------------------Jdialog creation and adding components to it----------    

    //---------------------------Actions on dialog items--------------------------------
     size.addItemListener(this);
     fontFamily.addItemListener(this);
    //---------------------------Actions on dialog items--------------------------------

    //----------------------------Actions on MenuItems-------------------------------
      New.addActionListener(this);
      Open.addActionListener(this);
      SaveAs.addActionListener(this);
      Exit.addActionListener(this);
      selectAll.addActionListener(this);  
      Copy.addActionListener(this);
      Paste.addActionListener(this);
      Cut.addActionListener(this);
      wrapText.addActionListener(this);
      Edit_Font.addActionListener(this);
      About_.addActionListener(this);
    //----------------------------Actions on MenuItems-------------------------------
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==New){
            text.setText(null);
        }
        if(e.getSource()==Open){
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\DELL1\\Documents");
            FileNameExtensionFilter filefilter = new FileNameExtensionFilter("Ony Text Files (.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(filefilter);

            int j = fileChooser.showOpenDialog(null);
            if(j == JFileChooser.APPROVE_OPTION){
                String path = fileChooser.getSelectedFile().getAbsolutePath().toString();
                try {
                    File file = new File(path);
                    Scanner sc = new Scanner(file);
                    while(sc.hasNextLine()){
                        String data = sc.nextLine();
                        text.setText(data);  
                    }
                    sc.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
               
            }
        }
        if(e.getSource()==SaveAs){
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\DELL1\\Documents");
            FileNameExtensionFilter filefilter = new FileNameExtensionFilter("Ony Text Files (.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(filefilter);

            int i = fileChooser.showSaveDialog(null);
            if(i == JFileChooser.APPROVE_OPTION){
                String filename = fileChooser.getSelectedFile().getAbsolutePath().toString();
                if(!filename.contains(".txt"))
                    filename = filename+".txt";
                try {
                    FileWriter writeout = new FileWriter(filename);
                    String s = text.getText();
                    writeout.write(s);
                    writeout.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }    
            }
        }
        if(e.getSource()==Exit){
            dispose();
        }
        if(e.getSource()==selectAll){
            text.selectAll();
        }
        if(e.getSource()==Copy){
            text.copy();
        }
        if(e.getSource()==Cut){
            text.cut();
        }
        if(e.getSource()==Paste){
            text.paste();
        }
        if(e.getSource()==wrapText){
            text.setLineWrap(true);
        }
        if(e.getSource()==Edit_Font){
            fontA.setVisible(true);    
        }
        if(e.getSource()==About_){
           new About().setVisible(true);
        
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
       String si = size.getSelectedItem().toString();
       int siz = Integer.parseInt(si);
       String fa = fontFamily.getSelectedItem().toString();
       text.setFont(new Font(fa,Font.PLAIN,siz));
    }
}

public class Frame {
    public static void main(String[] args) {
        App pad = new App();
        pad.setTitle("Notepad");
        pad.setSize(500,500);
        pad.setLocation(500,500);
        pad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pad.setVisible(true);
    }
}