package control;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.Cliente;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControladorListaClientes implements Initializable{

    @FXML
    private TableColumn<Cliente, String> enderecoC;

    @FXML
    private TableColumn<Cliente, String> nomeC;
    
    private Stage stage;

    @FXML
    private TableView<Cliente> tableCliente;

    @FXML
    private TableColumn<Cliente, String> telefoneC;
    
    
    DAO<Cliente> dao = new DAO<>(Cliente.class);

    @FXML
    private TextField textFildPesquisa;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	preencher();
    }

    @FXML
    void btAtualizar(ActionEvent event) {
    	preencher();
    }

    @FXML
    void btPesquisa(ActionEvent event) {
    	preencherPesquisa();
    }

    @FXML
    void btSair(ActionEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void btExcluir(ActionEvent event) {
    	Cliente cliente = tableCliente.getSelectionModel().getSelectedItem();
		Long id = cliente.getId();
		dao.openT();
		dao.removerBancoCliente(id);
		dao.closeT();

		preencher();
    }

    @FXML
    void btVoltar(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fx/inicio.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene lista = new Scene(root);
		stage.setResizable(false);
		stage.setTitle("Inicio");
		stage.centerOnScreen();
		stage.setScene(lista);
		stage.centerOnScreen();
		stage.show();
    }

    public void preencher() {
    	nomeC.setCellValueFactory(new PropertyValueFactory<>("nomeC"));
    	enderecoC.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    	telefoneC.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    	tableCliente.setItems(preencherTabela());
    	
    }
    
    public ObservableList<Cliente> preencherTabela(){
    	List<Cliente> lista = dao.getCliente();
    	return FXCollections.observableArrayList(lista);
    }
    
    private void preencherPesquisa() {
    	nomeC.setCellValueFactory(new PropertyValueFactory<>("nomeC"));
    	enderecoC.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    	telefoneC.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    	tableCliente.setItems(atualizaTabelaPesquisa());
	}

	public ObservableList<Cliente> atualizaTabelaPesquisa() {
		String pesquisa = textFildPesquisa.getText();
		List<Cliente> teste = dao.pesquisarTableCliente(pesquisa);
		return FXCollections.observableArrayList(teste); // ****
	}
    
}
