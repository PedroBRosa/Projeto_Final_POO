package br.com.projetoPoo.dao;
import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import java.util.List;
import java.util.Optional;

public interface IItensDAO {
    Itens save(Itens itens);
    Itens update(Itens itens);
    void delete(Long id);
    List<Itens> findAll();
    Optional<Itens> findById(Long id);
    List<Itens> findByStatus(Status status);

}
