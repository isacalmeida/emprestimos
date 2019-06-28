package br.edu.unoesc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.model.Parcela;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long>{

	@Query("SELECT p FROM Parcela p WHERE p.vencimento >= ?1 AND p.vencimento <= ?2")
	List<Parcela> findByVencimento(Date dataInicio, Date dataFim);
}
