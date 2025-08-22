import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminView extends JPanel {

    private JTabbedPane tabbedPane;
    public JTextField annoTrattamentoField;
    public JButton caricaTrattamentiBtn;
    public JTable trattamentiTable;
    public DefaultTableModel trattamentiTableModel;
    public JComboBox<String> manutMacchinaBox;
    public JTextField descrizioneManutField, costoManutField, dataManutField, tipoManutField;
    public JButton registraManutBtn, refreshManutBtn;
    public JTable manutenzioniTable;
    public DefaultTableModel manutenzioniTableModel;
    public JTextField cfField, nomeField, cognomeField, telefonoField,
            viaField, numCivField, cittaField, noteField;
    public JButton salvaDipBtn, refreshDipBtn;
    public JTable dipendentiTable;
    public DefaultTableModel dipendentiTableModel;
    public JComboBox<String> dipendenteBox, macchinaBox;
    public JTextField dataAssegnazioneField, noteAssegnazioneField;
    public JButton assegnaMacchinaBtn, refreshAssegnazioniBtn;
    public JTable assegnazioniTable;
    public DefaultTableModel assegnazioniTableModel;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public AdminView() {
        initilizeGUI();
    }
    public void initilizeGUI() {
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
        dipendenteBox = new JComboBox<>();
        formPanel.add(dipendenteBox);

        formPanel.add(new JLabel("Macchina:"));
        macchinaBox = new JComboBox<>();
        formPanel.add(macchinaBox);

        formPanel.add(new JLabel("Data (YYYY-MM-DD):"));
        dataAssegnazioneField = new JTextField();
        formPanel.add(dataAssegnazioneField);

        formPanel.add(new JLabel("Note:"));
        noteAssegnazioneField = new JTextField();
        formPanel.add(noteAssegnazioneField);

        JPanel btnPanel = new JPanel();
        assegnaMacchinaBtn = new JButton("Assegna Macchina");
        refreshAssegnazioniBtn = new JButton("Aggiorna Lista");
        btnPanel.add(assegnaMacchinaBtn);
        btnPanel.add(refreshAssegnazioniBtn);

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
        manutMacchinaBox = new JComboBox<>();
        descrizioneManutField = new JTextField();
        costoManutField = new JTextField();
        dataManutField = new JTextField();
        tipoManutField = new JTextField();

        form.add(new JLabel("Macchina:")); form.add(manutMacchinaBox);
        form.add(new JLabel("Descrizione:")); form.add(descrizioneManutField);
        form.add(new JLabel("Costo (€):")); form.add(costoManutField);
        form.add(new JLabel("Data:")); form.add(dataManutField);
        form.add(new JLabel("Tipo:")); form.add(tipoManutField);

        registraManutBtn = new JButton("Registra");
        refreshManutBtn = new JButton("Aggiorna Lista");
        JPanel btnPanel = new JPanel();
        btnPanel.add(registraManutBtn);
        btnPanel.add(refreshManutBtn);

        manutenzioniTableModel = new DefaultTableModel(new Object[]{"ID", "Macchina", "Descrizione", "Costo", "Data", "Tipo"}, 0);
        manutenzioniTable = new JTable(manutenzioniTableModel);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(manutenzioniTable), BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTrattamentiPanel() {
        JPanel panel = new JPanel();
       return  panel;
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
        refreshDipBtn = new JButton("Aggiorna Lista");
        btnPanel.add(salvaDipBtn);
        btnPanel.add(refreshDipBtn);

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
    public static void main(String[] args) {
        // Avvio in thread grafico
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pannello Amministratore");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Aggiungo il pannello admin
            frame.setContentPane(new AdminView());

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null); // centra la finestra
            frame.setVisible(true);
        });
    }
}

