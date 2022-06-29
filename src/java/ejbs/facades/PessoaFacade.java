/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Pessoa;
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
public class PessoaFacade extends AbstractFacade<Pessoa> {

    @PersistenceContext(unitName = "Proj2_2022PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFacade() {
        super(Pessoa.class);
    }
    
    public Pessoa getPessoByEmail(String email){
        
        Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.email = :email");
        query.setParameter("email", email );
        
        List<Pessoa> listObject = query.getResultList();
        
        if(listObject.isEmpty()){
            return null;
        }
     
        return listObject.get(0);
    }
    
}
