package secureLogin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserRegistrationPage implements ActionListener {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private static final String FILE_PATH = "Location path of user_credentials.txt file"; 

    UserRegistrationPage() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame();
        frame.setTitle("User Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(null);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(90, 100, 100, 30);
        frame.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(90, 150, 100, 30);
        frame.add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 100, 200, 30);
        frame.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 150, 200, 30);
        frame.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(180, 200, 100, 30);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);
        frame.add(registerButton);

        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(280, 200, 150, 30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        frame.add(backButton);

        messageLabel = new JLabel();
        messageLabel.setBounds(140, 250, 300, 50);
        frame.add(messageLabel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Username and password are required");
                messageLabel.setForeground(Color.red);
            } else {
                registerUser(username, password);
            }
        } else if (e.getActionCommand().equals("Back to Login")) {
            frame.dispose();
            new LoginPage(new LoginInfo().getData());
        }
    }

    private void registerUser(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    messageLabel.setText("Username already exists");
                    messageLabel.setForeground(Color.red);
                    reader.close();
                    return;
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            writer.write(username + ":" + password);
            writer.newLine();
            writer.close();

            messageLabel.setText("Registration Successful!");
            messageLabel.setForeground(Color.green);
        } catch (IOException ex) {
            ex.printStackTrace();
            messageLabel.setText("Error occurred during registration");
            messageLabel.setForeground(Color.red);
        }
    }
}
