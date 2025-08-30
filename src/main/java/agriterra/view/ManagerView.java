package agriterra.view;

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

import agriterra.controller.Controller;
import agriterra.data.CropCycleImpl;
import agriterra.data.api.CropCycle;
import agriterra.data.api.Machinery;
import agriterra.data.api.Plant;

public class ManagerView extends JPanel {
    private final Controller controller;
    private final JTabbedPane tabbedPane;
    private JButton visualizzaPiante;
    private JTextField terrenoField;
    private JTextField annoField;
    private JTextField cicloField;
    private DefaultTableModel pianteTableModel;
    private List<Plant> lista;
    private JTable pianteTable;
    private DefaultTableModel rendimentiTableModel;
    private List<CropCycle> lista1;
    private List<Machinery> lista3;
    private JTable rendimentiTable;
    private JTextField nomeField;
    private JButton visualizzaRendimenti;
    private JButton visualizzaLista;
    private JTextField ciclo1;
    private JTextField terreno1Field;
    private JTextField pianta1Field;
    private JTextField anno1Field;
    private JTextField data1Field;
    private JTextField data2Field;
    private JTextField rendimentoField;
    private JTextField unita;
    private JTextField descrizione;
    private JTextField vendita;
    private JButton aggiungiCicli;
    private List<CropCycle> lista2;
    private JTable terreniTable;
    private DefaultTableModel terreniTableModel;
    private JTable macchinariTable;
    private DefaultTableModel macchinariTableModel;

    public ManagerView( Controller controller) {

        this.controller = controller;

        setLayout(new BorderLayout());
        this.tabbedPane = new JTabbedPane();

        // Aggiungo i 4 pannelli
        this.tabbedPane.addTab("Terreni",  createTerreniPanel());
        this.tabbedPane.addTab("Macchinari",  createMacchinaPanel());
        this.tabbedPane.addTab("Rendimento", createRendimentoPanel());
        this.tabbedPane.addTab("Piante", createPiantePanel());

        add(this.tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createPiantePanel() {
        JPanel pianta = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("ID_Terreno:"));
        this.terrenoField = new JTextField();
        formPanel.add(this.terrenoField);

        formPanel.add(new JLabel("Anno:"));
        this.annoField = new JTextField();
        formPanel.add(this.annoField);

        this.lista = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        this.visualizzaPiante = new JButton("Visualizza Piante Coltivate");
        this.visualizzaPiante.addActionListener(e -> {
            this.lista.clear();
            this.lista = this.controller.getAnnualLandCultivation(getTerreno(), getAnno());
            this.pianteTableModel.setRowCount(0);
            for (Plant p : this.lista) {
                String[] parts = p.toString().split(";");
                this.pianteTableModel.addRow(parts);
            }
        });
        btnPanel.add(this.visualizzaPiante);
  
        pianta.add(formPanel, BorderLayout.NORTH);
        pianta.add(btnPanel, BorderLayout.SOUTH);

        this.pianteTableModel = new DefaultTableModel(
                new Object[]{"ID_Pianta", "Nome"}, 0);
        this.pianteTable = new JTable(this.pianteTableModel);
        this.pianteTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        pianta.add(new JScrollPane(this.pianteTable), BorderLayout.CENTER);

        return pianta;
    }

    private JPanel createRendimentoPanel() {
        JPanel rendimento = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));

        formPanel.add(new JLabel("Pianta:"));
        this.nomeField = new JTextField();
        formPanel.add(this.nomeField);

        this.lista1 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        this.visualizzaRendimenti = new JButton("Visualizza Terreni con Rendimento minimo");
        this.visualizzaRendimenti.addActionListener(e -> {
            this.lista1.clear();
            this.lista1 = this.controller.getLowYieldLands(getNome());
            this.rendimentiTableModel.setRowCount(0);
            for (CropCycle r : this.lista1) {
                String[] row = { String.valueOf(r.id()), String.valueOf(r.getRendimento())};
                this.rendimentiTableModel.addRow(row);
            }
        });
        btnPanel.add(visualizzaRendimenti);
  
        rendimento.add(formPanel, BorderLayout.NORTH);
        rendimento.add(btnPanel, BorderLayout.SOUTH);

