/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Conta;
import ejbs.facades.ContaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author creuma
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    private Conta conta;

    @EJB
    private ContaFacade contaFacade;

    public LoginBean() {
    }

    @PostConstruct
    public void init() {
        conta = new Conta();
    }

    public String login() {
        
        Conta contaRetornada = contaFacade.findContaByUsernameAndPassword(
                conta.getUsername(), conta.getPassword());
        
        if(contaRetornada != null){
            return "home.xhtml?faces-redirect=true";
        }
        return "index.xhtml?faces-redirect=true";
        
    }

    //getters and setters
    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
