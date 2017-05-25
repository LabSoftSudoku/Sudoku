package presentacio;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import aplicacio.ControladorSudoku;

class Listener implements KeyListener, MouseListener {

	private CasellaGrafica casellaSeleccionada;
	private int[] ultimPosColor = { -1, -1 };
	private CasellaGrafica taulaCasella[][];
	private ControladorSudoku intermediari;
	private boolean enJoc = false;

	public Listener(ControladorSudoku intermediari, CasellaGrafica[][] taulaCasella) {
		this.intermediari = intermediari;
		this.taulaCasella = taulaCasella;

	}
	
	public void enJoc(){
		enJoc = true;
	}

	public void actualitzarFrame() {
		CasellaGrafica casellaGrafica;
		String[][] taulell = intermediari.getNumeros();
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				for (int l = 0; l < 3; l++) {
					for (int j = 0; j < 3; j++) {
						casellaGrafica = taulaCasella[i * 3 + k][l * 3 + j];
						if (taulell[i * 3 + k][l * 3 + j] != null) {
							casellaGrafica.setText(taulell[i * 3 + k][l * 3 + j]);
						} else {
							casellaGrafica.setText("");
						}
					}
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent a) {
	}

	@Override
	public void keyTyped(KeyEvent a) {
	}
	
	@Override
	public void keyReleased(KeyEvent a) {
		int i, j;
		int[] ultimError;

		try {
			if (ultimPosColor[0] != -1){
				taulaCasella[ultimPosColor[0]][ultimPosColor[1]]
						.setBackground(taulaCasella[ultimPosColor[0]][ultimPosColor[1]].getBackgroundColor());
				ultimPosColor[0] = -1;
				ultimPosColor[1] = -1;
			}

			CasellaGrafica casellaGrafica = (CasellaGrafica) a.getSource();
			i = casellaGrafica.getFila();
			j = casellaGrafica.getColumna();

			if (casellaGrafica.getText().equals("")) {

				intermediari.borrarValorSodoku((i + 1) + "", (j + 1) + "");

			} else {

				intermediari.addValorSudoku((i + 1) + "", (j + 1) + "", casellaGrafica.getText());

				ultimError = intermediari.getUltimError();

				if (ultimError[0] != -1) {

					ultimPosColor[0] = ultimError[0];
					ultimPosColor[1] = ultimError[1];

					taulaCasella[ultimError[0]][ultimError[1]].setBackground(Color.RED);
					taulaCasella[i][j].setText(null);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
			actualitzarFrame();
		}

		if (enJoc && intermediari.isSodokuComplet()) {
			JOptionPane.showMessageDialog(new JFrame(), "Felicitats! Has guanyat");
		}
	}

	@Override
	public void mouseClicked(MouseEvent a) {
	}

	@Override
	public void mouseEntered(MouseEvent a) {
	}

	@Override
	public void mouseExited(MouseEvent a) {
	}

	@Override
	public void mousePressed(MouseEvent a) {
		
		
		if (this.casellaSeleccionada != null)
			this.casellaSeleccionada.setBackground(casellaSeleccionada.getBackgroundColor());
		
		CasellaGrafica casellaGrafica = (CasellaGrafica) a.getSource();

		casellaGrafica.setBackground(new Color(30, 102, 223));
		casellaGrafica.setCaretColor(new Color(30, 102, 223));
		
		this.casellaSeleccionada = casellaGrafica;
		
		casellaGrafica.setCaretPosition(casellaGrafica.getText().length());
	}

	@Override
	public void mouseReleased(MouseEvent a) {
	}

}
