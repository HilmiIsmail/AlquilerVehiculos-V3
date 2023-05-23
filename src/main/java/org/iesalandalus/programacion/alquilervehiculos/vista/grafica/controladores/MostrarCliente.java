package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class MostrarCliente extends Controlador {

	@FXML
	private Label lblDni;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTelefono;

	@FXML
	private Button modificar;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnAceptar;

	Cliente cliente;

	@FXML
	private void initialize() {
		System.out.println("Mostrando datos del cliente");
	}

	public void mostrar(Cliente cliente) {
		if (cliente != null) {
			lblDni.setText(cliente.getDni());
			lblNombre.setText(cliente.getNombre());
			lblTelefono.setText(cliente.getTelefono());
		}
	}

	@FXML
	void cerrarVentana(ActionEvent event) {
		getEscenario().close();
	}

	/*
	 * public void setDniCliente(String dni) { lblDni.setText(dni); }
	 */

	@FXML
	void modificarCliente(ActionEvent event) {
		try {
			ModificarCliente modificarCliente = (ModificarCliente) Controladores.get("vistas/ModificarCliente.fxml",
					"Modificar Cliente", getEscenario());
			Cliente cliente = Cliente.getClienteConDni(lblDni.getText());
			modificarCliente.setCliente(cliente);
			modificarCliente.limpiar();
			modificarCliente.getEscenario().showAndWait();
			getEscenario().close();
		} catch (IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoInformacion("Modificar Cliente", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void devolverAlquilerCliente(ActionEvent event) {
		Cliente cliente = Cliente.getClienteConDni(lblDni.getText());

		if (cliente != null) {
			try {
				DevolverAlquilerCliente devolverAlquilerCliente = (DevolverAlquilerCliente) Controladores
						.get("vistas/DevolverAlquilerCliente.fxml", "Devolver Alquiler de Cliente", getEscenario());
				devolverAlquilerCliente.mostrar(cliente);
				devolverAlquilerCliente.getEscenario().showAndWait();
			} catch (IllegalArgumentException | NullPointerException e) {
				Dialogos.mostrarDialogoInformacion("Devolver alquiler Cliente", e.getMessage(), getEscenario());
			}
		}
	}

	@FXML
	private void borrarCliente(ActionEvent event) {
		Cliente cliente = Cliente.getClienteConDni(lblDni.getText());

		if (cliente != null) {
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Borrar cliente");
			confirmacion.setHeaderText("¿Desea borrar el cliente seleccionado?");

			Optional<ButtonType> resultado = confirmacion.showAndWait();
			if (resultado.get() == ButtonType.OK) {
				try {
					VistaGrafica.getInstancia().getControlador().borrar(cliente);
					Dialogos.mostrarDialogoConfirmacion("Borrar Cliente", "El cliente ha sido borrado correctamente.",
							getEscenario());
					getEscenario().close();
				} catch (OperationNotSupportedException e) {
					Dialogos.mostrarDialogoError("Borrar Cliente", e.getMessage(), getEscenario());
				}
			}
		}
	}

	@FXML
	void listarAlquileresCliente(ActionEvent event) {
		ListarAlquileresCliente listarAlquileresCliente = (ListarAlquileresCliente) Controladores
				.get("vistas/ListarAlquileresCliente.fxml", "Listar Alquileres del Cliente", getEscenario());
		listarAlquileresCliente.actualizar(
				VistaGrafica.getInstancia().getControlador().getAlquileres(Cliente.getClienteConDni(lblDni.getText())));
		listarAlquileresCliente.getEscenario().showAndWait();
	}

}
