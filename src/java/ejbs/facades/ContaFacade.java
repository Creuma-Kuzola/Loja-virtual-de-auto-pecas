/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Conta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author creuma
 */
@Stateless
public class ContaFacade extends AbstractFacade<Conta> {

    @PersistenceContext(unitName = "Proj2_2022PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContaFacade() {
        super(Conta.class);
    }
    
}
