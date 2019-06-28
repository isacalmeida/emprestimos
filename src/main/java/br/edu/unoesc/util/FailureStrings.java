package br.edu.unoesc.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FailureStrings {

	// == constants defaults ==
	public static final String ERRO_INCLUSAO = "Ocorreu algum erro na inclusão!";
	public static final String ERRO_EDICAO = "Ocorreu algum erro na edição!";
	public static final String ERRO_REMOCAO = "Ocorreu algum erro na remoção!";
	
	// == constants related ==
	public static final String FUNCIONARIO_RELACIONADO = "Funcionário com relacionamentos ativos!";
	public static final String EMPRESTIMO_RELACIONADO = "Empréstimo com relacionamentos ativos!";
	public static final String PARCELA_RELACIONADO = "Parcela com relacionamentos ativos!";
	
	// == constants specifics ==
	public static final String FUNCIONARIO_CPF = "O CPF informado já encontra-se cadastrado!";
	public static final String FUNCIONARIO_EMPRESTIMO = "Funcionário já possui um empréstimo ativo!";
	public static final String EMPRESTIMO_PARCELA = "Empréstimo com alguma parcela baixada!";
	public static final String EMPRESTIMO_VENCIMENTO = "Empréstimo com a data de vencimento menor que o dia atual!";
	public static final String PARCELA_BAIXADA = "Parcela já encontra-se baixada!";
	
}
