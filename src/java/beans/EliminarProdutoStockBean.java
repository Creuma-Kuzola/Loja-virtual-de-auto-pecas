/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Portfolio;
import ejbs.entities.Stock;
import ejbs.facades.PortfolioFacade;
import ejbs.facades.StockFacade;
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
@Named(value = "eliminarProdutoStockBean")
@ViewScoped
public class EliminarProdutoStockBean implements Serializable {

    /**
     * Creates a new instance of EliminarProdutoStockBean
     */
    @EJB
    private StockFacade stockFacade;

    @EJB
    private PortfolioFacade portfolioFacade;

    private Stock stock;
    private Portfolio portfolio;
    private List<Stock> listaStock;

    public EliminarProdutoStockBean() {

    }

    // Negócio
    @PostConstruct
    public void listaDeProdutosDoStock() {

        listaStock = stockFacade.findAll();
    }

    public void removerProdutosDoStock(int pkStock) {

        stockFacade.remove(new Stock(pkStock));
    }

    // getters
    public List<Stock> getListaStock() {
        return listaStock;
    }

    public void setListaStock(List<Stock> listaStock) {
        this.listaStock = listaStock;
    }

}
