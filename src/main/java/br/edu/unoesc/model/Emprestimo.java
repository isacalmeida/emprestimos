package br.edu.unoesc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emprestimo {
	
	// == fields ==
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDaOperacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funcionario", foreignKey = @ForeignKey(name = "FK_emprestimo_funcionario"))
	private Funcionario funcionario;
	
	private Double valorEmprestimo;
	
	private Integer qtdParcelas;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPrimeiraParcela;
	
	@OneToMany(mappedBy = "emprestimo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Parcela> parcelas;
	
	private Boolean ativo;
}
