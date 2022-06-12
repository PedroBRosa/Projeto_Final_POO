package br.com.projetoPoo.dao;

import br.com.projetoPoo.infra.ConnectionFactory;
import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItensDAO implements IItensDAO {

    @Override
    public Itens save(Itens itens) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO itens(titulo, local, observacao, status, data) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, itens.getTitulo());
            preparedStatement.setString(2,itens.getLocal());
            preparedStatement.setString(3,itens.getObservacao());
            preparedStatement.setString(4, itens.getStatus().toString());
            preparedStatement.setDate(5,java.sql.Date.valueOf(itens.getDateTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return itens;
    }

    @Override
    public Itens update(Itens itens) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Itens> findAll() {
        return null;
    }

    @Override
    public Optional<Itens> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Itens> findByStatus(Status status) {
        return null;
    }
}
