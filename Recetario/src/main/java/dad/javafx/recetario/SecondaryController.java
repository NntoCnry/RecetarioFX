package dad.javafx.recetario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.recetario.model.Ingrediente;
import dad.javafx.recetario.model.Instruccion;
import dad.javafx.recetario.model.Medida;
import dad.javafx.recetario.model.Receta;
import dad.javafx.recetario.model.Recetario;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * Clase del controlador de Secondary.fxml
 * 
 * @author Francisco Yeray Gomez Carrion
 *
 */
//Ingrediente ing1 = new Ingrediente("Carne de ternera", 150.0, Medida.GRAMOS); // nombre, cantidad, unidad de medida
//Ingrediente ing2 = new Ingrediente("Aceite", 2.0, Medida.CUCHARADITAS);
//Ingrediente ing3 = new Ingrediente("Macarrones", 300.0, Medida.GRAMOS);
//Ingrediente ing4 = new Ingrediente("Tomates", 4.0, Medida.KILOS);
//
//Instruccion ins1 = new Instruccion("Picar la carne"); 					// orden, descripción
//Instruccion ins2 = new Instruccion("Guisar los macarrones");
//Instruccion ins3 = new Instruccion("Hacer la salsa de tomate");
//Instruccion ins4 = new Instruccion("Echar la salsa por encima de los macarrones");
public class SecondaryController implements Initializable {

	private Stage stage;
	private StringProperty servidor = new SimpleStringProperty();
	private Receta r = new Receta();
	private ObjectProperty<Ingrediente> seleccionadoIngrediente = new SimpleObjectProperty<>();
	private ListProperty<Receta> receta = new SimpleListProperty<Receta>(FXCollections.observableArrayList());
	private ListProperty<Ingrediente> ingrediente = new SimpleListProperty<Ingrediente>(
			FXCollections.observableArrayList());
	Medida medida;
	Recetario recetario;
	@FXML
	private AnchorPane view;

	@FXML
	private TextField textFieldNombre;

	public final TextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public final TextField getTextFieldDuracion() {
		return textFieldDuracion;
	}

	public final TextField getTextFieldComensales() {
		return textFieldComensales;
	}

	public final ComboBox<Medida> getComboBoxMedida() {
		return comboBoxMedida;
	}

	public final TextField getTextFieldNombreIngrediente() {
		return textFieldNombreIngrediente;
	}

	public final TextField getTextFieldCantidadIngrediente() {
		return textFieldCantidadIngrediente;
	}

	public final TextField getTextFieldMedidaIngrediente() {
		return textFieldMedidaIngrediente;
	}

	public final TextField getTextFieldDescripcion() {
		return textFieldDescripcion;
	}

	public final void setTextFieldNombre(TextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}

	public final void setTextFieldDuracion(TextField textFieldDuracion) {
		this.textFieldDuracion = textFieldDuracion;
	}

	public final void setTextFieldComensales(TextField textFieldComensales) {
		this.textFieldComensales = textFieldComensales;
	}

	public final void setComboBoxMedida(ComboBox<Medida> comboBoxMedida) {
		this.comboBoxMedida = comboBoxMedida;
	}

	public final void setTextFieldNombreIngrediente(TextField textFieldNombreIngrediente) {
		this.textFieldNombreIngrediente = textFieldNombreIngrediente;
	}

	public final void setTextFieldCantidadIngrediente(TextField textFieldCantidadIngrediente) {
		this.textFieldCantidadIngrediente = textFieldCantidadIngrediente;
	}

	public final void setTextFieldMedidaIngrediente(TextField textFieldMedidaIngrediente) {
		this.textFieldMedidaIngrediente = textFieldMedidaIngrediente;
	}

	public final void setTextFieldDescripcion(TextField textFieldDescripcion) {
		this.textFieldDescripcion = textFieldDescripcion;
	}

