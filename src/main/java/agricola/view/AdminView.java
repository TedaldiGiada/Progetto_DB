package agricola.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import agricola.controller.Controller;
import agricola.data.EmployeeImpl;
import agricola.data.MaintenanceImpl;
import agricola.data.VehicleAssignmentImpl;
import agricola.data.api.Employee;
import agricola.data.api.Maintenance;
import agricola.data.api.Treatment;
import agricola.data.api.VehicleAssignment;

public class AdminView extends JPanel {
    private final Controller controller;
    private final JTabbedPane tabbedPane;
    private JTextField dipendenteField;
    private JTextField macchinaField;
    private JTextField dataAssegnazioneField;
    private JTextField noteAssegnazioneField;
    private JButton assegnaMacchinaBtn;
    private List<VehicleAssignment> lista;
    private DefaultTableModel assegnazioniTableModel;
    private JTable assegnazioniTable;
    private JTextField manutMacchinaField;
    private JTextField macchinarioField;
    private JTextField descrizioneManutField;
    private JTextField spesaField;
    private JTextField aziendaField;
    private JTextField tipoManutField;
    private List<Maintenance> lista1;
    private JButton registraManutBtn;
    private DefaultTableModel manutenzioniTableModel;
    private JTable manutenzioniTable;
    private JTextField annoField;
    private List<Treatment> lista2;
    private JButton visualizzaTratt;
    private DefaultTableModel trattTableModel;
    private JTable trattTable;
    private JTextField cfField;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField telefonoField;
    private JTextField viaField;
    private JTextField numCivField;
    private JTextField cittaField;
    private JTextField noteField;
    private List<Employee> lista3;
    private JButton salvaDipBtn;
    private DefaultTableModel dipendentiTableModel;
    private JTable dipendentiTable;

