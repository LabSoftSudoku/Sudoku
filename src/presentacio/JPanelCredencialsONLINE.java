package presentacio;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import aplicacio.ControladorJugador;
import presentacio.FrameSudoku;

import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.JButton;

public class JPanelCredencialsONLINE extends JPanel {
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private JTextField textFieldNomJugador;
	
	private ControladorJugador controladorJugador;
	
	public JPanelCredencialsONLINE() {
		
		generarPanellCredencials();
	}

	private void generarPanellCredencials() {
		setLayout(null);
		
		JLabel lblIntrodueixLesCredencials = new JLabel("Introdueix les credencials de la BBDD");
		lblIntrodueixLesCredencials.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIntrodueixLesCredencials.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntrodueixLesCredencials.setBounds(12, 13, 466, 77);
		add(lblIntrodueixLesCredencials);
		
		JLabel lblUserBbdd = new JLabel("User BBDD:");
		lblUserBbdd.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserBbdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserBbdd.setBounds(22, 89, 133, 53);
		add(lblUserBbdd);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(166, 104, 235, 22);
		add(textFieldUser);
		textFieldUser.setColumns(10);
		textFieldUser.setText("G9GEILAB1");
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(22, 155, 133, 53);
		add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(166, 170, 235, 22);
		add(textFieldPassword);
		textFieldPassword.setText("G9GEILAB181");
		
		JLabel lblNomJugador = new JLabel("Nom jugador:");
		lblNomJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomJugador.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomJugador.setBounds(22, 221, 133, 53);
		add(lblNomJugador);
		
		textFieldNomJugador = new JTextField();
		textFieldNomJugador.setColumns(10);
		textFieldNomJugador.setBounds(166, 236, 235, 22);
		add(textFieldNomJugador);
		textFieldNomJugador.setText("NomProva");
		
		JButton btnNewButton = new JButton("Iniciar Sessi\u00F3");
		btnNewButton.setBounds(359, 286, 119, 35);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					controladorJugador = new ControladorJugador(textFieldUser.getText(), textFieldPassword.getText(), textFieldNomJugador.getText()); 
					
					
					
					
					// AQUI HEM DE ELIMINAR EL JFRAME actual
					
					
					
					
					
					
					
					
					// Comprobar si existeixen partides -> si no existeixen cridar al metode crearNovaPartida();
					
					// if()
					consultaPartidesBBDD();
					// else
					// crearNovaPartida();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new Frame(), e, "ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});		
	}
	
	private void consultaPartidesBBDD() {
		Object[] options = {"Crear nova partida","Carregar partida"};
		int respostaConsultaPartidesBBDD = JOptionPane.showOptionDialog(new Frame(), "Vols crear una partida nova o carregar de BBDD?",
											"Partides de Base de Dades", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if (respostaConsultaPartidesBBDD == 1) { // Carregar partida de BBDD
			try {
				carregarPartidaBBDD();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		} else{ // El jugador vol crear una partida no jugada abans (nova)
			crearNovaPartida();
		}
	}

	private void carregarPartidaBBDD() throws Exception {
		
		// numeroPartides per fer proves
		int numeroPartides = 1;
		
		
		if(numeroPartides  == 1)
			
			
			
			// OJO! Falten coses crec perqe no s'ha carregat res.
			
			new FrameSudoku(controladorJugador);
		
		
		
		
		else
			if(numeroPartides > 1)
				mostrarPartidesBBDD();
			else
				throw new Exception("Error: No s'ha pogut determinar el nombre de partides a BBDD.");
	}

	private void mostrarPartidesBBDD() {
		
		Object[] possibilities = {"ham", "spam", "yam"};
		
		
		
		
		
		
		// Posibilities dona error ja que fem servir un map (replantejar la manera de fer-ho o fer un JPanel independent)
		// controladorJugador.getInfoPartides()
		
		
		
		
		
		
		String s = (String)JOptionPane.showInputDialog(new Frame(), "Selecciona la partida que volguis jugar:\n",
		                    "Partides registrades a BBDD", JOptionPane.PLAIN_MESSAGE, null, possibilities,"");

	}

	private void crearNovaPartida() {
		
		
		
		
		
		/*
		 * PROBLEMAAAAAAAAAAAA!!
		 * 
		 * COMO !*? cerramos el JFRAME si no tenemos control sobre el aqui??
		 */
		
		
		
		
		
		new FrameSudoku(controladorJugador);
		
	}

}
