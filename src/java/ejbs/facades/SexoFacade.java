/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Sexo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author creuma
 */
@Stateless
public class SexoFacade extends AbstractFacade<Sexo> {

    @PersistenceContext(unitName = "Proj2_2022PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SexoFacade() {
        super(Sexo.class);
    }
    
}
