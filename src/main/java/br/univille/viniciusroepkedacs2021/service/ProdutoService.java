package br.univille.viniciusroepkedacs2021.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.viniciusroepkedacs2021.model.Produto;

@Service //esta tag n é obrigatória, mas ajuda
public interface ProdutoService {
    public List<Produto> getAllProdutos();
    public Produto save(Produto produto);
}
