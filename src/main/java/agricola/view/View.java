package agricola.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import agricola.controller.Controller;

public class View extends JFrame{
    private Controller controller;
    private LoginView login;
    
public View(){
    login = new LoginView(controller);
    initializeGUI();
    }

    public void initializeGUI(){
        setTitle("AgriTerra S.r.l");
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

        JPanel loginPanel = new LoginView(this.controller);
        centerPanel.add(loginPanel);
        getRootPane().setDefaultButton(login.getLoginButton());

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
    }

    public void addPanel(JPanel panel){
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

}
