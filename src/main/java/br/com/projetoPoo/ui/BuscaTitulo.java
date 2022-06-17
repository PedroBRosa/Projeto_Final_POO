package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class BuscaTitulo extends JFrame {
    private JPanel buscaTituloPanel;
    private JButton buscarButton;
    private JButton cancelarButton;
    private JFormattedTextField tituloTf;
    private JTextArea buscaTA;


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
                buscaTA.setText("");
                ItensDAO dao = new ItensDAO();
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
            }
        });


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }


}
