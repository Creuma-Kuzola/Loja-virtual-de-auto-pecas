/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author creuma
 */
@Entity
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Montra.findAll", query = "SELECT m FROM Montra m"),
    @NamedQuery(name = "Montra.findByPkMontra", query = "SELECT m FROM Montra m WHERE m.pkMontra = :pkMontra"),
    @NamedQuery(name = "Montra.findByDataMontra", query = "SELECT m FROM Montra m WHERE m.dataMontra = :dataMontra"),
    @NamedQuery(name = "Montra.findByFkPortfolio", query = "SELECT m FROM Montra m WHERE m.fkPortfolio = :fkPortfolio")})
public class Montra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_montra", nullable = false)
    private Integer pkMontra;
    @Column(name = "data_montra")
    @Temporal(TemporalType.DATE)
    private Date dataMontra;
    @Column(name = "fk_portfolio")
    private Integer fkPortfolio;

    public Montra() {
    }

    public Montra(Integer pkMontra) {
        this.pkMontra = pkMontra;
    }

    public Integer getPkMontra() {
        return pkMontra;
    }

    public void setPkMontra(Integer pkMontra) {
        this.pkMontra = pkMontra;
    }

    public Date getDataMontra() {
        return dataMontra;
    }

    public void setDataMontra(Date dataMontra) {
        this.dataMontra = dataMontra;
    }

    public Integer getFkPortfolio() {
        return fkPortfolio;
    }

    public void setFkPortfolio(Integer fkPortfolio) {
        this.fkPortfolio = fkPortfolio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkMontra != null ? pkMontra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Montra)) {
            return false;
        }
        Montra other = (Montra) object;
        if ((this.pkMontra == null && other.pkMontra != null) || (this.pkMontra != null && !this.pkMontra.equals(other.pkMontra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Montra[ pkMontra=" + pkMontra + " ]";
    }
    
}
