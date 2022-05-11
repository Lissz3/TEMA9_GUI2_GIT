package ex1;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MControl extends JFrame {
	JButton btnFirst;
	JButton btnSecond;

	JLabel lblKeys;

	String title;

	Second s;

	int color;

	public MControl() {
		super("Control de Ratón");
		this.setLayout(null);
		this.setSize(350, 170);
		title = "Control de Ratón";

		MouseHandler myMouse = new MouseHandler();
		KeyHandler myKeyb = new KeyHandler();

		this.getContentPane().addMouseListener(myMouse);
		this.getContentPane().addMouseMotionListener(myMouse);
		this.addKeyListener(myKeyb);

		btnFirst = new JButton("Izquierdo");
		btnFirst.setSize(btnFirst.getPreferredSize());
		btnFirst.setLocation(80, 20);
		btnFirst.addMouseListener(myMouse);
		btnFirst.addMouseMotionListener(myMouse);
		btnFirst.addKeyListener(myKeyb);
		add(btnFirst);

		btnSecond = new JButton("Derecho");
		btnSecond.setSize(btnFirst.getPreferredSize());
		btnSecond.setLocation(btnFirst.getX() + btnFirst.getWidth() + 20, btnFirst.getY());
		btnSecond.addMouseListener(myMouse);
		btnSecond.addMouseMotionListener(myMouse);
		btnSecond.addKeyListener(myKeyb);
		add(btnSecond);

		lblKeys = new JLabel("<html><h2>Información</h2></html>");
		lblKeys.setSize(lblKeys.getPreferredSize());
		lblKeys.setLocation(this.getWidth() / 2 - lblKeys.getWidth() / 2, btnSecond.getY() + 40);
		add(lblKeys);

		addWindowListener(new CloseWindow());
	}

	private class MouseHandler extends MouseAdapter {

		@Override
		public void mouseMoved(MouseEvent e) {
			String aux = String.format("%s - X:%d Y:%d", title, e.getX(), e.getY());
			if (e.getSource() == btnFirst){
				aux = String.format("%s - X:%d Y:%d", title, e.getX() + btnFirst.getX(), e.getY() + btnFirst.getY());
			} else if (e.getSource() == btnSecond){
				aux = String.format("%s - X:%d Y:%d", title, e.getX() + btnSecond.getX(), e.getY() + btnSecond.getY());
			}
			MControl.this.setTitle(aux);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Color[] myC = { Color.PINK, Color.RED, Color.BLUE, Color.BLACK, Color.WHITE };
			if (s == null) {
				color = 0;
			}

			if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
				btnFirst.setBackground(myC[color]);
			} else if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK) {
				btnSecond.setBackground(myC[color]);
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getModifiersEx() == 0) {
				btnFirst.setBackground(null);
			} else if (e.getModifiersEx() == 256) {
				btnSecond.setBackground(null);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			MControl.this.setTitle(title);
		}
	}

	private class KeyHandler extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			lblKeys.setText("<html><h2>" + e.getKeyCode() + " : " + e.getKeyChar() + "</h2></html>");
			lblKeys.setSize(lblKeys.getPreferredSize());
			lblKeys.setLocation(MControl.this.getWidth() / 2 - lblKeys.getWidth() / 2, btnSecond.getY() + 40);
			if (e.isControlDown() && e.getKeyCode() == 67) {
				s = new Second(MControl.this);
				s.pack();
				s.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				s.setVisible(true);
			}
		}
	}

	private class CloseWindow extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			int res = JOptionPane.showConfirmDialog(null,
					"¿Salir?", MControl.this.getTitle(),
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (res == JOptionPane.OK_OPTION)
				e.getWindow().dispose();
		}

	}
}
