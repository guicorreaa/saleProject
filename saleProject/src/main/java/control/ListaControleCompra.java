package control;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.Pedido;
import infra.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListaControleCompra implements Initializable {

	DAO<Pedido> dao = new DAO<>(Pedido.class);
	

	private Stage stage;

	@FXML
	private Button botaoExcluir;

	@FXML
	private Button botaoSair;

	@FXML
	private Button botaoVoltar;

	@FXML
	private Button btAtualizar;

	@FXML
	private TableColumn<Pedido, Double> valorTotal;

	@FXML
	private TableColumn<Pedido, String> dataEntrega;

	@FXML
	private TableColumn<Pedido, String> nome;

	@FXML
	private TableColumn<Date, Integer> data;

	@FXML
	private TableColumn<Pedido, String> pedido;

	@FXML
	private TableColumn<Pedido, Integer> quantidade;

	@FXML
	private TableView<Pedido> tabela;

	@FXML
	private TableColumn<Pedido, Double> valor;

	@FXML
	private Button btPesquisar;

	@FXML
	private TextField pesquisaText;

	@FXML
	void btPesquisarOn(ActionEvent event) {
		preencherPesquisa();
	}

	@FXML
	void botaoExcluir(ActionEvent event) {
		Pedido pedido = tabela.getSelectionModel().getSelectedItem();
		Long id = pedido.getId();
		dao.openT();
		dao.removerBancoPedido(id);
		dao.closeT();
		
		preencher();

	}

	@FXML
	void botaoSair(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void botaoVoltar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/fx/inicio.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene lista = new Scene(root);
		stage.setResizable(false);
		stage.setScene(lista);
		stage.centerOnScreen();
		stage.show();
	}

	@FXML
	void btAtualizar(ActionEvent event) {
		preencher();
	}

	public void initialize(URL location, ResourceBundle resources) {
		preencher();
	}

	private void preencher() {
		data.setCellValueFactory(new PropertyValueFactory<>("data"));
		nome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		pedido.setCellValueFactory(new PropertyValueFactory<>("nomePedido"));
		dataEntrega.setCellValueFactory(new PropertyValueFactory<>("dataEntrega"));
		valorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		quantidade.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		valor.setCellValueFactory(new PropertyValueFactory<>("valor"));

		tabela.setItems(atualizaTabela());
	}

	public ObservableList<Pedido> atualizaTabela() {
		List<Pedido> pedido = dao.getNomesClientePedido();
		return FXCollections.observableArrayList(pedido); // ****
	}

	private void preencherPesquisa() {
		data.setCellValueFactory(new PropertyValueFactory<>("data"));
		nome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		pedido.setCellValueFactory(new PropertyValueFactory<>("nomePedido"));
		dataEntrega.setCellValueFactory(new PropertyValueFactory<>("dataEntrega"));
		valorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		quantidade.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tabela.setItems(atualizaTabelaPesquisa());
	}

	public ObservableList<Pedido> atualizaTabelaPesquisa() {
		String pesquisa = pesquisaText.getText();
		List<Pedido> teste = dao.pesquisarTablePedido(pesquisa);
		return FXCollections.observableArrayList(teste); // ****

	}
}
