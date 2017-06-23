package presentacio;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import aplicacio.ControladorJugador;
import presentacio.FrameSudoku;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

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
		
		ActionListener iniciarSessio = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String nomJugador = textFieldNomJugador.getText().trim();
					if (nomJugador.length() == 0){
						textFieldNomJugador.setText("");
						throw new Exception("Nom del jugador erroni, minim un caracter");
						}
					controladorJugador = new ControladorJugador(textFieldUser.getText(), textFieldPassword.getText(),
							nomJugador);

					HashMap<Integer, Date> infoPartides = controladorJugador.getInfoPartides();

					Object[] options = { "Jugar", "Crear" };
					int resposte = JOptionPane.showOptionDialog(new JFrame(),
							"Vols crear un nou taulell o jugar directament", "Seleccio", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					if (resposte == JOptionPane.OK_OPTION) {
						if (!infoPartides.isEmpty()) {
							// te partides en la bbdd?

							int selOpcioModeJoc = consultaPartidesBBDD();

							if (selOpcioModeJoc == JOptionPane.NO_OPTION) {
								// cargar partida
								int idPartida = -1;
								if (infoPartides.size() > 1) {
									// te mes d'una partida
									try {
										int respostaMostrarPartidesBBDD = mostrarPartidesBBDD(infoPartides);
										idPartida = respostaMostrarPartidesBBDD;
									} catch (Exception e) {
										controladorJugador.setOffline();
										System.exit(0);
									}

								} else {
									// sol te una pertida
									idPartida = (int) infoPartides.keySet().toArray()[0];
								}

								carregarPartidaBBDD(idPartida);
								jFrameInicial.dispose();
							} else if (selOpcioModeJoc == JOptionPane.OK_OPTION) {
								// no cargar partida
								crearNovaPartidaAux();
								jFrameInicial.dispose();
							} else {
								controladorJugador.setOffline();
							}

						} else {
							// no te partides en la bbdd
							crearNovaPartidaAux();
							jFrameInicial.dispose();
						}

					} else if (resposte == JOptionPane.NO_OPTION) {
						controladorJugador.novaPartida();
						new FrameSudoku(controladorJugador, FrameSudoku.CREACIO);
						jFrameInicial.dispose();
					} else {
						controladorJugador.setOffline();
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}

		};

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
		textFieldNomJugador.addActionListener(iniciarSessio);

		JButton btnNewButton = new JButton("Iniciar Sessi\u00F3");
		btnNewButton.setBounds(359, 286, 144, 35);
		add(btnNewButton);
		btnNewButton.addActionListener(iniciarSessio);
	}

	private int consultaPartidesBBDD() {
		Object[] options = { "Crear nova partida", "Carregar partida" };
		int respostaConsultaPartidesBBDD = JOptionPane.showOptionDialog(new Frame(),
				"Vols crear una partida nova o carregar de BBDD?", "Partides de Base de Dades",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		return respostaConsultaPartidesBBDD;
	}

	private void carregarPartidaBBDD(int idPartida) throws Exception {
		controladorJugador.carregarPartida(idPartida);
		new FrameSudoku(controladorJugador, FrameSudoku.JUGAR);

	}

	private int mostrarPartidesBBDD(HashMap<Integer, Date> infoPartides) {

		Object[] options = new Object[infoPartides.size()];
		int[] ids = new int[infoPartides.size()];

		int i = 0;
		for (Integer id : infoPartides.keySet()) {
			options[i] = id.toString() + ": " + infoPartides.get(id).toString();
			ids[i] = id;
			i++;
		}

		Object partida = JOptionPane.showInputDialog(new Frame(), "Selecciona la partida que volguis jugar:\n",
				"Partides registrades a BBDD", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		int j = java.util.Arrays.asList(options).indexOf(partida);

		return ids[j];

	}

	private void crearNovaPartidaAux() throws Exception {

		controladorJugador.novaPartida();
		controladorJugador.generarGraellaAux();
		new FrameSudoku(controladorJugador, FrameSudoku.JUGAR);

	}
}
