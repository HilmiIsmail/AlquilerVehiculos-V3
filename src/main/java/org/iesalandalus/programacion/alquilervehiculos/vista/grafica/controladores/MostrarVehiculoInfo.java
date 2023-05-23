package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class MostrarVehiculoInfo extends Controlador {

	@FXML
	private Label lblCilindrada;
	@FXML
	private Label lblMarca;
	@FXML
	private Label lblMatricula;
	@FXML
	private Label lblModelo;
	@FXML
	private Label lblPlazas;
	@FXML
	private Label lblPma;

	Vehiculo vehiculo;

	public void mostrar(Vehiculo vehiculo) {
		if (vehiculo != null) {
			lblMatricula.setText(vehiculo.getMatricula());
			lblModelo.setText(vehiculo.getModelo());
			lblMarca.setText(vehiculo.getMarca());
			if (TipoVehiculo.get(vehiculo) == TipoVehiculo.TURISMO) {
				lblCilindrada.setText(String.valueOf(((Turismo) vehiculo).getCilindrada()));
			} else if (TipoVehiculo.get(vehiculo) == TipoVehiculo.AUTOBUS) {
				lblPlazas.setText(String.valueOf(((Autobus) vehiculo).getPlazas()));
			} else if (TipoVehiculo.get(vehiculo) == TipoVehiculo.FURGONETA) {
				lblPlazas.setText(String.valueOf(((Furgoneta) vehiculo).getPlazas()));
				lblPma.setText(String.valueOf(((Furgoneta) vehiculo).getPma()));
			}

		}
	}

	@FXML
	void borrar(ActionEvent event) {

		Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(lblMatricula.getText());

		if (vehiculo != null) {
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Borrar Vehículo");
			confirmacion.setHeaderText("¿Desea borrar el vehículo seleccionado?");

			Optional<ButtonType> resultado = confirmacion.showAndWait();
			if (resultado.get() == ButtonType.OK) {
				try {
					VistaGrafica.getInstancia().getControlador().borrar(vehiculo);
					Dialogos.mostrarDialogoConfirmacion("Borrar Vehículo", "El vehículo ha sido borrado correctamente.",
							getEscenario());
					getEscenario().close();
				} catch (OperationNotSupportedException e) {
					Dialogos.mostrarDialogoError("Borrar Vehículo", e.getMessage(), getEscenario());
				}
			}
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void devolver(ActionEvent event) {
		Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(lblMatricula.getText());

		try {
			if (vehiculo != null) {
				DevolverAlquilerVehiculo devolverAlquilerVehiculo = (DevolverAlquilerVehiculo) Controladores
						.get("vistas/DevolverAlquilerVehiculo.fxml", "Devolver Alquiler de Cliente", getEscenario());
				devolverAlquilerVehiculo.mostrar(vehiculo);
				devolverAlquilerVehiculo.getEscenario().showAndWait();
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoInformacion("Devolver alquiler Cliente", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void listarAlquileresVehiculo(ActionEvent event) {
		ListarAlquileresVehiculo listarAlquileresVehiculo = (ListarAlquileresVehiculo) Controladores
				.get("vistas/ListarAlquileresVehiculo.fxml", "Listar Alquileres del Vehículo", getEscenario());
		listarAlquileresVehiculo.actualizar(VistaGrafica.getInstancia().getControlador()
				.getAlquileres(Vehiculo.getVehiculoConMatricula(lblMatricula.getText())));
		listarAlquileresVehiculo.getEscenario().showAndWait();

	}

	@FXML
	void initialize() {
		System.out.println("Mostrando datos del vehículo");
	}
}
