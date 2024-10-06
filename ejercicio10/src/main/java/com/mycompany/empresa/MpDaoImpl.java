package com.mycompany.empresa;

import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class MpDaoImpl implements MpDao {
    private final Sql2o sql2o;

    public MpDaoImpl() {
        this.sql2o = Sql2oDAO.getSql2o(); 
    }

    @Override
    public void add(Mp materiaPrima) {
        String insertSQL = "INSERT INTO materiasprimas (nombre_MP, stock, unidades, fecha_vto_prox) VALUES (:nombre_MP, :stock, :unidades, :fecha_vto_prox)";
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSQL)
                .addParameter("nombre_MP", materiaPrima.getNombre_MP())
                .addParameter("stock", materiaPrima.getstock())
                .addParameter("unidades", materiaPrima.getunidades())
                .addParameter("fecha_vto_prox", materiaPrima.getvencimiento())
                .executeUpdate();
        }
    }

    @Override
    public void update(Mp materiaPrima) {
        String updateSQL = "UPDATE materiasprimas SET nombre_MP = :nombre_MP, stock = :stock, unidades = :unidades, fecha_vto_prox = :fecha_vto_prox WHERE id_MateriaPrima = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSQL)
                .addParameter("nombre_MP", materiaPrima.getNombre_MP())
                .addParameter("stock", materiaPrima.getstock())
                .addParameter("unidades", materiaPrima.getunidades())
                .addParameter("fecha_vto_prox", materiaPrima.getvencimiento())
                .addParameter("id", materiaPrima.getid_MateriaPrima())
                .executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) {
        String deleteSQL = "DELETE FROM materiasprimas WHERE id_MateriaPrima = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(deleteSQL)
                .addParameter("id", id)
                .executeUpdate();
        }
    }

    @Override
    public Mp getById(Integer id) {
        String selectSQL = "SELECT * FROM materiasprimas WHERE id_MateriaPrima = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(selectSQL)
                      .addParameter("id", id)
                      .executeAndFetchFirst(Mp.class);
        }
    }

    @Override
    public List<Mp> getAll() {
        String selectAllSQL = "SELECT * FROM materiasprimas";
        try (Connection con = sql2o.open()) {
            return con.createQuery(selectAllSQL).executeAndFetch(Mp.class);
        }
    }
}

