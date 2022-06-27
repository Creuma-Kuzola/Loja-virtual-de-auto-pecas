/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Portfolio;
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
public class PortfolioFacade extends AbstractFacade<Portfolio> {

    @PersistenceContext(unitName = "Proj2_2022PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortfolioFacade() {
        super(Portfolio.class);
    }

    public List<Portfolio> findAllMarcas() {

        Query query = em.createQuery("SELECT p FROM Portfolio p WHERE p.fkPortfolioPai.pkPortfolio = NULL");

        return query.getResultList();

    }

    public List<Portfolio> findAllModelos() {

        Query query = em.createQuery("SELECT p FROM Portfolio p WHERE p.pkPortfolio LIKE '_._' ");
        return query.getResultList();
    }

    public List<Portfolio> findAllCategoriaPecas() {
        Query query = em.createQuery("SELECT p FROM Portfolio p WHERE p.pkPortfolio LIKE '_._._' ");
        return query.getResultList();
    }
    
    public List<Portfolio> findAllPecas(){
        Query query = em.createQuery("SELECT p FROM Portfolio p WHERE p.pkPortfolio LIKE '_._._._' ");
        return query.getResultList();
    }
}
