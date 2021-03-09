package br.com.fiap.my.transport.onibus.api.repository;

import br.com.fiap.my.transport.onibus.api.entity.Linha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinhaRepository extends JpaRepository<Linha, Long> {

    Optional<Linha> findAllByCodigo(String codigo);
}
