package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class ListarTodos extends JFrame {
    private JPanel listarPanel;
    private JButton sairButton;
    private JTextArea itensTA;
    private JButton buscarButton;



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

    }

    public ListarTodos() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itensTA.setText("");
                ItensDAO dao = new ItensDAO();
                List<Itens> itens1 = dao.findAll();

                for(Itens itens : itens1){

                   itensTA.append("ID: "+itens.getId()+
                           "\nTitulo: "+itens.getTitulo()+
                           "\nLocal: "+itens.getLocal()+
                           "\nObservação: "+itens.getObservacao()+
                           "\nStatus: "+itens.getStatus()+
                           "\nData: "+itens.getDateTime()+
                           "\n===========================================================\n");
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
    }


}
