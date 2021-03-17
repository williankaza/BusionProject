package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.RotaCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.RotaDTO;
import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Rota;
import br.com.fiap.my.transport.onibus.api.repository.RotaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RotaServiceImpl implements RotaService {
    public RotaRepository rotaRepository;
    public LinhaService linhaService;

    public RotaServiceImpl( RotaRepository rotaRepository, LinhaService linhaService){
        this.rotaRepository = rotaRepository;
        this.linhaService = linhaService;
    }

    @Override
    public RotaDTO create(Long idLinha, RotaCreateUpdateDTO rotaCreateUpdateDTO) {
        Linha linhaExistente = this.linhaService.findLinhaById(idLinha);

        Rota novaRota = new Rota(linhaExistente);
        novaRota.setAtivo( rotaCreateUpdateDTO.isAtivo() );
        novaRota.setLatitude( rotaCreateUpdateDTO.getLatitude() );
        novaRota.setLongitude( rotaCreateUpdateDTO.getLongitude() );
        novaRota.setOrdem( rotaCreateUpdateDTO.getOrdem() );

        return new RotaDTO( this.rotaRepository.save(novaRota) );
    }

    @Override
    public RotaDTO update(Long idRota, RotaCreateUpdateDTO rotaCreateUpdateDTO) {
        Rota rotaExistente = this.findRotaById(idRota);

        rotaExistente.setOrdem( rotaCreateUpdateDTO.getOrdem() );
        rotaExistente.setLongitude( rotaCreateUpdateDTO.getLongitude() );
        rotaExistente.setLatitude( rotaCreateUpdateDTO.getLatitude() );
        rotaExistente.setAtivo( rotaCreateUpdateDTO.isAtivo() );
        return new RotaDTO( this.rotaRepository.save(rotaExistente) );
    }

    @Override
    public void delete(Long idRota) {
        Rota rotaExistente = this.findRotaById(idRota);
        this.rotaRepository.delete( rotaExistente );
    }

    @Override
    public RotaDTO findById(Long idRota) {
        return new RotaDTO( this.findRotaById(idRota) );
    }

    @Override
    public List<RotaDTO> findAllByLinha(Long idLinha) {
        return this.rotaRepository.findAll().stream()
                .filter(rota -> rota.isAtivo() &&
                        rota.getLinha().getId() == idLinha)
                .map( rota -> new RotaDTO(rota) )
                .collect( Collectors.toList() );
    }

    @Override
    public Rota findRotaById(Long idRota) {
        return this.rotaRepository
                .findById( idRota )
                .orElseThrow(()-> new ResponseStatusException( HttpStatus.NOT_FOUND ));
    }
}
