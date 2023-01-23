package control;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.Cliente;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class controleCompra implements Initializable{

	
	@FXML
	private Button btVoltar;

	@FXML
	private Button adicionar;

	@FXML
	private TextField endereco;

	@FXML
	private TextField nome;

	@FXML
	private TextField qtdBrigadeiro;

	@FXML
	private TextField qtdBrigadeiroCone;

	@FXML
	private TextField qtdChocolate;

	@FXML
	private TextField qtdChocolateCone;

	@FXML
	private TextField qtdDoceLEite;

	@FXML
	private TextField qtdDoceLEiteCone;

	@FXML
	private TextField qtdNutella;

	@FXML
	private TextField qtdNutellaCone;

	@FXML
	private TextField qtdPrestigio;

	@FXML
	private TextField qtdPrestigioCone;

	@FXML
	private TextField telefone;

	@FXML
	private Text erroInformacoes;
	
	@FXML
    private DatePicker dataTx;
	
	@FXML
    private Text pedidoAceito;

   @FXML
    private TextField valorCone;

    @FXML
    private TextField valorPao;

	private Stage stage;

	
	 @FXML
	 private ComboBox<Cliente> comboBOX;	 
	 
	 private List<Cliente> clientes = new ArrayList<>();
	 private ObservableList<Cliente> obs;

	 
	 //ComboBox janela pedido
	 public void carregarComboBox() {
		 DAO<Cliente> daoCliente = new DAO<>(Cliente.class);
		 clientes.addAll(daoCliente.get_All());
		 
		 obs = FXCollections.observableArrayList(clientes);
		 
		 comboBOX.setItems(obs);
	 }
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarComboBox();
	}
	

	@FXML
	void AdicionarPedido(ActionEvent event) {
		
		Cliente clientee = comboBOX.getSelectionModel().getSelectedItem();
		System.out.println(clientee);

		try {
			erroInformacoes.setOpacity(0);
			int qtdCho = Integer.parseInt(qtdChocolate.getText());
			int qtdDoce = Integer.parseInt(qtdDoceLEite.getText());
			int qtdBrig = Integer.parseInt(qtdBrigadeiro.getText());
			int qtdPres = Integer.parseInt(qtdPrestigio.getText());
			int qtdNut = Integer.parseInt(qtdNutella.getText());
			int qtdChoCon = Integer.parseInt(qtdChocolateCone.getText());
			int qtdDoceCon = Integer.parseInt(qtdDoceLEiteCone.getText());
			int qtdBrigCon = Integer.parseInt(qtdBrigadeiroCone.getText());
			int qtdPresCon = Integer.parseInt(qtdPrestigioCone.getText());
			int qtdNutCon = Integer.parseInt(qtdNutellaCone.getText());
			
			double valorPaoDeMel = Double.parseDouble(valorPao.getText().replace(",", "."));
			double valorCones = Double.parseDouble(valorCone.getText().replace(",", "."));
			
			try {
				DAO<Object> dao = new DAO<>();

				if (qtdCho > 0 || qtdDoce > 0 || qtdBrig > 0 || qtdPres > 0 || qtdNut > 0 || qtdChoCon > 0
						|| qtdDoceCon > 0 || qtdBrigCon > 0 || qtdPresCon > 0 || qtdNutCon > 0 || valorPaoDeMel >= 0 || valorCones >= 0) {

					List<Pedido> produtos = new ArrayList<>();
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Pao de Mel de Chocolate", valorPaoDeMel, qtdCho, (qtdCho * valorPaoDeMel)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Pao de Mel de Doce de Leite", valorPaoDeMel, qtdDoce,(qtdDoce * valorPaoDeMel)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Pao de Mel de Brigadeiro", valorPaoDeMel, qtdBrig,(qtdBrig * valorPaoDeMel)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Pao de Mel de Prestigio", valorPaoDeMel, qtdPres,(qtdPres * valorPaoDeMel)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Pao de Mel de Nutella", valorPaoDeMel, qtdNut,(qtdNut * valorPaoDeMel)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Cone de Chocolate ", valorCones, qtdChoCon, (qtdChoCon * valorCones)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Cone de Doce de Leite", valorCones, qtdDoceCon,  (qtdDoceCon * valorCones)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Cone de Brigadeiro", valorCones, qtdBrigCon,  (qtdBrigCon * valorCones)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Cone de Prestigio", valorCones, qtdPresCon,  (qtdPresCon * valorCones)));
					produtos.add(new Pedido(getData(), dataEntrega(),clientee.getNomeC(),"Cone de Nutella", valorCones, qtdNutCon,  (qtdNutCon * valorCones)));

					for (Pedido produto : produtos) {
						if (produto.getQtd() > 0) {
							dao.openT().include(produto).closeT();
							pedidoAceito.setOpacity(100);
							}
						}
				}
			   } catch (Exception e) {
			}

		} catch (Exception e) {
			pedidoAceito.setOpacity(0);
			erroInformacoes.setOpacity(1);
		}

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

	public String getData() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}
	
	public String dataEntrega() {
		LocalDate dataa = dataTx.getValue();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatador.format(dataa);
	}
	


}

	

