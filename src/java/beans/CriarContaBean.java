/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Conta;
import ejbs.entities.Localidade;
import ejbs.entities.Pessoa;
import ejbs.entities.Sexo;
import ejbs.entities.TipoConta;
import ejbs.facades.ContaFacade;
import ejbs.facades.LocalidadeFacade;
import ejbs.facades.PessoaFacade;
import ejbs.facades.SexoFacade;
import ejbs.facades.TipoContaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author creuma
 */
@Named(value = "criarContaBean")
@ViewScoped
public class CriarContaBean implements Serializable {

    @EJB
    private LocalidadeFacade localidadeFacade;

    @EJB
    private ContaFacade contaFacade;

    @EJB
    private SexoFacade sexoFacade;

    @EJB
    private TipoContaFacade tipoContaFacade;
    
    @EJB
    private PessoaFacade pessoaFacade;

    private Localidade localidade;
    private Pessoa pessoa;
    private Conta conta;
    private Sexo sexo;
    private TipoConta tipoConta;

    private List<Localidade> listaPaises;
    private List<Localidade> listaProvincias;
    private List<Localidade> listaMunicipios;
    private List<Localidade> listaDistritos;
    private List<Sexo> listaSexo;
    private List<TipoConta> listaTipoConta;

    private String pais;
    private String provincia;
    private String municipio;
    private String distrito;

    @PostConstruct
    public void init() {

        listOFSexos();
        listOfTipoConta();

        sexo = new Sexo();
        pessoa = new Pessoa();
        conta = new Conta();
        localidade = new Localidade();
        tipoConta = new TipoConta();

        listaPaises = localidadeFacade.findAllPaises();
        listaProvincias = localidadeFacade.findAllProvinciasByParent(listaPaises.get(0).getPkLocalidade());
        listaMunicipios = localidadeFacade.findAllMunicipiosByParent(listaProvincias.get(0).getPkLocalidade());
        listaDistritos = localidadeFacade.findAllDistritosByParent(listaMunicipios.get(0).getPkLocalidade());
        
        pais = listaPaises.get(0).getPkLocalidade();
        provincia = listaProvincias.get(0).getPkLocalidade();
        municipio = listaMunicipios.get(0).getPkLocalidade();
        distrito = listaDistritos.get(0).getPkLocalidade();

    }

    /**
     * Creates a new instance of CriarContaBean
     */
    public CriarContaBean() {
    }

    //Negócio
    public void listOFSexos() {

        listaSexo = sexoFacade.findAll();

    }

    public void listOfTipoConta() {
        listaTipoConta = tipoContaFacade.findAll();
    }

    public void updateProvincias() {

        listaProvincias = localidadeFacade.findAllProvinciasByParent(pais);
        provincia = listaProvincias.get(0).getPkLocalidade();
        updateMunicipios();

    }

    public void updateMunicipios() {
        listaMunicipios = localidadeFacade.findAllMunicipiosByParent(provincia);
        municipio = listaMunicipios.get(0).getPkLocalidade();
        updateDistritos();

    }

    public void updateDistritos() {

        listaDistritos = localidadeFacade.findAllDistritosByParent(municipio);

    }
    
    public void salvarPessoa(){
        
        pessoa.setFkSexo(sexo);
        pessoa.setFkLocalidade(new Localidade(distrito));
        pessoaFacade.create(pessoa);
        
    }

    public void salvarConta() {

        salvarPessoa();
        
        pessoa = pessoaFacade.getPessoByEmail(pessoa.getEmail());
        
        System.out.println("Pessoa " + pessoa);
        conta.setFkPessoa(pessoa);
        conta.setFkTipoConta(tipoConta);
        
        
        contaFacade.create(conta);
        pessoa = new Pessoa();
        conta = new Conta();
        
        /*System.out.println("Pais" + pais + " provincia " + provincia + " municipio "
                + municipio + " distrito " + distrito);
        System.out.println("Sexo" + sexo.getPkSexo());
        System.out.println("nome" + pessoa.getNome() + "email" + pessoa.getEmail()
                + "dtnasc " + pessoa.getDataNascimento());
        System.out.println("tipo conta"+ tipoConta.getPkTipoConta());
        System.out.println("Username "+ conta.getUsername() + "Password"+ conta.getPassword());*/

    }

    //getters e setters
    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Localidade> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(List<Localidade> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public List<Localidade> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<Localidade> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<Localidade> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<Localidade> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List<Localidade> getListaDistritos() {
        return listaDistritos;
    }

    public void setListaDistritos(List<Localidade> listaDistritos) {
        this.listaDistritos = listaDistritos;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<Sexo> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<Sexo> listaSexo) {
        this.listaSexo = listaSexo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public List<TipoConta> getListaTipoConta() {
        return listaTipoConta;
    }

    public void setListaTipoConta(List<TipoConta> listaTipoConta) {
        this.listaTipoConta = listaTipoConta;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

}
