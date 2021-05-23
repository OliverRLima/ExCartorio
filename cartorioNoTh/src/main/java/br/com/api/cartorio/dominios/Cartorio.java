package br.com.api.cartorio.dominios;

import br.com.api.cartorio.controlers.forms.CartorioForm;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cartorio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartorio;

    @NotNull
    private String nome;

    @NotNull
    private String endereco;

    @OneToMany(mappedBy = "cartorio")
    private List<Certidao> certidoes = new ArrayList();

    public Cartorio(String nome, String endereço) {
        this.nome = nome;
        this.endereco = endereço;
    }

    public Cartorio(CartorioForm cartorioForm) {
        this.nome = cartorioForm.getNome();
        this.endereco = cartorioForm.getEndereco();
        this.certidoes = cartorioForm.getCertidoes().stream().map(Certidao::new).collect(Collectors.toList());
    }

    public Cartorio() {

    }

    public Long getIdCartorio() {
        return idCartorio;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Certidao> getCertidoes() {
        return certidoes;
    }
}
