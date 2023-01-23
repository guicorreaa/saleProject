package fxml;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class MainFX extends Application{
	
		
	@Override
	public void start(Stage primaryStage) throws Exception {
				
		String arquivoCSS = getClass().getResource("/fx/Inicio.css").toExternalForm();
		
		
		URL arquivoJxml = getClass().getResource("/fx/inicio.fxml");
		GridPane raiz = FXMLLoader.load(arquivoJxml);
		
		Scene cenaPrincipal = new Scene(raiz);
		cenaPrincipal.getStylesheets().add(arquivoCSS);
		
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Tela de Login");
		primaryStage.setScene(cenaPrincipal);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}