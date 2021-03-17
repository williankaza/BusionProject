package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.LinhaDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusDTO;
import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.repository.OnibusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OnibusServiceImpl implements OnibusService {
    public OnibusRepository onibusRepository;
    public LinhaService linhaService;

    public OnibusServiceImpl(OnibusRepository onibusRepository, LinhaService linhaService){
        this.onibusRepository = onibusRepository;
        this.linhaService = linhaService;
    }

    @Override
    public OnibusDTO create(Long idLinha, OnibusCreateUpdateDTO onibusCreateUpdateDTO) {
        Linha linhaExistente = this.linhaService.findLinhaById(idLinha);
        Onibus novoOnibus = new Onibus(linhaExistente);
        novoOnibus.setCodigo( onibusCreateUpdateDTO.getCodigo() );
        novoOnibus.setAtivo( onibusCreateUpdateDTO.isAtivo() );

        Onibus onibusCriado = this.onibusRepository.save(novoOnibus);

        return new OnibusDTO(onibusCriado);
    }

    @Override
    public OnibusDTO update(Long idOnibus, OnibusCreateUpdateDTO onibusCreateUpdateDTO) {
        Onibus onibusExistente = this.findOnibusById(idOnibus);

        onibusExistente.setAtivo( onibusCreateUpdateDTO.isAtivo() );
        onibusExistente.setCodigo( onibusCreateUpdateDTO.getCodigo() );

        return new OnibusDTO( this.onibusRepository.save( onibusExistente ) );
    }

    @Override
    public void delete(Long idOnibus) {
        this.onibusRepository.deleteById(idOnibus);
    }

    @Override
    public OnibusDTO findById(Long idOnibus) {
        return new OnibusDTO( this.findOnibusById(idOnibus) );
    }

    @Override
    public List<OnibusDTO> findAllByLinha(Long idLinha) {
        return this.onibusRepository
                .findAll()
                .stream()
                .filter( onibus -> onibus.isAtivo() &&
                         onibus.getLinha().getId() == idLinha)
                .map( onibus -> new OnibusDTO(onibus) )
                .collect( Collectors.toList() );
    }

    @Override
    public Onibus findOnibusById(Long idOnibus){
        return this.onibusRepository
                .findById(idOnibus)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
