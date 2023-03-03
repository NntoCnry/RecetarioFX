package dad.javafx.recetario;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {
	private static Scene scene;

	private static Stage primaryStage;
	private PrimaryController controller;

	@Override
	public void start(Stage stage) throws IOException {

		App.primaryStage = stage;

		controller = new PrimaryController();

		Scene scene = new Scene(controller.getView());
		KeyCodeCombination kcNuevo = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
		KeyCodeCombination kcModificar = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
		KeyCodeCombination kcEliminar = new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN);
		scene.setOnKeyPressed(event -> {
			if (kcNuevo.match(event)) {
				controller.NuevaRecetaDefecto();
			} else if (kcModificar.match(event)) {
				try {
					controller.Modificar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (kcEliminar.match(event)) {
				controller.Eliminar();
			}
		});
	    primaryStage.setTitle("Recetas");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

//	  static void setRoot(String fxml) throws IOException {
//	        scene.setRoot(loadFXML(fxml));
//			Stage stage = new Stage();
//		    stage.setScene(scene);
//		    stage.initModality(Modality.APPLICATION_MODAL);
//		    stage.showAndWait();
//		    
//		    stage.close();
//	    }

//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void error(String header) {
		Alert error = new Alert(AlertType.ERROR);
		error.initOwner(getPrimaryStage());
		error.setTitle("Error");
		error.setHeaderText(header);
		error.showAndWait();
	}

	public static void info(String title, String header, String content) {
		Alert info = new Alert(AlertType.CONFIRMATION);
		info.initOwner(getPrimaryStage());
		info.setTitle(title);
		info.setHeaderText(header);
		info.setContentText(content);
		info.showAndWait();
	}

}
