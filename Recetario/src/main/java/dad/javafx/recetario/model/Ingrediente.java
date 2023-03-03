package dad.javafx.recetario.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Ingrediente {
	private StringProperty nombre;
	private DoubleProperty cantidad;
	private ObjectProperty<Medida> medida;

	public Ingrediente() {
		nombre = new SimpleStringProperty(this, "nombre");
		cantidad = new SimpleDoubleProperty(this, "cantidad", 0.0);
		medida = new SimpleObjectProperty<>(this, "medida");
	}

	public Ingrediente(String nombre, Double cantidad, Medida medida) {
		this();
		setNombre(nombre);
		setCantidad(cantidad);
		setMedida(medida);
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

	public DoubleProperty cantidadProperty() {
		return this.cantidad;
	}

	@XmlAttribute
	public double getCantidad() {
		return this.cantidadProperty().get();
	}

	public void setCantidad(final double cantidad) {
		this.cantidadProperty().set(cantidad);
	}

	public ObjectProperty<Medida> medidaProperty() {
		return this.medida;
	}

	@XmlAttribute
	public Medida getMedida() {
		return this.medidaProperty().get();
	}

	public void setMedida(final Medida medida) {
		this.medidaProperty().set(medida);
	}

	@Override
	public String toString() {
		return getCantidad() + " " + getMedida() + " de " + getNombre();
	}

}
