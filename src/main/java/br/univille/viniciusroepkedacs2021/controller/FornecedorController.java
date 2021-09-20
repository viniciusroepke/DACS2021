package br.univille.viniciusroepkedacs2021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.viniciusroepkedacs2021.model.Fornecedor;
import br.univille.viniciusroepkedacs2021.service.FornecedorService;


@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public ModelAndView index(){

        List<Fornecedor> listaFornecedores = fornecedorService.getAllFornecedores();

        return new ModelAndView("fornecedor/index","listaFornecedores",listaFornecedores);
    }

    @GetMapping("/novo")
    public ModelAndView novo(@ModelAttribute Fornecedor fornecedor){
        return new ModelAndView("fornecedor/form");
    }

    @PostMapping(params = "form")
    public ModelAndView save(Fornecedor fornecedor){
        fornecedorService.save(fornecedor);
        return new ModelAndView("redirect:/fornecedor");
    }

    @GetMapping(value = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Fornecedor fornecedor){
        return new ModelAndView("fornecedor/form", "fornecedor", fornecedor);
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Fornecedor fornecedor){
        fornecedorService.delete(fornecedor);
        return new ModelAndView("redirect:/fornecedor");
    }


}