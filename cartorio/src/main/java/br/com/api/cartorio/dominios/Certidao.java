package br.com.api.cartorio.dominios;

import br.com.api.cartorio.controlers.forms.CartorioForm;
import br.com.api.cartorio.controlers.forms.CertidaoForm;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Certidao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertidao;

    @NotNull
    private String nome;

    @ManyToOne
    private Cartorio cartorio;

    public Certidao(String nome) {
        this.nome = nome;
    }

    public Certidao() {
    }

    public Certidao(CertidaoForm certidaoForm) {
        this.nome = certidaoForm.getNome();
    }

    public Certidao(CertidaoForm certidaoForm, Cartorio cartorio) {
        this.nome = certidaoForm.getNome();
        this.cartorio = cartorio;
    }

    public Long getIdCertidao() {
        return idCertidao;
    }

    public String getNome() {
        return nome;
    }
}
