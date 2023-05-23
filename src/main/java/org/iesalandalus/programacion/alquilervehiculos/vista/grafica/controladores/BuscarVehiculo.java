package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BuscarVehiculo extends Controlador {
	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfMatricula;

	private boolean cancelado;

	@FXML
	void limpiar() {
		tfMatricula.setText("");
		cancelado = true;
	}

	@FXML
	void aceptar(ActionEvent event) {
		cancelado = false;
		getEscenario().close();
	}

	@FXML
	void cancelar(ActionEvent event) {
		cancelado = true;
		getEscenario().close();
	}

	public String getMatricula() {
		return cancelado ? null : tfMatricula.getText();
	}

	@FXML
	void initialize() {
		tfMatricula.textProperty()
				.addListener((ob, ov, nv) -> Controles.validarCampoTexto(Vehiculo.ER_MATRICULA, tfMatricula));
	}
}
