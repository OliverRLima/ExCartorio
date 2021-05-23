package br.com.api.cartorio.controlers.forms;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CertidaoForm {

    @NotEmpty @NotNull @Length(min = 10 , max = 50)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
