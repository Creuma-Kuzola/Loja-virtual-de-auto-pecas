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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author creuma
 */
@Entity
@Table(name = "compra_item", catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraItem.findAll", query = "SELECT c FROM CompraItem c"),
    @NamedQuery(name = "CompraItem.findByPkCompraItem", query = "SELECT c FROM CompraItem c WHERE c.pkCompraItem = :pkCompraItem"),
    @NamedQuery(name = "CompraItem.findByQuantidadeProduto", query = "SELECT c FROM CompraItem c WHERE c.quantidadeProduto = :quantidadeProduto")})
public class CompraItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_compra_item", nullable = false)
    private Integer pkCompraItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_produto", nullable = false)
    private int quantidadeProduto;
    @JoinColumn(name = "fk_compra", referencedColumnName = "pk_compra")
    @ManyToOne
    private Compra fkCompra;
    @JoinColumn(name = "fk_portfolio", referencedColumnName = "pk_portfolio")
    @ManyToOne
    private Portfolio fkPortfolio;
    @JoinColumn(name = "fk_precario", referencedColumnName = "pk_precario")
    @ManyToOne
    private Precario fkPrecario;

    public CompraItem() {
    }

    public CompraItem(Integer pkCompraItem) {
        this.pkCompraItem = pkCompraItem;
    }

    public CompraItem(Integer pkCompraItem, int quantidadeProduto) {
        this.pkCompraItem = pkCompraItem;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Integer getPkCompraItem() {
        return pkCompraItem;
    }

    public void setPkCompraItem(Integer pkCompraItem) {
        this.pkCompraItem = pkCompraItem;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Compra getFkCompra() {
        return fkCompra;
    }

    public void setFkCompra(Compra fkCompra) {
        this.fkCompra = fkCompra;
    }

    public Portfolio getFkPortfolio() {
        return fkPortfolio;
    }

    public void setFkPortfolio(Portfolio fkPortfolio) {
        this.fkPortfolio = fkPortfolio;
    }

    public Precario getFkPrecario() {
        return fkPrecario;
    }

    public void setFkPrecario(Precario fkPrecario) {
        this.fkPrecario = fkPrecario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCompraItem != null ? pkCompraItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraItem)) {
            return false;
        }
        CompraItem other = (CompraItem) object;
        if ((this.pkCompraItem == null && other.pkCompraItem != null) || (this.pkCompraItem != null && !this.pkCompraItem.equals(other.pkCompraItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.CompraItem[ pkCompraItem=" + pkCompraItem + " ]";
    }
    
}
