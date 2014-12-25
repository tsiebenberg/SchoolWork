package animations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BlackOutPanel extends JPanel {
	
	private int pixleOpacity;
	private boolean gettingDarker;
	private Timer timer;
	
	public BlackOutPanel() {
		pixleOpacity = 255;
		gettingDarker = true;
		this.setBackground(new Color(pixleOpacity, pixleOpacity, pixleOpacity));
		startAnimation();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(pixleOpacity, pixleOpacity, pixleOpacity));	
	}

	private void startAnimation() {
		timer = new Timer(35, new ChangeOpacity());
		timer.start();
	}

	private class ChangeOpacity implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if( pixleOpacity == 255 ) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException error) {
					error.printStackTrace();
				}
			}
			if( gettingDarker == true ) {
				pixleOpacity -= 5;
				if(pixleOpacity < 0 ) {
					gettingDarker = false;
					pixleOpacity = 0;
				}
				repaint();
			}
			else if( gettingDarker == false) {
				pixleOpacity += 5;
				if(pixleOpacity > 255) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "Your pokemon have been healed to full health!");
					return;
				}
				repaint();
			}
		}
		
	}
}
