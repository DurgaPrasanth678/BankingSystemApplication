import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import java.util.regex.*;

public class Withdraw extends Balance
{
	JFrame fd ;
	JLabel bankname,amount,pin,pinError,successMsg;
	JButton back,close,checkingbtn,enterbtn;
	public static JTextField amountfield;
	JPasswordField pinfield;
	static String subtract;
	
	Withdraw()
	{
		fd = new JFrame("Withdraw");
		Font fontd = new Font("POPPINS",Font.BOLD,17);
		
		bankname = new JLabel("STUDENTS BANK");bankname.setBounds(140,30,250,30);
		fd.add(bankname);bankname.setFont(font1);bankname.setForeground(Color.red);
		
		pinError = new JLabel();pinError.setBounds(160,140,300,30);
		fd.add(pinError);pinError.setForeground(Color.red);
		
		successMsg = new JLabel();successMsg.setBounds(20,150,380,30);
		fd.add(successMsg);successMsg.setForeground(Color.blue);successMsg.setFont(font2);
		
		amount = new JLabel("Amount");amount.setBounds(10,70,150,30);
		fd.add(amount);amount.setFont(fontd);
		
		amountfield = new JTextField();amountfield.setBounds(160,70,200,30);
		fd.add(amountfield);amountfield.setFont(fontd);
		
		pin = new JLabel("Password");pin.setBounds(10,115,150,30);
		fd.add(pin);pin.setFont(fontd);
		
		pinfield = new JPasswordField();pinfield.setBounds(160,115,200,30);
		fd.add(pinfield);pinfield.setFont(fontd);
		/*
			Subtracting some amount from Main Balance
		*/
		enterbtn = new JButton("Withdraw");enterbtn.setBounds(160,180,100,40);
		fd.add(enterbtn);enterbtn.setFont(font2);
		enterbtn.addActionListener(ae ->
		{
			Pattern pout = Pattern.compile("[1-9]+[0-9]*");
			String sout = String.valueOf(amountfield.getText());
			Matcher mout = pout.matcher(sout);
			int f1 = 0;
			if(mout.find() && (amountfield.getText() != null) )
			{
				pinError.setText("");
				if(pinfield.getPassword().length != 0)
				{
					pinError.setText("");
					f1 =1;
				}else { pinError.setText("Please Enter valid Password");}
			}else { pinError.setText("Please Enter valid Amount");}
			
			if(f1 ==1)
			{
			try
			{
				String url = "jdbc:mysql://localhost:3306/BankingSystemApplication";
				String user = "root";
				String password = "Prasanth@6";
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection c = DriverManager.getConnection(url,user,password);
				
				String spass = String.valueOf(pinfield.getPassword());
				
				Statement st1 = c.createStatement();
				Statement st2 = c.createStatement();
				Statement st3 = c.createStatement();
				
				String query = 	"select * from clientmaintable;" ;
				String bquery = "select * from bank;" ;
				ResultSet rs = st1.executeQuery(query);
				ResultSet brs = st2.executeQuery(bquery);
				
				char[] pass = pinfield.getPassword();
				String strpass = String.valueOf(pass).trim();
				Double aamount = Double.valueOf(amountfield.getText().trim());
				Double balance = 0.0;
				int f2 = 0,f3 = 0;
				while(rs.next())
				{
					if(laccno == rs.getLong("AccountNumber")
						&& strpass.equals(rs.getString("Password")))
					{
						while(brs.next())
						{
							if(laccno == brs.getLong("AccountNumber"))
							{
								Double b = Double.valueOf(brs.getDouble("Balance"));
								if( b >= aamount)
								{
									balance = b - aamount;
									f3 = 1;
								}
								f2 = 1;	
							}
						}
					}
				}
				if(f2 ==1)
				{
					pinError.setText("");
					if(f3 == 1)
					{
						pinError.setText("");
						String update = "update bank set Balance="+balance+" where AccountNumber="+laccno+";";
						st3.executeUpdate(update);
						String msg = "Amount Successfully Withdraw from your Account";
						JOptionPane.showMessageDialog(fd,msg,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);
						enterbtn.setVisible(false);
						
					}else { pinError.setText("Please Check your Balance"); }
				}else { pinError.setText("Please Enter Valid Passwod"); }
				c.close();
			}catch(Exception e) {e.printStackTrace();}
			}
		});
		
		checkingbtn = new JButton("Balance");checkingbtn.setBounds(270,180,90,40);
		fd.add(checkingbtn);checkingbtn.setFont(font2);
		checkingbtn.addActionListener(ae ->
		{
			new Balance();
			fd.setVisible(false);
		});
				
		back = new JButton("Back");back.setBounds(30,300,100,30);
		fd.add(back);back.setFont(font2);
		back.addActionListener(ae ->
		{
			new BankOptions();
			fd.setVisible(false);
		});
		close = new JButton("Close");close.setBounds(260,300,100,30);
		fd.add(close);close.setFont(font2);
		close.addActionListener(ae ->
		{
			fd.dispose();
		});
		
		fd.setSize(500,400);
		fd.setLayout(null);
		fd.setVisible(true);
		f2.setVisible(false);
		fd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}