    public AdminView (Controller controller){
        this.controller = controller;
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        // Aggiungo i 4 pannelli
        this.tabbedPane.addTab("Dipendenti",  createDipendentePanel());
        this.tabbedPane.addTab("Assegnazione Macchina",  createAssegnaMacchinaPanel());
        this.tabbedPane.addTab("Trattamenti", createTrattamentiPanel());
        this.tabbedPane.addTab("Manutenzioni", createManutenzionePanel());

        add(this.tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createAssegnaMacchinaPanel() {
        JPanel assegnazioni = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Dipendente:"));
        this.dipendenteField = new JTextField();
        formPanel.add(this.dipendenteField);

        formPanel.add(new JLabel("Macchina:"));
        this.macchinaField = new JTextField();
        formPanel.add(this.macchinaField);

        formPanel.add(new JLabel("Data (YYYY-MM-DD):"));
        this.dataAssegnazioneField = new JTextField();
        formPanel.add(this.dataAssegnazioneField);

        formPanel.add(new JLabel("Note:"));
        this.noteAssegnazioneField = new JTextField();
        formPanel.add(this.noteAssegnazioneField);

        JPanel btnPanel = new JPanel();
        this.lista = new ArrayList<>();
        this.assegnaMacchinaBtn = new JButton("Assegna Macchina");
        this.assegnaMacchinaBtn.addActionListener(e -> {
            this.lista.clear();
            this.controller.assignVehicleToEmployee(new VehicleAssignmentImpl(getDipendenteField(),
             getMacchinaField(), getDataAssegnazioneField(), getNoteAssegnazioneField()));
            this.lista = this.controller.assignment();
            this.assegnazioniTableModel.setRowCount(0);
            for (VehicleAssignment a : this.lista) {
                String[] parts = a.toString().split(";");
                this.assegnazioniTableModel.addRow(parts);
            }
        });
        
        btnPanel.add(this.assegnaMacchinaBtn);

        assegnazioni.add(formPanel, BorderLayout.NORTH);
        assegnazioni.add(btnPanel, BorderLayout.SOUTH);

        this.assegnazioniTableModel = new DefaultTableModel(
                new Object[]{"CF", "Macchina", "Data", "Note"}, 0);
        this.assegnazioniTable = new JTable(this.assegnazioniTableModel);
        this.assegnazioniTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        assegnazioni.add(new JScrollPane(this.assegnazioniTable), BorderLayout.CENTER);

        return assegnazioni;
    }

    private JPanel createManutenzionePanel() {
       JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        this.manutMacchinaField = new JTextField();
        this.macchinarioField = new JTextField();
        this.descrizioneManutField = new JTextField();
        this.spesaField = new JTextField();
        this.aziendaField = new JTextField();
        this.tipoManutField = new JTextField();

        form.add(new JLabel("ID_Manutenzione:")); 
        form.add(this.manutMacchinaField);
        form.add(new JLabel("ID_Macchinario:")); 
        form.add(this.macchinarioField);
        form.add(new JLabel("Azienda:")); 
        form.add(this.aziendaField);
        form.add(new JLabel("Descrizione:")); 
        form.add(this.descrizioneManutField);
        form.add(new JLabel("Tipo:")); 
        form.add(this.tipoManutField);
        form.add(new JLabel("ID_Spesa:")); 
        form.add(this.spesaField);
        
        this.lista1 = new ArrayList<>();
        this.registraManutBtn = new JButton("Registra");
        this.registraManutBtn.addActionListener(e -> {
            this.lista1.clear();
            this.controller.registerMaintenance(new MaintenanceImpl(getManutMacchinaField(),
             getAzienda(), getDescrizioneManutField(), getTipoManutField(), getSpesa(), getMacchinario()));
            this.lista1 = this.controller.getMaintenances();
            this.manutenzioniTableModel.setRowCount(0);
            for (Maintenance m : this.lista1) {
                String[] parts = m.toString().split(";");
                this.manutenzioniTableModel.addRow(parts);
            }
        });
        JPanel btnPanel = new JPanel();
        btnPanel.add(this.registraManutBtn);

        this.manutenzioniTableModel = new DefaultTableModel(new Object[]{"ID", "Azienda", "Descrizione", "Tipo", "ID_Spesa", "ID_Macchinario"}, 0);
        this.manutenzioniTable = new JTable(this.manutenzioniTableModel);
        this.manutenzioniTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(this.manutenzioniTable), BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTrattamentiPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(1, 3, 10, 10));
        form.add(new JLabel("Anno:"));
        this.annoField = new JTextField();
        form.add(annoField);
        this.lista2 = new ArrayList<>();
        this.visualizzaTratt = new JButton("Visualizza Trattamenti Annui");
        this.visualizzaTratt.addActionListener(e -> {
            this.lista2.clear();
            this.lista2 = this.controller.getAnnualTreatments(getAnnoTrattamentoField());
            this.trattTableModel.setRowCount(0);
            for (Treatment t : this.lista2) {
                String[] parts = t.toString().split(";");
                this.trattTableModel.addRow(parts);
            }
        });
        JPanel btnPanel = new JPanel();
        btnPanel.add(this.visualizzaTratt);
        this.trattTableModel = new DefaultTableModel(new Object[]{"ID_Trattamento", "data", "tipo", "descrizione" }, 0);
        this.trattTable = new JTable(this.trattTableModel);
        this.trattTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        panel.add(btnPanel, BorderLayout.SOUTH);
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(this.trattTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDipendentePanel() {
        JPanel dipendenti = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        formPanel.add(new JLabel("CF:"));
        this.cfField = new JTextField();
        formPanel.add(this.cfField);

        formPanel.add(new JLabel("Nome:"));
        this.nomeField = new JTextField();
        formPanel.add(this.nomeField);

        formPanel.add(new JLabel("Cognome:"));
        this.cognomeField = new JTextField();
        formPanel.add(this.cognomeField);

        formPanel.add(new JLabel("Telefono:"));
        this.telefonoField = new JTextField();
        formPanel.add(this.telefonoField);

        formPanel.add(new JLabel("Via:"));
        this.viaField = new JTextField();
        formPanel.add(this.viaField);

        formPanel.add(new JLabel("Num Civ:"));
        this.numCivField = new JTextField();
        formPanel.add(this.numCivField);

        formPanel.add(new JLabel("Città:"));
        this.cittaField = new JTextField();
        formPanel.add(this.cittaField);

        formPanel.add(new JLabel("Note:"));
        this.noteField = new JTextField();
        formPanel.add(this.noteField);

        this.lista3 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        this.salvaDipBtn = new JButton("Registra Dipendente");
        this.salvaDipBtn.addActionListener(e -> {
            this.lista3.clear();
            this.controller.registerEmployee(new EmployeeImpl(getCfField(), getNomeField(),
            getCognomeField(), getTelefonoField(), getViaField(), getNumCivField(), getCittaField(), getNoteField()));
            this.lista3 = this.controller.getEmployeeList();
            this.dipendentiTableModel.setRowCount(0);
            for (Employee d : this.lista3) {
                String[] parts = d.toString().split(";");
                this.dipendentiTableModel.addRow(parts);
            }
        });
        btnPanel.add(this.salvaDipBtn);

        dipendenti.add(formPanel, BorderLayout.NORTH);
        dipendenti.add(btnPanel, BorderLayout.SOUTH);

        this.dipendentiTableModel = new DefaultTableModel(
                new Object[]{"CF", "Nome", "Cognome", "Telefono", "Via", "Num Civ", "Città", "Note"}, 0);
        dipendentiTable = new JTable(this.dipendentiTableModel);
        dipendentiTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        dipendenti.add(new JScrollPane(dipendentiTable), BorderLayout.CENTER);

        return dipendenti;
    }




    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    public int getAnnoTrattamentoField() {
        return Integer.parseInt(annoField.getText()); 
    }

    public int getManutMacchinaField() {
        return Integer.parseInt(manutMacchinaField.getText()); 
    }

    public String getDescrizioneManutField() {
        return descrizioneManutField.getText();
    }

     private int getSpesa() {
        return Integer.parseInt(spesaField.getText());
    }
    private String getMacchinario() {
        return macchinarioField.getText(); 
    }
    private String getAzienda() {
        return aziendaField.getText();
    }

    public String getTipoManutField() {
        return tipoManutField.getText();
    }

    public String getCfField() {
        return cfField.getText();
    }

    public String getNomeField() {
        return nomeField.getText();
    }

    public String getCognomeField() {
        return cognomeField.getText();
    }

    public long getTelefonoField() {
        return Long.parseLong(telefonoField.getText()); 
    }

    public String getViaField() {
        return viaField.getText();
    }

    public int getNumCivField() {
        return Integer.parseInt(numCivField.getText());
    }

    public String getCittaField() {
        return cittaField.getText();
    }

    public String getNoteField() {
        return noteField.getText();
    }


    public String getDipendenteField() {
        return dipendenteField.getText(); // sostituito JComboBox → JTextField
    }

    public String getMacchinaField() {
        return macchinaField.getText(); // sostituito JComboBox → JTextField
    }

    public Date getDataAssegnazioneField() {
        return  Date.valueOf(dataAssegnazioneField.getText());
    }


    public String getNoteAssegnazioneField() {
        return noteAssegnazioneField.getText();
    }
}
