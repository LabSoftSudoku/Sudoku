package presentacio;

import aplicacio.ControladorJugador;
import aplicacio.ControladorSudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FrameSudoku{

	private JFrame frame;
	private CasellaGrafica taulaCasella[][] = new CasellaGrafica[9][9];
	private ControladorSudoku controladorSudoku;
	private BarraMenu barraMenu;
	private Listener listener;

	public FrameSudoku(ControladorJugador controladorJugador) {

		try {
			controladorSudoku = new ControladorSudoku(controladorJugador);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "ERROR: en inicialitzar el programa"+e.getStackTrace(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		initialize();
	}

	private void initialize() {

		frame = new JFrame("SUDOKU V.5");
		barraMenu = new BarraMenu(this);
		frame.setSize(500, 500);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(barraMenu);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	
		        	try {
						controladorSudoku.guardarPartida();
						controladorSudoku.setOffline();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); // esta mal
					}
		            System.exit(0);
		        }
		        
		        System.exit(0);//mirar
		    }
		});

		JPanel sudokuPanel = new JPanel();
		sudokuPanel.setSize(500, 500);
		sudokuPanel.setLayout(new GridLayout(3, 3, 0, 0));
		frame.getContentPane().add(sudokuPanel, BorderLayout.CENTER);

		JPanel regioPanel[][] = new JPanel[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				regioPanel[i][j] = new JPanel();
				sudokuPanel.add(regioPanel[i][j]);
				regioPanel[i][j].setLayout(new GridLayout(3, 3, 0, 0));
				regioPanel[i][j].setBorder(new LineBorder(new Color(0, 0, 0), 1));
			}
		}
		
		listener = new Listener(controladorSudoku, taulaCasella);

		CasellaGrafica casellaGrafica;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				for (int l = 0; l < 3; l++) {
					for (int j = 0; j < 3; j++) {
						taulaCasella[i * 3 + k][l * 3 + j] = new CasellaGrafica(i * 3 + k, l * 3 + j);
						casellaGrafica = taulaCasella[i * 3 + k][l * 3 + j];
						regioPanel[i][l].add(casellaGrafica);
						casellaGrafica.setBorder(new LineBorder(new Color(0, 0, 0), 1));
						casellaGrafica.setFont(new Font("Tahoma", Font.BOLD, 25));
						casellaGrafica.setHorizontalAlignment(SwingConstants.CENTER);
						casellaGrafica.setBackground(Color.WHITE);
						casellaGrafica.addKeyListener(listener);
						casellaGrafica.addMouseListener(listener);
						casellaGrafica.setCaretColor(new Color(255,255,255));
						casellaGrafica.setCursor(new Cursor(Cursor.HAND_CURSOR));
						
					}
				}
			}
		}
		
		/*
		Object[] options = { "Jugar", "Crear" };
		int n = JOptionPane.showOptionDialog(new JFrame(), "Vols crear un nou taulell o jugar directament",
				"Seleccio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (n == JOptionPane.OK_OPTION) { //JOptionPane.OK_OPTION
			try {
				//controladorSudoku.generarSudokuAux();
				generarSodoku();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "ERROR: en inicialitzar el programa", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			frame.setSize(500, 540);

			JButton bJugar = new JButton("Jugar");
			bJugar.setSize(500, 20);
			frame.getContentPane().add(bJugar, BorderLayout.SOUTH);

			bJugar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int num = 0;

					String[][] t = controladorSudoku.getNumeros();

					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							if (t[i][j] != "") {
								num++;
							}
						}
					}
					if (num>80) {
						JOptionPane.showMessageDialog(new JFrame(), "Ha d'haver menys de 80 n�meros");
					} else if(num<17){
						JOptionPane.showMessageDialog(new JFrame(), "Ha d'haver m�s de 17 n�meros ");
					}else{
						try {
							controladorSudoku.setNumerosInicials();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(new JFrame(), "ERROR: n�meros inicials", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						generarSodoku();
						frame.getContentPane().remove(bJugar);
						frame.setSize(500, 500);
						listener.enJoc();
					}
				}
			});
		}
		*/
		generarSodoku();
		frame.setVisible(true);
		
	}

	public void generarSodoku() {
		
		
		
		barraMenu.activeOpcioNou();
		String[][] taulell = controladorSudoku.getNumerosInicials();
		CasellaGrafica casellaGrafica;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				for (int l = 0; l < 3; l++) {
					for (int j = 0; j < 3; j++) {
						casellaGrafica = taulaCasella[i * 3 + k][l * 3 + j];
						casellaGrafica.removeKeyListener(listener);
						casellaGrafica.removeMouseListener(listener);
						casellaGrafica.setCaretColor(Color.WHITE);
						if (taulell[i * 3 + k][l * 3 + j] != null) {
							casellaGrafica.setText(taulell[i * 3 + k][l * 3 + j]);
							casellaGrafica.setEditable(false);
							casellaGrafica.setBackground(Color.GRAY);
							casellaGrafica.setForeground(Color.YELLOW);
							casellaGrafica.setBackgroundColor(Color.GRAY);
							casellaGrafica.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						} else {
							casellaGrafica.setText("");
							casellaGrafica.setEditable(true);
							casellaGrafica.setBackground(Color.WHITE);
							casellaGrafica.setForeground(Color.BLACK);
							casellaGrafica.setBackgroundColor(Color.WHITE);
							casellaGrafica.addKeyListener(listener);
							casellaGrafica.addMouseListener(listener);
							casellaGrafica.setCursor(new Cursor(Cursor.HAND_CURSOR));
							
						}
					}
				}
			}
		}
	}

	public void generarNouSodoku() throws Exception {
		controladorSudoku.generarNouSudoku();
		generarSodoku();
	}

}
