package secureLogin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener {
	private HashMap<String, String> loginInfo;
    private JFrame frame;
    private JTextField userText;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    LoginPage(HashMap<String, String> loginData) {
        loginInfo = loginData;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame();
        frame.setTitle("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(null);

        JLabel userIDLabel = new JLabel("Username");
        userIDLabel.setBounds(90, 100, 100, 30);
        frame.add(userIDLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(90, 150, 100, 30);
        frame.add(passwordLabel);

        userText = new JTextField();
        userText.setBounds(180, 100, 200, 30);
        frame.add(userText);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 150, 200, 30);
        frame.add(passwordField);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(180, 200, 100, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        frame.add(resetButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(280, 200, 100, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(180, 250, 200, 30);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);
        frame.add(registerButton);

        messageLabel = new JLabel();
        messageLabel.setBounds(140, 300, 300, 50);
        frame.add(messageLabel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Reset")) {
            userText.setText("");
            passwordField.setText("");
            messageLabel.setText("");
        } else if (e.getActionCommand().equals("Login")) {
            String username = userText.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Username and password are required");
                messageLabel.setForeground(Color.red);
            } else {
                validateLogin(username, password);
            }
        } else if (e.getActionCommand().equals("Register")) {
            frame.dispose();
            new UserRegistrationPage();
        }
    }

    private void validateLogin(String username, String password) {
        if (loginInfo.containsKey(username)) {
            if (loginInfo.get(username).equals(password)) {
                messageLabel.setText("Login Successfully!");
                messageLabel.setForeground(Color.green);
                new WelcomePage(username);
                frame.dispose();
            } else {
                messageLabel.setText("Wrong Password!");
                messageLabel.setForeground(Color.red);
            }
        } else {
            messageLabel.setText("Username is not found!");
            messageLabel.setForeground(Color.red);
        }
    }
}
