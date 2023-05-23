
package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LanzadorVentanaPrincipal extends Application {

	private static final String TITULO = "Vista Ventanas Alquiler de Vehiculos";

	@Override
	public void start(Stage escenarioPrincipal) throws Exception {

		try {

			/*
			 * FXMLLoader cargadorVentanaPrincipal = new
			 * FXMLLoader(LocalizadorRecursos.class.getResource(
			 * "vistas/VentanaPrincipal.fxml")); Parent raiz =
			 * cargadorVentanaPrincipal.load();
			 * 
			 * Scene escena = new Scene(raiz);
			 * escenarioPrincipal.setTitle("Vista Gráfica Alquiler de Vehículos");
			 * escenarioPrincipal.setScene(escena); escenarioPrincipal.show();
			 */

			Controlador ventanaPrincipal = Controladores.get("vistas/VentanaPrincipal.fxml", TITULO, null);
			// Dialogos.setHojaEstilos(ventanaPrincipal.getEscenario().getScene().getRoot().getStylesheets().get(0));
			ventanaPrincipal.getEscenario().setOnCloseRequest(e -> confirmarSalida(ventanaPrincipal.getEscenario(), e));
			//Image icono = new Image("C:/Users/Ismail/Downloads/sport-car.png");
			// Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/icono.png"));
			//ventanaPrincipal.getEscenario().getIcons().add(icono);
			ventanaPrincipal.getEscenario().show();

			/*
			 * Controlador leerCliente = Controladores.get("vistas/LeerCliente.fxml",
			 * "Ventana Leer Cliente", null); Image iconoLeerCliente = new
			 * Image("C:/Users/Ismail/Downloads/add-friend.png");
			 * leerCliente.getEscenario().getIcons().add(iconoLeerCliente);
			 * 
			 * Controlador listarCliente = Controladores.get("vistas/ListarClientes.fxml",
			 * "Ventana Listar Clientes", null); Image iconoListarCliente = new
			 * Image("C:/Users/Ismail/Downloads/list.png");
			 * listarCliente.getEscenario().getIcons().add(iconoListarCliente);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void comenzar() {
		launch(LanzadorVentanaPrincipal.class);
	}

	private void confirmarSalida(Stage escenario, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				escenario)) {
			escenario.close();
		} else {
			e.consume();
		}
	}
}
