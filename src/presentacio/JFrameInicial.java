package presentacio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aplicacio.ControladorJugador;
import presentacio.JPanelCredencialsONLINE;

public class JFrameInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Object[] options = {"ONLINE","OFFLINE"};
		int modeDeJoc = JOptionPane.showOptionDialog(new Frame(), "Vols jugar amb connexió a BBDD (ONLINE) o sense (OFFLINE)?",
											"Mode de joc", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		if(modeDeJoc == 0){ // Si el jugador tria jugar ONLINE
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						JFrameInicial frame = new JFrameInicial(modeDeJoc);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		else{
			/*
			 * GENERAR EL JOC COM SEMPRE.
			 */
			
			System.exit(0);
		}
		
		
		
	}
	
	public JFrameInicial(int modeDeJoc) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 380);
		setResizable(false);

		contentPane = new JPanelCredencialsONLINE();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}
	

}
