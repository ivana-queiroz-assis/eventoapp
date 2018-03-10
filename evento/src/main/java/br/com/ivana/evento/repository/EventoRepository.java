package br.com.ivana.evento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ivana.evento.model.Evento;

public interface EventoRepository extends CrudRepository<Evento,String>{
	
	Evento findByCodigo(long codigo);
	
}
