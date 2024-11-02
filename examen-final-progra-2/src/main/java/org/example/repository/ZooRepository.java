package org.example.repository;

import org.example.configs.MysqlConfig;
import org.example.domain.Zoo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZooRepository {
    public void save(Zoo zoo) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("INSERT INTO zoo (nombre, edad, fechaNacimiento, especie) VALUES (?, ?, ?, ?)");
        statement.setString(1, zoo.getNombre());
        statement.setInt(2, zoo.getId());
        statement.setDate(3, new Date(zoo.getFechaNacimiento().getTime()));
        statement.setString(4, zoo.getEspecie());
        statement.executeUpdate();
    }

    public void delete(Zoo zoo) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("DELETE FROM zoo WHERE nombre = ?");
        statement.setString(1, zoo.getNombre());
        statement.executeUpdate();

        System.out.println("Deleted: " + zoo.getNombre());
    }

    public void update(Zoo zoo) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("UPDATE zoo SET nombre = ? WHERE id = ?");
        statement.setInt(2, zoo.getId());
        statement.setString(1, zoo.getNombre());
        statement.executeUpdate();

        System.out.println("Updated: " + zoo.getNombre());
    }

    public Zoo read(Zoo zoo) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("SELECT * FROM zoo WHERE id = ?");
        statement.setInt(1, zoo.getId());
        var resultSet = statement.executeQuery();

        zoo.setId(resultSet.getInt("id"));
        zoo.setNombre(resultSet.getString("nombre"));
        zoo.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
        zoo.setEspecie(resultSet.getString("especie"));

        return zoo;
    }

    public List<Zoo> readAll() throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("SELECT * FROM zoo");
        var result = statement.executeQuery();

        List<Zoo> zoos = new ArrayList<>();

        while (result.next()) {
            var zoo = new Zoo();
            zoo.setId(result.getInt("id"));
            zoo.setNombre(result.getString("nombre"));
            zoo.setFechaNacimiento(result.getDate("fechaNacimiento"));
            zoo.setEspecie(result.getString("especie"));
            zoos.add(zoo);
        }
        return zoos;
    }
}
