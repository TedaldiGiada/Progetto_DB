package agricola.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import agricola.controller.Controller;

public class LoginView extends JPanel{
    private Controller controller;
    private JTextArea usernameArea;
    private JTextArea passwordArea;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginView(Controller controller) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        setPreferredSize(new Dimension(350,280));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 10, 10);
        JLabel titleLabel = new JLabel("AGRITERRA S.r.l.", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(Box.createVerticalStrut(20), gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(usernameLabel, gbc);

        this.usernameArea = new JTextArea();
        this.usernameArea.setFont(new Font("Arial", Font.PLAIN, 14));
        this.usernameArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(this.usernameArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordLabel, gbc);

        this.passwordArea = new JTextArea();
        this.passwordArea.setFont(new Font("Arial", Font.PLAIN, 14));
        this.passwordArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(this.passwordArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(Box.createVerticalStrut(15), gbc);

        loginButton = new JButton("ACCEDI");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createRaisedBevelBorder());
        loginButton.setSize(new Dimension(200, 35));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener( e -> {
            JPanel newPan = controller.checkPanel(usernameArea.getText(), passwordArea.getText());
            this.controller.setView(newPan);
            clearFields();
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 6;
        add(messageLabel, gbc);

        SwingUtilities.invokeLater(() -> usernameArea.requestFocus());
        setVisible(true);
    }

       public void clearFields() {
        usernameArea.setText("");
        passwordArea.setText("");
        messageLabel.setText(" ");
    }

    public void showMessage(String message, boolean isError) {
        messageLabel.setText(message);
        if (isError) {
            messageLabel.setForeground(Color.RED);
        } else {
            messageLabel.setForeground(new Color(34, 139, 34));
        }
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
