/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Conta;
import ejbs.facades.ContaFacade;
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
@Named(value = "verContaBean")
@ViewScoped
public class VerContaBean implements Serializable {

    @EJB
    private ContaFacade contaFacade;
    
    private List<Conta> listaContas;
    /**
     * Creates a new instance of VerContaBean
     */
    public VerContaBean() {
    }
    
    @PostConstruct
    public void init(){
    
        listaContas = listaDeContas();
    }
    
    
    
    public List<Conta> listaDeContas(){
        
        return contaFacade.findAll();
    }
    
    
    
    
    public List<Conta> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Conta> listaContas) {
        this.listaContas = listaContas;
    }
    
    
    
    
    
    
    
}