        this.rendimentiTableModel = new DefaultTableModel(
                new Object[]{"ID_Terreno", "Rendimento"}, 0);
        this.rendimentiTable = new JTable(this.rendimentiTableModel);
        this.rendimentiTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        rendimento.add(new JScrollPane(this.rendimentiTable), BorderLayout.CENTER);
        return rendimento;
    }

    @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
    private JPanel createTerreniPanel() {
        JPanel terreni = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));

        formPanel.add(new JLabel("ID_Ciclo:"));
        this.ciclo1 = new JTextField();
        formPanel.add(this.ciclo1);
        formPanel.add(new JLabel("ID_Terreno:"));
        this.terreno1Field = new JTextField();
        formPanel.add(this.terreno1Field);
        formPanel.add(new JLabel("ID_Pianta:"));
        this.pianta1Field = new JTextField();
        formPanel.add(this.pianta1Field);
        formPanel.add(new JLabel("Anno:"));
        this.anno1Field = new JTextField();
        formPanel.add(this.anno1Field);
        formPanel.add(new JLabel("DataInizio:"));
        this.data1Field = new JTextField();
        formPanel.add(this.data1Field);
        formPanel.add(new JLabel("DataFine:"));
        this.data2Field = new JTextField();
        formPanel.add(this.data2Field);
        formPanel.add(new JLabel("Rendimento:"));
        this.rendimentoField = new JTextField();
        formPanel.add(this.rendimentoField);
        formPanel.add(new JLabel("Unità Misura:"));
        this.unita = new JTextField();
        formPanel.add(this.unita);
        formPanel.add(new JLabel("Descrizione:"));
        this.descrizione = new JTextField();
        formPanel.add(this.descrizione);
        formPanel.add(new JLabel("ID_Vendita:"));
        this.vendita = new JTextField();
        formPanel.add(this.vendita);

        this.lista2 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        this.aggiungiCicli = new JButton("Aggiungi Cicli");
        this.aggiungiCicli.addActionListener(e -> {
            this.lista2.clear();
            this.controller.insertPlantAssignment(new CropCycleImpl(getCiclo1(), getAnno1(), getDataIn(), getDataF(), getRendimento(), getUnita(), getDesc(), getTerreno1(), getPianta()));
            this.lista2 = this.controller.getCycle();
            this.terreniTableModel.setRowCount(0);
            for (CropCycle t : this.lista2) {
                String[] parts = t.toString().split(";");
                this.terreniTableModel.addRow(parts);
            }
        });
        
        btnPanel.add(this.aggiungiCicli);

        terreni.add(formPanel, BorderLayout.NORTH);
        terreni.add(btnPanel, BorderLayout.SOUTH);

        this.terreniTableModel = new DefaultTableModel(
                new Object[]{"ID_Ciclo", "anno", "data_inizio", "data_fine", "rendimento", "unità_misura", "descrizione", "ID_Terreno", "ID_Pianta"}, 0);
        terreniTable = new JTable(this.terreniTableModel);

        terreni.add(new JScrollPane(this.terreniTable), BorderLayout.CENTER);
        return terreni;
    }

    private JPanel createMacchinaPanel() {
        JPanel visualizza = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        formPanel.add(new JLabel("Ciclo Colturale:"));
        this.cicloField = new JTextField();
        formPanel.add(this.cicloField);

        this.lista3 = new ArrayList<>();
        JPanel btnPanel = new JPanel();
        this.visualizzaLista = new JButton("Visualizza Macchinari Usati");
        this.visualizzaLista.addActionListener(e -> {
            this.lista3.clear();
            this.lista3 = this.controller.usedMachineryCycle(getCiclo());
            this.macchinariTableModel.setRowCount(0);
            for (Machinery m : this.lista3) {
                String[] parts = m.toString().split(";");
                this.macchinariTableModel.addRow(parts);
            }
        });
        btnPanel.add(this.visualizzaLista);
  
        visualizza.add(formPanel, BorderLayout.NORTH);
        visualizza.add(btnPanel, BorderLayout.SOUTH);

        this.macchinariTableModel = new DefaultTableModel(
                new Object[]{"ID_Macchinario", "Nome", "Marca/Modello"}, 0);
        this.macchinariTable = new JTable(this.macchinariTableModel);
        this.macchinariTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        visualizza.add(new JScrollPane(this.macchinariTable), BorderLayout.CENTER);

        return visualizza;
    }


    private int getAnno1() {
    return Integer.parseInt(anno1Field.getText());
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
        String testo = rendimentoField.getText().trim();
        if (testo.isEmpty()) {
            return 0; 
        }
        return Integer.parseInt(testo);
    }
    private Date getDataIn() {
        return Date.valueOf(data1Field.getText());
    }
    private Date getDataF() {
        return Date.valueOf(data2Field.getText());
    }
    private int getCiclo1() {
        return Integer.parseInt(ciclo1.getText());
    }

    private int getTerreno(){
        return Integer.parseInt(terrenoField.getText());
    }

    private int getAnno(){
         return Integer.parseInt(annoField.getText());
    }


     private String getNome() {
        return nomeField.getText();
    }

    private int getCiclo() {
        return Integer.parseInt(cicloField.getText());
    }



}