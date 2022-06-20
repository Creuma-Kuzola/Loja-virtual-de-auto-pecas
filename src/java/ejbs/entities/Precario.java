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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Precario.findAll", query = "SELECT p FROM Precario p"),
    @NamedQuery(name = "Precario.findByPkPrecario", query = "SELECT p FROM Precario p WHERE p.pkPrecario = :pkPrecario"),
    @NamedQuery(name = "Precario.findByDataPrecario", query = "SELECT p FROM Precario p WHERE p.dataPrecario = :dataPrecario"),
    @NamedQuery(name = "Precario.findByFkPortfolio", query = "SELECT p FROM Precario p WHERE p.fkPortfolio = :fkPortfolio")})
public class Precario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_precario", nullable = false)
    private Integer pkPrecario;
    @Column(name = "data_precario")
    @Temporal(TemporalType.DATE)
    private Date dataPrecario;
    @Column(name = "fk_portfolio")
    private Integer fkPortfolio;
    @OneToMany(mappedBy = "fkPrecario")
    private List<CompraItem> compraItemList;

    public Precario() {
    }

    public Precario(Integer pkPrecario) {
        this.pkPrecario = pkPrecario;
    }

    public Integer getPkPrecario() {
        return pkPrecario;
    }

    public void setPkPrecario(Integer pkPrecario) {
        this.pkPrecario = pkPrecario;
    }

    public Date getDataPrecario() {
        return dataPrecario;
    }

    public void setDataPrecario(Date dataPrecario) {
        this.dataPrecario = dataPrecario;
    }

    public Integer getFkPortfolio() {
        return fkPortfolio;
    }

    public void setFkPortfolio(Integer fkPortfolio) {
        this.fkPortfolio = fkPortfolio;
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
        hash += (pkPrecario != null ? pkPrecario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Precario)) {
            return false;
        }
        Precario other = (Precario) object;
        if ((this.pkPrecario == null && other.pkPrecario != null) || (this.pkPrecario != null && !this.pkPrecario.equals(other.pkPrecario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Precario[ pkPrecario=" + pkPrecario + " ]";
    }
    
}
