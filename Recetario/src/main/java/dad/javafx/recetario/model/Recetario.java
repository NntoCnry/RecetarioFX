package dad.javafx.recetario.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
@XmlType
public class Recetario {
	private ListProperty<Receta> recetas;

	public Recetario() {
		recetas = new SimpleListProperty<>(this, "recetas", FXCollections.observableArrayList());
	}
	

	public ListProperty<Receta> recetasProperty() {
		return this.recetas;
	}

	@XmlElement
	public ObservableList<Receta> getRecetas() {
		return this.recetasProperty().get();
	}

	public void setRecetas(final ObservableList<Receta> recetas) {
		this.recetasProperty().set(recetas);
	}

	public void guardar(File destino) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Recetario.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, destino);
	}

	public static Recetario leer(File origen) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Recetario.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Recetario) unmarshaller.unmarshal(origen);
	}

}
