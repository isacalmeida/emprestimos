package br.edu.unoesc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.unoesc.filter.ParcelaFilter;
import br.edu.unoesc.model.Emprestimo;
import br.edu.unoesc.model.Parcela;
import br.edu.unoesc.service.EmprestimoService;
import br.edu.unoesc.service.ParcelaService;
import br.edu.unoesc.util.Constants;
import br.edu.unoesc.util.FailureStrings;
import br.edu.unoesc.util.SuccessStrings;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/parcela")
public class ParcelaController {

	// == fields ==
	private ParcelaService parcelaService;
	private EmprestimoService emprestimoService;
	
	// == constructors ==
	@Autowired
	public ParcelaController(ParcelaService parcelaService, EmprestimoService emprestimoService) {
		this.parcelaService = parcelaService;
		this.emprestimoService = emprestimoService;
	}
	
	// == public methods ==
	@GetMapping("")
	public ModelAndView home(Model model) {
		log.info("GetMapping /parcela/ on ParcelaController");
		
		model.addAttribute("parcelas", parcelaService.getAll());
		model.addAttribute("dataInicio", "");
		model.addAttribute("dataFim", "");
		return new ModelAndView("parcela/index", "parcelaFilter", new ParcelaFilter());
	}
	
	@GetMapping("index")
	public ModelAndView home(@ModelAttribute Integer status, @ModelAttribute String message, Model model) {
		log.info("GetMapping /parcela/ on ParcelaController");
		
		model.addAttribute("status", status);
		model.addAttribute("message", message);
		
		model.addAttribute("parcelas", parcelaService.getAll());
		model.addAttribute("dataInicio", "");
		model.addAttribute("dataFim", "");
		return new ModelAndView("parcela/index", "parcelaFilter", new ParcelaFilter());
	}
	
	@GetMapping("/{id}/baixar")
	public String baixar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		log.info("GetMapping /parcela/{id}/baixar on ParcelaController");
		
		Optional<Parcela> parcela = parcelaService.findOne(id);
		
		if(parcela.get().getAtivo()) {
		
			parcela.get().setAtivo(false);
			parcela.get().setValorPago(parcela.get().getValorParcela());
			
			parcelaService.update(parcela.get());
			
			Optional<Emprestimo> emprestimo = emprestimoService.findOne(parcela.get().getEmprestimo().getId());
			List<Parcela> parcelas = emprestimo.get().getParcelas();
			
			for(Parcela parcelaAux : parcelas) {
				if(parcelaAux.getAtivo()) {
					redirectAttributes.addFlashAttribute("status", Constants.SUCESSO);
					redirectAttributes.addFlashAttribute("message", SuccessStrings.PARCELA_BAIXADA);
			        return "redirect:/parcela";
				}
			}
			
			emprestimo.get().setAtivo(false);
			emprestimoService.update(emprestimo.get());
			
			redirectAttributes.addFlashAttribute("status", Constants.SUCESSO);
			redirectAttributes.addFlashAttribute("message", SuccessStrings.PARCELA_BAIXADA.concat("<br>"+ SuccessStrings.EMPRESTIMO_FINALIZADO));
	        return "redirect:/parcela";
		}
		else {
			redirectAttributes.addFlashAttribute("status", Constants.FALHA);
			redirectAttributes.addFlashAttribute("message", FailureStrings.PARCELA_BAIXADA);
			return "redirect:/parcela";
		}
	}
	
	@PostMapping("/filtrar")
	public String filtrar(@ModelAttribute ParcelaFilter parcelaFilter, Model model) {
		
		model.addAttribute("parcelas", parcelaService.findByVencimento(parcelaFilter.getDataInicio(), parcelaFilter.getDataFim()));
		return "parcela/index";
	}
}
