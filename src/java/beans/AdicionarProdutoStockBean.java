/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Portfolio;
import ejbs.entities.Stock;
import ejbs.facades.PortfolioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author creuma
 */
@Named(value = "adicionarProdutoStockBean")
@ViewScoped
public class AdicionarProdutoStockBean implements Serializable {

    /**
     * Creates a new instance of AdicionarProdutoStockBean
     */
    @EJB
    private PortfolioFacade portfolioFacade;
    
    
    
    private Stock stock;
    private List<Portfolio> listaMarca;
    private List<Portfolio> listaModelo;
    private List<Portfolio> listaCategoriaPeca;
    private List<Portfolio> listaPeca;
    

    public AdicionarProdutoStockBean() {
    }
    
    @PostConstruct
    public void init(){
        listaMarca = portfolioFacade.findAllMarcas();
        listaModelo = portfolioFacade.findAllModelos();
        listaCategoriaPeca = portfolioFacade.findAllCategoriaPecas();
        listaPeca = portfolioFacade.findAllPecas();
        stock = new Stock();
    }

    
    // Negócio
    
    
    
    
    //getters and setters
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<Portfolio> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(List<Portfolio> listaMarca) {
        this.listaMarca = listaMarca;
    }

    public List<Portfolio> getListaModelo() {
        return listaModelo;
    }

    public void setListaModelo(List<Portfolio> listaModelo) {
        this.listaModelo = listaModelo;
    }

    public List<Portfolio> getListaCategoriaPeca() {
        return listaCategoriaPeca;
    }

    public void setListaCategoriaPeca(List<Portfolio> listaCategoriaPeca) {
        this.listaCategoriaPeca = listaCategoriaPeca;
    }

    public List<Portfolio> getListaPeca() {
        return listaPeca;
    }

    public void setListaPeca(List<Portfolio> listaPeca) {
        this.listaPeca = listaPeca;
    }
    
    
    
    
    
}
