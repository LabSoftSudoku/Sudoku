package presentacio;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class BarraMenu extends JMenuBar implements ActionListener {

	private JMenu menuFitxer;
	private JMenuItem opcioNou;
	private JMenuItem opcioSave;
	private JMenuItem opcioTancar;
	private FrameSudoku frameSudoku;

	BarraMenu(FrameSudoku frameSudoku) {
		this.frameSudoku = frameSudoku;
		menuFitxer = new JMenu("Fitxer");
		opcioNou = new JMenuItem("Nou");
		opcioSave = new JMenuItem("Save");
		opcioTancar = new JMenuItem("Tancar");

		opcioNou.addActionListener(this);
		opcioTancar.addActionListener(this);
		opcioSave.addActionListener(this);

		menuFitxer.add(opcioNou);
		menuFitxer.add(opcioSave);
		menuFitxer.add(opcioTancar);

		this.add(menuFitxer);

		menuFitxer.setMnemonic('F');
		opcioNou.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		opcioSave.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		opcioNou.setEnabled(false);
		opcioSave.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			if (e.getSource().equals((JMenuItem) opcioTancar)) {
				//opcio Tancar
				frameSudoku.exit();
			} else if (e.getSource().equals((JMenuItem) opcioNou)) {
				//opcio Nou
				if(frameSudoku.getIsModified()){
					int option = JOptionPane.showConfirmDialog(new JFrame(), "Vols guardar el contingut d'aquest Sudou?");
					if (option == JOptionPane.OK_OPTION) {
						frameSudoku.guardarPartida();
						frameSudoku.generarNouSodoku();

					} else if (option == JOptionPane.NO_OPTION) {
						frameSudoku.generarNouSodoku();
					}
				}else{
					frameSudoku.generarNouSodoku();
				}
				opcioSave.setEnabled(true);
				frameSudoku.modified();
				

			} else {
				//opcio Save
				frameSudoku.guardarPartida();
				opcioSave.setEnabled(false);

			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void activeOpcioNou() {
		opcioNou.setEnabled(true);
	}

	public void setEnableSave() {
		opcioSave.setEnabled(true);
	}
	
}
