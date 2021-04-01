package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.LinhaDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusDTO;
import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.repository.LinhaRepository;
import br.com.fiap.my.transport.onibus.api.repository.OnibusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnibusServiceImpl implements OnibusService {
    private OnibusRepository onibusRepository;
    private LinhaRepository linhaRepository;
    private LinhaService linhaService;

    public OnibusServiceImpl(OnibusRepository onibusRepository, LinhaService linhaService, LinhaRepository linhaRepository){
        this.onibusRepository = onibusRepository;
        this.linhaRepository = linhaRepository;
        this.linhaService = linhaService;
    }

    @Override
    public OnibusDTO create(Long idLinha, OnibusCreateUpdateDTO onibusCreateUpdateDTO) {
        Linha linhaExistente = this.linhaRepository.findById(idLinha).orElse(null);

        if (linhaExistente == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A linha informada nao existe!");
        }

        Onibus novoOnibus = new Onibus(linhaExistente);
        novoOnibus.setCodigo( onibusCreateUpdateDTO.getCodigo() );
        novoOnibus.setAtivo( onibusCreateUpdateDTO.isAtivo() );

        Onibus onibusCriado = this.onibusRepository.save(novoOnibus);

        return new OnibusDTO(onibusCriado);
    }

    @Override
    public OnibusDTO update(Long idOnibus, OnibusCreateUpdateDTO onibusCreateUpdateDTO) {
        Onibus onibusExistente = this.findOnibusById(idOnibus);
        if (onibusExistente == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O onibus informado nao existe!");
        }

        onibusExistente.setAtivo( onibusCreateUpdateDTO.isAtivo() );
        onibusExistente.setCodigo( onibusCreateUpdateDTO.getCodigo() );

        return new OnibusDTO( this.onibusRepository.save( onibusExistente ) );
    }

    @Override
    public void delete(Long idOnibus) {
        Onibus onibusExistente = this.findOnibusById(idOnibus);
        if (onibusExistente == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O onibus informado nao existe!");
        }
        this.onibusRepository.deleteById(idOnibus);
    }

    @Override
    public OnibusDTO findById(Long idOnibus) {
        return new OnibusDTO( this.findOnibusById(idOnibus) );
    }

    @Override
    public List<OnibusDTO> findAllByLinha(Long idLinha) {
        return this.onibusRepository.buscaOnibusPorLinha(idLinha)
                .stream()
                .filter( onibus -> onibus.isAtivo())
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
