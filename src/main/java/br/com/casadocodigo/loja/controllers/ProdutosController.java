package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");// Manda a lista para a tela
		modelAndView.addObject("tipos",TipoPreco.values()); // Manda a lista para a tela
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	private ModelAndView listar(){
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");// Manda a lista para a tela
		modelAndView.addObject("produtos",produtos); // Manda a lista para a tela
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	private ModelAndView grava(Produto produto, RedirectAttributes redirectAttributes) {
		System.out.println(produto);
		produtoDao.gravar(produto);
		
		redirectAttributes.addFlashAttribute("sucesso","Produto cadastrado com sucesso!!");
		
		return new ModelAndView("redirect:produtos");

	}
	
}