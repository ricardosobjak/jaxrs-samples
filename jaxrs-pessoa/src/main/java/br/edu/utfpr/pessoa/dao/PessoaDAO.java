package br.edu.utfpr.pessoa.dao;

import javax.persistence.EntityManager;

import br.edu.utfpr.pessoa.model.Pessoa;

public class PessoaDAO extends GenericDAO<Integer, Pessoa> {
	public PessoaDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
