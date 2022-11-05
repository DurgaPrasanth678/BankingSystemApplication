import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Balance extends BankLogin
{
	JFrame f2;
	public static Font font1,font2;
	JLabel bankname,username,useraccount,userbkblc,time,date;
	public static String uaccount,ubkblc,utime,udate;
	JButton back,close;
	
	Balance()
	{
		f2 = new JFrame("Balance");
		font1 = new Font("ARIAL",Font.BOLD,20);
		font2 = new Font("POPPINS",Font.PLAIN,14);
		
		utime = "Time : "+java.time.LocalTime.now();
		udate = "Date : "+java.time.LocalDate.now();
		
		bankname = new JLabel("STUDENTS BANK");bankname.setBounds(140,30,300,30);
		f2.add(bankname);bankname.setFont(font1);bankname.setForeground(Color.red);
		
		username = new JLabel();username.setBounds(30,70,400,30);
		f2.add(username);username.setFont(font2);username.setForeground(Color.black);
		
		useraccount = new JLabel();useraccount.setBounds(30,100,300,30);
		f2.add(useraccount);useraccount.setFont(font2);useraccount.setForeground(Color.black);
		
		userbkblc = new JLabel();userbkblc.setBounds(30,130,300,30);
		f2.add(userbkblc);userbkblc.setFont(font2);userbkblc.setForeground(Color.black);
		
		time = new JLabel(utime);time.setBounds(30,160,200,30);
		f2.add(time);time.setFont(font2);time.setForeground(Color.black);
		
		date = new JLabel(udate);date.setBounds(30,200,150,30);
		f2.add(date);date.setFont(font2);date.setForeground(Color.black);
		try
		{
			String url = "jdbc:mysql://localhost:3306/BankingSystemApplication";
			String user = "root";
			String password = "Prasanth@6";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection c = DriverManager.getConnection(url,user,password);
				
			Statement st1 = c.createStatement();
			Statement st2 = c.createStatement();
			
			String cmtquery = 	"select AccountNumber,ClientName from clientmaintable;" ;
			String bankquery = 	"select AccountNumber,Balance from bank;" ;
			ResultSet cmtrs = st1.executeQuery(cmtquery);
			ResultSet bankrs = st2.executeQuery(bankquery);
			
			while(cmtrs.next() )
			{
				if(laccno == cmtrs.getLong("AccountNumber") )
				{
					username.setText("Client Name : "+cmtrs.getString("ClientName"));
					useraccount.setText("Account Number : "+cmtrs.getLong("AccountNumber"));
				}	
			}
			while(bankrs.next())
			{
				if(laccno== bankrs.getLong("AccountNumber"))
				{
					userbkblc.setText("Balance : "+bankrs.getDouble("Balance"));
				}
			}
			c.close();
		}catch(Exception e) {e.printStackTrace();}
		
		back = new JButton("Back");back.setBounds(30,300,100,30);
		f2.add(back);back.setFont(font2);
		back.addActionListener(ae ->
		{
			new BankOptions();
			f2.setVisible(false);
		});
		close = new JButton("Close");close.setBounds(260,300,100,30);
		f2.add(close);close.setFont(font2);
		close.addActionListener(ae ->
		{
			f2.dispose();
			f2.setVisible(false);
		});
		
		
		f2.setSize(500,400);
		f2.setLayout(null);
		f2.setVisible(true);
		f.setVisible(false);
		f2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}
