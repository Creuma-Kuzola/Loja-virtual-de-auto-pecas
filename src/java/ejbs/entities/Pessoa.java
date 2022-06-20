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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByPkPessoa", query = "SELECT p FROM Pessoa p WHERE p.pkPessoa = :pkPessoa"),
    @NamedQuery(name = "Pessoa.findByFkLocalidade", query = "SELECT p FROM Pessoa p WHERE p.fkLocalidade = :fkLocalidade"),
    @NamedQuery(name = "Pessoa.findByDataNascimento", query = "SELECT p FROM Pessoa p WHERE p.dataNascimento = :dataNascimento")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_pessoa", nullable = false)
    private Integer pkPessoa;
    @Size(max = 2147483647)
    @Column(name = "fk_localidade", length = 2147483647)
    private String fkLocalidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @OneToMany(mappedBy = "fkPessoa")
    private List<Conta> contaList;

    public Pessoa() {
    }

    public Pessoa(Integer pkPessoa) {
        this.pkPessoa = pkPessoa;
    }

    public Pessoa(Integer pkPessoa, Date dataNascimento) {
        this.pkPessoa = pkPessoa;
        this.dataNascimento = dataNascimento;
    }

    public Integer getPkPessoa() {
        return pkPessoa;
    }

    public void setPkPessoa(Integer pkPessoa) {
        this.pkPessoa = pkPessoa;
    }

    public String getFkLocalidade() {
        return fkLocalidade;
    }

    public void setFkLocalidade(String fkLocalidade) {
        this.fkLocalidade = fkLocalidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @XmlTransient
    public List<Conta> getContaList() {
        return contaList;
    }

    public void setContaList(List<Conta> contaList) {
        this.contaList = contaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPessoa != null ? pkPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.pkPessoa == null && other.pkPessoa != null) || (this.pkPessoa != null && !this.pkPessoa.equals(other.pkPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Pessoa[ pkPessoa=" + pkPessoa + " ]";
    }
    
}
