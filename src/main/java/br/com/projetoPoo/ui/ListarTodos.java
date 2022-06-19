package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class ListarTodos extends JFrame {
    ItensDAO dao = new ItensDAO();
    private JPanel listarPanel;
    private JButton sairButton;
    private JTextArea itensTA;
    private JButton buscarButton;
    private JRadioButton statusRB;
    private JRadioButton dataRB;
    private JRadioButton todosRB;
    private JComboBox statusBox;
    private JComboBox dataBox;

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
        setSize(550, 500);
        setContentPane(listarPanel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        setLocation(width / 3, 200);
        setVisible(true);
        statusBox.setVisible(false);
        dataBox.setVisible(false);

    }

    public ListarTodos() {
        buscarButton.addActionListener(e -> {
            itensTA.setText("");
            List<Itens> itens1;
            if (todosRB.isSelected()) {
                itens1 = dao.findAll();
                for (Itens itens : itens1) {
                    printAll(itens);
                }
            } else if (statusRB.isSelected()) {
                if (statusBox.getSelectedIndex() == 0) {
                    itens1 = dao.findByStatus(Status.PERDIDO);
                    for (Itens itens : itens1) {
                        printAll(itens);
                    }
                } else {
                    itens1 = dao.findByStatus(Status.ACHADO);
                    for (Itens itens : itens1) {
                        printAll(itens);
                    }
                }
            } else if (dataRB.isSelected()) {
                itens1 = dao.findDate(dataBox.getSelectedIndex());
                for (Itens itens : itens1) {
                    printAll(itens);
                }
            } else {
                JOptionPane.showMessageDialog(listarPanel, "Selecione uma opção");
            }

        });
        sairButton.addActionListener(e -> {
            setVisible(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        });
        statusRB.addItemListener(e -> {
            if (statusRB.isSelected()) {
                dataRB.setEnabled(false);
                todosRB.setEnabled(false);
                statusBox.setVisible(true);
            } else {
                dataRB.setEnabled(true);
                todosRB.setEnabled(true);
                statusBox.setVisible(false);
            }
        });
        dataRB.addItemListener(e -> {
            if (dataRB.isSelected()) {
                statusRB.setEnabled(false);
                todosRB.setEnabled(false);
                dataBox.setVisible(true);
            } else {
                statusRB.setEnabled(true);
                todosRB.setEnabled(true);
                dataBox.setVisible(false);
            }
        });
        todosRB.addItemListener(e -> {
            if (todosRB.isSelected()) {
                statusRB.setEnabled(false);
                dataRB.setEnabled(false);
            } else {
                statusRB.setEnabled(true);
                dataRB.setEnabled(true);
            }
        });
    }


}
