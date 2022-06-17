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


    public void build() throws ParseException {
        setTitle("Controle de Itens");
        setSize(550, 500);
        setContentPane(buscaTituloPanel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        int height = screen.height;
        setLocation(width / 3, 200);
        setVisible(true);
    }


    public BuscaTitulo() {


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarButton.getDisabledIcon();
                buscaTA.setText("");
                ItensDAO dao = new ItensDAO();
                if (tituloRadioButton.isSelected()==true) {
                    buscarButton.isEnabled();
                    List<Itens> itens1 = dao.findTitulo(tituloTf.getText());
                    for (Itens itens : itens1) {
                        buscaTA.append("ID: " + itens.getId() +
                                "\nTitulo: " + itens.getTitulo() +
                                "\nLocal: " + itens.getLocal() +
                                "\nObservação: " + itens.getObservacao() +
                                "\nStatus: " + itens.getStatus() +
                                "\nData: " + itens.getDateTime() +
                                "\n===========================================================\n");
                    }
                } else if (IDRadioButton.isSelected()==true) {
                    buscarButton.isEnabled();
                    Optional<Itens> itens1 = dao.findById(Long.valueOf(tituloTf.getText()));
                    itens1.ifPresent(itens ->{
                        buscaTA.append("ID: " + itens.getId() +
                                "\nTitulo: " + itens.getTitulo() +
                                "\nLocal: " + itens.getLocal() +
                                "\nObservação: " + itens.getObservacao() +
                                "\nStatus: " + itens.getStatus() +
                                "\nData: " + itens.getDateTime() +
                                "\n===========================================================\n");
                    });
                    }else{
                    JOptionPane.showMessageDialog(buscaTituloPanel,"Por favor, selecione\numa opção de busca!");
                }
                }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        IDRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (IDRadioButton.isSelected()) {
                    tituloRadioButton.setEnabled(false);
                } else {
                    tituloRadioButton.setEnabled(true);
                }
            }
        });

        tituloRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (tituloRadioButton.isSelected()) {
                    IDRadioButton.setEnabled(false);
                } else {
                    IDRadioButton.setEnabled(true);
                }
            }
        });
    }

}
