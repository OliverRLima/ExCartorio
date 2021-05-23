package br.com.api.cartorio.controlers;

import br.com.api.cartorio.controlers.forms.CertidaoForm;
import br.com.api.cartorio.dtos.CertidaoDto;
import br.com.api.cartorio.servicos.CertidaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/certidoes")
public class CertidaoController {

    @Autowired
    private CertidaoService certidaoService;

    @PostMapping("/{idCartorio}")
    public ResponseEntity salvarCertidao(@PathVariable Long idCartorio, @RequestBody @Valid CertidaoForm certidaoForm){
        CertidaoDto certidaoDto = certidaoService.salvarCertidao(idCartorio, certidaoForm);
        return certidaoDto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(certidaoDto);
    }

    @PutMapping("/{idCertidao}")
    public ResponseEntity alterarCertidao(@PathVariable Long idCertidao, @RequestBody @Valid CertidaoForm certidaoForm){
        Boolean alterado = certidaoService.alterarCertidao(idCertidao, certidaoForm);
        return !alterado ? ResponseEntity.badRequest().build() : ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idCertidao}")
    public ResponseEntity deletarCertidao(@PathVariable Long idCertidao){
        Boolean deletado = certidaoService.deletarCertidao(idCertidao);
        return !deletado ? ResponseEntity.badRequest().build() : ResponseEntity.ok().build();
    }
}
