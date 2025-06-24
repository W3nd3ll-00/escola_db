/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.escola_db.model.services;


import com.unincor.escola_db.exceptions.CadastroException;
import com.unincor.escola_db.model.domain.Aluno;
import com.unincor.escola_db.model.dao.AlunoDao;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alunos
 */
public class AlunoService {
    private final AlunoDao alunoDao = new AlunoDao();

    public void salvarAluno(Aluno aluno) throws CadastroException {
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new CadastroException("O aluno não possui um nome!");
        }
        
        if (aluno.getEmail() == null || aluno.getEmail().isBlank()) {
            throw new CadastroException("O aluno não possui email cadastrado!");
        }

        if (aluno.getDataNascimento()== null) {
            throw new CadastroException("O aluno não possui data de nascimento!");
        }

        alunoDao.inserirAluno(aluno);
    }
    
    public List<Aluno> buscarAluno() {
        return alunoDao.buscarTodosAlunos();
    }

    public static void main(String[] args) {
        AlunoService alunoService = new AlunoService();

        Aluno aluno = new Aluno(null, "Wendell", "wendell@gmail.com", LocalDate.parse("2005-06-21"));
        
        try {
            alunoService.salvarAluno(aluno);
        } catch (CadastroException ex) {
            Logger.getLogger(AlunoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
