package agriterra.view;

import java.awt.BorderLayout;
import java.awt.Component;
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

import agriterra.data.api.LandCultivationDAO;
import agriterra.data.api.MinimumYieldDAO;
import agriterra.data.api.PlantAssignmentDAO;
import agriterra.data.api.UsedVehiclesDAO;
import agriterra.data.dao.LandCultivationDAOImpl;
import agriterra.data.dao.MinimumYieldDAOImpl;
import agriterra.data.dao.PlantAssignmentDAOImpl;
import agriterra.data.dao.UsedVehiclesDAOImpl;
import agriterra.model.Model;

public class ManagerView extends JPanel {
    
    private JTabbedPane tabbedPane;
    private JButton visualizzaLista;
    private DefaultTableModel macchinariTableModel;
    private JTable macchinariTable;
    private JButton visualizzaPiante;
    private Component pianteTable;
    private DefaultTableModel pianteTableModel;
    private DefaultTableModel rendimentiTableModel;
    private JButton visualizzaRendimenti;
    private Component rendimentiTable;
    private DefaultTableModel terreniTableModel;
    private Component terreniTable;
    private JButton aggiungiCicli;
    private final LandCultivationDAO lc;
    private final MinimumYieldDAO my;
    private final PlantAssignmentDAO pa;
    private final UsedVehiclesDAO uv;
    private JTextField terrenoField;
    private JTextField annoField;
    private JTextField rendimentoField;
    private JTextField cicloField;
    private JTextField data1Field;
    private JTextField data2Field;
    private JTextField pianta1Field;
    private JTextField terreno1Field;
    private JTextField anno1Field;
    private JTextField nomeField;
    private JTextField unita;
    private JTextField descrizione;
    private JTextField vendita;
    private JTextField ciclo1;
    private final Connection c;
    @SuppressWarnings("unused")
    private final Model model;
    private List<String> lista, lista1, lista2, lista3;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ManagerView(Model model) {
        this.model = model;
        c = model.getConnection();
        uv = new UsedVehiclesDAOImpl(c);
        pa = new PlantAssignmentDAOImpl(c);
        my = new MinimumYieldDAOImpl(c);
        lc = new LandCultivationDAOImpl(c);
        initializeGUI();
    }
    public void initializeGUI() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        // Aggiungo i 4 pannelli
        tabbedPane.addTab("Terreni",  createTerreniPanel());
        tabbedPane.addTab("Macchinari",  createMacchinaPanel());
        tabbedPane.addTab("Rendimento", createRendimentoPanel());
        tabbedPane.addTab("Piante", createPiantePanel());

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createPiantePanel() {
        JPanel pianta = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("ID_Terreno:"));
        terrenoField = new JTextField();
        formPanel.add(terrenoField);

        formPanel.add(new JLabel("Anno:"));
        annoField = new JTextField();
        formPanel.add(annoField);

        lista = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        visualizzaPiante = new JButton("Visualizza Piante Coltivate");
        visualizzaPiante.addActionListener(e -> {
            lista.clear();
            lista = lc.coltureInTerrenoAnno(getTerreno(), getAnno());
            pianteTableModel.setRowCount(0);
            for (String p : lista) {
                String[] parts = p.split(";");
                pianteTableModel.addRow(parts);
            }
        });
        btnPanel.add(visualizzaPiante);
  
        pianta.add(formPanel, BorderLayout.NORTH);
        pianta.add(btnPanel, BorderLayout.SOUTH);

        pianteTableModel = new DefaultTableModel(
                new Object[]{"ID_Pianta", "Nome"}, 0);
        pianteTable = new JTable(pianteTableModel);

        pianta.add(new JScrollPane(pianteTable), BorderLayout.CENTER);

