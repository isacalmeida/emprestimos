package br.edu.unoesc.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelaFilter {

	// == fields ==
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFim;
	
}
