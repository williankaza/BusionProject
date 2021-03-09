package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.LinhaCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.LinhaDTO;
import br.com.fiap.my.transport.onibus.api.entity.Linha;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LinhaService {
    public LinhaDTO create(LinhaCreateUpdateDTO linhaCreateUpdateDTO);

    public LinhaDTO update(Long id, LinhaCreateUpdateDTO linhaCreateUpdateDTO);

    public void delete(Long id);

    public LinhaDTO desativarLinha(Long id);

    public List<LinhaDTO> findAll();

    public LinhaDTO findById(Long id);

    public Linha findLinhaById(Long id);
}
