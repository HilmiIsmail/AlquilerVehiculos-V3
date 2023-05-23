package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModificarCliente extends Controlador {
	@FXML
	private TextField tfDni;
	@FXML
	private TextField tfNombre;
	@FXML
	private TextField tfTelefono;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnCancelar;

	/****************************/

	@FXML
	private void initialize() {
		System.out.println("Modificar datos del cliente");
		tfNombre.textProperty().addListener((ob, ov, nv) -> Controles.validarCampoTexto(Cliente.ER_NOMBRE, tfNombre));
		tfTelefono.textProperty()
				.addListener((ob, ov, nv) -> Controles.validarCampoTexto(Cliente.ER_TELEFONO, tfTelefono));
	}
	
	@FXML
	void limpiar() {
		tfNombre.setText("");
		tfTelefono.setText("");
	}
	@FXML
	void cerrarVentana(ActionEvent event) {
		getEscenario().close();
	}

	public void setCliente(Cliente cliente) {
		if (cliente != null) {
			tfDni.setText(cliente.getDni());
			tfNombre.setText(cliente.getNombre());
			tfTelefono.setText(cliente.getTelefono());
			tfDni.setDisable(true);
		}
	}

	@FXML
	void modificar(ActionEvent event) {
		String nombre = tfNombre.getText();
		String telefono = tfTelefono.getText();

		try {
			VistaGrafica.getInstancia().getControlador().modificar(Cliente.getClienteConDni(tfDni.getText()), nombre, telefono);
			Dialogos.mostrarDialogoInformacion("Modificar Cliente", "Cliente modificado correctamente", getEscenario());
			getEscenario().close();
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Modificar Cliente", e.getMessage(), getEscenario());
		}

	}
}
