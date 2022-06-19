package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class BuscaTitulo extends JFrame {
    private JPanel buscaTituloPanel;
    private JButton buscarButton;
    private JButton cancelarButton;
    private JFormattedTextField tituloTf;
    private JTextArea buscaTA;
    private JRadioButton IDRadioButton;
    private JRadioButton tituloRadioButton;

    private void printAll(Itens itens) {
        String dataBr;
        String[] d = new String[3];
        dataBr = String.valueOf(itens.getDateTime());
        d[0] = dataBr.substring(8, 10);
        d[1] = dataBr.substring(5, 7);
        d[2] = dataBr.substring(0, 4);

        buscaTA.append("ID: " + itens.getId() +
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
        setContentPane(buscaTituloPanel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        setLocation(width / 3, 200);
        setVisible(true);
    }


    public BuscaTitulo() {


        buscarButton.addActionListener(e -> {
            buscaTA.setText("");
            ItensDAO dao = new ItensDAO();
            if (tituloRadioButton.isSelected()) {
                buscarButton.isEnabled();
                List<Itens> itens1 = dao.findTitulo(tituloTf.getText());
                if (itens1.isEmpty()) {
                    JOptionPane.showMessageDialog(buscaTituloPanel, "Registro inexistente");
                    tituloTf.setText("");
                }
                for (Itens itens : itens1) {
                    printAll(itens);
                }

            } else if (IDRadioButton.isSelected()) {
                buscarButton.isEnabled();
                try {
                    Optional<Itens> itens1 = dao.findById(Long.valueOf(tituloTf.getText()));
                    if (itens1.isEmpty()) {
                        JOptionPane.showMessageDialog(buscaTituloPanel, "Registro inexistente");
                        tituloTf.setText("");
                    }
                    itens1.ifPresent(this::printAll);
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(buscaTituloPanel, "Por favor, escreva\num ID válido!");
                    tituloTf.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(buscaTituloPanel, "Por favor, selecione\numa opção de busca!");
            }
        });

        cancelarButton.addActionListener(e -> {
            setVisible(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        });

        IDRadioButton.addItemListener(e -> tituloRadioButton.setEnabled(!IDRadioButton.isSelected()));

        tituloRadioButton.addItemListener(e -> IDRadioButton.setEnabled(!tituloRadioButton.isSelected()));
    }

}
