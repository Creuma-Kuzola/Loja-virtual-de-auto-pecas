/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByPkCompra", query = "SELECT c FROM Compra c WHERE c.pkCompra = :pkCompra"),
    @NamedQuery(name = "Compra.findByDataCompra", query = "SELECT c FROM Compra c WHERE c.dataCompra = :dataCompra")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_compra", nullable = false)
    private Integer pkCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_compra", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    @JoinColumn(name = "fk_conta", referencedColumnName = "pk_conta")
    @ManyToOne
    private Conta fkConta;
    @OneToMany(mappedBy = "fkCompra")
    private List<CompraItem> compraItemList;

    public Compra() {
    }

    public Compra(Integer pkCompra) {
        this.pkCompra = pkCompra;
    }

    public Compra(Integer pkCompra, Date dataCompra) {
        this.pkCompra = pkCompra;
        this.dataCompra = dataCompra;
    }

    public Integer getPkCompra() {
        return pkCompra;
    }

    public void setPkCompra(Integer pkCompra) {
        this.pkCompra = pkCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }

    @XmlTransient
    public List<CompraItem> getCompraItemList() {
        return compraItemList;
    }

    public void setCompraItemList(List<CompraItem> compraItemList) {
        this.compraItemList = compraItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCompra != null ? pkCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.pkCompra == null && other.pkCompra != null) || (this.pkCompra != null && !this.pkCompra.equals(other.pkCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Compra[ pkCompra=" + pkCompra + " ]";
    }
    
}