        return pianta;
    }
    private JPanel createRendimentoPanel() {
        JPanel rendimento = new JPanel();
        JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));

        formPanel.add(new JLabel("Pianta:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        lista1 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        visualizzaRendimenti = new JButton("Visualizza Terreni con Rendimento minimo");
        visualizzaRendimenti.addActionListener(e -> {
            lista1.clear();
            lista1 = my.terreniConRendimentoMin(getNome());
            rendimentiTableModel.setRowCount(0);
            for (String r : lista1) {
                String[] parts = r.split(";");
                rendimentiTableModel.addRow(parts);
            }
        });
        btnPanel.add(visualizzaRendimenti);
  
        rendimento.add(formPanel, BorderLayout.NORTH);
        rendimento.add(btnPanel, BorderLayout.SOUTH);

        rendimentiTableModel = new DefaultTableModel(
                new Object[]{"ID_Terreno", "Rendimento"}, 0);
        rendimentiTable = new JTable(rendimentiTableModel);

        rendimento.add(new JScrollPane(rendimentiTable), BorderLayout.CENTER);
        return rendimento;
    }
    private JPanel createTerreniPanel() {
        JPanel terreni = new JPanel();
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        formPanel.add(new JLabel("ID_Ciclo:"));
        ciclo1 = new JTextField();
        formPanel.add(ciclo1);
        formPanel.add(new JLabel("ID_Terreno:"));
        terreno1Field = new JTextField();
        formPanel.add(terreno1Field);
        formPanel.add(new JLabel("ID_Pianta:"));
        pianta1Field = new JTextField();
        formPanel.add(pianta1Field);
        formPanel.add(new JLabel("Anno:"));
        anno1Field = new JTextField();
        formPanel.add(anno1Field);
        formPanel.add(new JLabel("DataInizio:"));
        data1Field = new JTextField();
        formPanel.add(data1Field);
        formPanel.add(new JLabel("DataFine:"));
        data2Field = new JTextField();
        formPanel.add(data2Field);
        formPanel.add(new JLabel("Rendimento:"));
        rendimentoField = new JTextField();
        formPanel.add(rendimentoField);
        formPanel.add(new JLabel("Unità Misura:"));
        unita = new JTextField();
        formPanel.add(unita);
        formPanel.add(new JLabel("Descrizione:"));
        descrizione = new JTextField();
        formPanel.add(descrizione);
        formPanel.add(new JLabel("ID_Vendita:"));
        vendita = new JTextField();
        formPanel.add(vendita);
        
        lista2 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        aggiungiCicli = new JButton("Aggiungi Cicli");
        aggiungiCicli.addActionListener(e -> {
            lista2.clear();
            pa.assegnaTerrenoAColtura( getCiclo1(), getAnno1(), getDataIn(), getDataF(), getRendimento(), getUnita(), getDesc(), getTerreno1(), getPianta(), getVendita());
            lista2 = pa.cicli();
            terreniTableModel.setRowCount(0);
            for (String t : lista2) {
                String[] parts = t.split(";");
                terreniTableModel.addRow(parts);
            }
        });
        btnPanel.add(aggiungiCicli);
  
        terreni.add(formPanel, BorderLayout.NORTH);
        terreni.add(btnPanel, BorderLayout.SOUTH);

        terreniTableModel = new DefaultTableModel(
                new Object[]{"ID_Ciclo", "anno", "data_inizio", "data_fine", "rendimento", "unità_misura", "descrizione", "ID_Terreno", "ID_Pianta"}, 0);
        terreniTable = new JTable(terreniTableModel);

        terreni.add(new JScrollPane(terreniTable), BorderLayout.CENTER);
        return terreni;
    }
    private int getPianta() {
        return Integer.parseInt(pianta1Field.getText());
    }
    private int getTerreno1() {
        return Integer.parseInt(terreno1Field.getText());
    }
    private String getDesc() {
        return descrizione.getText();
    }
    private String getUnita() {
        return unita.getText();
    }
    private int getRendimento() {
        return Integer.parseInt(rendimentoField.getText());
    }
    private LocalDate getDataF() {
        return LocalDate.parse(data1Field.getText());
    }
    private LocalDate getDataIn() {
        return LocalDate.parse(data2Field.getText());
    }
    private int getCiclo1() {
        return Integer.parseInt(ciclo1.getText());
    }
    private JPanel createMacchinaPanel() {
        JPanel visualizza = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        formPanel.add(new JLabel("Ciclo Colturale:"));
        cicloField = new JTextField();
        formPanel.add(cicloField);

        lista3 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        visualizzaLista = new JButton("Visualizza Macchinari Usati");
        visualizzaLista.addActionListener(e -> {
            lista3.clear();
            lista3 = uv.macchinariUsatiInCiclo(getCiclo());
            macchinariTableModel.setRowCount(0);
            for (String m : lista3) {
                String[] parts = m.split(";");
                macchinariTableModel.addRow(parts);
            }
        });
        btnPanel.add(visualizzaLista);
  
        visualizza.add(formPanel, BorderLayout.NORTH);
        visualizza.add(btnPanel, BorderLayout.SOUTH);

        macchinariTableModel = new DefaultTableModel(
                new Object[]{"ID_Macchinario", "Nome", "Marca/Modello"}, 0);
        macchinariTable = new JTable(macchinariTableModel);

        visualizza.add(new JScrollPane(macchinariTable), BorderLayout.CENTER);

        return visualizza;
    }

    private int getTerreno() {
        return Integer.parseInt(terrenoField.getText());
    }

    private int getAnno() {
        return Integer.parseInt(annoField.getText());
    }

    private String getNome() {
        return nomeField.getText();
    }

    private int getCiclo() {
        return Integer.parseInt(cicloField.getText());
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    
    private int getVendita() {
        return Integer.parseInt(vendita.getText());
    }
    private int getAnno1() {
        return Integer.parseInt(anno1Field.getText());
    }
}
