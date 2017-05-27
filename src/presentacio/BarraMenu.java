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

		menuFitxer.add(opcioNou);
		menuFitxer.add(opcioSave);
		menuFitxer.add(opcioTancar);

		this.add(menuFitxer);
		
		menuFitxer.setMnemonic('F');
		opcioNou.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		opcioSave.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		opcioNou.setEnabled(false);
		setDisableSave();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals((JMenuItem) opcioTancar)) {
			System.exit(0);
		} else {
			try {
				int option = JOptionPane.showConfirmDialog(new JFrame(),
						"Vols guardar el contingut d'aquesst Sudou?");
				if (option == JOptionPane.OK_OPTION) {
					frameSudoku.guardarPartida();
					frameSudoku.generarNouSodoku();
					
				}else if(option == JOptionPane.NO_OPTION){
					frameSudoku.generarNouSodoku();
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);	
			}
		}
	}
	
	public void activeOpcioNou(){
		opcioNou.setEnabled(true);
	}
	
	public void setEnableSave(){
		opcioSave.setEnabled(true);
	}
	
	public void setDisableSave(){
		opcioSave.setEnabled(false);
	}
}
