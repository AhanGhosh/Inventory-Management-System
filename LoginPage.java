package mainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage {

    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginPage window = new LoginPage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginPage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setBounds(100, 100, 350, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(40, 40, 80, 20);
        frame.getContentPane().add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(130, 40, 150, 20);
        frame.getContentPane().add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(40, 80, 80, 20);
        frame.getContentPane().add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 80, 150, 20);
        frame.getContentPane().add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 120, 90, 25);
        frame.getContentPane().add(btnLogin);

       
        btnLogin.addActionListener(e -> {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());

         
            if (user.equals("AhanGhosh") && pass.equals("ahan1234")) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                frame.dispose(); 
                InterfaceDesign.main(null); 
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
        });
    }
}