package br.com.api.cartorio.controlers.forms;

import br.com.api.cartorio.dominios.Certidao;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CartorioForm {

    @NotNull @NotEmpty @Length(min = 8, max = 50)
    private String nome;

    @NotNull @NotEmpty @Length(min = 15, max = 60)
    private String endereco;

    @NotNull @NotEmpty
    private String certidoes;

//    @NotNull @NotEmpty
//    private List<CertidaoForm> certidoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCertidoes() {
        return certidoes;
    }

    public void setCertidoes(String certidoes) {
        this.certidoes = certidoes;
    }
}
