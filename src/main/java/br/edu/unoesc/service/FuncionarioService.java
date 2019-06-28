package br.edu.unoesc.service;

import java.util.List;
import java.util.Optional;

import br.edu.unoesc.model.Funcionario;

public interface FuncionarioService {

	// == crud default ==
	List<Funcionario> getAll();
	
	Optional<Funcionario> findOne(Long id);
	
	Funcionario create(Funcionario funcionario);
	
	Funcionario update(Funcionario funcionario);
	
	void delete(Long id);
}
