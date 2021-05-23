package br.com.api.cartorio.dtos;

import br.com.api.cartorio.dominios.Cartorio;

import java.util.List;
import java.util.stream.Collectors;

public class CartorioCompletoDto {

    private Long id;
    private String nome;
    private String endereco;
    private List<CertidaoDto> certidoes;

    public CartorioCompletoDto(Cartorio cartorioPersistido) {
        this.id = cartorioPersistido.getIdCartorio();
        this.nome = cartorioPersistido.getNome();
        this.endereco = cartorioPersistido.getEndereco();
        this.certidoes = cartorioPersistido.getCertidoes().stream().map(CertidaoDto::new).collect(Collectors.toList());
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

    public List<CertidaoDto> getCertidoes() {
        return certidoes;
    }
}
