package gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import threading.Out;

@SuppressWarnings("serial")
public class MainScreen extends JFrame implements ActionListener {
	private JButton beep, beeps, reset, name, version, clear;
	public JTextField id;
	private boolean colorToggle=false;
	public JTextArea area;
	public static Out out;
	
	public MainScreen(Out out) {
		beep=new JButton("Beep");
		beep.addActionListener(this);
		beeps=new JButton("Beeps");
		beeps.addActionListener(this);
		reset=new JButton("Reset");
		reset.addActionListener(this);
		name=new JButton("Name");
		name.addActionListener(this);
		version=new JButton("Version");
		version.addActionListener(this);
		clear=new JButton("Clear");
		clear.addActionListener(this);
		
		area=new JTextArea(30, 80);
		id=new JTextField(25);
		id.setEditable(false);
		
		this.out=out;
		setSize(640, 480);
		setVisible(true);
		setTitle("PROMAG");
		setLayout(null);
		getContentPane().add(beep);
		getContentPane().add(beeps);
		getContentPane().add(reset);
		getContentPane().add(name);
		getContentPane().add(version);
		getContentPane().add(clear);
		getContentPane().add(id);
		getContentPane().add(area);
		beep.setBounds(540, 10, 80, 30);
		beeps.setBounds(540, 60, 80, 30);
		reset.setBounds(540, 110, 80, 30);
		name.setBounds(540, 160, 80, 30);
		version.setBounds(540, 210, 80, 30);
		clear.setBounds(540, 260, 80, 30);
		id.setBounds(540, 310, 80, 30);
		area.setBounds(10,10, 520, 470);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource()==beep) out.beep();
			if (e.getSource()==beeps) out.beeps();
			if (e.getSource()==reset) out.reset();
			if (e.getSource()==name) out.name();
			if (e.getSource()==version) out.version();
			if (e.getSource()==clear) {area.setText(""); id.setText("");}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setText(String s) {
		area.append(s);
	}
	
	public String getText() {
		return area.getText();
	}
	
	public void setID(String s) {
		id.setText(s);
		if (colorToggle) {
			id.setBackground(Color.BLUE);
		} else {
			id.setBackground(Color.GREEN);
		}
		colorToggle=!colorToggle;
	}
}
