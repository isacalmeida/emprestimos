package br.edu.unoesc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.model.Funcionario;
import br.edu.unoesc.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
	
	// == fields ==
	private FuncionarioRepository funcionarioRepository;
	
	// == constructors ==
	@Autowired
	public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	// == implement methods ==
	public List<Funcionario> getAll(){
		return funcionarioRepository.findAll();
	}

	public Optional<Funcionario> findOne(Long id) {
		return funcionarioRepository.findById(id);
	}

	public Funcionario create(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public Funcionario update(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public void delete(Long id) {
		funcionarioRepository.deleteById(id);
	}
	
}
