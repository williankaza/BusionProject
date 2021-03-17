package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.PosicaoCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.PosicaoDTO;
import br.com.fiap.my.transport.onibus.api.entity.Posicao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PosicaoService {
    public PosicaoDTO create(Long idOnibus, PosicaoCreateUpdateDTO posicaoCreateUpdateDTO);

    public PosicaoDTO update(Long idPosicao, PosicaoCreateUpdateDTO posicaoCreateUpdateDTO);

    public void delete(Long idPosicao);

    public PosicaoDTO findById(Long idPosicao);

    public List<PosicaoDTO> findAllByOnibus(Long idOnibus);

    public Posicao findPosicaoById(Long idPosicao);
}
