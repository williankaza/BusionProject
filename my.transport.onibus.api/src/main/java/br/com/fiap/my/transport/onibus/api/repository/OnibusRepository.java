package br.com.fiap.my.transport.onibus.api.repository;

import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnibusRepository extends JpaRepository<Onibus, Long> {
}
