package br.com.api.cartorio.repositorios;

import br.com.api.cartorio.dominios.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CartorioRepository extends JpaRepository<Cartorio, Long> {

    @Transactional
    @Modifying
    @Query("update Cartorio c set c.nome = ?1, c.endereco = ?2 where c.idCartorio = ?3")
    void alterarRegistroPorId(String nome, String endereco, Long idCartorio);
}
