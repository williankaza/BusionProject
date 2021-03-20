package br.com.fiap.my.transport.onibus.api.service;

import br.com.fiap.my.transport.onibus.api.dto.LinhaCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.LinhaDTO;
import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.repository.LinhaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LinhaServiceImpl implements LinhaService {
    private final Logger logger = LoggerFactory.getLogger(LinhaServiceImpl.class);
    private LinhaRepository linhaRepository;

    public LinhaServiceImpl(LinhaRepository linhaRepository){
        this.linhaRepository = linhaRepository;
    }


    @Override
    public LinhaDTO create(LinhaCreateUpdateDTO linhaCreateUpdateDTO) {
        Linha linhaExistente = this.findLinhaByCodigo(linhaCreateUpdateDTO.getCodigoLinha());
        if (linhaExistente != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A linha informada ja existe!");
        }

        Linha novaLinha = new Linha();
        novaLinha.setCodigo(linhaCreateUpdateDTO.getCodigoLinha());
        novaLinha.setAtivo(linhaCreateUpdateDTO.isAtivo());

        Linha linhaCriada = this.linhaRepository.save(novaLinha);
        return new LinhaDTO(linhaCriada);

    }

    @Override
    public LinhaDTO update(Long id, LinhaCreateUpdateDTO linhaCreateUpdateDTO) {
        Linha linhaAlterada = findLinhaById(id);

        linhaAlterada.setAtivo(linhaCreateUpdateDTO.isAtivo());
        linhaAlterada.setCodigo(linhaCreateUpdateDTO.getCodigoLinha());

        return new LinhaDTO(this.linhaRepository.save(linhaAlterada));
    }

    @Override
    public void delete(Long id) {
        this.linhaRepository.deleteById(id);
    }

    @Override
    public LinhaDTO desativarLinha(Long id) {
        return null;
    }

    @Override
    public List<LinhaDTO> findAll() {
        return this.linhaRepository
                .findAll()
                .stream()
                .map(linha -> new LinhaDTO(linha))
                .collect(Collectors.toList());
    }

    @Override
    public LinhaDTO findById(Long id) {
        Linha linhaEncontrada = findLinhaById(id);
        return new LinhaDTO(linhaEncontrada);
    }

    @Override
    public Linha findLinhaById(Long id){
        return this.linhaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public Linha findLinhaByCodigo(String codigo){
        Linha linhaExistente = this.linhaRepository
                .findAllByCodigo(codigo)
                .orElse(null);
        return linhaExistente;
    }
}
