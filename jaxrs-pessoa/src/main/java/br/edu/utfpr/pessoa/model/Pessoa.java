package br.edu.utfpr.pessoa.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="pessoa")
@XmlType(propOrder={"id","nome","dataNascimento","telefone", "foto"})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="tb_pessoa")
public class Pessoa {
	@Id @GeneratedValue
	private int id;
	private String nome;
	private String telefone;
	private byte[] foto;
	private Calendar dataNascimento;
	
	public Pessoa() { }

	public Pessoa(int id, String nome, String telefone, byte[] foto,
			Calendar dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.foto = foto;
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}