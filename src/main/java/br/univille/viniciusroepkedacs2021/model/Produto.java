package br.univille.viniciusroepkedacs2021.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Column(length = 1000)
   private String descricao;
   private float preco;
   @Temporal(value = TemporalType.DATE)
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date dataRegistro;

   @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}) //efeito cascata
   private Categoria categoria; 

public Date getDataRegistro() {
    return dataRegistro;
}
public Categoria getCategoria() {
    return categoria;
}
public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
}
public void setDataRegistro(Date dataRegistro) {
    this.dataRegistro = dataRegistro;
}
public long getId() {
    return id;
}
public void setId(long id) {
    this.id = id;
}
public String getDescricao() {
    return descricao;
}
public void setDescricao(String descricao) {
    this.descricao = descricao;
}
public float getPreco() {
    return preco;
}
public void setPreco(float preco) {
    this.preco = preco;
}

   
}
