package br.edu.unoesc.service;

import java.util.List;
import java.util.Optional;

import br.edu.unoesc.model.Emprestimo;
import br.edu.unoesc.model.Funcionario;

public interface EmprestimoService {

	// == crud default ==
	List<Emprestimo> getAll();
	
	Optional<Emprestimo> findOne(Long id);
	
	Emprestimo create(Emprestimo emprestimo);
	
	Emprestimo update(Emprestimo emprestimo);
	
	void delete(Long id);
	
	// == method query ==
	List<Emprestimo> findAllFuncionarioAtivo(Funcionario funcionario, Boolean ativo);
}
