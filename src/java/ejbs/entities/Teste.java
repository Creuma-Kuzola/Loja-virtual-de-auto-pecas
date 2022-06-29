/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author creuma
 */
@Entity
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teste.findAll", query = "SELECT t FROM Teste t"),
    @NamedQuery(name = "Teste.findByPkTeste", query = "SELECT t FROM Teste t WHERE t.pkTeste = :pkTeste")})
public class Teste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_teste", nullable = false)
    private Integer pkTeste;

    public Teste() {
    }

    public Teste(Integer pkTeste) {
        this.pkTeste = pkTeste;
    }

    public Integer getPkTeste() {
        return pkTeste;
    }

    public void setPkTeste(Integer pkTeste) {
        this.pkTeste = pkTeste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTeste != null ? pkTeste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teste)) {
            return false;
        }
        Teste other = (Teste) object;
        if ((this.pkTeste == null && other.pkTeste != null) || (this.pkTeste != null && !this.pkTeste.equals(other.pkTeste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Teste[ pkTeste=" + pkTeste + " ]";
    }
    
}
