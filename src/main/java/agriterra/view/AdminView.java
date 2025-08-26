package agriterra.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.time.LocalDate;
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

import agriterra.data.api.AnnualTreatmentsDAO;
import agriterra.data.api.MaintenanceRegistrationDAO;
import agriterra.data.api.RegistrationEmployeeDAO;
import agriterra.data.api.VehicleAssignmentDAO;
import agriterra.data.dao.AnnualTreatmentsDAOImpl;
import agriterra.data.dao.MaintenanceRegistrationDAOImpl;
import agriterra.data.dao.RegistrationEmployeeDAOImpl;
import agriterra.data.dao.VehicleAssignmentDAOImpl;
import agriterra.model.Model;

public class AdminView extends JPanel {

    private JTabbedPane tabbedPane;
    public JButton caricaTrattamentiBtn;
    public JTextField descrizioneManutField, costoManutField, dataManutField, tipoManutField;
    public JButton registraManutBtn, refreshManutBtn;
    public JTable manutenzioniTable;
    public DefaultTableModel manutenzioniTableModel;
    public JTextField cfField, nomeField, cognomeField, telefonoField,
            viaField, numCivField, cittaField, noteField;
    public JButton salvaDipBtn, refreshDipBtn;
    public JTable dipendentiTable;
    public DefaultTableModel dipendentiTableModel;
    public JTextField dataAssegnazioneField, noteAssegnazioneField;
    public JButton assegnaMacchinaBtn, refreshAssegnazioniBtn;
    public JTable assegnazioniTable;
    public DefaultTableModel assegnazioniTableModel;
    private final RegistrationEmployeeDAO re;
    private final MaintenanceRegistrationDAO mr;
    private final VehicleAssignmentDAO va;
    private JButton visualizzaTratt;
    private DefaultTableModel trattTableModel;
    private JTable trattTable;
    private final AnnualTreatmentsDAO at;
    private JTextField dipendenteField;
    private JTextField macchinaField;
    private JTextField manutMacchinaField;
    private JTextField annoField;
    private JTextField spesaField;
    private JTextField macchinarioField;
    private JTextField aziendaField;
    private final Connection c;
    @SuppressWarnings({"unused"})
    private final Model model;
    private List<String> lista, lista1, lista2, lista3;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public AdminView(Model model) {
        this.model = model;
        c = model.getConnection();
        at = new AnnualTreatmentsDAOImpl(c);
        va = new VehicleAssignmentDAOImpl(c);
        mr = new MaintenanceRegistrationDAOImpl(c);
        re = new RegistrationEmployeeDAOImpl(c);
        initializeGUI();
    }
    public void initializeGUI() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        // Aggiungo i 4 pannelli
        tabbedPane.addTab("Dipendenti",  createDipendentePanel());
        tabbedPane.addTab("Assegnazione Macchina",  createAssegnaMacchinaPanel());
        tabbedPane.addTab("Trattamenti", createTrattamentiPanel());
        tabbedPane.addTab("Manutenzioni", createManutenzionePanel());

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createAssegnaMacchinaPanel() {
        JPanel assegnazioni = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Dipendente:"));
        dipendenteField = new JTextField();
        formPanel.add(dipendenteField);

        formPanel.add(new JLabel("Macchina:"));
        macchinaField = new JTextField();
        formPanel.add(macchinaField);

        formPanel.add(new JLabel("Data (YYYY-MM-DD):"));
        dataAssegnazioneField = new JTextField();
        formPanel.add(dataAssegnazioneField);

        formPanel.add(new JLabel("Note:"));
        noteAssegnazioneField = new JTextField();
        formPanel.add(noteAssegnazioneField);

        JPanel btnPanel = new JPanel();
        lista = new ArrayList<>();
        assegnaMacchinaBtn = new JButton("Assegna Macchina");
        assegnaMacchinaBtn.addActionListener(e -> {
            lista.clear();
            va.assegnaDipendenteAMacchina(getDipendenteField(), getMacchinaField(), getDataAssegnazioneField(), getNoteAssegnazioneField());
            lista = va.assegnazioni();
            assegnazioniTableModel.setRowCount(0);
            for (String a : lista) {
                String[] parts = a.split(";");
                assegnazioniTableModel.addRow(parts);
            }
        });
        
        btnPanel.add(assegnaMacchinaBtn);

        assegnazioni.add(formPanel, BorderLayout.NORTH);
        assegnazioni.add(btnPanel, BorderLayout.SOUTH);

        assegnazioniTableModel = new DefaultTableModel(
                new Object[]{"CF", "Macchina", "Data", "Note"}, 0);
        assegnazioniTable = new JTable(assegnazioniTableModel);

        assegnazioni.add(new JScrollPane(assegnazioniTable), BorderLayout.CENTER);

        return assegnazioni;
    }

