package presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aplicacio.ControladorJugador;
import aplicacio.ControladorSudoku;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Inici extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtNom;
	private ControladorJugador controladorJugador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inici frame = new Inici();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //esta mal
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Inici() throws Exception {
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnJugarOffline = new JButton("Jugar Offline");
		btnJugarOffline.setBounds(264, 233, 140, 25);
		contentPane.add(btnJugarOffline);
		btnJugarOffline.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(52, 120, 70, 15);
		contentPane.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setText("nom");
		txtNom.setBounds(170, 118, 114, 19);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		txtNom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					controladorJugador = new ControladorJugador("G9GEILAB1", "G9GEILAB181", txtNom.getText());// esto se coje de la textField
					
				} catch (Exception e) {
					
					e.printStackTrace(); // no system.print
				}
				
				
			}});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
