package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class MostrarAlquilerInfo extends Controlador {

	@FXML
	private Label lblCilindrada;
	@FXML
	private Label lblDni;
	@FXML
	private Label lblFechaAlquiler;
	@FXML
	private Label lblFechaDevolucion;
	@FXML
	private Label lblMarca;
	@FXML
	private Label lblMatricula;
	@FXML
	private Label lblModelo;
	@FXML
	private Label lblNombre;
	@FXML
	private Label lblPlazas;
	@FXML
	private Label lblPma;
	@FXML
	private Label lblPrecio;
	@FXML
	private Label lblTelefono;

	Alquiler alquiler;

	@FXML
	void initialize() {
		System.out.println("Mostrando datos del vehículo");
	}

	@FXML
	void cancelar(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void borrar(ActionEvent event) {
		if (alquiler != null) {
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Borrar Alquiler");
			confirmacion.setHeaderText("¿Desea borrar el alquiler seleccionado?");

			Optional<ButtonType> resultado = confirmacion.showAndWait();
			if (resultado.get() == ButtonType.OK) {
				try {
					VistaGrafica.getInstancia().getControlador().borrar(alquiler);
					Dialogos.mostrarDialogoConfirmacion("Borrar Alquiler", "El alquiler ha sido borrado correctamente.",
							getEscenario());
					getEscenario().close();
				} catch (OperationNotSupportedException e) {
					Dialogos.mostrarDialogoError("Borrar Alquiler", e.getMessage(), getEscenario());
				}
			}
		}

	}

	public void mostrar(Alquiler alquiler) {
		if (alquiler != null) {
			this.alquiler = alquiler;

			lblDni.setText(alquiler.getCliente().getDni());
			lblNombre.setText(alquiler.getCliente().getNombre());
			lblTelefono.setText(alquiler.getCliente().getTelefono());
			
			lblFechaAlquiler.setText(alquiler.getFechaAlquiler().format(Alquiler.FORMATO_FECHA));
			
			LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
			if (fechaDevolucion != null) {
				lblFechaDevolucion.setText(fechaDevolucion.format(Alquiler.FORMATO_FECHA));
			} else {
				lblFechaDevolucion.setText("Aún no devuelto.");
			}
			
			lblMarca.setText(alquiler.getVehiculo().getMarca());
			lblMatricula.setText(alquiler.getVehiculo().getMatricula());
			lblModelo.setText(alquiler.getVehiculo().getModelo());
			lblPrecio.setText(String.valueOf(alquiler.getPrecio()));
			

			Vehiculo vehiculo = alquiler.getVehiculo();

			if (vehiculo instanceof Turismo) {
				lblCilindrada.setText(String.valueOf(((Turismo) vehiculo).getCilindrada()));
				lblPlazas.setText("");
				lblPma.setText("");
			} else if (vehiculo instanceof Autobus) {
				lblCilindrada.setText("");
				lblPlazas.setText(String.valueOf(((Autobus) vehiculo).getPlazas()));
				lblPma.setText("");
			} else if (vehiculo instanceof Furgoneta) {
				lblCilindrada.setText("");
				lblPlazas.setText(String.valueOf(((Furgoneta) vehiculo).getPlazas()));
				lblPma.setText(String.valueOf(((Furgoneta) vehiculo).getPma()));
			}
		}
	}

}
