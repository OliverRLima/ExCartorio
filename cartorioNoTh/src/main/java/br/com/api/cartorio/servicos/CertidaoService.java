package br.com.api.cartorio.servicos;

import br.com.api.cartorio.controlers.forms.CertidaoForm;
import br.com.api.cartorio.dominios.Cartorio;
import br.com.api.cartorio.dominios.Certidao;
import br.com.api.cartorio.dtos.CertidaoDto;
import br.com.api.cartorio.repositorios.CartorioRepository;
import br.com.api.cartorio.repositorios.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertidaoService {

    @Autowired
    private CertidaoRepository certidaoRepository;

    @Autowired
    private CartorioRepository cartorioRepository;

    public CertidaoDto salvarCertidao(Long idCartorio, CertidaoForm certidaoForm) {
        Optional<Cartorio> cartorio = cartorioRepository.findById(idCartorio);
        if (cartorio.isEmpty()) return null;
        Certidao certidaoPersistida = certidaoRepository.save(new Certidao(certidaoForm, cartorio.get()));
        return new CertidaoDto(certidaoPersistida);
    }

    public Boolean alterarCertidao(Long idCertidao, CertidaoForm certidaoForm) {
        boolean exists = certidaoRepository.existsById(idCertidao);
        if (!exists) return false;
        certidaoRepository.alterarRegistroPorId(certidaoForm.getNome(), idCertidao);
        return true;
    }

    public Boolean deletarCertidao(Long idCertidao) {
        boolean exists = certidaoRepository.existsById(idCertidao);
        if (!exists) return false;
        certidaoRepository.deleteById(idCertidao);
        return true;
    }
}
