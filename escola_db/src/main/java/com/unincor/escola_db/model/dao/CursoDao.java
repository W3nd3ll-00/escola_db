/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.escola_db.model.dao;

import com.unincor.escola_db.configurations.MySQL;
import com.unincor.escola_db.model.domain.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alunos
 */
public class CursoDao {
    public void inserirCurso(Curso curso) {
        String sql = "INSERT INTO cursos(nome, descricao, carga_horaria) VALUES (?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getDescricao());
            ps.setInt(3, curso.getCargaHoraria());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Curso> buscarTodosCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var curso = construirCursoSql(rs);
                cursos.add(curso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    public Curso buscarCursoPorId(Long idCurso) {
        String sql = "SELECT * FROM cursos WHERE id_curso = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idCurso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirCursoSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Curso construirCursoSql(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setIdCurso(rs.getLong("id_curso"));
        curso.setNome(rs.getString("nome"));
        curso.setDescricao(rs.getString("descricao"));
        curso.setCargaHoraria(rs.getInt("carga_horaria"));
        return curso;
    }

    public static void main(String[] args) {
        CursoDao cursoDao = new CursoDao();
        var c = cursoDao.buscarCursoPorId(1l);
        System.out.println("Id: " + c.getIdCurso() 
                + " Nome: " + c.getNome());
    }
}
