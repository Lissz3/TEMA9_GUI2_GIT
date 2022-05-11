
package ex1;

import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		MControl mc = new MControl();

		mc.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		mc.setVisible(true);

		mc.setLocationRelativeTo(null);
	}
}
