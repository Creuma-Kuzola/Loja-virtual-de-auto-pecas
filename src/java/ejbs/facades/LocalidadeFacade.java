/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Localidade;
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
public class LocalidadeFacade extends AbstractFacade<Localidade> {

    @PersistenceContext(unitName = "Proj2_2022PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalidadeFacade() {
        super(Localidade.class);
    }

    public List<Localidade> findAllPaises() {

        Query query = em.createQuery("SELECT l FROM Localidade l WHERE l.fkLocalidadePai.pkLocalidade = NULL");

        return query.getResultList();

    }

    public List<Localidade> findAllProvincias() {

        Query query = em.createQuery("SELECT l FROM Localidade l WHERE l.pkLocalidade LIKE '_._' ");
        return query.getResultList();
    }

    public List<Localidade> findAllMunicipios() {

        Query query = em.createQuery("SELECT l FROM Localidade l WHERE l.pkLocalidade LIKE '_._._' ");
        return query.getResultList();
    }

    public List<Localidade> findAllDistritos() {

        Query query = em.createQuery("SELECT l FROM Localidade l WHERE l.pkLocalidade LIKE '_._._._' ");
        return query.getResultList();
    }

    public List<Localidade> findAllPaisesByParent(String fkLocalidadePai) {
        Query query = em.createQuery("SELECT l from Localidade l WHERE l.fkLocalidadePai.pkLocalidade = :fkLocalidadePai");
        query.setParameter("fkLocalidadePai", fkLocalidadePai);
        return query.getResultList();
    }

    public List<Localidade> findAllProvinciasByParent(String fkLocalidadePai) {
        Query query = em.createQuery("SELECT l from Localidade l WHERE l.fkLocalidadePai.pkLocalidade = :fkLocalidadePai");
        query.setParameter("fkLocalidadePai", fkLocalidadePai);
        return query.getResultList();
    }

    public List<Localidade> findAllMunicipiosByParent(String fkLocalidadePai) {
        Query query = em.createQuery("SELECT l from Localidade l WHERE l.fkLocalidadePai.pkLocalidade = :fkLocalidadePai");
        query.setParameter("fkLocalidadePai", fkLocalidadePai);
        return query.getResultList();
    }

    public List<Localidade> findAllDistritosByParent(String fkLocalidadePai) {
        Query query = em.createQuery("SELECT l from Localidade l WHERE l.fkLocalidadePai.pkLocalidade = :fkLocalidadePai");
        query.setParameter("fkLocalidadePai", fkLocalidadePai);
        return query.getResultList();
    }
}
