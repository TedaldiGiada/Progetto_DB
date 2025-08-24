package agriterra.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

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

import agriterra.data.api.AnnualTreatmentsDAO;
import agriterra.data.api.MaintenanceRegistrationDAO;
import agriterra.data.api.RegistrationEmployeeDAO;
import agriterra.data.api.VehicleAssignmentDAO;

public class AdminView extends JPanel {

    private JTabbedPane tabbedPane;
    public JTextField annoTrattamentoField;
    public JButton caricaTrattamentiBtn;
    public JTable trattamentiTable;
    public DefaultTableModel trattamentiTableModel;
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
    private RegistrationEmployeeDAO re;
    private MaintenanceRegistrationDAO mr;
    private VehicleAssignmentDAO va;
    private JButton visualizzaTratt;
    private DefaultTableModel trattTableModel;
    private JTable trattTable;
    private AnnualTreatmentsDAO at;
    private JTextField dipendenteField;
    private JTextField macchinaField;
    private JTextField manutMacchinaField;
    private JTextField annoField;
    private JTextField spesaField;
    private JTextField macchinarioField;
    private JTextField aziendaField;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public AdminView() {
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
        assegnaMacchinaBtn = new JButton("Assegna Macchina");
        assegnaMacchinaBtn.addActionListener(e -> {
            va.assegnaDipendenteAMacchina(getDipendenteField(), getMacchinaField(), getDataAssegnazioneField(), getNoteAssegnazioneField());
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
        

        registraManutBtn = new JButton("Registra");
        registraManutBtn.addActionListener(e -> {
            mr.registraManutenzione(getManutMacchinaField(), getAzienda(), getDescrizioneManutField(), getTipoManutField(), getMacchinario(), getSpesa());
        });
        JPanel btnPanel = new JPanel();
        btnPanel.add(registraManutBtn);

        manutenzioniTableModel = new DefaultTableModel(new Object[]{"ID", "Macchina", "Descrizione", "Costo", "Data", "Tipo"}, 0);
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
        visualizzaTratt = new JButton("Visualizza Trattamenti Annui");
        visualizzaTratt.addActionListener(e -> {
           at.listaTrattamentiAnnui(getAnnoTrattamentoField());
        });
        form.add(visualizzaTratt);

        trattTableModel = new DefaultTableModel(new Object[]{"ID_Trattamento"}, 0);
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

        JPanel btnPanel = new JPanel();
        salvaDipBtn = new JButton("Registra Dipendente");
        salvaDipBtn.addActionListener(e -> {
            re.registraDipendente(getCfField(), getNomeField(), getCognomeField(), getTelefonoField(), getViaField(), getNumCivField(), getCittaField(), getNomeField());
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
        return Integer.parseInt(annoTrattamentoField.getText()); 
    }

    public int getManutMacchinaField() {
        return Integer.parseInt(manutMacchinaField.getText()); 
    }

    public String getDescrizioneManutField() {
        return descrizioneManutField.getText();
    }

     private String getSpesa() {
        return spesaField.getText();
    }
    private int getMacchinario() {
        return Integer.parseInt(macchinarioField.getText()); 
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

    public int getTelefonoField() {
        return Integer.parseInt(telefonoField.getText()); 
    }

    public String getViaField() {
        return viaField.getText();
    }

    public String getNumCivField() {
        return numCivField.getText();
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

    public static void main(String[] args) {
        // Avvio in thread grafico
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pannello Amministratore");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // passo "conn" al costruttore
            frame.setContentPane(new AdminView());

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    });
}
}

