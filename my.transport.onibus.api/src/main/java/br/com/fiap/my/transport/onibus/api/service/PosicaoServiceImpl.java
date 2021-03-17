package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.controller.PosicaoController;
import br.com.fiap.my.transport.onibus.api.dto.PosicaoCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.PosicaoDTO;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.entity.Posicao;
import br.com.fiap.my.transport.onibus.api.repository.PosicaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosicaoServiceImpl implements PosicaoService {
    public PosicaoRepository posicaoRepository;
    public OnibusService onibusService;

    public PosicaoServiceImpl( PosicaoRepository posicaoRepository,
                              OnibusService onibusService){
        this.onibusService = onibusService;
        this.posicaoRepository = posicaoRepository;
    }

    @Override
    public PosicaoDTO create(Long idOnibus, PosicaoCreateUpdateDTO posicaoCreateUpdateDTO) {
        Onibus onibusExitente = this.onibusService.findOnibusById(idOnibus);
        Posicao novaPosicao = new Posicao(onibusExitente);

        novaPosicao.setDataAtualizacao( LocalDateTime.now() );
        novaPosicao.setLatitude( posicaoCreateUpdateDTO.getLatitude() );
        novaPosicao.setLongitude( posicaoCreateUpdateDTO.getLongitude() );
        novaPosicao.setLotacaoAtual( posicaoCreateUpdateDTO.getLotacaoAtual() );

        return new PosicaoDTO( this.posicaoRepository.save( novaPosicao ) );
    }

    @Override
    public PosicaoDTO update(Long idPosicao,
                             PosicaoCreateUpdateDTO posicaoCreateUpdateDTO) {
        Posicao posicaoExistente = this.findPosicaoById(idPosicao);

        posicaoExistente.setLatitude( posicaoCreateUpdateDTO.getLatitude() );
        posicaoExistente.setLongitude( posicaoCreateUpdateDTO.getLongitude() );
        posicaoExistente.setLotacaoAtual( posicaoCreateUpdateDTO.getLotacaoAtual() );

        return new PosicaoDTO( this.posicaoRepository.save(posicaoExistente) );
    }

    @Override
    public void delete(Long idPosicao) {
        this.posicaoRepository.delete( this.findPosicaoById(idPosicao) );
    }

    @Override
    public PosicaoDTO findById(Long idPosicao) {
        return new PosicaoDTO( this.findPosicaoById( idPosicao ) );
    }

    @Override
    public List<PosicaoDTO> findAllByOnibus(Long idOnibus) {
        return this.posicaoRepository.findAll().stream()
                .filter(posicao -> posicao.getOnibus().getId() == idOnibus)
                .map( posicao -> new PosicaoDTO(posicao) )
                .collect( Collectors.toList() );
    }

    @Override
    public Posicao findPosicaoById(Long idPosicao) {
        return this.posicaoRepository.findById( idPosicao )
                .orElseThrow( ()-> new ResponseStatusException( HttpStatus.NOT_FOUND ));
    }
}
