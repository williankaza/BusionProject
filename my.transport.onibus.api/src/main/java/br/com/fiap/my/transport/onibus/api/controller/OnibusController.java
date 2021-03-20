package br.com.fiap.my.transport.onibus.api.controller;


import br.com.fiap.my.transport.onibus.api.dto.OnibusCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusDTO;
import br.com.fiap.my.transport.onibus.api.service.OnibusService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("linha/{idLinha}/onibus")
public class OnibusController {
    private final OnibusService onibusService;

    public OnibusController(OnibusService onibusService){
        this.onibusService = onibusService;
    }

    @PostMapping("")
    @ResponseStatus( HttpStatus.CREATED )
    public OnibusDTO create(@RequestBody OnibusCreateUpdateDTO onibusCreateUpdateDTOS,
                                  @PathVariable Long idLinha){
        OnibusDTO onibusDTO = this.onibusService.create(idLinha, onibusCreateUpdateDTOS);

        return onibusDTO;
    }

    @PutMapping("{idOnibus}")
    public OnibusDTO update(@RequestBody OnibusCreateUpdateDTO onibusCreateUpdateDTO,
                            @PathVariable Long idLinha,
                            @PathVariable Long idOnibus){
        OnibusDTO onibusDTO = this.onibusService.update(idOnibus, onibusCreateUpdateDTO);

        return onibusDTO;
    }

    @DeleteMapping("{idOnibus}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idLinha, @PathVariable Long idOnibus){
        this.onibusService.delete(idOnibus);
    }

    @GetMapping("")
    public List<OnibusDTO> getAllByIdLinha(@PathVariable Long idLinha){
        return this.onibusService.findAllByLinha(idLinha);
    }

    @GetMapping("{idOnibus}")
    public OnibusDTO getOnibusById(@PathVariable Long idOnibus){
        return this.onibusService.findById(idOnibus);
    }
}
