import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class railway extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4;
    JTextField jf1,jf2,jf3,jf4;
    JButton bookbtn,cancelbtn,updatebtn,displaybtn;
    Connection con; 

    railway()
    {
    setTitle("Railway Booking System");
    setSize(400,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout (null);

    l1=new JLabel("Passenger Name :");
    l2=new JLabel("Travel date");
    l3=new JLabel("Number of passenegrs :");
    l4=new JLabel("Destination :");

    jf1=new JTextField();
    jf2=new JTextField();
    jf3=new JTextField();
    jf4=new JTextField();

    bookbtn=new JButton("Book");
    cancelbtn=new JButton("Cancel");
    updatebtn=new JButton("update");
    displaybtn=new JButton("Display");

    bookbtn.addActionListener(this);
    cancelbtn.addActionListener(this);
    updatebtn.addActionListener(this);
    displaybtn.addActionListener(this);

    l1.setBounds(20,100,200,50);
    l2.setBounds(20,180,200,50);
    l3.setBounds(20,260,200,50);
    l4.setBounds(20,340,200,50);

    jf1.setBounds(500,100,150,50);
    jf2.setBounds(500,180,150,50);
    jf3.setBounds(500,260,150,50);
    jf4.setBounds(500,340,150,50);

    bookbtn.setBounds(80,500,100,50);
    cancelbtn.setBounds(200,500,100,50);
    updatebtn.setBounds(320,500,100,50);
    displaybtn.setBounds(440,500,100,50);

    add(l1);add(l2);add(l3);add(l4);add(jf1);add(jf2);add(jf3);add(jf4);add(bookbtn);add(cancelbtn);add(updatebtn);add(displaybtn);
    

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","jobin123");
    }
    catch(Exception e)
    {
        System.out.println("Error occured "+e);
    }
    setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        try{
            if(e.getSource()==bookbtn)
            {
                PreparedStatement stmt=con.prepareStatement("INSERT INTO booking VALUES (?,?,?,?)");
                stmt.setString(1,jf1.getText());
                stmt.setString(2,jf2.getText());
                stmt.setInt(3,Integer.parseInt(jf3.getText()));
                stmt.setString(4,jf4.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this,"Details Added Successfullt");
            }
            else if(e.getSource()==cancelbtn)
            {
                PreparedStatement stmt=con.prepareStatement("DELETE from booking where Name=?");
                stmt.setString(1,jf1.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this,"Data deleted successfully");
            }
            else if(e.getSource()==updatebtn)
            {
                PreparedStatement stmt=con.prepareStatement("UPDATE booking set date=?,Number_of_passengers=?,destination=? where name=?");
                stmt.setString(1,jf2.getText());
                stmt.setInt(2,Integer.parseInt(jf3.getText()));
                stmt.setString(3,(jf4.getText()));
                stmt.setString(4,jf1.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this,"Details Added Successfullt");
            }
            else if(e.getSource()==displaybtn)
            {
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from booking");
                StringBuilder data=new StringBuilder();
                while(rs.next()){
                    data.append("Passenger Name :").append(rs.getString(1))
                    .append("Travel date :").append(rs.getString(2))
                    .append("Number of Passengers :").append(rs.getInt(3))
                    .append("Destination :").append(rs.getString(4)).append("\n");
                }
                JOptionPane.showMessageDialog(this, data.toString());
            }
        }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Error Occured "+ex.getMessage());
            }

        }
    
    public static void main(String[] args) {
        new railway();
    }

}