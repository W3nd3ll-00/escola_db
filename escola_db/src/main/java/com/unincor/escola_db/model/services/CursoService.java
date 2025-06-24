/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.escola_db.model.services;

import com.unincor.escola_db.exceptions.CadastroException;
import com.unincor.escola_db.model.dao.CursoDao;
import com.unincor.escola_db.model.domain.Curso;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alunos
 */
public class CursoService {
    private final CursoDao cursoDao = new CursoDao();

    public void salvarCurso(Curso curso) throws CadastroException {
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new CadastroException("O curso não possui um nome!");
        }
        
        if (curso.getDescricao()== null || curso.getDescricao().isBlank()) {
            throw new CadastroException("O curso não possui descrição válida!");
        }

        if (curso.getCargaHoraria() < 0) {
            throw new CadastroException("O curso não possui carga horária válida!");
        }

        cursoDao.inserirCurso(curso);
    }
    
    public List<Curso> buscarCurso() {
        return cursoDao.buscarTodosCursos();
    }

    public static void main(String[] args) {
        CursoService cursoService = new CursoService();

        Curso curso = new Curso(null, "Ciências da Computação", "Um curso sobre ciências e sobre computação", 10000);
        
        try {
            cursoService.salvarCurso(curso);
        } catch (CadastroException ex) {
            Logger.getLogger(CursoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
