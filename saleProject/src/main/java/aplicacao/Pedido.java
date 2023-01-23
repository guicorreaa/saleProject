package aplicacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nomePedido;
	
	@Column(name = "valor_pedido",nullable = false)
	private Double valor;
	
	@ManyToOne
	private Cliente clienteID;

	@Column(nullable = false)
	String nomeCliente;
	
	@Column(nullable = false)
	private Double valorTotal;
	
	@Column(nullable = false)
	private int qtd;
	
	@Column(nullable = false)
	private String data;

	@Column(nullable = false)
	private String dataEntrega;
	
	public Pedido() {
	}
	
	public Pedido(String data, String dataEntrega,String nomeCliente,String nomePedido, Double valor, int qtd, Double valorTotal) {
		super();
		this.data = data;
		this.dataEntrega = dataEntrega;
		this.nomeCliente = nomeCliente;
		this.nomePedido = nomePedido;
		this.valor = valor;
		this.qtd = qtd;
		this.valorTotal = valorTotal;
	}
	
		public String getNomeCliente() {
			return nomeCliente;
		}
		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNomePedido() {
			return nomePedido;
		}
		public void setNomePedido(String nomePedido) {
			this.nomePedido = nomePedido;
		}
		public Double getValor() {
			return valor;
		}
		public void setValor(Double valor) {
			this.valor = valor;
		}
		public Cliente getClienteID() {
			return clienteID;
		}
		public void setClienteID(Cliente clienteID) {
			this.clienteID = clienteID;
		}
		public int getQtd() {
			return qtd;
		}
		public void setQtd(int qtd) {
			this.qtd = qtd;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public Double getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(Double valorTotal) {
			this.valorTotal = valorTotal;
		}
		public String getDataEntrega() {
			return dataEntrega;
		}
		public void setDataEntrega(String dataEntrega) {
			this.dataEntrega = dataEntrega;
		}

		
		
		
		
		
		
		
		

		

		
	
	
	
	


	

	
	
	
	
	
	
}
