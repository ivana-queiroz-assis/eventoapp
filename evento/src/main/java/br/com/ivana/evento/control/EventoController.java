package br.com.ivana.evento.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.ivana.evento.model.Evento;
import br.com.ivana.evento.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository repEvento;
	
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
}
