package br.edu.unoesc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.model.Emprestimo;
import br.edu.unoesc.model.Funcionario;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

	List<Emprestimo> findByFuncionarioAndAtivo(Funcionario funcionario, Boolean ativo);
}
