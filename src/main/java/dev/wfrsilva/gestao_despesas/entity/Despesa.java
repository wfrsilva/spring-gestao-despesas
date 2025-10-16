package dev.wfrsilva.gestao_despesas.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(length = 100, nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private LocalDate data_criacao;

    public UUID getId()
    {
        return id;
    }//getId


    public void setId(UUID id)
    {
        this.id = id;
    }//setId

    public String  getDescricao()
    {
        return descricao;
    }//getDescricao

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }//setDescricao

    public LocalDate getData()
    {
        return data;
    }//getData

    public void setData(LocalDate data)
    {
        this.data = data;
    }//setData

    public BigDecimal getValor()
    {
        return valor;
    }//getValor

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }//valor

    public String getCategoria()
    {
        return categoria;
    }//getCategoria

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }//setCategoria

    public String getEmail()
    {
        return email;
    }//getEmail

    public void setEmail(String email)
    {
        this.email = email;
    }//setEmail

    public LocalDate getData_criacao()
    {
        return data_criacao;
    }//getData_criacao

    public void setData_criacao(LocalDate data_criacao)
    {
        this.data_criacao = data_criacao;
    }//setData_criacao


}//Despesa
