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

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						JFrameInicial frame = new JFrameInicial();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		
		
	}
	
	public JFrameInicial() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 380);
		setResizable(false);

		contentPane = new JPanelCredencialsONLINE(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}
	

}