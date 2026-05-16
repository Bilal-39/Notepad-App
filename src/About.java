import javax.swing.*;

public class About extends JFrame{   
    About(){
        setTitle("About");
        setSize(400,350);
        setLocation(200,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLayout(null);
        JLabel l =new JLabel("Helo world cvxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        l.setBounds(20,30,120,50);
        add(l);

    }
    public static void main(String[] args){
      new About().setVisible(false);

    }
    
}
