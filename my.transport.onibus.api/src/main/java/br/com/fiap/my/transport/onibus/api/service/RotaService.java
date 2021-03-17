package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.RotaCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.RotaDTO;
import br.com.fiap.my.transport.onibus.api.entity.Rota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RotaService {
    public RotaDTO create(Long idLinha, RotaCreateUpdateDTO rotaCreateUpdateDTO);

    public RotaDTO update(Long idRota, RotaCreateUpdateDTO rotaCreateUpdateDTO);

    public void delete(Long idRota);

    public RotaDTO findById(Long idRota);

    public List<RotaDTO> findAllByLinha(Long idLinha);

    public Rota findRotaById(Long idRota);
}
