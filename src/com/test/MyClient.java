package com.test;

/*
 * Disadvantage of simple chat: 
 * 1, can just has 2 chatters: client and server,
 * 2, Client must be the 1st person to speak.
 * 3， they are both in the local area network.
 * 
 * The advanced version is multi-threading chat system.
 */

import java.net.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyClient extends JFrame implements ActionListener{
	JScrollPane jsp = null;
	JTextArea jta = null;
	JPanel jp1 = null;
	JTextField jtf = null;
	JButton jb = null;
	
	PrintWriter pw = null;
	
	public static void main(String[] args) {
		MyClient mc = new MyClient();

	}
	public MyClient(){
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		
		jtf = new JTextField(10);
		jb = new JButton("Send");
		jb.addActionListener(this);
		jp1 = new JPanel();	
		jp1.add(jtf);
		jp1.add(jb);
		
		this.add(jsp, "Center");
		this.add(jp1, "South");
		this.setSize(300, 200);
		this.setTitle("Let'sChat--Client");
		this.setVisible(true);
		
		try {
			Socket s = new Socket("127.0.0.1", 9988);
			InputStreamReader isr= new InputStreamReader(s.getInputStream());
			BufferedReader br= new BufferedReader(isr);
			// convert the output character to bytes  
			pw = new PrintWriter(s.getOutputStream(), true);
			         
			
			while(true){
				// keep reading and waiting from server
				String msgIn= br.readLine();
				jta.append("Server: " +msgIn+ "\n");
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource()==jb){
			 String msgOut = jtf.getText();
			 //also shows in the jta
			 jta.append("Client: "+ msgOut+ "\n");
			 pw.println(msgOut);
			 jtf.setText("");
		}
	}
	
}
