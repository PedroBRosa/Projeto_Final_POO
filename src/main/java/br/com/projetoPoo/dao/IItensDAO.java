package br.com.projetoPoo.dao;

import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import java.util.List;
import java.util.Optional;

public interface IItensDAO {
    void save(Itens itens);

    void update(Itens itens);

    void delete(Long id);

    List<Itens> findAll();

    List<Itens> findTitulo(String it);

    Optional<Itens> findById(Long id);

    List<Itens> findByStatus(Status status);

}
