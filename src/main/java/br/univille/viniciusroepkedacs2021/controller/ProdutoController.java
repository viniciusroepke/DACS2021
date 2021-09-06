package br.univille.viniciusroepkedacs2021.controller;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.viniciusroepkedacs2021.model.Produto;
import br.univille.viniciusroepkedacs2021.service.ProdutoService;
import br.univille.viniciusroepkedacs2021.model.Categoria;
import br.univille.viniciusroepkedacs2021.service.CategoriaService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public ModelAndView index(){

        /* Produto prod1 = new Produto();
        prod1.setDescricao("Produto legal 1");
        prod1.setDataRegistro(new Date());
        prod1.setPreco(2000);

        Produto prod2 = new Produto();
        prod2.setDescricao("Produto legal 2");
        prod2.setDataRegistro(new Date());
        prod2.setPreco(7800); */

        HashMap<String,Object> dados = new HashMap<>();

        List<Produto> listaProdutos = service.getAllProdutos();
        dados.put("listaProdutos",listaProdutos);
        List<Categoria> listaCategorias = categoriaService.getAllCategorias();
        dados.put("listaCategorias",listaCategorias);
        /* listaProdutos.add(prod1);
        listaProdutos.add(prod2); */


        return new ModelAndView("produto/index",dados);
    }

    @GetMapping("/novo")
    public ModelAndView novo(@ModelAttribute Produto produto){
        return new ModelAndView("produto/form");
    }

    @PostMapping(params = "form")
    public ModelAndView save(Produto produto){
        service.save(produto);
        return new ModelAndView("redirect:/produto");
    }

    @GetMapping(value = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Produto produto){
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("produto",produto);
        List<Categoria> listaCategorias = categoriaService.getAllCategorias();
        dados.put("listaCategorias",listaCategorias);

        return new ModelAndView("produto/form",dados);
    }  

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Produto produto){
        service.delete(produto);
        return new ModelAndView("redirect:/produto");
    }

}
