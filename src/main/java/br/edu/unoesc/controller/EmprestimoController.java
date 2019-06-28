package br.edu.unoesc.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import br.edu.unoesc.model.Emprestimo;
import br.edu.unoesc.model.Funcionario;
import br.edu.unoesc.model.Parcela;
import br.edu.unoesc.service.EmprestimoService;
import br.edu.unoesc.service.FuncionarioService;
import br.edu.unoesc.util.Constants;
import br.edu.unoesc.util.FailureStrings;
import br.edu.unoesc.util.SuccessStrings;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {

	// == fields ==
	private EmprestimoService emprestimoService;
	private FuncionarioService funcionarioService;
	
	// == constructors ==
	@Autowired
	public EmprestimoController(EmprestimoService emprestimoService, FuncionarioService funcionarioService) {
		this.emprestimoService = emprestimoService;
		this.funcionarioService = funcionarioService;
	}
	
	// == public methods ==
	@GetMapping("")
	public String home(Model model) {
		log.info("GetMapping /emprestimo/ on EmprestimoController");
		
		model.addAttribute("emprestimos", emprestimoService.getAll());
		return "emprestimo/index";
	}
	
	@GetMapping("index")
	public String home(@ModelAttribute Integer status, @ModelAttribute String message, Model model) {
		log.info("GetMapping /emprestimo/ on EmprestimoController");
		
		model.addAttribute("status", status);
		model.addAttribute("message", message);
		
		model.addAttribute("emprestimos", emprestimoService.getAll());
		return "emprestimo/index";
	}
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		log.info("GetMapping /emprestimo/novo on EmprestimoController");
		
		return new ModelAndView("emprestimo/novo", "emprestimo", new Emprestimo())
						.addObject("funcionarios", funcionarioService.getAll());
	}
	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Emprestimo emprestimo, RedirectAttributes redirectAttributes){
		log.info("PostMapping /emprestimo/salvar on EmprestimoController");
		
		Optional<Funcionario> funcionario = funcionarioService.findOne(emprestimo.getFuncionario().getId());
		
		if(emprestimoService.findAllFuncionarioAtivo(funcionario.get(), true).size() > 0) {
			redirectAttributes.addFlashAttribute("status", Constants.FALHA);
			redirectAttributes.addFlashAttribute("message", FailureStrings.FUNCIONARIO_EMPRESTIMO);
			return "redirect:/emprestimo";
		}
		
		if(emprestimo.getDataPrimeiraParcela().before(Calendar.getInstance().getTime())) {
			redirectAttributes.addFlashAttribute("status", Constants.FALHA);
			redirectAttributes.addFlashAttribute("message", FailureStrings.EMPRESTIMO_VENCIMENTO);
			return "redirect:/emprestimo";
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(emprestimo.getDataPrimeiraParcela());
		
		List<Parcela> parcelas = new ArrayList<Parcela>();
		
		for(int i = 1; i <= emprestimo.getQtdParcelas(); i++) {
			Parcela parcela = new Parcela();
			parcela.setParcela(i);
			parcela.setValorParcela((emprestimo.getValorEmprestimo() * 1.05) / emprestimo.getQtdParcelas());
			parcela.setValorPago(0.00);
			parcela.setVencimento(c.getTime());
			parcela.setEmprestimo(emprestimo);
			parcela.setAtivo(true);
			parcelas.add(parcela);
			parcela = null;
			c.add(Calendar.MONTH, 1);
		}
		
		emprestimo.setAtivo(true);
		emprestimo.setParcelas(parcelas);
		emprestimoService.create(emprestimo);
		
		redirectAttributes.addFlashAttribute("status", Constants.SUCESSO);
		redirectAttributes.addFlashAttribute("message", SuccessStrings.CADASTRO_REALIZADO);
        return "redirect:/emprestimo";
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable("id") Long id) {
		log.info("GetMapping /emprestimo/{id}/editar on EmprestimoController");
		
		Optional<Emprestimo> emprestimo = emprestimoService.findOne(id);
		
		return new ModelAndView("emprestimo/editar", "emprestimo", emprestimo.get())
						.addObject("funcionarios", funcionarioService.getAll());
	}
	
	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		log.info("GetMapping /emprestimo/{id}/excluir on EmpretimoController");
		
		List<Parcela> parcelas = emprestimoService.findOne(id).get().getParcelas();
		for(Parcela parcela : parcelas) {
			if(!parcela.getAtivo()) {
				redirectAttributes.addFlashAttribute("status", Constants.FALHA);
				redirectAttributes.addFlashAttribute("message", FailureStrings.EMPRESTIMO_PARCELA);
				return "redirect:/emprestimo";
			}
		}
		
		redirectAttributes.addFlashAttribute("status", Constants.SUCESSO);
		redirectAttributes.addFlashAttribute("message", SuccessStrings.CADASTRO_REMOVIDO);
		
		emprestimoService.delete(id);
		return "redirect:/emprestimo";
	}
}
