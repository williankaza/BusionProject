package br.com.fiap.my.transport.onibus.api.repository;

import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OnibusRepository extends JpaRepository<Onibus, Long> {

    @Query("from Onibus " +
           "where id_linha = :idLinha")
    List<Onibus> buscaOnibusPorLinha(@Param("idLinha") Long idLinha);
}
