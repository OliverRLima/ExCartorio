package br.com.api.cartorio.servicos;

import br.com.api.cartorio.controlers.forms.CartorioAlteradoForm;
import br.com.api.cartorio.controlers.forms.CartorioForm;
import br.com.api.cartorio.controlers.forms.CertidaoForm;
import br.com.api.cartorio.dominios.Cartorio;
import br.com.api.cartorio.dominios.Certidao;
import br.com.api.cartorio.dtos.CartorioCompletoDto;
import br.com.api.cartorio.dtos.CartorioDto;
import br.com.api.cartorio.repositorios.CartorioRepository;
import br.com.api.cartorio.repositorios.CertidaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartorioService {
    
    @Autowired
    private CartorioRepository cartorioRepository;

    @Autowired
    private CertidaoRepository certidaoRepository;

    public CartorioDto salvarCartorio(CartorioForm cartorioForm) {
        Cartorio cartorioPersistido = cartorioRepository.save(new Cartorio(cartorioForm));
        persistirCertidoes(cartorioForm, cartorioPersistido);
        return new CartorioDto(cartorioPersistido);
    }

    private void persistirCertidoes(CartorioForm cartorioForm, Cartorio cartorioPersistido) {
        List<Certidao> certidoes = new ArrayList();
        CertidaoForm certidaoForm = new CertidaoForm();
        certidaoForm.setNome(cartorioForm.getCertidoes());
        certidoes.add(new Certidao(certidaoForm, cartorioPersistido));
//        cartorioForm.getCertidoes().stream().forEach(certidaoForm -> certidoes.add(new Certidao(certidaoForm, cartorioPersistido)));
        certidaoRepository.saveAll(certidoes);
    }

    public List<CartorioDto> recuperarTodosCartorios() {
        List<Cartorio> cartorios = cartorioRepository.findAll();
        return cartorios.stream().map(CartorioDto::new).collect(Collectors.toList());
    }

    public CartorioCompletoDto recurperarCartorioPorId(Long idCartorio) {
        Optional<Cartorio> cartorio = cartorioRepository.findById(idCartorio);
        return cartorio.isEmpty() ? null : new CartorioCompletoDto(cartorio.get());
    }

    public Boolean deletarCartorioPorId(Long idCartorio) {
        boolean exists = cartorioRepository.existsById(idCartorio);
        if (!exists){
            return false; 
        }
        certidaoRepository.deleteAllByCartorio_IdCartorio(idCartorio);
        cartorioRepository.deleteById(idCartorio);
        return true;
    }

    public Boolean alterarCartorioPorId(Long idCartorio, CartorioAlteradoForm cartorio) {
        boolean exists = cartorioRepository.existsById(idCartorio);
        if (!exists) return false;
        cartorioRepository.alterarRegistroPorId(cartorio.getNome(), cartorio.getEndereco(), idCartorio);
        return true;
    }
}
