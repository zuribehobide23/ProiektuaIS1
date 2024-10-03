package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButtonDeskontu = null;
	private JButton jButtonkude = null;
	private JButton jButtonEzabatu = null;
	private JButton jButtonItxi = null;
	private JLabel jLabelSelectOption;
	private static final String ETIKETA = "Etiquetas";

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	public AdminGUI(String username) {
		AdminGUI.setBussinessLogic(LoginGUI.getBusinessLogic());

		this.setTitle(ResourceBundle.getBundle(ETIKETA).getString("AdminGUI.Admin"));
		this.setSize(495, 290);

		jLabelSelectOption = new JLabel(ResourceBundle.getBundle(ETIKETA).getString("AdminGUI.Admin"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);

		jButtonDeskontu = new JButton();
		jButtonDeskontu.addActionListener(e -> {
		    JFrame a = new DeskontuaGUI(username);
		    a.setVisible(true);
		});

		jButtonDeskontu.setText(ResourceBundle.getBundle(ETIKETA).getString("AdminGUI.Deskontua"));

		jButtonkude = new JButton();
		jButtonkude.addActionListener(e->{
				JFrame a = new DeskontuKudeatuGUI(username);
				a.setVisible(true);
			
		});
		jButtonkude.setText(ResourceBundle.getBundle(ETIKETA).getString("AdminGUI.Kudea"));

		jButtonEzabatu = new JButton();
		jButtonEzabatu.setText(ResourceBundle.getBundle(ETIKETA).getString("AdminGUI.Ezab"));
		jButtonEzabatu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new EzabatuGUI();
				a.setVisible(true);
			}
		});

		jButtonItxi = new JButton();
		jButtonItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCloseactionPerformed();
			}
		});
		jButtonItxi.setText(ResourceBundle.getBundle(ETIKETA).getString("EgoeraGUI.Close"));

		jContentPane = new JPanel();
		jContentPane.setLayout(new GridLayout(6, 1, 0, 0));
		jContentPane.add(jLabelSelectOption);
		jContentPane.add(jButtonDeskontu);
		jContentPane.add(jButtonkude);
		jContentPane.add(jButtonEzabatu);
		jContentPane.add(jButtonItxi);

		setContentPane(jContentPane);

	}

	private void jButtonCloseactionPerformed() {
		this.setVisible(false);
	}

}
