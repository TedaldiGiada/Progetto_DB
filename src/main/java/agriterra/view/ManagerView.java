import java.awt.BorderLayout;
import java.awt.Component;
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

public class ManagerView extends JPanel {
    
    private JTabbedPane tabbedPane;
    private Component visualizzaLista;
    private Component cicloBox;
    private DefaultTableModel macchinariTableModel;
    private JTable macchinariTable;
    private JButton visualizzaPiante;
    private Component pianteTable;
    private DefaultTableModel pianteTableModel;
    private DefaultTableModel rendimentiTableModel;
    private JButton visualizzaRendimenti;
    private Component rendimentiTable;
    private Component rendimentoBox;
    @SuppressWarnings("rawtypes")
    private JComboBox annoBox;
    @SuppressWarnings("rawtypes")
    private JComboBox terrenoBox;
    private DefaultTableModel terreniTableModel;
    @SuppressWarnings("rawtypes")
    private JComboBox terreno1Box;
    private Component pianta3Box;
    private Component data3Box;
    private Component data1Box;
    private Component terreniTable;
    private Component pianta2Box;
    private Component terrreno1Box;
    private Component pianta1Box;
    @SuppressWarnings("rawtypes")
    private JComboBox data2Box;
    private JButton aggiungiCicli;

    public ManagerView() {
        initilizeGUI();
    }
    public void initilizeGUI() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        // Aggiungo i 4 pannelli
        tabbedPane.addTab("Terreni",  createTerreniPanel());
        tabbedPane.addTab("Macchinari",  createMacchinaPanel());
        tabbedPane.addTab("Rendimento", createRendimentoPanel());
        tabbedPane.addTab("Piante", createPiantePanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createPiantePanel() {
        JPanel pianta = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("Terreno:"));
        terrenoBox = new JComboBox<>();
        formPanel.add(terrenoBox);

        formPanel.add(new JLabel("Anno:"));
        annoBox = new JComboBox<>();
        formPanel.add(annoBox);

        JPanel btnPanel = new JPanel();
        visualizzaPiante = new JButton("Visualizza Piante Coltivate");
        btnPanel.add(visualizzaPiante);
  
        pianta.add(formPanel, BorderLayout.NORTH);
        pianta.add(btnPanel, BorderLayout.SOUTH);

        pianteTableModel = new DefaultTableModel(
                new Object[]{"Nome", "Tipo", "Descrizione"}, 0);
        pianteTable = new JTable(pianteTableModel);

        pianta.add(new JScrollPane(pianteTable), BorderLayout.CENTER);

        return pianta;
    }
    private JPanel createRendimentoPanel() {
        JPanel rendimento = new JPanel();
        JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));

        formPanel.add(new JLabel("Pianta:"));
        rendimentoBox = new JComboBox<>();
        formPanel.add(rendimentoBox);

        JPanel btnPanel = new JPanel();
        visualizzaRendimenti = new JButton("Visualizza Terreni con Rendimento minimo");
        btnPanel.add(visualizzaRendimenti);
  
        rendimento.add(formPanel, BorderLayout.NORTH);
        rendimento.add(btnPanel, BorderLayout.SOUTH);

        rendimentiTableModel = new DefaultTableModel(
                new Object[]{"ID_Terreno"}, 0);
        rendimentiTable = new JTable(rendimentiTableModel);

        rendimento.add(new JScrollPane(rendimentiTable), BorderLayout.CENTER);
        return rendimento;
    }
    private JPanel createTerreniPanel() {
        JPanel terreni = new JPanel();
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        formPanel.add(new JLabel("Terreno:"));
        terreno1Box = new JComboBox<>();
        formPanel.add(terrreno1Box);

        formPanel.add(new JLabel("Pianta1:"));
        pianta1Box = new JComboBox<>();
        formPanel.add(pianta1Box);
        formPanel.add(new JLabel("Pianta2:"));
        pianta2Box = new JComboBox<>();
        formPanel.add(pianta2Box);
        formPanel.add(new JLabel("Pianta3:"));
        pianta3Box = new JComboBox<>();
        formPanel.add(pianta3Box);

        formPanel.add(new JLabel("Data1:"));
        data1Box = new JComboBox<>();
        formPanel.add(data1Box);
        formPanel.add(new JLabel("Data2:"));
        data2Box = new JComboBox<>();
        formPanel.add(data2Box);
        formPanel.add(new JLabel("Data3:"));
        data3Box = new JComboBox<>();
        formPanel.add(data3Box);

        JPanel btnPanel = new JPanel();
        aggiungiCicli = new JButton("Aggiungi Cicli");
        btnPanel.add(aggiungiCicli);
  
        terreni.add(formPanel, BorderLayout.NORTH);
        terreni.add(btnPanel, BorderLayout.SOUTH);

        terreniTableModel = new DefaultTableModel(
                new Object[]{"ID_Ciclo", "anno", "data_inizio", "data_fine", "rendimento", "unità_misura", "descrizione", "ID_Terreno", "ID_Pianta"}, 0);
        terreniTable = new JTable(terreniTableModel);

        terreni.add(new JScrollPane(terreniTable), BorderLayout.CENTER);
        return terreni;
    }
    private JPanel createMacchinaPanel() {
        JPanel visualizza = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        formPanel.add(new JLabel("Ciclo Colturale:"));
        cicloBox = new JComboBox<>();
        formPanel.add(cicloBox);

        JPanel btnPanel = new JPanel();
        visualizzaLista = new JButton("Visualizza Macchinari Usati");
        btnPanel.add(visualizzaLista);
  
        visualizza.add(formPanel, BorderLayout.NORTH);
        visualizza.add(btnPanel, BorderLayout.SOUTH);

        macchinariTableModel = new DefaultTableModel(
                new Object[]{"ID_Macchinario", "Marca/Modello", "Nome", "Categoria", "Descrizione"}, 0);
        macchinariTable = new JTable(macchinariTableModel);

        visualizza.add(new JScrollPane(macchinariTable), BorderLayout.CENTER);

        return visualizza;
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
            frame.setContentPane(new ManagerView());

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null); // centra la finestra
            frame.setVisible(true);
        });
    }
}