    private JPanel createManutenzionePanel() {
       JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        manutMacchinaField = new JTextField();
        macchinarioField = new JTextField();
        descrizioneManutField = new JTextField();
        spesaField = new JTextField();
        aziendaField = new JTextField();
        dataManutField = new JTextField();
        tipoManutField = new JTextField();

        form.add(new JLabel("ID_Manutenzione:")); form.add(manutMacchinaField);
        form.add(new JLabel("ID_Macchinario:")); form.add(macchinarioField);
        form.add(new JLabel("Azienda:")); form.add(aziendaField);
        form.add(new JLabel("Descrizione:")); form.add(descrizioneManutField);
        form.add(new JLabel("Tipo:")); form.add(tipoManutField);
        form.add(new JLabel("ID_Spesa:")); form.add(spesaField);
        
        lista1 = new ArrayList<>();
        registraManutBtn = new JButton("Registra");
        registraManutBtn.addActionListener(e -> {
            lista1.clear();
            mr.registraManutenzione(getManutMacchinaField(), getAzienda(), getDescrizioneManutField(), getTipoManutField(), getSpesa(), getMacchinario());
            lista1 = mr.manutenzioni();
            manutenzioniTableModel.setRowCount(0);
            for (String m : lista1) {
                String[] parts = m.split(";");
                manutenzioniTableModel.addRow(parts);
            }
        });
        JPanel btnPanel = new JPanel();
        btnPanel.add(registraManutBtn);

        manutenzioniTableModel = new DefaultTableModel(new Object[]{"ID", "Azienda", "Descrizione", "Tipo", "ID_Spesa", "ID_Macchinario"}, 0);
        manutenzioniTable = new JTable(manutenzioniTableModel);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(manutenzioniTable), BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTrattamentiPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(1, 3, 10, 10));
        form.add(new JLabel("Anno:"));
        annoField = new JTextField();
        form.add(annoField);
        lista2 = new ArrayList<>();
        visualizzaTratt = new JButton("Visualizza Trattamenti Annui");
        visualizzaTratt.addActionListener(e -> {
            lista2.clear();
            lista2 = at.listaTrattamentiAnnui(getAnnoTrattamentoField());
            trattTableModel.setRowCount(0);
            for (String t : lista2) {
                String[] parts = t.split(";");
                trattTableModel.addRow(parts);
            }
        });
        form.add(visualizzaTratt);
        trattTableModel = new DefaultTableModel(new Object[]{"ID_Trattamento", "data", "tipo", "descrizione" }, 0);
        trattTable = new JTable(trattTableModel);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(trattTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDipendentePanel() {
        JPanel dipendenti = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        formPanel.add(new JLabel("CF:"));
        cfField = new JTextField();
        formPanel.add(cfField);

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Cognome:"));
        cognomeField = new JTextField();
        formPanel.add(cognomeField);

        formPanel.add(new JLabel("Telefono:"));
        telefonoField = new JTextField();
        formPanel.add(telefonoField);

        formPanel.add(new JLabel("Via:"));
        viaField = new JTextField();
        formPanel.add(viaField);

        formPanel.add(new JLabel("Num Civ:"));
        numCivField = new JTextField();
        formPanel.add(numCivField);

        formPanel.add(new JLabel("Città:"));
        cittaField = new JTextField();
        formPanel.add(cittaField);

        formPanel.add(new JLabel("Note:"));
        noteField = new JTextField();
        formPanel.add(noteField);

        lista3 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        salvaDipBtn = new JButton("Registra Dipendente");
        salvaDipBtn.addActionListener(e -> {
            lista3.clear();
            re.registraDipendente(getCfField(), getNomeField(), getCognomeField(), getTelefonoField(), getViaField(), getNumCivField(), getCittaField(), getNoteField());
            lista3 = re.listaDipendenti();
            dipendentiTableModel.setRowCount(0);
            for (String d : lista3) {
                String[] parts = d.split(";");
                dipendentiTableModel.addRow(parts);
            }
        });
        btnPanel.add(salvaDipBtn);

        dipendenti.add(formPanel, BorderLayout.NORTH);
        dipendenti.add(btnPanel, BorderLayout.SOUTH);

        dipendentiTableModel = new DefaultTableModel(
                new Object[]{"CF", "Nome", "Cognome", "Telefono", "Via", "Num Civ", "Città", "Note"}, 0);
        dipendentiTable = new JTable(dipendentiTableModel);

        dipendenti.add(new JScrollPane(dipendentiTable), BorderLayout.CENTER);

        return dipendenti;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
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

    public LocalDate getDataAssegnazioneField() {
        return  LocalDate.parse(dataAssegnazioneField.getText());
    }


    public String getNoteAssegnazioneField() {
        return noteAssegnazioneField.getText();
    }
}

