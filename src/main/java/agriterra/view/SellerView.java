package agriterra.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class SellerView extends JPanel {

    private JTabbedPane tabbedPane;

    // Tab Clienti
    public JTable clientiTable;
    public DefaultTableModel clientiTableModel;
    public JButton refreshClientiBtn;

    // Tab Vendite
    public JComboBox<String> annoVenditeBox;
    public JButton calcolaVenditeBtn;
    public JTable venditeTable;
    public DefaultTableModel venditeTableModel;

    // Tab Veicoli Usati
    public JComboBox<String> cicloBox;
    public JButton visualizzaVeicoliUsatiBtn;
    public JTable veicoliUsatiTable;
    public DefaultTableModel veicoliUsatiTableModel;

    public SellerView() {
        initializeGUI();
    }

    public void initializeGUI() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Clienti", createClientiPanel());
        tabbedPane.addTab("Vendite", createVenditePanel());
        tabbedPane.addTab("Veicoli usati", createVeicoliUsatiPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createClientiPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel top = new JPanel(new GridLayout(1, 2, 10, 10));
        refreshClientiBtn = new JButton("Aggiorna Lista Clienti");
        top.add(new JLabel("Elenco clienti"));
        top.add(refreshClientiBtn);

        clientiTableModel = new DefaultTableModel(new Object[]{"CF", "Nome", "Cognome", "Telefono", "Città"}, 0);
        clientiTable = new JTable(clientiTableModel);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(clientiTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createVenditePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(1, 3, 10, 10));
        form.add(new JLabel("Anno:"));
        annoVenditeBox = new JComboBox<>();
        form.add(annoVenditeBox);
        calcolaVenditeBtn = new JButton("Calcola Vendite");
        form.add(calcolaVenditeBtn);

        venditeTableModel = new DefaultTableModel(new Object[]{"Prodotto", "Quantità", "Ricavo"}, 0);
        venditeTable = new JTable(venditeTableModel);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(venditeTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createVeicoliUsatiPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(1, 3, 10, 10));
        form.add(new JLabel("Ciclo Colturale:"));
        cicloBox = new JComboBox<>();
        form.add(cicloBox);
        visualizzaVeicoliUsatiBtn = new JButton("Visualizza Veicoli Usati");
        form.add(visualizzaVeicoliUsatiBtn);

        veicoliUsatiTableModel = new DefaultTableModel(new Object[]{"ID", "Marca/Modello", "Nome", "Categoria"}, 0);
        veicoliUsatiTable = new JTable(veicoliUsatiTableModel);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(veicoliUsatiTable), BorderLayout.CENTER);
        return panel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pannello Vendite");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new SellerView());
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
