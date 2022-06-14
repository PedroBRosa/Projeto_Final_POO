package br.com.projetoPoo.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Home extends JFrame{
    private JButton cadastrarItemButton;
    private JButton procurarItemButton;
    private JButton listarItensButton;
    private JButton alterarExcluirButton;
    private JPanel homePanel;

    public void build(){
        setTitle("Controle de Itens");
        setSize(500,250);
        setContentPane(homePanel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        int height = screen.height;
        setLocation(width/3,height/3);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public Home() {
        cadastrarItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cadastro cadastro = new Cadastro();
                    cadastro.build();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
