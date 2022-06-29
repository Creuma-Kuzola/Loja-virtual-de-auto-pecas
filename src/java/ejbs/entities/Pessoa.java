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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author creuma
 */
@Entity
@Table(catalog = "ucandb", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByPkPessoa", query = "SELECT p FROM Pessoa p WHERE p.pkPessoa = :pkPessoa"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email"),
    @NamedQuery(name = "Pessoa.findByDataNascimento", query = "SELECT p FROM Pessoa p WHERE p.dataNascimento = :dataNascimento")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pessoa", nullable = false)
    private Integer pkPessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @JoinColumn(name = "fk_localidade", referencedColumnName = "pk_localidade")
    @ManyToOne
    private Localidade fkLocalidade;
    @JoinColumn(name = "fk_sexo", referencedColumnName = "pk_sexo")
    @ManyToOne
    private Sexo fkSexo;
    @OneToMany(mappedBy = "fkPessoa")
    private List<Conta> contaList;

    public Pessoa() {
    }

    public Pessoa(Integer pkPessoa) {
        this.pkPessoa = pkPessoa;
    }

    public Pessoa(Integer pkPessoa, String nome, String email, Date dataNascimento) {
        this.pkPessoa = pkPessoa;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Integer getPkPessoa() {
        return pkPessoa;
    }

    public void setPkPessoa(Integer pkPessoa) {
        this.pkPessoa = pkPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Localidade getFkLocalidade() {
        return fkLocalidade;
    }

    public void setFkLocalidade(Localidade fkLocalidade) {
        this.fkLocalidade = fkLocalidade;
    }

    public Sexo getFkSexo() {
        return fkSexo;
    }

    public void setFkSexo(Sexo fkSexo) {
        this.fkSexo = fkSexo;
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
