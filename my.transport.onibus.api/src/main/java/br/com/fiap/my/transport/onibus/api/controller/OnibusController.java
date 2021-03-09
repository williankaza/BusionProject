package br.com.fiap.my.transport.onibus.api.controller;


import br.com.fiap.my.transport.onibus.api.dto.OnibusCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusDTO;
import br.com.fiap.my.transport.onibus.api.service.OnibusService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("onibus")
public class OnibusController {
    private final OnibusService onibusService;

    public OnibusController(OnibusService onibusService){
        this.onibusService = onibusService;
    }

    @PostMapping("{idLinha}")
    @ResponseStatus( HttpStatus.CREATED)
    public List<OnibusDTO> create(@RequestBody List<OnibusCreateUpdateDTO> onibusCreateUpdateDTOS,
                                  @PathVariable Long idLinha){
        List<OnibusDTO> onibusDTOS = new ArrayList<>();

        for (int i = 0; i < onibusCreateUpdateDTOS.size(); i++) {
            OnibusDTO onibusDTO = this.onibusService.create(idLinha, onibusCreateUpdateDTOS.get(i));
            onibusDTOS.add(onibusDTO);
        }

        return onibusDTOS;
    }
}
