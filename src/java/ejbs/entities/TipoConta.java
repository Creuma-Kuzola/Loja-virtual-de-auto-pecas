/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author creuma
 */
@Entity
@Table(name = "tipo_conta", catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoConta.findAll", query = "SELECT t FROM TipoConta t"),
    @NamedQuery(name = "TipoConta.findByPkTipoConta", query = "SELECT t FROM TipoConta t WHERE t.pkTipoConta = :pkTipoConta"),
    @NamedQuery(name = "TipoConta.findByNome", query = "SELECT t FROM TipoConta t WHERE t.nome = :nome")})
public class TipoConta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_conta", nullable = false)
    private Integer pkTipoConta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nome;
    @OneToMany(mappedBy = "fkTipoConta")
    private Collection<Conta> contaCollection;

    public TipoConta() {
    }

    public TipoConta(Integer pkTipoConta) {
        this.pkTipoConta = pkTipoConta;
    }

    public TipoConta(Integer pkTipoConta, String nome) {
        this.pkTipoConta = pkTipoConta;
        this.nome = nome;
    }

    public Integer getPkTipoConta() {
        return pkTipoConta;
    }

    public void setPkTipoConta(Integer pkTipoConta) {
        this.pkTipoConta = pkTipoConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Conta> getContaCollection() {
        return contaCollection;
    }

    public void setContaCollection(Collection<Conta> contaCollection) {
        this.contaCollection = contaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTipoConta != null ? pkTipoConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConta)) {
            return false;
        }
        TipoConta other = (TipoConta) object;
        if ((this.pkTipoConta == null && other.pkTipoConta != null) || (this.pkTipoConta != null && !this.pkTipoConta.equals(other.pkTipoConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.TipoConta[ pkTipoConta=" + pkTipoConta + " ]";
    }
    
}
