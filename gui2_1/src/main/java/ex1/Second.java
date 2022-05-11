package ex1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Second extends JDialog implements ItemListener, ActionListener {
	JLabel lblTitle;
	JLabel lblChose;

	JTextField txfNewFile;

	JComboBox<String> cbColors;
	String[] colors = { "Rosa", "Rojo", "Azul", "Negro", "Blanco" };

	public Second(MControl m) {
		super(m);
		setLayout(new FlowLayout());
		setTitle("Ventana secundaria");

		lblTitle = new JLabel("Nuevo título:");
		lblTitle.setSize(lblTitle.getPreferredSize());
		add(lblTitle);

		txfNewFile = new JTextField(15);
		txfNewFile.addActionListener(this);
		add(txfNewFile);

		lblChose = new JLabel("Escoge color:");
		lblChose.setSize(lblChose.getPreferredSize());
		add(lblChose);

		cbColors = new JComboBox<String>(colors);
		cbColors.setMaximumRowCount(5);
		cbColors.setSelectedIndex(0);
		cbColors.addItemListener(this);
		add(cbColors);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MControl m = (MControl) this.getOwner();
		if (!txfNewFile.getText().equals("")) {
			m.title = txfNewFile.getText();
			m.setTitle(m.title);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		MControl m = (MControl) this.getOwner();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			m.color = cbColors.getSelectedIndex();
		}
	}

}
