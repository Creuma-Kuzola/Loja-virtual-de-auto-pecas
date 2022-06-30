/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Montra;
import ejbs.entities.Portfolio;
import ejbs.entities.Stock;
import ejbs.facades.MontraFacade;
import ejbs.facades.StockFacade;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author creuma
 */
@Named(value = "adicionarProdutoMontraBean")
@ViewScoped
public class AdicionarProdutoMontraBean implements Serializable {

    @EJB
    private StockFacade stockFacade;
    
    @EJB
    private MontraFacade montraFacade;
    
    private List<Stock> listaDeProdutosStock;
    private Montra montra;
    /**
     * Creates a new instance of AdicionarProdutoMontraBean
     */
    public AdicionarProdutoMontraBean() {
        
    }
    
    
    @PostConstruct
    public void init(){
        
        listaDeProdutosStock= stockFacade.listaDeProdutosQueEstaoNoStockENaoNaMontra();
        montra = new Montra();
        
    }

    
    
    public void adicionarProdutoMontra(String pkPortfolio){
    
        montra.setDataMontra(new Date());
        montra.setFkPortfolio(new Portfolio(pkPortfolio));
        montraFacade.create(montra);
        
    }
    
    public void removerProdutosMontra(int pkMontra){
    
        montraFacade.remove(new Montra(pkMontra));
    }
    
    
    public List<Stock> getListaDeProdutosStock() {
        return listaDeProdutosStock;
    }

    public void setListaDeProdutosStock(List<Stock> listaDeProdutosStock) {
        this.listaDeProdutosStock = listaDeProdutosStock;
    }
    
    
    
}
