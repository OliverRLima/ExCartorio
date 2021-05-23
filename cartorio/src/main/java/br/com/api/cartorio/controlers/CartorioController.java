package br.com.api.cartorio.controlers;

import br.com.api.cartorio.controlers.forms.CartorioForm;
import br.com.api.cartorio.dtos.CartorioCompletoDto;
import br.com.api.cartorio.dtos.CartorioDto;
import br.com.api.cartorio.servicos.CartorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cartorios")
public class CartorioController {

    @Autowired
    private CartorioService cartorioService;

    @Bean
    @Description("Spring Message Resolver")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @GetMapping
    public String recuperarTodosCartorios(Model model) {
        List<CartorioDto> cartorios = cartorioService.recuperarTodosCartorios();

        if (cartorios.isEmpty()) {
            model.addAttribute("cartorios", null);
        } else {
            model.addAttribute("cartorios", cartorios);
        }

        return "index";
    }

    @GetMapping("/{id}")
    public String recuperarCartorio(@PathVariable Long id, Model model){
        CartorioCompletoDto cartorio = cartorioService.recurperarCartorioPorId(id);
        if (cartorio == null) {
            model.addAttribute("cartorio", null);
        } else {
            model.addAttribute("cartorio", cartorio);
        }

        return "cartorio";
    }

    @GetMapping("/formulario")
    public String cadastrarCartorio(Model model){
        return "formulario/cartorio";
    }

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String cadastrarCartorio(CartorioForm cartorio){
        CartorioDto cartorioPersistido = cartorioService.salvarCartorio(cartorio);
        return cartorioPersistido == null ? "formulario/cartorio" : "redirect:/cartorios";
    }

}
