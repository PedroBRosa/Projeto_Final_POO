package br.com.projetoPoo.dao;

import br.com.projetoPoo.infra.ConnectionFactory;
import br.com.projetoPoo.model.Itens;
import br.com.projetoPoo.model.Status;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItensDAO implements IItensDAO {


    private List<Itens> find(String sql) {
        List<Itens> itens = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String local = rs.getString("local");
                String observacao = rs.getString("observacao");
                Status status = Status.valueOf(rs.getString("status"));
                LocalDate data = rs.getDate("data").toLocalDate();
                Itens item = new Itens(id, titulo, local, observacao, data, status);
                itens.add(item);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itens;
    }

    @Override
    public Itens save(Itens itens) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO itens(titulo, local, observacao, status, data) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, itens.getTitulo());
            preparedStatement.setString(2, itens.getLocal());
            preparedStatement.setString(3, itens.getObservacao());
            preparedStatement.setString(4, itens.getStatus().toString());
            preparedStatement.setDate(5, java.sql.Date.valueOf(itens.getDateTime()));
            preparedStatement.executeUpdate();
            ResultSet rs  = preparedStatement.getGeneratedKeys();
                rs.next();
                Long newId = rs.getLong("id");
                System.out.println(newId);
                itens.setId(newId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return itens;
    }

    @Override
    public Itens update(Itens itens) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE itens SET titulo = ?, local= ?, observacao = ?, status = ?, data = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, itens.getTitulo());
            preparedStatement.setString(2, itens.getLocal());
            preparedStatement.setString(3, itens.getObservacao());
            preparedStatement.setString(4, itens.getStatus().toString());
            preparedStatement.setDate(5, java.sql.Date.valueOf(itens.getDateTime()));
            preparedStatement.setLong(6,itens.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return itens;
    }
    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM itens WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Itens> findAll() {
        String sql = "SELECT * FROM itens ORDER BY id ASC";
        return find(sql);
    }

    @Override
    public List<Itens> findTitulo(String it) {
        it = "'%" + it + "%'";
        String sql = "SELECT * FROM itens WHERE titulo LIKE" + it;
        return find(sql);
    }


    @Override
    public Optional<Itens> findById(Long id) {
        String sql = "SELECT * FROM itens WHERE id =" + id;
        Itens item=null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long pKId = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String local = rs.getString("local");
                String observacao = rs.getString("observacao");
                Status status = Status.valueOf(rs.getString("status"));
                LocalDate data = rs.getDate("data").toLocalDate();
                item = new Itens(pKId, titulo, local, observacao, data, status);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(item);
    }

    @Override
    public List<Itens> findByStatus(Status status) {

        return null;
    }
}
