/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Montra;
import ejbs.facades.MontraFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author creuma
 */
@Named(value = "montraBean")
@ViewScoped
public class MontraBean implements Serializable {

    /**
     * Creates a new instance of MontraBean
     */
    @EJB
    private MontraFacade montraFacade;
    
    private List<Montra> listaMontra;

    
    public MontraBean() {
    }
    
    @PostConstruct
    public void init(){
    
        listaMontra = getProdutosMontra();
    }

    public List<Montra> getProdutosMontra() {

        List<Montra> listaProdutosMontra = montraFacade.findAll();
        return listaProdutosMontra;

    }
    
    
    

    public List<Montra> getListaMontra() {
        return listaMontra;
    }

    public void setListaMontra(List<Montra> listaMontra) {
        this.listaMontra = listaMontra;
    }
    
    
    
    

}
