package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.BeginningView;

public class testBeginningView extends JFrame implements ActionListener {
	
	
	
	public static void main(String[] args){
		new testBeginningView().setVisible(true);
		
	}
	
	public testBeginningView(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		this.add(new BeginningView());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("stuff");
	}
	
	
	
	

}
