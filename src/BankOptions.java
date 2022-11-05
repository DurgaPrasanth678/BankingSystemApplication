import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BankOptions
{
	JFrame f1;
	JButton balance,deposit,withdraw,transfer,details,close,back;
	JLabel bank_name;
	Font font1,font2,bnamef;
	
	BankOptions()
	{
		f1 = new JFrame("Bank System Application");
		bnamef = new Font("POPPINS",Font.BOLD,30);
		font1 = new Font("POPPINS",Font.BOLD,18);
		font2 = new Font("POPPINS",Font.BOLD,16);
		
		bank_name = new JLabel("STUDENTS BANK");bank_name.setBounds(270,30,350,30);
		f1.add(bank_name);bank_name.setFont(bnamef);bank_name.setForeground(Color.red);
		
		balance = new JButton("Check Balance");balance.setBounds(320,100,170,40);
		f1.add(balance);balance.setFont(font2);
		balance.addActionListener(ae ->
		{
			new Balance();
			f1.setVisible(false);
			
		});
		deposit = new JButton("Deposit Money");deposit.setBounds(320,150,170,40);
		f1.add(deposit);deposit.setFont(font2);
		deposit.addActionListener(ae ->
		{
			new Deposit();
			f1.setVisible(false);
		});
		withdraw = new JButton("Withdraw Money");withdraw.setBounds(320,200,170,40);
		f1.add(withdraw);withdraw.setFont(font2);
		withdraw.addActionListener(ae ->
		{
			new Withdraw();
			f1.setVisible(false);
		});
		transfer = new JButton("Tranfer Money");transfer.setBounds(320,250,170,40);
		f1.add(transfer);transfer.setFont(font2);
		transfer.addActionListener(ae ->
		{
			new Transfer();
			f1.setVisible(false);
		});
		details = new JButton("Details");details.setBounds(320,300,170,40);
		f1.add(details);details.setFont(font2);
		details.addActionListener(ae ->
		{
			new Information();
			f1.setVisible(false);
		});
		back = new JButton("Back");back.setBounds(30,380,100,30);
		f1.add(back);back.setFont(font2);
		back.addActionListener(ae ->
		{
			new BankLogin();
			f1.setVisible(false);
		});
		close = new JButton("Close");close.setBounds(650,380,100,30);
		f1.add(close);close.setFont(font2);
		close.addActionListener(ae ->
		{
			f1.dispose();
			f1.setVisible(false);
		});
		
		f1.setSize(800,500);
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}
}
