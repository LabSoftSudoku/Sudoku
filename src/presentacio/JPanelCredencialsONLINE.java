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
import java.sql.Timestamp;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JButton;


public class JPanelCredencialsONLINE extends JPanel {
	private JFrameInicial jFrameInicial;
	
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	private JTextField textFieldNomJugador;
	
	private ControladorJugador controladorJugador;
	
	public JPanelCredencialsONLINE(JFrameInicial jFrameInicial) {
		this.jFrameInicial = jFrameInicial;
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
					
					
					
					HashMap<Integer, Timestamp> infoPartides = controladorJugador.getInfoPartides();
					
					if(!infoPartides.isEmpty()){
						
						if(consultaPartidesBBDD()==1){
							
							System.out.println("cargar partida");
							int idPartida;
							if(infoPartides.size()>1){
								
								idPartida = mostrarPartidesBBDD(infoPartides);
								
							}else{
								idPartida = (int) infoPartides.keySet().toArray()[0];
							}
							
							
							carregarPartidaBBDD(idPartida);
							new FrameSudoku(controladorJugador);
						}
						
					}else{
						crearNovaPartida();
					}
					
					jFrameInicial.dispose();
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new Frame(), e, "ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});		
	}
	
	private int consultaPartidesBBDD() {
		Object[] options = {"Crear nova partida","Carregar partida"};
		int respostaConsultaPartidesBBDD = JOptionPane.showOptionDialog(new Frame(), "Vols crear una partida nova o carregar de BBDD?",
											"Partides de Base de Dades", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		return respostaConsultaPartidesBBDD;
	}

	private void carregarPartidaBBDD(int idPartida) throws Exception {
		controladorJugador.carregarPartida(idPartida);
		new FrameSudoku(controladorJugador);
		
	}
	

	private int mostrarPartidesBBDD(HashMap<Integer, Timestamp> infoPartides) {
		
		Object[] options = new Object[infoPartides.size()];
		int[] ids = new int[infoPartides.size()];
		
		int i = 0;
		for (Integer id : infoPartides.keySet()) {
			options[i] = id.toString()+": "+infoPartides.get(id).toString();
			ids[i] = id;
			i++;
		}
		
		Object partida = JOptionPane.showInputDialog(new Frame(), "Selecciona la partida que volguis jugar:\n",
		                    "Partides registrades a BBDD", JOptionPane.PLAIN_MESSAGE, null, options,options[0]);
		
		int j = java.util.Arrays.asList(options).indexOf(partida);
		
		
		return ids[j];
		
		
	}

	private void crearNovaPartida() throws Exception {
		
		controladorJugador.novaPartida();
		new FrameSudoku(controladorJugador);
		
	}

}
