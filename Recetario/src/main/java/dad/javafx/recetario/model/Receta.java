package dad.javafx.recetario.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
public class Receta {
	private StringProperty nombre;
	private IntegerProperty duracion;
	private IntegerProperty comensales;
	private ListProperty<Ingrediente> ingredientes;
	private ListProperty<Instruccion> instrucciones;

	public Receta() {
		nombre = new SimpleStringProperty(this, "nombre");
		duracion = new SimpleIntegerProperty(this, "duracion", 0);
		comensales = new SimpleIntegerProperty(this, "comensales", 0);
		ingredientes = new SimpleListProperty<>(this, "ingredientes", FXCollections.observableArrayList());
		instrucciones = new SimpleListProperty<>(this, "instrucciones", FXCollections.observableArrayList());
	}
	
	public Receta(String nombre, int duracion, int comensales) {
		this();
		setNombre(nombre);
		setDuracion(duracion);
		setComensales(comensales);
	}

	public StringProperty nombreProperty() {
		return this.nombre;
	}

	@XmlAttribute
	public String getNombre() {
		return this.nombreProperty().get();
	}

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public IntegerProperty duracionProperty() {
		return this.duracion;
	}

	@XmlAttribute
	public int getDuracion() {
		return this.duracionProperty().get();
	}

	public void setDuracion(final int duracion) {
		this.duracionProperty().set(duracion);
	}

	public IntegerProperty comensalesProperty() {
		return this.comensales;
	}

	@XmlAttribute
	public int getComensales() {
		return this.comensalesProperty().get();
	}

	public void setComensales(final int comensales) {
		this.comensalesProperty().set(comensales);
	}

	public ListProperty<Ingrediente> ingredientesProperty() {
		return this.ingredientes;
	}

	@XmlElement
	public ObservableList<Ingrediente> getIngredientes() {
		return this.ingredientesProperty().get();
	}

	public void setIngredientes(final ObservableList<Ingrediente> ingredientes) {
		this.ingredientesProperty().set(ingredientes);
	}

	public ListProperty<Instruccion> instruccionesProperty() {
		return this.instrucciones;
	}

	@XmlElement
	public ObservableList<Instruccion> getInstrucciones() {
		return this.instruccionesProperty().get();
	}

	public void setInstrucciones(final ObservableList<Instruccion> instrucciones) {
		this.instruccionesProperty().set(instrucciones);
	}

}
