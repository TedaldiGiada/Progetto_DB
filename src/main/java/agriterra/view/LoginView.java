package agriterra.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import agriterra.Controller;
import agriterra.model.Model;

public class LoginView extends JFrame {
    private Controller controller;
    private JTextArea usernameField;
    private JTextArea passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public LoginView() {
        initializeGUI();
    }

    public void initializeGUI() {
        setTitle("Sistema di Gestione Agricola - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Pannello principale con sfondo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Pannello centrale per il form di login
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 248, 255));
        
        // Pannello del form di login
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        loginPanel.setPreferredSize(new Dimension(350, 280));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 10, 10);
        
        // Titolo
        JLabel titleLabel = new JLabel("AGRITERRA S.r.l.", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);
        
        // Spazio
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        loginPanel.add(Box.createVerticalStrut(20), gbc);
        
        // Username
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(usernameLabel, gbc);
        
        usernameField = new JTextArea();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(usernameField, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        loginPanel.add(passwordLabel, gbc);
        
        passwordField = new JTextArea();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordField, gbc);
        
        // Spazio
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        loginPanel.add(Box.createVerticalStrut(15), gbc);
        
        // Pulsante Login
        loginButton = new JButton("ACCEDI");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createRaisedBevelBorder());
        loginButton.setSize(new Dimension(200, 35));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener( e -> {
            controller.check();
            clearFields();
        });
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);
        
        // Messaggio di errore/successo
        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 6;
        loginPanel.add(messageLabel, gbc);
        
        // Aggiungere il pannello di login al pannello centrale
        centerPanel.add(loginPanel);
        
        // Pannello inferiore con informazioni sui ruoli
        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.setBackground(new Color(240, 248, 255));
        
        JLabel infoLabel = new JLabel("<html><center>" +
            "<b>Utenti di test:</b><br>" +
            "admin/admin123 - Amministratore<br>" +
            "campo/campo123 - Responsabile Terreno<br>" +
            "vendite/vendite123 - Addetto Vendite" +
            "</center></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.GRAY);
        infoPanel.add(infoLabel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        SwingUtilities.invokeLater(() -> usernameField.requestFocus());
        getRootPane().setDefaultButton(loginButton);
    }
    
    // Metodi getter per i componenti
    public String getUsername() {
        return usernameField.getText();
    }
    
    public String getPassword() {
        return passwordField.getText();
    }
    
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
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
    
    // Metodo per aggiungere listener al pulsante login
  
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    // Metodo per mostrare la finestra
    public void display() {
        setVisible(true);
    }
    
    // Metodo per nascondere la finestra
    @Override
    public void hide() {
        setVisible(false);
    }
    
    // Metodo main per test della view
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model = new Model() {};
        LoginView loginView = new LoginView();
        loginView.setController(new Controller(model, loginView)); // 🔴 collega controller e view
        loginView.display();
    });
    }
}