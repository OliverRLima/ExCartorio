package br.com.api.cartorio.repositorios;

import br.com.api.cartorio.dominios.Certidao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CertidaoRepository extends JpaRepository<Certidao, Long> {

    @Transactional
    @Modifying
    @Query("delete from Certidao c where c.cartorio.idCartorio = ?1")
    void deleteAllByCartorio_IdCartorio(Long idCartorio);

    @Transactional
    @Modifying
    @Query("update Certidao c set c.nome = ?1 where c.idCertidao = ?2")
    void alterarRegistroPorId(String nome, Long idCertidao);
}
