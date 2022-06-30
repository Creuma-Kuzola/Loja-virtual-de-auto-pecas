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
    @NamedQuery(name = "Portfolio.findAll", query = "SELECT p FROM Portfolio p"),
    @NamedQuery(name = "Portfolio.findByPkPortfolio", query = "SELECT p FROM Portfolio p WHERE p.pkPortfolio = :pkPortfolio"),
    @NamedQuery(name = "Portfolio.findByDesignacao", query = "SELECT p FROM Portfolio p WHERE p.designacao = :designacao")})
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_portfolio", nullable = false, length = 2147483647)
    private String pkPortfolio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(mappedBy = "fkPortfolio")
    private Collection<Precario> precarioCollection;
    @OneToMany(mappedBy = "fkPortfolioPai")
    private Collection<Portfolio> portfolioCollection;
    @JoinColumn(name = "fk_portfolio_pai", referencedColumnName = "pk_portfolio")
    @ManyToOne
    private Portfolio fkPortfolioPai;
    @OneToMany(mappedBy = "fkPortfolio")
    private Collection<Montra> montraCollection;
    @OneToMany(mappedBy = "fkPortfolio")
    private Collection<CompraItem> compraItemCollection;
    @OneToMany(mappedBy = "fkPortfolio")
    private Collection<Stock> stockCollection;

    public Portfolio() {
    }

    public Portfolio(String pkPortfolio) {
        this.pkPortfolio = pkPortfolio;
    }

    public Portfolio(String pkPortfolio, String designacao) {
        this.pkPortfolio = pkPortfolio;
        this.designacao = designacao;
    }

    public String getPkPortfolio() {
        return pkPortfolio;
    }

    public void setPkPortfolio(String pkPortfolio) {
        this.pkPortfolio = pkPortfolio;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @XmlTransient
    public Collection<Precario> getPrecarioCollection() {
        return precarioCollection;
    }

    public void setPrecarioCollection(Collection<Precario> precarioCollection) {
        this.precarioCollection = precarioCollection;
    }

    @XmlTransient
    public Collection<Portfolio> getPortfolioCollection() {
        return portfolioCollection;
    }

    public void setPortfolioCollection(Collection<Portfolio> portfolioCollection) {
        this.portfolioCollection = portfolioCollection;
    }

    public Portfolio getFkPortfolioPai() {
        return fkPortfolioPai;
    }

    public void setFkPortfolioPai(Portfolio fkPortfolioPai) {
        this.fkPortfolioPai = fkPortfolioPai;
    }

    @XmlTransient
    public Collection<Montra> getMontraCollection() {
        return montraCollection;
    }

    public void setMontraCollection(Collection<Montra> montraCollection) {
        this.montraCollection = montraCollection;
    }

    @XmlTransient
    public Collection<CompraItem> getCompraItemCollection() {
        return compraItemCollection;
    }

    public void setCompraItemCollection(Collection<CompraItem> compraItemCollection) {
        this.compraItemCollection = compraItemCollection;
    }

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPortfolio != null ? pkPortfolio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portfolio)) {
            return false;
        }
        Portfolio other = (Portfolio) object;
        if ((this.pkPortfolio == null && other.pkPortfolio != null) || (this.pkPortfolio != null && !this.pkPortfolio.equals(other.pkPortfolio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Portfolio[ pkPortfolio=" + pkPortfolio + " ]";
    }
    
}
