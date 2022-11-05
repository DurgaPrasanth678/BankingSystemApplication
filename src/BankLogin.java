import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;
import java.util.*;
import java.sql.*;

public class BankLogin
{
	public static JFrame f;
	public static JTextField accnumber;//,pin;
	public static JLabel l1,l2,bname,Acc_error;
	public static Font bnamef,font1,font2;
	public static JButton loginbtn,resetbtn,forgetbtn,createaccbtn,closebtn;
	public static Long laccno;
	public static JPasswordField pin; 

	BankLogin()
	{
		f = new JFrame("Bank System Application");
		
		bnamef = new Font("POPPINS",Font.BOLD,30);
		font1 = new Font("POPPINS",Font.BOLD,18);
		font2 = new Font("POPPINS",Font.BOLD,16);
		
		bname = new JLabel("STUDENTS BANK");bname.setBounds(270,30,350,30);f.add(bname);
		bname.setFont(bnamef);bname.setForeground(Color.red);
		
		//Account number,Pin and new account creation text fields
		accnumber = new JTextField();accnumber.setBounds(350,100,200,40);
		f.add(accnumber);accnumber.setFont(font2);
		
		pin = new JPasswordField();pin.setBounds(350,160,200,40);f.add(pin);pin.setFont(font2);
		
		//Account number,Pin and new account creation Labels
		l1 = new JLabel("Account Number  ");l1.setBounds(160,100,250,40);f.add(l1);l1.setFont(font1);
		l2 = new JLabel("Password  ");l2.setBounds(160,160,150,40);f.add(l2);l2.setFont(font1);
		
		Acc_error = new JLabel();Acc_error.setBounds(350,200,350,30);
		f.add(Acc_error);Acc_error.setForeground(Color.red);
		
		//Login Button
		loginbtn = new JButton("LogIn");loginbtn.setBounds(350,250,90,40);f.add(loginbtn);
		loginbtn.setFont(font2);
		loginbtn.addActionListener(ae ->
		{
			Pattern paccno = Pattern.compile("[6-9]{1}[0-9]{9}");
			String saccno = accnumber.getText();
			Matcher maccno = paccno.matcher(saccno);
			int fa = 0,fp = 0;
			if(accnumber.getText().length() != 0 && maccno.find())
			{
				Acc_error.setText("");
				fa = 1;
				if(pin.getPassword().length != 0)
				{
					Acc_error.setText("");
					fp = 1;
				}else{	Acc_error.setText("Please Enter Password");	}
			}else{	Acc_error.setText("Please Enter Valid Account Number");	}
			if(fa == 1 && fp ==1)
			{
			try
			{
				String url = "jdbc:mysql://localhost:3306/BankingSystemApplication";
				String user = "root";
				String password = "Prasanth@6";
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Connection c = DriverManager.getConnection(url,user,password);
				
				laccno = Long.valueOf(String.valueOf(accnumber.getText().trim()));
				
				String spass = String.valueOf(pin.getPassword()).trim();
				
				Statement st = c.createStatement();
				
				String query = 	"select AccountNumber,Password from clientmaintable;" ;
				ResultSet rs = st.executeQuery(query);
				
				int f1 = 0,f2 = 0;
				while(rs.next())
				{
					if(	laccno == rs.getLong("AccountNumber") )
					{
						f1 = 1;
						if( spass.equals(rs.getString("Password")) )
						{
							f2 = 1;
						}
					}
				}
				if(f1 == 1)
				{
					Acc_error.setText("");
					if(f2 == 1 )
					{
						Acc_error.setText("");
						new BankOptions();
						f.setVisible(false);
					}else{	Acc_error.setText("Please Enter valid Password");	}
				}else{	Acc_error.setText("Account Not Found,Please create an Account");	}
				c.close();
			}catch(Exception e) {e.printStackTrace();}
			}
		});
		
		//Reset Button
		resetbtn = new JButton("Reset");resetbtn.setBounds(460,250,90,40);f.add(resetbtn);resetbtn.setFont(font2);
		resetbtn.addActionListener(ae ->
		{
			accnumber.setText("");pin.setText("");
		});
		
		//Forget Button
		forgetbtn = new JButton("forget password?");forgetbtn.setBounds(400,227,150,15);
		f.add(forgetbtn);//forgetbtn.setFont(font1);
		forgetbtn.addActionListener(ae ->
		{
			new Password();
			f.setVisible(false);
		});
		
		//Create new account Button
		createaccbtn = new JButton("Create Account");createaccbtn.setBounds(150,350,200,40);
		f.add(createaccbtn);createaccbtn.setFont(font1);
		createaccbtn.addActionListener(ae ->
		{
			new Bank_Account_Creation();
			f.setVisible(false);
		});				
//		//Close Button
		closebtn = new JButton("Close");closebtn.setBounds(530,350,200,40);
		f.add(closebtn);closebtn.setFont(font1);
		closebtn.addActionListener(ae ->{
			f.dispose();
		});
		
		f.setSize(800,500);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String buddy[]) throws Exception
	{
		 new BankLogin();	
	}	
}
