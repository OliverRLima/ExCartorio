package br.com.api.cartorio.dtos;

import br.com.api.cartorio.dominios.Cartorio;

public class CartorioDto {

    private Long id;
    private String nome;
    private String endereco;

    public CartorioDto(Cartorio cartorioPersistido) {
        this.id = cartorioPersistido.getIdCartorio();
        this.nome = cartorioPersistido.getNome();
        this.endereco = cartorioPersistido.getEndereco();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
}
