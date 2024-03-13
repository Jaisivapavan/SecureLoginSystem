package secureLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePage {
	WelcomePage(String name) {
        JFrame frame = new JFrame();
        JLabel label1 = new JLabel("Login Success");
        JLabel label2 = new JLabel("Welcome " + name);
        label1.setFont(new Font("Sans Serif", Font.BOLD, 30));
        label2.setFont(new Font("Sans Serif", Font.BOLD, 40));
        label1.setForeground(Color.BLUE);
        label2.setForeground(Color.BLUE);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label1, BorderLayout.NORTH);
        panel.add(label2, BorderLayout.CENTER);

        frame.add(panel);
        frame.setSize(500, 400);
        frame.setTitle("Welcome Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
