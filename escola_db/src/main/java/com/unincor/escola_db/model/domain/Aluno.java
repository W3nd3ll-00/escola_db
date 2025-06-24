/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.escola_db.model.domain;

import java.time.LocalDate;

/**
 *
 * @author alunos
 */
public class Aluno {
    private Long idAluno;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    
    
    public Aluno() {
    }

    public Aluno(Long idAluno, String nome, String email, LocalDate dataNascimento) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    
    
}
