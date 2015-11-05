package BetalingsAfhandeling;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PinView extends JFrame {
	private static final long serialVersionUID = 1L;
	String pin;
	JLabel scherm;
	
	public PinView(int rekeningsnummer) {
		pin = new String();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(300, 300));
		Container contents = getContentPane();
		contents.setLayout(new GridLayout(5, 3));
		contents.add(new JLabel(""));
		scherm = new JLabel("", SwingConstants.CENTER);
		contents.add(scherm);
		contents.add(new JLabel(""));
		for(int i = 1; i < 10; i++) {;
			contents.add(createButton(i));
		}
		
		JButton corrButton = new JButton("CORR");
		JButton submitButton = new JButton("OK");
		corrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pin.length() > 0) {
					pin = pin.substring(0, pin.length() - 1);
					scherm.setText("" + pin);
				}
			}
		});
		
		corrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stuur naar bank
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
					scherm.setText("" + pin);
				}
			}
		});
		return newButton;
	}
	
}
