package br.com.fiap.my.transport.onibus.api.repository;

import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.entity.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RotaRepository extends JpaRepository<Rota, Long> {

    @Query("from Rota " +
            "where id_linha = :idLinha")
    List<Rota> buscaRotaPorLinha(@Param("idLinha") Long idLinha);
}
