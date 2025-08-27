package agricola.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import agricola.controller.Controller;
import agricola.data.api.Customer;

public class SellerView extends JPanel {
    private Controller controller;
    private JTabbedPane tabbedPane;
    private List<Customer> lista;
    private JButton refreshClientiBtn;
    private JTextField anno;
    private DefaultTableModel clientiTableModel;
    private JTable clientiTable;
    private String stringa;
    private JTextField annoVenditeField;
    private JButton calcolaVenditeBtn;
    private DefaultTableModel venditeTableModel;
    private JTable venditeTable;

    public SellerView(Controller controller){
        this.controller = controller;
        setLayout(new BorderLayout());
        this.tabbedPane = new JTabbedPane();

        this.tabbedPane.addTab("Clienti", createClientiPanel());
        this.tabbedPane.addTab("Vendite", createVenditePanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createClientiPanel() {
        this.lista = new ArrayList<>();
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel top = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel btm = new JPanel();
        this.refreshClientiBtn = new JButton("Lista Clienti Annui");
        this.refreshClientiBtn.addActionListener(e -> {
            this.lista.clear();
            this.lista = this.controller.getAnnualCustomerList(getAnno());
            this.clientiTableModel.setRowCount(0);
            for (Customer c : this.lista) {
                String[] parts = c.toString().split(";");
                this.clientiTableModel.addRow(parts);
            }
        });
        top.add(new JLabel("Anno:"));
        this.anno = new JTextField();
        top.add(this.anno);
        top.add(new JLabel("Elenco clienti"));
        btm.add(this.refreshClientiBtn);

        this.clientiTableModel = new DefaultTableModel(new Object[]{"CF", "Nome", "Cognome"}, 0);
        this.clientiTable = new JTable(this.clientiTableModel);
        this.clientiTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        panel.add(top, BorderLayout.NORTH);
        panel.add(btm, BorderLayout.SOUTH);
        panel.add(new JScrollPane(this.clientiTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createVenditePanel() {
        this.stringa = new String();
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(1, 3, 10, 10));
        form.add(new JLabel("Anno:"));
        this.annoVenditeField = new JTextField();
        form.add(this.annoVenditeField);
        this.calcolaVenditeBtn = new JButton("Calcola Vendite");
        this.calcolaVenditeBtn.addActionListener(e -> {
            this.stringa = "";
            this.stringa = String.valueOf(this.controller.getAnnualSalesCalculation(getAnnoVendite()));
            this.venditeTableModel.setRowCount(0);
            this.venditeTableModel.addRow(new Object[]{this.stringa});
        });
        panel.add(this.calcolaVenditeBtn, BorderLayout.SOUTH);

        this.venditeTableModel = new DefaultTableModel(new Object[]{"Ricavo Totale"}, 0);
        this.venditeTable = new JTable(this.venditeTableModel);
        this.venditeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(venditeTable), BorderLayout.CENTER);
        return panel;
    }

    private int getAnno() {
        return Integer.parseInt(this.anno.getText());
    }

    private int getAnnoVendite() {
        return Integer.parseInt(this.annoVenditeField.getText());
    }

    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }
}
