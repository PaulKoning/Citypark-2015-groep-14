package BetalingsAfhandeling;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PinView extends JFrame {
	private static final long serialVersionUID = 1L;
	String pin;
	
	public PinView() {
		pin = new String();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(300, 300));
		Container contents = getContentPane();
		contents.setLayout(new GridLayout(4, 3));
		
		for(int i = 1; i < 10; i++) {;
			contents.add(createButton(i));
		}
		
		JButton corrButton = new JButton("CORR");
		JButton submitButton = new JButton("OK");
		corrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pin.length() > 0) {
					pin = pin.substring(0, pin.length() - 1);
				}
			}
		});
		corrButton.addActionListener(new ActionListener() {
			//TODO: submit code
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		contents.add(corrButton);
		contents.add(createButton(0));
		contents.add(submitButton);
		
		pack();
		setVisible(true);
	}
	
	/*
	 * Maakt JButton die de gegeven int weergeeft en bij klikken de gegeven int aan de huidige pincode toevoegd
	 * @param int number
	 * @returns JButton
	 */
	JButton createButton(int number) {
		JButton newButton = new JButton(String.valueOf(number));
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pin.length() < 4) {
					pin += String.valueOf(number);
				}
			}
		});
		return newButton;
	}
	
}
