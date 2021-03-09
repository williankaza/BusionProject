package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.OnibusCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusDTO;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OnibusService {
    public OnibusDTO create(Long idLinha, OnibusCreateUpdateDTO onibusCreateUpdateDTO);

    public OnibusDTO update(Long idOnibus, OnibusCreateUpdateDTO onibusCreateUpdateDTO);

    public void delete(Long idOnibus);

    public OnibusDTO findById(Long idOnibus);

    public List<OnibusDTO> findAllByLinha(Long idLinha);

    public Onibus findOnibusById(Long idOnibus);
}
