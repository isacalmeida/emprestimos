package br.edu.unoesc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.edu.unoesc.model.Parcela;

public interface ParcelaService {

	// == crud default ==
	List<Parcela> getAll();
	
	Optional<Parcela> findOne(Long id);
	
	Parcela create(Parcela parcela);
	
	Parcela update(Parcela parcela);
	
	void delete(Long id);
	
	// == method query ==
	List<Parcela> findByVencimento(Date dataInicio, Date dataFim);
}
