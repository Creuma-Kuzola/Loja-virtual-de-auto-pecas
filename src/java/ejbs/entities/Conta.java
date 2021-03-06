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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findByPkConta", query = "SELECT c FROM Conta c WHERE c.pkConta = :pkConta"),
    @NamedQuery(name = "Conta.findByUsername", query = "SELECT c FROM Conta c WHERE c.username = :username"),
    @NamedQuery(name = "Conta.findByPassword", query = "SELECT c FROM Conta c WHERE c.password = :password")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_conta", nullable = false)
    private Integer pkConta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String password;
    @OneToMany(mappedBy = "fkConta")
    private Collection<Compra> compraCollection;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "pk_pessoa")
    @ManyToOne
    private Pessoa fkPessoa;
    @JoinColumn(name = "fk_tipo_conta", referencedColumnName = "pk_tipo_conta")
    @ManyToOne
    private TipoConta fkTipoConta;

    public Conta() {
    }

    public Conta(Integer pkConta) {
        this.pkConta = pkConta;
    }

    public Conta(Integer pkConta, String username, String password) {
        this.pkConta = pkConta;
        this.username = username;
        this.password = password;
    }

    public Integer getPkConta() {
        return pkConta;
    }

    public void setPkConta(Integer pkConta) {
        this.pkConta = pkConta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    public Pessoa getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(Pessoa fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    public TipoConta getFkTipoConta() {
        return fkTipoConta;
    }

    public void setFkTipoConta(TipoConta fkTipoConta) {
        this.fkTipoConta = fkTipoConta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkConta != null ? pkConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.pkConta == null && other.pkConta != null) || (this.pkConta != null && !this.pkConta.equals(other.pkConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Conta[ pkConta=" + pkConta + " ]";
    }
    
}
