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

import br.edu.unoesc.model.Funcionario;
import br.edu.unoesc.service.FuncionarioService;
import br.edu.unoesc.util.Constants;
import br.edu.unoesc.util.FailureStrings;
import br.edu.unoesc.util.SuccessStrings;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	// == fields ==
	private FuncionarioService funcionarioService;
	
	// == constructors ==
	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	// == public methods ==
	@GetMapping("")
	public String home(Model model) {
		log.info("GetMapping /funcionario/ on FuncionarioController");
		
		model.addAttribute("funcionarios", funcionarioService.getAll());
		return "funcionario/index";
	}
	
	@GetMapping("index")
	public String home(@ModelAttribute Integer status, @ModelAttribute String message, Model model) {
		log.info("GetMapping /funcionario/index on FuncionarioController");
		
		model.addAttribute("status", status);
		model.addAttribute("message", message);
		
		model.addAttribute("funcionarios", funcionarioService.getAll());
		return "funcionario/index";
	}
	
	@GetMapping("/novo")
	public ModelAndView novo() {
		log.info("GetMapping /funcionario/novo on FuncionarioController");
		
		return new ModelAndView("funcionario/novo", "funcionario", new Funcionario());
	}
	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Funcionario funcionario, RedirectAttributes redirectAttributes){
		log.info("PostMapping /funcionario/salvar on FuncionarioController");
		
		List<Funcionario> funcionarios = funcionarioService.getAll();
		
		for(Funcionario funcionarioAux : funcionarios) {
			if(funcionario.getCpf().equals(funcionarioAux.getCpf())) {
				redirectAttributes.addFlashAttribute("status", Constants.FALHA);
				redirectAttributes.addFlashAttribute("message", FailureStrings.FUNCIONARIO_CPF);
				return "redirect:/funcionario";
			}
		}
		
		funcionarioService.create(funcionario);
		
		redirectAttributes.addFlashAttribute("status", Constants.SUCESSO);
		redirectAttributes.addFlashAttribute("message", SuccessStrings.CADASTRO_REALIZADO);
        return "redirect:/funcionario";
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable("id") Long id) {
		log.info("GetMapping /funcionario/{id}/editar on FuncionarioController");
		
        Optional<Funcionario> funcionario = funcionarioService.findOne(id);
        
        return new ModelAndView("funcionario/editar", "funcionario", funcionario.get());
    }
	
	@PostMapping("/atualizar")
	public String atualizar(@ModelAttribute Funcionario funcionario, RedirectAttributes redirectAttributes){
		log.info("PostMapping /funcionario/atualizar on FuncionarioController");
		
		funcionarioService.update(funcionario);
		
		redirectAttributes.addFlashAttribute("status", Constants.SUCESSO);
		redirectAttributes.addFlashAttribute("message", SuccessStrings.CADASTRO_ALTERADO);
		return "redirect:/funcionario";
	}
	
	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		log.info("GetMapping /funcionario/{id}/excluir on FuncionarioController");
		
		if(funcionarioService.findOne(id).get().getEmprestimos().isEmpty()) {
			funcionarioService.delete(id);
			
			redirectAttributes.addFlashAttribute("status", Constants.SUCESSO);
			redirectAttributes.addFlashAttribute("message", SuccessStrings.CADASTRO_REMOVIDO);
		}
		else {
			redirectAttributes.addFlashAttribute("status", Constants.FALHA);
			redirectAttributes.addFlashAttribute("message", FailureStrings.FUNCIONARIO_RELACIONADO);
		}
        return "redirect:/funcionario";
	}
}
