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
    @NamedQuery(name = "Localidade.findAll", query = "SELECT l FROM Localidade l"),
    @NamedQuery(name = "Localidade.findByPkLocalidade", query = "SELECT l FROM Localidade l WHERE l.pkLocalidade = :pkLocalidade"),
    @NamedQuery(name = "Localidade.findByNome", query = "SELECT l FROM Localidade l WHERE l.nome = :nome")})
public class Localidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_localidade", nullable = false, length = 2147483647)
    private String pkLocalidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nome;
    @OneToMany(mappedBy = "fkLocalidade")
    private Collection<Pessoa> pessoaCollection;
    @OneToMany(mappedBy = "fkLocalidadePai")
    private Collection<Localidade> localidadeCollection;
    @JoinColumn(name = "fk_localidade_pai", referencedColumnName = "pk_localidade")
    @ManyToOne
    private Localidade fkLocalidadePai;

    public Localidade() {
    }

    public Localidade(String pkLocalidade) {
        this.pkLocalidade = pkLocalidade;
    }

    public Localidade(String pkLocalidade, String nome) {
        this.pkLocalidade = pkLocalidade;
        this.nome = nome;
    }

    public String getPkLocalidade() {
        return pkLocalidade;
    }

    public void setPkLocalidade(String pkLocalidade) {
        this.pkLocalidade = pkLocalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    @XmlTransient
    public Collection<Localidade> getLocalidadeCollection() {
        return localidadeCollection;
    }

    public void setLocalidadeCollection(Collection<Localidade> localidadeCollection) {
        this.localidadeCollection = localidadeCollection;
    }

    public Localidade getFkLocalidadePai() {
        return fkLocalidadePai;
    }

    public void setFkLocalidadePai(Localidade fkLocalidadePai) {
        this.fkLocalidadePai = fkLocalidadePai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLocalidade != null ? pkLocalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidade)) {
            return false;
        }
        Localidade other = (Localidade) object;
        if ((this.pkLocalidade == null && other.pkLocalidade != null) || (this.pkLocalidade != null && !this.pkLocalidade.equals(other.pkLocalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Localidade[ pkLocalidade=" + pkLocalidade + " ]";
    }
    
}
