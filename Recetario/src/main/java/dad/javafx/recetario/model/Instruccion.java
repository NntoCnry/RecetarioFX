package dad.javafx.recetario.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Instruccion {
	private StringProperty descripcion;

	public Instruccion() {
		descripcion = new SimpleStringProperty(this, "descripcion");
	}
	
	public Instruccion(String descripcion) {
		this();
		setDescripcion(descripcion);
	}

	public StringProperty descripcionProperty() {
		return this.descripcion;
	}

	@XmlAttribute
	public String getDescripcion() {
		return this.descripcionProperty().get();
	}

	public void setDescripcion(final String descripcion) {
		this.descripcionProperty().set(descripcion);
	}
	
	@Override
	public String toString() {
		return getDescripcion();
	}

}
