package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class controlador {
	
	private Stage stage;

    @FXML
    private Button listaClientes;
    
    @FXML
    void adcCliente(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fx/NovoCliente.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene lista = new Scene(root);
    	stage.setTitle("Cadastro");
    	stage.setResizable(false);    
    	stage.centerOnScreen();
    	stage.setScene(lista);
    	stage.show();
    }
    
    @FXML
    void listaClientesON(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fx/ListaClientes.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene lista = new Scene(root);
    	stage.setTitle("Lista de Clientes Cadastrados");
    	stage.setResizable(false);    	
    	stage.setScene(lista);
    	stage.show();
    }
    

    @FXML
    void listaPedidos(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fx/ListaPedidos.fxml"));
    	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Scene lista = new Scene(root);
    	stage.setTitle("Lista de Pedidos");
    	stage.setResizable(false);    	
    	stage.setScene(lista);
    	stage.centerOnScreen();
    	stage.show();
    	
    }

    @FXML
    void novoPedido(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fx/NovoPedido.fxml"));
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene1 = new Scene(root);
    	stage.setTitle("Novo Pedido");
    	stage.setResizable(false);    	
    	stage.setScene(scene1);
    	stage.centerOnScreen();
    	stage.show();
    }

    @FXML
    void botaoSair(ActionEvent event) {
    	System.exit(0);
    }
    
    
    
}
