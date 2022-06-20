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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author creuma
 */
@Entity
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findByPkStock", query = "SELECT s FROM Stock s WHERE s.pkStock = :pkStock"),
    @NamedQuery(name = "Stock.findByQuantidadeMinima", query = "SELECT s FROM Stock s WHERE s.quantidadeMinima = :quantidadeMinima"),
    @NamedQuery(name = "Stock.findByQuantidadeMaxima", query = "SELECT s FROM Stock s WHERE s.quantidadeMaxima = :quantidadeMaxima"),
    @NamedQuery(name = "Stock.findByQuantidadeActual", query = "SELECT s FROM Stock s WHERE s.quantidadeActual = :quantidadeActual"),
    @NamedQuery(name = "Stock.findByFkPortfolio", query = "SELECT s FROM Stock s WHERE s.fkPortfolio = :fkPortfolio")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_stock", nullable = false)
    private Integer pkStock;
    @Size(max = 2147483647)
    @Column(name = "quantidade_minima", length = 2147483647)
    private String quantidadeMinima;
    @Size(max = 2147483647)
    @Column(name = "quantidade_maxima", length = 2147483647)
    private String quantidadeMaxima;
    @Size(max = 2147483647)
    @Column(name = "quantidade_actual", length = 2147483647)
    private String quantidadeActual;
    @Column(name = "fk_portfolio")
    private Integer fkPortfolio;

    public Stock() {
    }

    public Stock(Integer pkStock) {
        this.pkStock = pkStock;
    }

    public Integer getPkStock() {
        return pkStock;
    }

    public void setPkStock(Integer pkStock) {
        this.pkStock = pkStock;
    }

    public String getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(String quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(String quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public String getQuantidadeActual() {
        return quantidadeActual;
    }

    public void setQuantidadeActual(String quantidadeActual) {
        this.quantidadeActual = quantidadeActual;
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
        hash += (pkStock != null ? pkStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.pkStock == null && other.pkStock != null) || (this.pkStock != null && !this.pkStock.equals(other.pkStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Stock[ pkStock=" + pkStock + " ]";
    }
    
}
