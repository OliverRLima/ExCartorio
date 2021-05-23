package br.com.api.cartorio.dtos;

import br.com.api.cartorio.dominios.Certidao;

public class CertidaoDto {

    private Long id;
    private String nome;

    public CertidaoDto(Certidao certidao) {
        this.id = certidao.getIdCertidao();
        this.nome = certidao.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