	@FXML
	private TextField textFieldDuracion;
	@FXML
	private TextField textFieldComensales;
	@FXML
	private ComboBox<Medida> comboBoxMedida;
	@FXML
	private TextField textFieldNombreIngrediente;
	@FXML
	private TextField textFieldCantidadIngrediente;
	@FXML
	private TextField textFieldMedidaIngrediente;
	@FXML
	private Button botonAnadirIngrediente;
	@FXML
	private Button botonEliminarIngrediente;
	@FXML
	private TextField textFieldDescripcion;
	@FXML
	private Button botonAnadirInstrucciones;
	@FXML
	private Button botonEliminarInstrucciones;
	@FXML
	private TableView<Ingrediente> tableViewIngredientes;
	@FXML
	private TableColumn<Ingrediente, String> columnNombreIngrediente;
	@FXML
	private TableColumn<Ingrediente, Number> columnCantidadIngrediente;
	@FXML
	private TableColumn<Ingrediente, Medida> columnMedidaIngrediente;
	@FXML
	private ListView<Instruccion> listaViewInstrucciones;

	Instruccion it = new Instruccion();
	Ingrediente ig = new Ingrediente();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		textFieldDescripcion.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				textFieldDescripcion.deselect();
			}
		});

		tableViewIngredientes.setEditable(true);
		tableViewIngredientes.itemsProperty().bind(ingrediente);
		seleccionadoIngrediente.bind(tableViewIngredientes.getSelectionModel().selectedItemProperty());
		columnNombreIngrediente.setCellValueFactory(v -> v.getValue().nombreProperty());
		columnCantidadIngrediente.setCellValueFactory(v -> v.getValue().cantidadProperty());
		columnMedidaIngrediente.setCellValueFactory(v -> v.getValue().medidaProperty());
		comboBoxMedida.getItems().addAll(Medida.values());
		botonAnadirInstrucciones.setDisable(true);
		botonEliminarInstrucciones.setDisable(true);
		botonAnadirIngrediente.setDisable(true);
		botonEliminarIngrediente.setDisable(true);
		textFieldDescripcion.textProperty().addListener((o, ov, nv) -> {
			if (nv == null || nv == "") {
				botonAnadirInstrucciones.setDisable(true);
			} else {
				botonAnadirInstrucciones.setDisable(false);
			}
		});
		listaViewInstrucciones.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			if (nv == null) {
				botonAnadirInstrucciones.setDisable(true);
				botonEliminarInstrucciones.setDisable(true);
			} else {
				botonAnadirInstrucciones.setDisable(true);
				botonEliminarInstrucciones.setDisable(false);
			}
		});
		tableViewIngredientes.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						// Activar el botón si hay un elemento seleccionado
						botonEliminarIngrediente.setDisable(false);
						
						
					} else {
						// Desactivar el botón si no hay ningún elemento seleccionado
						botonEliminarIngrediente.setDisable(true);
					}
				});
		// Agregar un ChangeListener al TextField
		textFieldCantidadIngrediente.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.trim().isEmpty() || newValue == null) {
				botonAnadirIngrediente.setDisable(true);
			} else {
				botonAnadirIngrediente.setDisable(false);
			}
		});
		textFieldNombreIngrediente.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.trim().isEmpty() || newValue == null || textFieldCantidadIngrediente.getText().trim().isEmpty()
					|| comboBoxMedida.getSelectionModel().isEmpty()) {
				botonAnadirIngrediente.setDisable(true);
			} else {
				botonAnadirIngrediente.setDisable(false);
			}
		});

		textFieldCantidadIngrediente.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.trim().isEmpty() || newValue == null || textFieldNombreIngrediente.getText().trim().isEmpty()
					|| comboBoxMedida.getSelectionModel().isEmpty() || !newValue.matches("\\d*")) {
				botonAnadirIngrediente.setDisable(true);
			} else {
				botonAnadirIngrediente.setDisable(false);
			}
		});

		comboBoxMedida.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null || textFieldNombreIngrediente.getText().trim().isEmpty()
					|| textFieldCantidadIngrediente.getText().trim().isEmpty()) {
				botonAnadirIngrediente.setDisable(true);
			} else {
				botonAnadirIngrediente.setDisable(false);
			}
		});

		columnNombreIngrediente.setCellValueFactory(new PropertyValueFactory<Ingrediente, String>("Nombre"));
		columnNombreIngrediente.setCellFactory(TextFieldTableCell.forTableColumn());
		columnNombreIngrediente.setOnEditCommit(event -> {
			Ingrediente ingre = event.getTableView().getItems().get(event.getTablePosition().getRow());
			ingre.setNombre(event.getNewValue());
		});

		columnCantidadIngrediente.setCellValueFactory(new PropertyValueFactory<Ingrediente, Number>("Cantidad"));

		columnCantidadIngrediente.setOnEditCommit(event -> {
			Ingrediente ingre = event.getTableView().getItems().get(event.getTablePosition().getRow());
			ingre.setCantidad(event.getNewValue().doubleValue());
		});

		columnMedidaIngrediente.setOnEditCommit(event -> {
			Ingrediente ingre = event.getTableView().getItems().get(event.getTablePosition().getRow());
			ingre.setMedida(event.getNewValue());
		});
		String a = "Modificar receta: "+textFieldNombre.getText();
		stage = new Stage();
		stage.setTitle(a);
		stage.setScene(new Scene(getView()));
		stage.initOwner(App.getPrimaryStage());
		stage.initModality(Modality.APPLICATION_MODAL);
	}

	public void show() {
		stage.showAndWait();
	}

	public AnchorPane getView() {
		return view;
	}

	public SecondaryController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/secondary.fxml"));
		loader.setController(this);
		loader.load();
	}

	@FXML
	private void onClickAñadirIngrediente() throws IOException {
		ig.setNombre(textFieldNombreIngrediente.getText());
		ig.setCantidad(Double.parseDouble(textFieldCantidadIngrediente.getText()));
		ig.setMedida(comboBoxMedida.getValue());
		// r.getIngredientes().addAll(ig);
		tableViewIngredientes.getItems().addAll(ig);
		textFieldNombreIngrediente.clear();
		textFieldCantidadIngrediente.clear();

	}

	@FXML
	private void onClickEliminarIngrediente() throws IOException {
		int a = tableViewIngredientes.getSelectionModel().getSelectedIndex();
		int size = receta.getSize() - 1;
		if (a >= 0) {
			tableViewIngredientes.getItems().remove(tableViewIngredientes.getSelectionModel().getFocusedIndex());
			a = -1;
		}
		a = -1;
	}

	@FXML
	private void onClickAnadirInstrucciones() throws IOException {
		// String desc;
		// desc = textFieldDescripcion.getText().toString();
		String texto = textFieldDescripcion.getText();
		it.setDescripcion(texto);
		System.out.println(texto);
		listaViewInstrucciones.getItems().add(it);
		// r.getInstrucciones().addAll(it);
		textFieldDescripcion.clear();
	}

	@FXML
	private void onClickEliminarInstruccion() throws IOException {
		listaViewInstrucciones.getItems().remove(listaViewInstrucciones.getSelectionModel().getSelectedIndex());
		listaViewInstrucciones.getSelectionModel().clearSelection();
	}

	@FXML
	private void onClickAtras() throws IOException {

		// r.getIngredientes(ingrediente.get());
		// recetario.setRecetas()
		//PrimaryController p = new PrimaryController();
		//p.setColumnNombre(textFieldNombre.getText());
		listaViewInstrucciones.getItems().clear();
		tableViewIngredientes.getItems().clear();
		stage.close();
	}

	public void recibirDatos(String nombre, int duracion, int comensales, ListProperty<Ingrediente> ingrediente,
			ListProperty<Instruccion> instruccion, Recetario rct) {
		textFieldNombre.setText(nombre);
		textFieldDuracion.setText("" + duracion);
		textFieldComensales.setText("" + comensales);
		tableViewIngredientes.getItems().addAll(ingrediente);
		listaViewInstrucciones.getItems().addAll(instruccion);
		recetario = rct;

	}

	private static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

}
