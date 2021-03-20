package br.com.fiap.my.transport.onibus.api.controller;

import br.com.fiap.my.transport.onibus.api.dto.LinhaCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.LinhaDTO;
import br.com.fiap.my.transport.onibus.api.service.LinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("linha")
public class LinhaController {
    private final LinhaService linhaService;

    public LinhaController(LinhaService linhaService) {
        this.linhaService = linhaService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody()
    public LinhaDTO create(@RequestBody LinhaCreateUpdateDTO linhaCreateUpdateDTO){
        return linhaService.create(linhaCreateUpdateDTO);
    }

    @PutMapping("{idLinha}")
    public LinhaDTO update(@PathVariable Long idLinha,
                           @RequestBody LinhaCreateUpdateDTO linhaCreateUpdateDTO ){
        return linhaService.update(idLinha, linhaCreateUpdateDTO);
    }

    @DeleteMapping("{idLinha}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idLinha){
        linhaService.delete(idLinha);
    }

    @GetMapping("")
    public List<LinhaDTO> getAll(){
        return linhaService.findAll();
    }

    @GetMapping("{idLinha}")
    public LinhaDTO getById(@PathVariable Long idLinha){
        return linhaService.findById(idLinha);
    }

}
