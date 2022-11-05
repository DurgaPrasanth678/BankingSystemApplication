import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Password
{
	JLabel accno,aadhaarno,DOB,email,ifsc,pass,cpass,bname;
	JLabel accnoErr,aadhaarnoErr,DOBErr,emailErr,ifscErr,passErr,cpassErr;
	JTextField taccno,taadhaarno,tDOB,temail,tifsc,tpass,tcpass;
	JButton checkbtn,checkbtn2,submitbtn,backbtn,ifsclistbtn;
	String spass;
	Long laccno;
	
	Password()
	{
		JFrame fp = new JFrame("Change Password");
		Font fontError = new Font("POPPINS",Font.BOLD,11);
		
		Font bnamef = new Font("POPPINS",Font.BOLD,30);
		bname = new JLabel("STUDENTS BANK");bname.setBounds(230,25,350,30);fp.add(bname);
		bname.setFont(bnamef);bname.setForeground(Color.red);
		/*Labels*/
		accno = new JLabel("Enter Account Number ");accno.setBounds(140, 80, 150, 30);fp.add(accno);
		
		aadhaarno = new JLabel("Enter Aadhaar Number ");aadhaarno.setBounds(140, 130, 150, 30);fp.add(aadhaarno);
		
		DOB = new JLabel("Enter Date of Birth ");DOB.setBounds(140, 180, 150, 30);fp.add(DOB);
		
		email = new JLabel("Enter Email ID");email.setBounds(140, 230, 150, 30);fp.add(email);
		
		ifsc = new JLabel("Enter IFSC Code");ifsc.setBounds(140, 280, 150, 30);fp.add(ifsc);
		
		pass = new JLabel("Enter new Password");pass.setBounds(140, 130, 150, 30);fp.add(pass);
		pass.setVisible(false);
		
		cpass = new JLabel("Confirm Password");cpass.setBounds(140, 230, 150, 30);fp.add(cpass);
		cpass.setVisible(false);
		
		/*Text Fields*/
		taccno = new JTextField();taccno.setBounds(290, 80, 200, 30);fp.add(taccno);
		
		taadhaarno = new JTextField();taadhaarno.setBounds(290, 130, 200, 30);fp.add(taadhaarno);
		
		tDOB = new JTextField();tDOB.setBounds(290, 180, 100, 30);fp.add(tDOB);
		
		temail = new JTextField();temail.setBounds(290, 230, 200, 30);fp.add(temail);
		
		tifsc = new JTextField();tifsc.setBounds(290, 280, 200, 30);fp.add(tifsc);
		
		tpass = new JTextField();tpass.setBounds(290, 130, 200, 30);fp.add(tpass);tpass.setVisible(false);
		
		tcpass = new JTextField();tcpass.setBounds(290, 230, 200, 30);fp.add(tcpass);tcpass.setVisible(false);
		
		/*Error Labels*/
		accnoErr = new JLabel();accnoErr.setBounds(290, 100, 250, 30);fp.add(accnoErr);
		accnoErr.setForeground(Color.red);
		
		aadhaarnoErr = new JLabel();aadhaarnoErr.setBounds(290, 150, 250, 30);fp.add(aadhaarnoErr);
		aadhaarnoErr.setForeground(Color.red);
		
		DOBErr = new JLabel();DOBErr.setBounds(290, 200, 240, 30);fp.add(DOBErr);
		DOBErr.setForeground(Color.red);
		
		emailErr = new JLabel();emailErr.setBounds(290, 250, 250, 30);fp.add(emailErr);
		emailErr.setForeground(Color.red);
		
		ifscErr = new JLabel();ifscErr.setBounds(290, 300, 250, 30);fp.add(ifscErr);
		ifscErr.setForeground(Color.red);
		
		passErr = new JLabel();passErr.setBounds(290, 150, 250, 30);fp.add(passErr);
		passErr.setForeground(Color.red);
		
		cpassErr = new JLabel();cpassErr.setBounds(290, 250, 250, 30);fp.add(cpassErr);
		cpassErr.setForeground(Color.red);
		 
		/*
		 *Submit Button 
		 */
		submitbtn = new JButton("Submit");submitbtn.setBounds(300, 350, 100, 30);fp.add(submitbtn);
		submitbtn.setBackground(Color.red);submitbtn.setForeground(Color.white);
		submitbtn.setVisible(false);
		/*
		 *Check Button for Password
		 */
		checkbtn2 = new JButton("Check");checkbtn2.setBounds(300, 350, 100, 30);fp.add(checkbtn2);
		checkbtn2.setBackground(Color.red);checkbtn2.setForeground(Color.white);
		checkbtn2.setVisible(false);
		/*
		 *Check Button 
		 */
		checkbtn = new JButton("Check");checkbtn.setBounds(300, 350, 100, 30);fp.add(checkbtn);
		checkbtn.setBackground(Color.red);checkbtn.setForeground(Color.white);
		checkbtn.addActionListener(ae ->
		{
			Pattern pacc = Pattern.compile("[6-9]{1}[0-9]{9}");
			String sacc = String.valueOf(accno.getText());
			Matcher macc = pacc.matcher(sacc);
			if(macc.find() && accno.getText().length() != 0)
				accnoErr.setText("");
			else { accnoErr.setText("Please Enter your Account Number");}
			
			Pattern paadno = Pattern.compile("[1-9][0-9]{11}");
			String saadno = String.valueOf(aadhaarno.getText());
			Matcher maddno = paadno.matcher(saadno);
			if(maddno.find() && aadhaarno.getText().length() != 0)
				aadhaarnoErr.setText("");
			else { aadhaarnoErr.setText("Please Enter your Aadhaar Number");}
			if(tDOB.getText().length() != 0)
				DOBErr.setText("");
			else { DOBErr.setText("Please Enter your Date of Birth");}
			if(temail.getText().length() != 0)
				emailErr.setText("");
			else { emailErr.setText("Please Enter your Mail ID");}
			if(tifsc.getText().length() != 0)
				ifscErr.setText("");
			else { ifscErr.setText("Please Enter your IFSC Number");}
			
			if(accno.getText().length() != 0
				&& aadhaarno.getText().length() != 0
				&& DOB.getText().length() != 0
				&& email.getText().length() != 0
				&& ifsc.getText().length() != 0
				)
			{
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
				
				String query = 	"select * from clientmaintable;" ;
				String bquery = "select * from bank;" ;
				ResultSet rs = st1.executeQuery(query);
				ResultSet brs = st2.executeQuery(bquery);
				
				while(rs.next())
				{
					laccno = Long.valueOf(taccno.getText().trim());
					if(laccno == rs.getLong("AccountNumber"))
					{
						accnoErr.setText("");
					}else { accnoErr.setText("Please Enter Valid Account Number");}
					Long laadno = Long.valueOf(taadhaarno.getText().trim());
					if(laadno == rs.getLong("AadhaarNumber"))
					{
						aadhaarnoErr.setText("");
					}else { aadhaarnoErr.setText("Please Enter Valid Aadhaar Number");}
					if((tDOB.getText().trim()).equals(rs.getString("DateOfBirth")))
					{
						DOBErr.setText("");
					}else { DOBErr.setText("Please Enter Valid Date of Birth");}
					if((temail.getText()).trim().equals(rs.getString("MailID")))
					{
						emailErr.setText("");
					}else { emailErr.setText("Please Enter Valid Mail ID");}
					while(brs.next())
					{
						if(laccno == brs.getLong("AccountNumber")
							&& tifsc.getText().equals(brs.getString("IFSC")))
						{
							ifscErr.setText("");
							accno.setVisible(false);
							aadhaarno.setVisible(false);
							DOB.setVisible(false);
							email.setVisible(false);
							ifsc.setVisible(false);
							taccno.setVisible(false);
							taadhaarno.setVisible(false);
							tDOB.setVisible(false);
							temail.setVisible(false);
							tifsc.setVisible(false);
							accnoErr.setVisible(false);
							aadhaarnoErr.setVisible(false);
							DOBErr.setVisible(false);
							emailErr.setVisible(false);
							ifscErr.setVisible(false);
							pass.setVisible(true);
							cpass.setVisible(true);
							tpass.setVisible(true);
							tcpass.setVisible(true);
							passErr.setVisible(true);
							cpassErr.setVisible(true);
							checkbtn2.setVisible(true);
							checkbtn.setVisible(false);
							
						}else { ifscErr.setText("Please Enter Valid IFSC Number");}
					}
				}
				
				checkbtn2.addActionListener(ae1 ->
				{
					if(tpass.getText().length() != 0 )
					{
						passErr.setText("");
						if(tpass.getText().equals(tcpass.getText()))
						{
							cpassErr.setText("");
							spass = String.valueOf(tcpass.getText()).trim();
							String msg = "Don't Share your Password to anyone";
							int a = JOptionPane.showConfirmDialog(fp,msg);
							if(a == JOptionPane.YES_OPTION)
							{
								tpass.setEditable(false);
								tcpass.setEditable(false);
								submitbtn.setVisible(true);
								checkbtn2.setVisible(false);
								checkbtn.setVisible(false);
							}
						}else { cpassErr.setText("Please Enter Correct Password");}	
					}else { passErr.setText("Please Enter new Password");}
				});

				submitbtn.addActionListener(ae2 ->
				{
					try
					{
						String update = "update clientmaintable set Password='"+spass+"' where AccountNumber="+laccno+";";
						st3.executeUpdate(update);
						JOptionPane.showMessageDialog(fp,"Your Password Successfully Changed");
					}
					catch(Exception e) {e.printStackTrace();}
				});
			}
			catch(Exception e) {/*e.printStackTrace();*/}
			}
			
		});
		/*
		 *Back Button 
		 */
		backbtn = new JButton("Back");backbtn.setBounds(30, 400, 80, 30);fp.add(backbtn);
		backbtn.addActionListener(ae ->
		{
			new BankLogin();
			fp.setVisible(false);
		});
		/*
		 *IFSC List Button 
		 */
		ifsclistbtn = new JButton("Check IFSC");ifsclistbtn.setBounds(500, 282, 120, 25);fp.add(ifsclistbtn);
		ifsclistbtn.addActionListener(ae ->
		{
			JLabel head,c1,c2,c3,c4,c5,c6,c7,c8;
			JFrame fifsc = new JFrame("IFSC Numbers");
			head = new JLabel("Branch Name                      IFSC   Number");
			head.setBounds(10, 10, 280, 20);fifsc.add(head);head.setForeground(Color.red);
			c1 = new JLabel("AndhraPradhesh      :       STUN0000091");
			c1.setBounds(10, 30, 280, 20);fifsc.add(c1);
			c2 = new JLabel("Telangana                  :       STUN0000093");
			c2.setBounds(10, 50, 280, 20);fifsc.add(c2);
			c3 = new JLabel("Tamilnadu                  :       STUN0000094");
			c3.setBounds(10, 70, 280, 20);fifsc.add(c3);
			c4 = new JLabel("Kerala                         :       STUN0000128");
			c4.setBounds(10, 90, 280, 20);fifsc.add(c4);
			c5 = new JLabel("Maharasthra             :       STUN0000129");
			c5.setBounds(10, 110, 280, 20);fifsc.add(c5);
			c6 = new JLabel("Bihar                           :       STUN0000332");
			c6.setBounds(10, 130, 280, 20);fifsc.add(c6);
			c7 = new JLabel("Kashmir                     :       STUN0000590");
			c7.setBounds(10, 150, 280, 20);fifsc.add(c7);
			c8 = new JLabel("Other                          :       STUN0123456");
			c8.setBounds(10, 170, 280, 20);fifsc.add(c8);
			
			
			fifsc.setSize(300,400);
			fifsc.setLayout(null);
			fifsc.setVisible(true);
			
		});
		
		fp.setSize(700,500);
		fp.setLayout(null);
		fp.setVisible(true);
		fp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}
