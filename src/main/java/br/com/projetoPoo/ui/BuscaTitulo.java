package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.IItensDAO;
import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class BuscaTitulo extends JFrame {
    private JPanel buscaTituloPanel;
    private JButton buscarButton;
    private JTextPane retornoTextPane;
    private JButton cancelarButton;
    private JFormattedTextField tituloTf;


    public void build() throws ParseException {
        setTitle("Controle de Itens");
        setSize(500,250);
        setContentPane(buscaTituloPanel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        int height = screen.height;
        setLocation(width/3,height/3);
        setVisible(true);
    }



    public BuscaTitulo() {
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
