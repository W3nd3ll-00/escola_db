/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.escola_db.model.dao;

import com.unincor.escola_db.configurations.MySQL;
import com.unincor.escola_db.model.domain.Aluno;
import java.sql.Connection;
import java.sql.Date;
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
public class AlunoDao {
    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos(nome, email, data_nascimento) VALUES (?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Aluno> buscarTodosAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var aluno = construirAlunoSql(rs);
                alunos.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alunos;
    }

    public Aluno buscarAlunoPorId(Long idAluno) {
        String sql = "SELECT * FROM alunos WHERE id_aluno = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idAluno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirAlunoSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Aluno construirAlunoSql(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setIdAluno(rs.getLong("id_aluno"));
        aluno.setNome(rs.getString("nome"));
        aluno.setEmail(rs.getString("email"));
        aluno.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        return aluno;
    }

    public static void main(String[] args) {
        AlunoDao alunoDao = new AlunoDao();
        var a = alunoDao.buscarAlunoPorId(1l);
        System.out.println("Id: " + a.getIdAluno() 
                + " Nome: " + a.getNome());
    }
}
