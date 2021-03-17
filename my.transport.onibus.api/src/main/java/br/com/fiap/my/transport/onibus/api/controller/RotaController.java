package br.com.fiap.my.transport.onibus.api.controller;

import br.com.fiap.my.transport.onibus.api.dto.RotaCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.RotaDTO;
import br.com.fiap.my.transport.onibus.api.service.RotaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("linha/{idLinha}/rota")
public class RotaController {
    private final RotaService rotaService;

    public RotaController( RotaService rotaService ){
        this.rotaService = rotaService;
    }

    @PostMapping("")
    @ResponseStatus( HttpStatus.CREATED )
    public RotaDTO create(@RequestBody RotaCreateUpdateDTO rotaCreateUpdateDTO,
                          @PathVariable Long idLinha){
        return this.rotaService.create(idLinha, rotaCreateUpdateDTO);
    }

    @PutMapping("{idRota}")
    public RotaDTO update(@RequestBody RotaCreateUpdateDTO rotaCreateUpdateDTO,
                          @PathVariable Long idLinha,
                          @PathVariable Long idRota){
        return this.rotaService.update( idRota, rotaCreateUpdateDTO );
    }

    @DeleteMapping("")
    public void delete(@PathVariable Long idRota){
        this.rotaService.delete(idRota);
    }

    @GetMapping("")
    public List<RotaDTO> getAllRotasByIdLinha(@PathVariable Long idLinha){
        return this.rotaService.findAllByLinha( idLinha );
    }

    @GetMapping("{idRota}")
    public RotaDTO getRotaById( @PathVariable Long idRota ){
        return this.rotaService.findById( idRota );
    }
}
