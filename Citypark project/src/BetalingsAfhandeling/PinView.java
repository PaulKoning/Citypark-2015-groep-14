package BetalingsAfhandeling;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import bankclient.BankProxy;

/**
 * 
 * @author Koen Berghuis
 * Geeft een pinapparaat weer en communiceert met de bank webservice om de betaling af te handelen
 *
 */

public class PinView extends JFrame {
	private static final long serialVersionUID = 1L;
	String pin;
	JLabel scherm;
	private static final int REKENINGSID_CITYPARK = 1;
	
	public PinView(final int rekeningsnummer, final double bedrag) {
		pin = new String();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(300, 300));
		Container contents = getContentPane();
		contents.setLayout(new GridLayout(5, 3));
		//lege JLabel om de positie van het scherm goed te krijgen in de grid
		contents.add(new JLabel(""));
		scherm = new JLabel("", SwingConstants.CENTER);
		scherm.setText(String.valueOf(bedrag));
		contents.add(scherm);
		contents.add(new JLabel(""));
		//De knoppen voor 1 tot 9
		for(int i = 1; i < 10; i++) {;
			contents.add(createButton(i));
		}
		JButton corrButton = new JButton("CORR");
		JButton submitButton = new JButton("OK");
		//Correctie knop verwijderd het laatste cijfer van de huidige pin
		corrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pin.length() > 0) {
					pin = pin.substring(0, pin.length() - 1);
					scherm.setText("" + pin);
				}
			}
		});
		
		//Maak verbinding met de bank om de transactie uit te voeren, laat zien of dit geslaagd is of niet
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankProxy bank = new BankProxy();
				try {
					if(bank.transferMetPin(rekeningsnummer, REKENINGSID_CITYPARK, bedrag, Integer.parseInt(pin))) {
						scherm.setText("Geslaagd");
					} else {
						scherm.setText("Afgebroken");
						pin = "";
					}
				} catch (NumberFormatException | RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		contents.add(corrButton);
		contents.add(createButton(0));
		contents.add(submitButton);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Maakt JButton die de gegeven int weergeeft en bij klikken de gegeven int aan de huidige pincode toevoegd
	 * @param int number
	 * @returns JButton
	 */
	JButton createButton(int number) {
		final int tmp = number;
		JButton newButton = new JButton(String.valueOf(number));
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pin.length() < 4) {
					pin += String.valueOf(tmp);
					scherm.setText("" + pin);
				}
			}
		});
		return newButton;
	}
	
}
