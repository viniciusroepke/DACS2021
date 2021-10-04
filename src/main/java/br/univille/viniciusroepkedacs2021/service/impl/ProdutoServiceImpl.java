package br.univille.viniciusroepkedacs2021.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.viniciusroepkedacs2021.model.Fornecedor;
import br.univille.viniciusroepkedacs2021.model.Produto;
import br.univille.viniciusroepkedacs2021.repository.ProdutoRepository;
import br.univille.viniciusroepkedacs2021.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    private ProdutoRepository repository;

    @Override
    public List<Produto> getAllProdutos() {
        return repository.findAll();
    }

    @Override
    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public void delete(Produto produto) {
        repository.delete(produto);        
    }

    @Override
    public List<Produto> importProduto(Fornecedor Fornecedor) {
        if(Fornecedor != null){
            try{
                //USAR ENDEREÇO NGROK DE ALGUEM EM BAIXO!!!
                URL endereco = new URL(Fornecedor.getUrlAPI()); //NÃO USAR HTTPS
                HttpURLConnection conn = (HttpURLConnection)endereco.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCode = conn.getResponseCode();

                if(responseCode == 200){
                    Scanner leitor = new Scanner(endereco.openStream());
                    
                    StringBuilder jsonText = new StringBuilder();
                    while(leitor.hasNext()){
                        jsonText.append(leitor.nextLine());
                        //System.out.println(leitor.nextLine());
                    }
        
                    Gson gson = new Gson();
                    //DDL INSERT - UPDATE - DELETE
                    //DML CREATE DATABASE - CREATE TABLE - DROP TABLE (SCHEMA-METADADOS)
        
                    // Parser
                    Type typeListProdutos = new TypeToken<ArrayList<Produto>>(){}.getType();
                    ArrayList<Produto> listaProdutos = gson.fromJson(jsonText.toString(), typeListProdutos);
                    return listaProdutos;
                }
    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
        return new ArrayList<Produto>();
    }
    
}
