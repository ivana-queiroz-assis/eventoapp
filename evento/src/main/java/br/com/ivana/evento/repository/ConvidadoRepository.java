package br.com.ivana.evento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ivana.evento.model.Convidado;
import br.com.ivana.evento.model.Evento;

public interface  ConvidadoRepository extends CrudRepository<Convidado, String>{
	
	Iterable<Convidado> findByEvento(Evento e);

	Convidado findByRg(String rg);
}
