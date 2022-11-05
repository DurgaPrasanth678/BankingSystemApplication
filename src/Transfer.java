import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import java.util.regex.*;

public class Transfer extends Balance
{
	JFrame ft ;
	JLabel bankname,amount,pin,pinError,successMsg,receiver;
	JButton back,close,checkingbtn,enterbtn;
	public static JTextField amountfield,receiverfield;
	JPasswordField pinfield;
	static String subtract;
	Double aamount;
	
	Transfer()
	{
		ft = new JFrame("Transfer");
		Font fontd = new Font("POPPINS",Font.BOLD,17);
		
		bankname = new JLabel("STUDENTS BANK");bankname.setBounds(140,30,250,30);
		ft.add(bankname);bankname.setFont(font1);bankname.setForeground(Color.red);
		/*
		Receiver Bank Account Number 
		*/
		receiver = new JLabel("Account Number");receiver.setBounds(10,70,200,30);
		ft.add(receiver);receiver.setFont(fontd);
		receiverfield = new JTextField();receiverfield.setBounds(160,70,200,30);
		ft.add(receiverfield);receiverfield.setFont(fontd);
		
		amount = new JLabel("Amount");amount.setBounds(10,115,150,30);
		ft.add(amount);amount.setFont(fontd);
		amountfield = new JTextField();amountfield.setBounds(160,115,200,30);
		ft.add(amountfield);amountfield.setFont(fontd);
		
		pin = new JLabel("Password");pin.setBounds(10,160,150,30);
		ft.add(pin);pin.setFont(fontd);
		pinfield = new JPasswordField();pinfield.setBounds(160,160,200,30);
		ft.add(pinfield);pinfield.setFont(fontd);
		
		pinError = new JLabel();pinError.setBounds(160,190,350,30);
		ft.add(pinError);pinError.setForeground(Color.red);
		
		successMsg = new JLabel();successMsg.setBounds(20,200,380,30);
		ft.add(successMsg);successMsg.setForeground(Color.blue);successMsg.setFont(font2);
		/*
			--->Transfer  some amount to Another Account
			--->subtracting some amount from Main Balance
		*/
		enterbtn = new JButton("Transfer");enterbtn.setBounds(160,240,90,40);
		ft.add(enterbtn);enterbtn.setFont(font2);
		enterbtn.addActionListener(ae ->
		{
			int f = 0;
			Pattern pacc = Pattern.compile("[6-9]{1}[0-9]{9}");
			String sacc = String.valueOf(receiverfield.getText());
			Matcher macc = pacc.matcher(sacc);
			String sr = String.valueOf(receiverfield.getText());
			String slaccno = String.valueOf(laccno);
			if(receiverfield.getText() != null 
					&& macc.find() )
			{
				if( sr.equals(slaccno))
				{
					pinError.setText("This Type of Transection Not possible");	
				}
				else 
				{ 
					pinError.setText(""); 
					Pattern pam = Pattern.compile("[0-9]+([.][0-9])*?");
					String sam = String.valueOf(amountfield.getText());
					Matcher mam = pam.matcher(sam);
					if((amountfield.getText() != null) && mam.find() )
					{
						pinError.setText("");
						f = 1;
					}else { pinError.setText("Please Enter Valid Amount"); }
				}
			}else { pinError.setText("Please Enter Valid Account Number"); }
					
			try
			{
				String url = "jdbc:mysql://localhost:3306/BankingSystemApplication";
				String user = "root";
				String password = "Prasanth@6";
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection c = DriverManager.getConnection(url,user,password);
				
				Statement st1 = c.createStatement();
				Statement st2 = c.createStatement();
				Statement st3 = c.createStatement();
				Statement st4 = c.createStatement();
				Statement st5 = c.createStatement();
				
				String query = 	"select * from clientmaintable;" ;
				String bquery = "select * from bank;" ;
				ResultSet rs = st1.executeQuery(query);
				ResultSet brs = st2.executeQuery(bquery);
				ResultSet brs2 = st5.executeQuery(bquery);
				int flag = 0,flag1 = 0,flag3 =0;
				if(f ==1)
				{
					while(rs.next())
					{
						char[] password2 = pinfield.getPassword();
						String spass = String.valueOf(password2).trim();
						long long1 = rs.getLong("AccountNumber");
						if( laccno == long1 
							&& spass.equals(rs.getString("Password"))
							)
						{
							pinError.setText("");
							flag1 = 1;
						}
						else { pinError.setText("Enter Valid Password");}
					}
					while(brs.next())
					{
						if(laccno == brs.getLong("AccountNumber") && flag1 ==1)
						{
							Double b = Double.valueOf(brs.getDouble("Balance"));
							aamount = Double.valueOf(amountfield.getText().trim());
							if( b >= aamount )
							{
								pinError.setText("");
								Double balance = b - aamount;
								String update = "update bank set Balance="+balance+" where AccountNumber="+laccno+";";
								st3.executeUpdate(update);
								flag = 1;
							}
							else { pinError.setText("Please Check your Balance");}
						}		
					}
					Long raccno = Long.valueOf(receiverfield.getText().trim());
					int flag4 = 0;
					while(brs2.next())
					{
						if(raccno == brs2.getLong("AccountNumber") && flag == 1 )
						{
							aamount = Double.valueOf(amountfield.getText().trim());
							Double balance = Double.valueOf(brs2.getDouble("Balance"));
							balance += aamount;
							String update = "update bank set Balance="+balance+" where AccountNumber="+raccno+";";
							st4.executeUpdate(update); 
							String msg = "Transaction Successfully Completed";
							JOptionPane.showMessageDialog(ft,msg,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);
							enterbtn.setVisible(false);
							flag4  = 1;
						}
					}
					if( flag4 == 0 && flag == 1)
					{
						String msg = "Transaction Successfully Completed";
						JOptionPane.showMessageDialog(ft,msg,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);
						enterbtn.setVisible(false);
					}
				}
				
			}
			catch(Exception e) { e.printStackTrace();}
		});
		
		checkingbtn = new JButton("Balance");checkingbtn.setBounds(260,240,90,40);
		ft.add(checkingbtn);checkingbtn.setFont(font2);
		checkingbtn.addActionListener(ae ->
		{
			new Balance();
			ft.setVisible(false);
		});
		
		back = new JButton("Back");back.setBounds(30,300,100,30);
		ft.add(back);back.setFont(font2);
		back.addActionListener(ae ->
		{
			new BankOptions();
			ft.setVisible(false);
		});
		close = new JButton("Close");close.setBounds(260,300,100,30);
		ft.add(close);close.setFont(font2);
		close.addActionListener(ae ->
		{
			ft.dispose();
		});
		
		ft.setSize(500,400);
		ft.setLayout(null);
		ft.setVisible(true);
		f2.setVisible(false);
		ft.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}
