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
	private JMenuItem opcioTancar;
	private FrameSudoku r;

	BarraMenu(FrameSudoku r) {
		this.r = r;
		menuFitxer = new JMenu("Fitxer");
		opcioNou = new JMenuItem("Nou");
		opcioTancar = new JMenuItem("Tancar");

		opcioNou.addActionListener(this);
		opcioTancar.addActionListener(this);

		menuFitxer.add(opcioNou);
		menuFitxer.add(opcioTancar);

		this.add(menuFitxer);
		
		menuFitxer.setMnemonic('F');
		opcioNou.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		opcioNou.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals((JMenuItem) opcioTancar)) {
			System.exit(0);
		} else {
			try {
				int confiramdo = JOptionPane.showConfirmDialog(new JFrame(),
						"Estas segur que vols fer un nou Sudoku? Perdr�s tot el contingut");
				if (JOptionPane.OK_OPTION == confiramdo) {
					r.generarNouSodoku();
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);	
			}
		}
	}
	
	public void activeOpcioNou(){
		opcioNou.setEnabled(true);
	}
}
