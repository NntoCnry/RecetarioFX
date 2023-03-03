package dad.javafx.recetario;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;

import dad.javafx.recetario.model.Ingrediente;
import dad.javafx.recetario.model.Instruccion;
import dad.javafx.recetario.model.Receta;
import dad.javafx.recetario.model.Recetario;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
	private SecondaryController sc = new SecondaryController();
	Main2 m = new Main2();
	Recetario recetario = new Recetario();
	Receta r = new Receta();
	private ObjectProperty<Receta> seleccionado = new SimpleObjectProperty<>();
	private ListProperty<Receta> receta = new SimpleListProperty<Receta>(FXCollections.observableArrayList());
	@FXML
	private VBox view;
	@FXML
	private Label labelRecetas;
	@FXML
	private Menu menuRecetas;
	@FXML
	private MenuItem menuItemNueva;
	@FXML
	private MenuItem menuItemModificar;
	@FXML
	private MenuItem menuItemEliminar;
	@FXML
	private TableView<Receta> tableViewRecetas;
	@FXML
	private TableColumn<Receta, String> columnNombre;
	@FXML
	private TableColumn<Receta, Number> columnDuracion;
	@FXML
	private TableColumn<Receta, Number> columnComensales;
	@FXML
	private Button botonNuevo;
	@FXML
	private Button botonModificar;
	@FXML
	private Button botonEliminar;

	private Receta recetaDefecto = new Receta("Nueva receta", 0, 1);

	ObservableList<Integer> seleccionados;
	private Ingrediente ig = new Ingrediente();
	private Instruccion it = new Instruccion();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tableViewRecetas.itemsProperty().bind(receta);
		tableViewRecetas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		seleccionado.bind(tableViewRecetas.getSelectionModel().selectedItemProperty());
		columnNombre.setCellValueFactory(v -> v.getValue().nombreProperty());
		columnDuracion.setCellValueFactory(v -> v.getValue().duracionProperty());
		columnComensales.setCellValueFactory(v -> v.getValue().comensalesProperty());
		try {
			leerFichero();
			labelRecetas
					.setText("hay " + Recetario.leer(new File("mis-recetas.xml")).getRecetas().size() + " receta(s)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuItemModificar.setDisable(true);
		menuItemEliminar.setDisable(true);
		tableViewRecetas.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			if (nv == null) {
				menuItemEliminar.setDisable(true);
				menuItemModificar.setDisable(true);
			} else {
				menuItemModificar.setDisable(false);
				menuItemEliminar.setDisable(false);

			}
		});

		// tableViewRecetas.getItems().addAll(Recetario.leer(new
		// File("mis-recetas.xml")));
	}

	public PrimaryController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void leerFichero() throws Exception {
		tableViewRecetas.getItems().addAll(Recetario.leer(new File("mis-recetas.xml")).getRecetas());
	}

	public Recetario rct(Recetario rct) {
		return rct;
	}

	@FXML
	private void onMenuNueva() throws IOException {
		NuevaRecetaDefecto();
	}

	public void NuevaRecetaDefecto() {
		int size = receta.getSize() + 1;
		tableViewRecetas.getItems().add(recetaDefecto);
		recetario.setRecetas(receta);
		try {
			recetario.guardar(new File("mis-recetas.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableViewRecetas.getSelectionModel().clearAndSelect(size - 1);
		labelRecetas.setText("hay " + size + " receta(s)");
	}

	public void Modificar() throws Exception {
		if (tableViewRecetas.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Modificar receta.");
			alert.setHeaderText("Debe Seleccionar una receta");
			alert.setContentText("Para modificar una receta antes debes seleccionarla");
			Optional<ButtonType> resultado = alert.showAndWait();
		} else {
			ListProperty<Ingrediente> ingredientes = tableViewRecetas.getSelectionModel().getSelectedItem()
					.ingredientesProperty();
			ListProperty<Instruccion> instrucciones = tableViewRecetas.getSelectionModel().getSelectedItem()
					.instruccionesProperty();
			String nombreT = tableViewRecetas.getSelectionModel().getSelectedItem().getNombre();
			int duracion = tableViewRecetas.getSelectionModel().getSelectedItem().getDuracion();
			int comensales = tableViewRecetas.getSelectionModel().getSelectedItem().getComensales();
			sc.recibirDatos(nombreT, duracion, comensales, ingredientes, instrucciones, recetario);
			sc.show();
			System.out.println("sadasdadsada" + sc.getTextFieldNombre().getText());
			/*
			 * tableViewRecetas.getSelectionModel().getSelectedItem().setNombre(sc.
			 * getTextFieldNombre().getText());
			 * tableViewRecetas.getSelectionModel().getSelectedItem().setDuracion(Integer.
			 * parseInt(sc.getTextFieldDuracion().getText()));
			 * tableViewRecetas.getSelectionModel().getSelectedItem().setComensales(Integer.
			 * parseInt(sc.getTextFieldComensales().getText()));
			 */tableViewRecetas.refresh();
			recetario.setRecetas(receta);
			try {
				recetario.guardar(new File("mis-recetas.xml"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void Eliminar() {
//		seleccionados = tableViewRecetas.getSelectionModel().getSelectedIndices();
//		List<Integer> a = seleccionados;
//		System.out.println("aaaaaa- "+ seleccionados);
//		System.out.println(seleccionados.get(0));
//		System.out.println(tableViewRecetas.getItems().size());
//		for(int i=1;i<=seleccionados.size();i++) {
//			System.out.println(i + " --- "+ seleccionados.get(i));
//			
//		}
		// TODO falta eliminar si se seleccionan varios items
		if (tableViewRecetas.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Eliminar receta");
			alert.setHeaderText("Debe Seleccionar una receta");
			alert.setContentText("Para eliminar una receta antes debes seleccionarla");
			Optional<ButtonType> resultado = alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Eliminar receta");
			alert.setHeaderText("Eliminando Receta");
			alert.setContentText("¿Seguro que deseas eliminar las recetas seleccionadas?");
			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.isPresent()) {
				if (resultado.get() == ButtonType.OK) {
					int a = tableViewRecetas.getSelectionModel().getSelectedIndex();
					int size = receta.getSize() - 1;
					if (a >= 0) {
						tableViewRecetas.getItems().remove(tableViewRecetas.getSelectionModel().getFocusedIndex());
						a = -1;
					}
					a = -1;
					try {
						recetario.guardar(new File("mis-recetas.xml"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					labelRecetas.setText("hay " + size + " receta(s)");
					tableViewRecetas.getSelectionModel().clearSelection();
				} else {

				}
			}
		}
	}

	public VBox getView() {
		return view;
	}

	@FXML
	private void onMenuModificar() throws Exception {
		Modificar();
	}

	@FXML
	private void onClickModificar(ActionEvent e) throws Exception {
		Modificar();
	}

	@FXML
	private void onClickNueva(ActionEvent e) throws IOException {
		NuevaRecetaDefecto();

	}

	@FXML
	private void onClickEliminar(ActionEvent e) throws IOException {
		Eliminar();
	}

	@FXML
	private void onMenuEliminar() throws IOException {
		Eliminar();
	}

	public Ingrediente recibirIg(Ingrediente ig) {
		return ig;
	}

	public Instruccion recibirIt(Instruccion it) {
		return it;
	}
}
