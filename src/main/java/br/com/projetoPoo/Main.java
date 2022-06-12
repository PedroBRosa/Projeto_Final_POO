package br.com.projetoPoo;

import br.com.projetoPoo.dao.ItensDAO;
import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ItensDAO dao = new ItensDAO();
        Itens it = new Itens();
        it.setTitulo("Caneta");
        it.setLocal("SAla");
        it.setObservacao("Perdido na sala 505");
        it.setStatus(Status.PERDIDO);
        it.setDateTime(LocalDate.of(2022,6,12));
        dao.save(it);
    }
}
