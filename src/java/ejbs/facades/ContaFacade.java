/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Conta;
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

    public Conta findContaByUsernameAndPassword(String username, String password) {

        Query query = em.createQuery("SELECT c FROM Conta c WHERE c.username = "
                + ":username AND c.password = :password");

        query.setParameter("username", username);
        query.setParameter("password", password);

        List<Conta> listaConta = query.getResultList();

        if (!listaConta.isEmpty()) {
            return listaConta.get(0);
        }

        return null;

    }

}
