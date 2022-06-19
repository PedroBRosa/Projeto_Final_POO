package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.List;

public class ListarTodos extends JFrame {
    private JPanel listarPanel;
    private JButton sairButton;
    private JTextArea itensTA;
    private JButton buscarButton;
    private JRadioButton statusRB;
    private JRadioButton dataRB;
    private JRadioButton todosRB;
    private JComboBox statusBox;
    private JFormattedTextField dataTF;

    private void printAll(Itens itens) {
        String dataBr;
        String[] d = new String[3];
        dataBr = String.valueOf(itens.getDateTime());
        d[0] = dataBr.substring(8, 10);
        d[1] = dataBr.substring(5, 7);
        d[2] = dataBr.substring(0, 4);

        itensTA.append("ID: " + itens.getId() +
                "\nTitulo: " + itens.getTitulo() +
                "\nLocal: " + itens.getLocal() +
                "\nObservação: " + itens.getObservacao() +
                "\nStatus: " + itens.getStatus() +
                "\nData: " + d[0] + "/" + d[1] + "/" + d[2] +
                "\n===========================================================\n");
    }

    public void build() throws ParseException {
        setTitle("Controle de Itens");
        setSize(550,500);
        setContentPane(listarPanel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        int height = screen.height;
        setLocation(width/3,200);
        setVisible(true);
        statusBox.setVisible(false);
        dataTF.setVisible(false);

    }

    public ListarTodos() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itensTA.setText("");
                ItensDAO dao = new ItensDAO();
                List<Itens> itens1 = dao.findAll();

                for(Itens itens : itens1){
                    printAll(itens);
               }
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        statusRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (statusRB.isSelected()) {
                    dataRB.setEnabled(false);
                    todosRB.setEnabled(false);
                    statusBox.setVisible(true);
                } else {
                    dataRB.setEnabled(true);
                    todosRB.setEnabled(true);
                    statusBox.setVisible(false);
                }
            }
        });
        dataRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (dataRB.isSelected()) {
                    statusRB.setEnabled(false);
                    todosRB.setEnabled(false);
                    dataTF.setVisible(true);
                } else {
                    statusRB.setEnabled(true);
                    todosRB.setEnabled(true);
                    dataTF.setVisible(false);
                }
            }
        });
    }


}
