package dad.javafx.recetario;

import java.io.File;

import dad.javafx.recetario.model.Ingrediente;
import dad.javafx.recetario.model.Instruccion;
import dad.javafx.recetario.model.Medida;
import dad.javafx.recetario.model.Receta;
import dad.javafx.recetario.model.Recetario;

public class Main2 {
	private static Recetario recetario = new Recetario();
	public static void main(String[] args) throws Exception {
		
		// RECETA: MACARRONES ----------------------------------------
		
		Ingrediente ing1 = new Ingrediente("Carne de ternera", 150.0, Medida.GRAMOS); // nombre, cantidad, unidad de medida
		Ingrediente ing2 = new Ingrediente("Aceite", 2.0, Medida.CUCHARADITAS);
		Ingrediente ing3 = new Ingrediente("Macarrones", 300.0, Medida.GRAMOS);
		Ingrediente ing4 = new Ingrediente("Tomates", 4.0, Medida.KILOS);
		
		Instruccion ins1 = new Instruccion("Picar la carne"); 					// orden, descripción
		Instruccion ins2 = new Instruccion("Guisar los macarrones");
		Instruccion ins3 = new Instruccion("Hacer la salsa de tomate");
		Instruccion ins4 = new Instruccion("Echar la salsa por encima de los macarrones");
		
		Receta receta1 = new Receta();
		receta1.setNombre("Macarrones");		// nombre de la receta
		receta1.setDuracion(60); 				// duración en minutos
		receta1.setComensales(4);				// número de comensales
		receta1.getIngredientes().addAll(ing1, ing2, ing3, ing4); // añadimos ingredientes
		receta1.getInstrucciones().addAll(ins1, ins2, ins3, ins4); // añadimos instrucciones

		// RECETARIO: ----------------------------------------
		
		// añadimos la receta al recetario
		
		recetario.getRecetas().add(receta1);
		
		// PERSISTENCIA: --------------------------------------
		
		// guardar recetas
		recetario.guardar(new File("mis-recetas.xml"));

		// recuperar recetas
		recetario = Recetario.leer(new File("mis-recetas.xml"));
		
		

	}
	public final Recetario getRecetario() {
		return recetario;
	}
	

}
