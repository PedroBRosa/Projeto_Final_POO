package br.com.projetoPoo.ui;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

public class Cadastro extends JFrame {
    ItensDAO dao = new ItensDAO();
    MaskFormatter dataFormatter = new MaskFormatter("##/##/####");
    private JPanel cadastroPanel;
    private JFormattedTextField tituloTF;
    private JFormattedTextField localTF;
    private JFormattedTextField dataTF;
    private JComboBox statusSB;
    private JEditorPane observacaoTF;
    private JButton salvarButton;
    private JButton sairButton;
    private JButton buscarButton;
    private JButton excluirButton;
    private JFormattedTextField idFT;
    private JButton novoButton;
    private JButton cancelarButton1;


    public void build() throws ParseException {
        idFT.setText("");
        setContentPane(cadastroPanel);
        setVisible(true);
        setTitle("Cadastrar Item");
        setSize(680, 750);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int width = screen.width;
        setLocation(width / 3, 60);
        dataFormatter.install(dataTF);
    }
    private void resetCampos() {
        idFT.setText("");
        tituloTF.setText("");
        localTF.setText("");
        dataTF.setText("");
        statusSB.setSelectedIndex(0);
        observacaoTF.setText("");
        idFT.setEnabled(true);
        idFT.setEditable(true);
        tituloTF.setEnabled(false);
        localTF.setEnabled(false);
        dataTF.setEnabled(false);
        statusSB.setEnabled(false);
        observacaoTF.setEnabled(false);
        buscarButton.setEnabled(true);
        salvarButton.setEnabled(false);
        excluirButton.setEnabled(false);
        novoButton.setEnabled(true);
    }
    public Cadastro() throws ParseException {
        novoButton.addActionListener(e -> {
            idFT.setText("0");
            idFT.setEditable(false);
            tituloTF.setEnabled(true);
            localTF.setEnabled(true);
            dataTF.setEnabled(true);
            statusSB.setEnabled(true);
            observacaoTF.setEnabled(true);
            buscarButton.setEnabled(false);
            salvarButton.setEnabled(true);
            excluirButton.setEnabled(false);
            novoButton.setEnabled(false);
        });
        salvarButton.addActionListener(e -> {
            String dataBr;
            int[] d = new int[3];
            Itens it = new Itens();
            int control = Integer.parseInt(idFT.getText());

            if (control == 0) {
                //INSERT
                it.setTitulo(tituloTF.getText());
                it.setLocal(localTF.getText());
                if (statusSB.getSelectedIndex() == 0) {
                    it.setStatus(Status.PERDIDO);
                } else {
                    it.setStatus(Status.ACHADO);
                }
                it.setObservacao(observacaoTF.getText());
                dataBr = dataTF.getText();
                d[0] = Integer.parseInt(dataBr.substring(0, 2));
                d[1] = Integer.parseInt(dataBr.substring(3, 5));
                d[2] = Integer.parseInt(dataBr.substring(6, 10));
                it.setDateTime(LocalDate.of(d[2], d[1], d[0]));
                dao.save(it);
                JOptionPane.showMessageDialog(cadastroPanel, "Salvo Com Sucesso" + "\nCOM O ID = " + it.getId());
            } else {
                //update
                it.setTitulo(tituloTF.getText());
                it.setLocal(localTF.getText());
                if (statusSB.getSelectedIndex() == 0) {
                    it.setStatus(Status.PERDIDO);
                } else {
                    it.setStatus(Status.ACHADO);
                }
                it.setObservacao(observacaoTF.getText());
                dataBr = dataTF.getText();
                d[0] = Integer.parseInt(dataBr.substring(0, 2));
                d[1] = Integer.parseInt(dataBr.substring(3, 5));
                d[2] = Integer.parseInt(dataBr.substring(6, 10));
                it.setDateTime(LocalDate.of(d[2], d[1], d[0]));
                it.setId(Long.valueOf(idFT.getText()));
                dao.update(it);
                JOptionPane.showMessageDialog(cadastroPanel, "Registro modificado com sucesso!");
            }
            resetCampos();

        });
        sairButton.addActionListener(e -> {
            setVisible(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        });
        buscarButton.addActionListener(e -> {
            String[] d = new String[3];
            idFT.setEnabled(false);

            try {
                Optional<Itens> itens1 = dao.findById(Long.valueOf(idFT.getText()));
                itens1.ifPresent(itens -> {
                    String dataBr = String.valueOf(itens.getDateTime());
                    d[0] = dataBr.substring(8, 10);
                    d[1] = dataBr.substring(5, 7);
                    d[2] = dataBr.substring(0, 4);
                    tituloTF.setText(itens.getTitulo());
                    localTF.setText(itens.getLocal());
                    if (itens.getStatus() == Status.ACHADO) {
                        statusSB.setSelectedIndex(1);
                    } else {
                        statusSB.setSelectedIndex(0);
                    }
                    dataTF.setText(d[0] + d[1] + d[2]);
                    observacaoTF.setText(itens.getObservacao());
                });
                excluirButton.setEnabled(true);
                salvarButton.setEnabled(true);
                novoButton.setEnabled(false);
                idFT.setEnabled(false);
                tituloTF.setEnabled(true);
                localTF.setEnabled(true);
                statusSB.setEnabled(true);
                dataTF.setEnabled(true);
                observacaoTF.setEnabled(true);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(cadastroPanel, "Escreva um ID");
                resetCampos();
            }
        });
        cancelarButton1.addActionListener(e -> resetCampos());
        excluirButton.addActionListener(e -> {
            dao.delete(Long.valueOf(idFT.getText()));
            JOptionPane.showMessageDialog(cadastroPanel,"Registro deletado com sucesso!");
            resetCampos();
        });
    }
}