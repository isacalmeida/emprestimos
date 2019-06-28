package br.edu.unoesc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.model.Emprestimo;
import br.edu.unoesc.model.Funcionario;
import br.edu.unoesc.repository.EmprestimoRepository;

@Service
public class EmprestimoServiceImpl implements EmprestimoService{
	
	// == fields ==
	private EmprestimoRepository emprestimoRepository;
	
	// == constructors ==
	@Autowired
	public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository) {
		this.emprestimoRepository = emprestimoRepository;
	}

	// == implement methods ==
	public List<Emprestimo> getAll(){
		return emprestimoRepository.findAll();
	}

	public Optional<Emprestimo> findOne(Long id) {
		return emprestimoRepository.findById(id);
	}

	public Emprestimo create(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}

	public Emprestimo update(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}

	public void delete(Long id) {
		emprestimoRepository.deleteById(id);
	}

	public List<Emprestimo> findAllFuncionarioAtivo(Funcionario funcionario, Boolean ativo) {
		return emprestimoRepository.findByFuncionarioAndAtivo(funcionario, ativo);
	}
	
}
