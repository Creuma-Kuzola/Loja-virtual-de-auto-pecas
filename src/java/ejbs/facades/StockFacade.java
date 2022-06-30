/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Stock;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author creuma
 */
@Stateless
public class StockFacade extends AbstractFacade<Stock> {

    @PersistenceContext(unitName = "Proj2_2022PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockFacade() {
        super(Stock.class);
    }
    
   
    public List<Stock> listaDeProdutosQueEstaoNoStockENaoNaMontra(){
        
        Query query = em.createQuery("SELECT s FROM Stock s WHERE s.fkPortfolio.pkPortfolio NOT IN (SELECT m.fkPortfolio.pkPortfolio FROM Montra m )");
        return query.getResultList();
        
    } 
    
   
    
}
