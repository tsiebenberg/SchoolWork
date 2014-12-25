package test;

import javax.swing.JFrame;

import model.HuntTheWumpusModel;
import view.TextView;

public class TestTextView extends JFrame {

	private static final long serialVersionUID = -7008951739775891666L;
	private HuntTheWumpusModel model;

	public static void main(String[] args) {
		new TestTextView().setVisible(true);
	}

	public TestTextView() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(772, 582);
		model = new HuntTheWumpusModel(1);
		add(new TextView(model));
	}

}
