package br.com.fiap.my.transport.onibus.api.controller;

import br.com.fiap.my.transport.onibus.api.dto.PosicaoCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.PosicaoDTO;
import br.com.fiap.my.transport.onibus.api.service.PosicaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("linha/{idLinha}/onibus/{idOnibus}/posicao")
public class PosicaoController {
    private final PosicaoService posicaoService;
    public PosicaoController(PosicaoService posicaoService){
        this.posicaoService = posicaoService;
    }

    @PostMapping("")
    @ResponseStatus( HttpStatus.CREATED )
    public PosicaoDTO create(@RequestBody PosicaoCreateUpdateDTO posicaoCreateUpdateDTO,
                             @PathVariable Long idOnibus){
        return this.posicaoService.create( idOnibus, posicaoCreateUpdateDTO );
    }

    @PutMapping("{idPosicao}")
    public PosicaoDTO update(@RequestBody PosicaoCreateUpdateDTO posicaoCreateUpdateDTO,
                             @PathVariable Long idPosicao){
        return this.posicaoService.update(idPosicao, posicaoCreateUpdateDTO);
    }

    @DeleteMapping("{idPosicao}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idPosicao){
        this.posicaoService.delete(idPosicao);
    }

    @GetMapping("")
    public List<PosicaoDTO> findAllPosicaoByIdOnibus(@PathVariable Long idOnibus){
        return  this.posicaoService.findAllByOnibus(idOnibus);
    }

    @GetMapping("{idPosicao}")
    public PosicaoDTO findPosicaoById(@PathVariable Long idPosicao ){
        return this.posicaoService.findById(idPosicao);
    }
}
