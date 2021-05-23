package br.com.api.cartorio.controlers;

import br.com.api.cartorio.controlers.forms.CartorioAlteradoForm;
import br.com.api.cartorio.controlers.forms.CartorioForm;
import br.com.api.cartorio.dtos.CartorioCompletoDto;
import br.com.api.cartorio.dtos.CartorioDto;
import br.com.api.cartorio.servicos.CartorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cartorios")
public class CartorioController {

    @Autowired
    private CartorioService cartorioService;

    @GetMapping
    public ResponseEntity recuperarTodosCartorios(Model model) {
        List<CartorioDto> cartorios = cartorioService.recuperarTodosCartorios();
        return cartorios.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(cartorios);
    }

    @GetMapping("/{idCartorio}")
    public ResponseEntity recuperarCartorio(@PathVariable Long idCartorio){
        CartorioCompletoDto cartorio = cartorioService.recurperarCartorioPorId(idCartorio);
        return cartorio == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(cartorio);
    }

    @DeleteMapping("/deletar/{idCartorio}")
    public String deletarCartorio(@PathVariable Long idCartorio){
        cartorioService.deletarCartorioPorId(idCartorio);
        return "redirect:/cartorios";
    }

    @PostMapping()
    public ResponseEntity salvarCartorio(@RequestBody @Valid CartorioForm cartorio){
        CartorioDto cartorioPersistido = cartorioService.salvarCartorio(cartorio);
        return cartorioPersistido == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(cartorio);
    }

    @PutMapping("/{idCartorio}")
    public ResponseEntity alterarCartorio(@PathVariable Long idCartorio, @RequestBody @Valid CartorioAlteradoForm cartorio){
        Boolean alterado = cartorioService.alterarCartorioPorId(idCartorio, cartorio);
        return !alterado ? ResponseEntity.badRequest().build() : ResponseEntity.ok(cartorio);
    }
}
