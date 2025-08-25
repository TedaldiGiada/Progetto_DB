package agriterra.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
import agriterra.data.dao.CustomerListDAOImpl;
import agriterra.data.dao.SalesCalculationDAOImpl;
import agriterra.model.DBModel;

public class SellerView extends JPanel {

    private JTabbedPane tabbedPane;

    public JTable clientiTable;
    public DefaultTableModel clientiTableModel;
    public JButton refreshClientiBtn;
    private final SalesCalculationDAO sc;
    private final CustomerListDAO cl;
    public JButton calcolaVenditeBtn;
    public JTable venditeTable;
    public DefaultTableModel venditeTableModel;
    public JButton visualizzaVeicoliUsatiBtn;
    public JTable veicoliUsatiTable;
    public DefaultTableModel veicoliUsatiTableModel;
    private JTextField anno;;
    private JTextField annoVenditeField;
    private final Connection c;
    private DBModel db;
    private List<String> lista, lista1;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public SellerView() {
        c = db.getConnection();
        cl = new CustomerListDAOImpl(c);
        sc = new SalesCalculationDAOImpl(c);
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
        lista = new ArrayList<>();
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel top = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel btm = new JPanel();
        refreshClientiBtn = new JButton("Lista Clienti Annui");
        refreshClientiBtn.addActionListener(e -> {
            lista.clear();
            lista = cl.listaCompratoriAnnui(getAnno());
            clientiTableModel.setRowCount(0);
            for (String cliente : lista) {
                String[] parts = cliente.split(";");
                clientiTableModel.addRow(parts);
            }
        });
        top.add(new JLabel("Anno:"));
        anno= new JTextField();
        top.add(anno);
        top.add(new JLabel("Elenco clienti"));
        btm.add(refreshClientiBtn);

        clientiTableModel = new DefaultTableModel(new Object[]{"CF", "Nome", "Cognome"}, 0);
        clientiTable = new JTable(clientiTableModel);

        panel.add(top, BorderLayout.NORTH);
        panel.add(btm, BorderLayout.SOUTH);
        panel.add(new JScrollPane(clientiTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createVenditePanel() {
        lista1 = new ArrayList<>();
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(1, 3, 10, 10));
        form.add(new JLabel("Anno:"));
        annoVenditeField = new JTextField();
        form.add(annoVenditeField);
        calcolaVenditeBtn = new JButton("Calcola Vendite");
        calcolaVenditeBtn.addActionListener(e -> {
            lista1.clear();
            lista1 = sc.calcoloVenditeAnnue(getAnnoVendite());
            venditeTableModel.setRowCount(0);
            for (String vendite : lista1) {
                String[] parts = vendite.split(";");
                venditeTableModel.addRow(parts);
            }
        });
        panel.add(calcolaVenditeBtn, BorderLayout.SOUTH);

        venditeTableModel = new DefaultTableModel(new Object[]{"Ricavo Totale"}, 0);
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
