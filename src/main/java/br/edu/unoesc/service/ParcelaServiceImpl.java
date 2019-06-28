package br.edu.unoesc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.model.Parcela;
import br.edu.unoesc.repository.ParcelaRepository;

@Service
public class ParcelaServiceImpl implements ParcelaService{
	
	// == fields ==
	private ParcelaRepository parcelaRepository;
	
	// == constructors ==
	@Autowired
	public ParcelaServiceImpl(ParcelaRepository parcelaRepository) {
		this.parcelaRepository = parcelaRepository;
	}

	// == implement methods ==
	public List<Parcela> getAll(){
		return parcelaRepository.findAll();
	}

	public Optional<Parcela> findOne(Long id) {
		return parcelaRepository.findById(id);
	}

	public Parcela create(Parcela parcela) {
		return parcelaRepository.save(parcela);
	}

	public Parcela update(Parcela parcela) {
		return parcelaRepository.save(parcela);
	}

	public void delete(Long id) {
		parcelaRepository.deleteById(id);
	}
	
	public List<Parcela> findByVencimento(Date dataInicio, Date dataFim){
		return parcelaRepository.findByVencimento(dataInicio, dataFim);
	}
	
}
