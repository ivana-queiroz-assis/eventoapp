package br.com.ivana.evento.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ivana.evento.model.Convidado;
import br.com.ivana.evento.model.Evento;
import br.com.ivana.evento.repository.ConvidadoRepository;
import br.com.ivana.evento.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository repEvento;
	
	@Autowired
	private ConvidadoRepository repConvidado;
	
	
	@RequestMapping(value="/cadastraEvento", method=RequestMethod.GET)
	public String form() {
		
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastraEvento", method=RequestMethod.POST)
	public String form(Evento e) {
		repEvento.save(e);
		return "redirect:/cadastraEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv= new ModelAndView("index");
		Iterable<Evento> eventos= repEvento.findAll();
		mv.addObject("eventos",eventos);
		return mv;
	}
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalheEvento(@PathVariable("codigo") long codigo) {
		Evento e= repEvento.findByCodigo(codigo);
		ModelAndView mv= new ModelAndView("evento/detalheEvento");
		mv.addObject("evento",e);
		Iterable<Convidado> listaConvidados=repConvidado.findByEvento(e);
		mv.addObject("listaConvidados",listaConvidados);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalheEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result,
		RedirectAttributes attributes	) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return  "redirect:/{codigo}";
		}
		
		Evento e= repEvento.findByCodigo(codigo);
		convidado.setEvento(e);
		
		repConvidado.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
	
}
