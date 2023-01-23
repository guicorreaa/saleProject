package control;

import java.io.IOException;

import aplicacao.Cliente;
import infra.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControleClientes {

	private Stage stage;
	
    @FXML
    private TextField endereco;

    @FXML
    private TextField nome;

    @FXML
    private TextField telefone;
    
    @FXML
    private Text adcSucesso;

    @FXML
    void btAdicionar(ActionEvent event) {
    	
    		DAO<Cliente> dao = new DAO<>();
    		String nomeCliente = nome.getText();
    		String telefoneCliente = telefone.getText();
    		String enderecoCliente = endereco.getText();
    		Cliente pedido = new Cliente(nomeCliente, telefoneCliente, enderecoCliente);
    		dao.atomic_Incluse(pedido);
    		adcSucesso.setOpacity(1);
    }

    @FXML
    void btSair(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void btVoltar(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fx/inicio.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene lista = new Scene(root);
		stage.setResizable(false);
		stage.setTitle("Inicio");
		stage.setScene(lista);
		stage.centerOnScreen();
		stage.show();
    }

}
