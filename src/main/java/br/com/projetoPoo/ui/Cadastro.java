package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

public class Cadastro extends JFrame {

    MaskFormatter formatter = new MaskFormatter("##/##/####");
    private JPanel cadastroPanel;
    private JFormattedTextField tituloTF;
    private JFormattedTextField localTF;
    private JFormattedTextField dataTF;
    private JComboBox statusSB;
    private JEditorPane observacaoTF;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JButton buscarButton;
    private JButton excluirButton;
    private JFormattedTextField formattedTextField1;


    public void build() throws ParseException {
        setContentPane(cadastroPanel);
        setVisible(true);
        setTitle("Cadastrar Item");
        setSize(580, 750);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        setLocation(width/3,60);
        formatter.install(dataTF);
    }

    public Cadastro() throws ParseException {
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItensDAO dao = new ItensDAO();
                Itens it = new Itens();
                int ano;
                int mes;
                int dia;
                it.setTitulo(tituloTF.getText());
                it.setLocal(localTF.getText());
                if (statusSB.getSelectedIndex()==0){
                    it.setStatus(Status.PERDIDO);
                }else{
                    it.setStatus(Status.ACHADO);
                }
                it.setObservacao(observacaoTF.getText());
                String dataStg = dataTF.getText();
                String d [] = dataStg.split("/");
                dia = Integer.parseInt(d[0]);
                mes = Integer.parseInt(d[1]);
                ano = Integer.parseInt(d[2]);
                it.setDateTime(LocalDate.of(ano,mes,dia));
                dao.save(it);
                tituloTF.setText("");
                observacaoTF.setText("");
                statusSB.setSelectedIndex(0);
                dataTF.setText("");
                localTF.setText("");
                JOptionPane.showMessageDialog(null,"Salvo Com Sucesso!");

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
