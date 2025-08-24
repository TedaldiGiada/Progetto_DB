package agriterra.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import agriterra.data.api.CustomerListDAO;
import agriterra.data.api.SalesCalculationDAO;

public class SellerView extends JPanel {

    private JTabbedPane tabbedPane;

    public JTable clientiTable;
    public DefaultTableModel clientiTableModel;
    public JButton refreshClientiBtn;
    private SalesCalculationDAO sc;
    private CustomerListDAO cl;
    public JButton calcolaVenditeBtn;
    public JTable venditeTable;
    public DefaultTableModel venditeTableModel;
    public JButton visualizzaVeicoliUsatiBtn;
    public JTable veicoliUsatiTable;
    public DefaultTableModel veicoliUsatiTableModel;
    private JTextField anno;;
    private JTextField annoVenditeField;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public SellerView() {
        initializeGUI();
    }

    public void initializeGUI() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Clienti", createClientiPanel());
        tabbedPane.addTab("Vendite", createVenditePanel());

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createClientiPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel top = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel btm = new JPanel();
        refreshClientiBtn = new JButton("Lista Clienti Annui");
        refreshClientiBtn.addActionListener(e -> {
            cl.listaCompratoriAnnui(getAnno());
        });
        top.add(new JLabel("Anno:"));
        anno= new JTextField();
        top.add(anno);
        top.add(new JLabel("Elenco clienti"));
        btm.add(refreshClientiBtn);

        clientiTableModel = new DefaultTableModel(new Object[]{"CF", "Nome", "Cognome", "Telefono", "Città"}, 0);
        clientiTable = new JTable(clientiTableModel);

        panel.add(top, BorderLayout.NORTH);
        panel.add(btm, BorderLayout.SOUTH);
        panel.add(new JScrollPane(clientiTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createVenditePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(1, 3, 10, 10));
        form.add(new JLabel("Anno:"));
        annoVenditeField = new JTextField();
        form.add(annoVenditeField);
        calcolaVenditeBtn = new JButton("Calcola Vendite");
        calcolaVenditeBtn.addActionListener(e -> {
            sc.calcoloVenditeAnnue(getAnnoVendite());
        });
        panel.add(calcolaVenditeBtn, BorderLayout.SOUTH);

        venditeTableModel = new DefaultTableModel(new Object[]{"Prodotto", "Quantità", "Ricavo"}, 0);
        venditeTable = new JTable(venditeTableModel);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(venditeTable), BorderLayout.CENTER);
        return panel;
    }

    private int getAnno() {
        return Integer.parseInt(anno.getText());
    }

    private int getAnnoVendite() {
        return Integer.parseInt(annoVenditeField.getText());
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
