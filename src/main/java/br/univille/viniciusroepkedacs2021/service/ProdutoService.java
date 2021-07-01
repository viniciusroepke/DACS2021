package br.univille.viniciusroepkedacs2021.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.viniciusroepkedacs2021.model.Produto;

@Service //n é obrigatório, mas ajuda
public interface ProdutoService {
    public List<Produto> getAllProdutos();
}
