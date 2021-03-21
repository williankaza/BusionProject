package br.com.fiap.my.transport.onibus.api.repository;

import br.com.fiap.my.transport.onibus.api.entity.Posicao;
import br.com.fiap.my.transport.onibus.api.entity.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PosicaoRepository extends JpaRepository<Posicao, Long> {

    @Query("from Posicao " +
            "where id_onibus = :idOnibus")
    List<Posicao> buscaPosicaoPorOnibus(@Param("idOnibus") Long idOnibus);
}
