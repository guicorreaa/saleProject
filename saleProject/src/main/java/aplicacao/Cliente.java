package aplicacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nome_Clientes",nullable = false)
	private String nomeC;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String endereco;
	
	public Cliente() {
	}
	
	public Cliente(String nomeC, String telefone, String endereco) {
		super();
		this.nomeC = nomeC;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeC() {
		return nomeC;
	}
	
		
	public void setNomeC(String nomeC) {
		this.nomeC = nomeC;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	@Override
	public String toString() {
		return getNomeC();
	}
	
	
	

	
	
	

	
	

	
	
	
	
	
	
	

	
	
}
