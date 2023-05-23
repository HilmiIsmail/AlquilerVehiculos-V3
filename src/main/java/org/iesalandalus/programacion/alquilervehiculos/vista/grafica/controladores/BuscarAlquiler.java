package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BuscarAlquiler extends Controlador {

	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfMatricula;

	private boolean cancelado;

	@FXML
	void limpiar() {
		tfMatricula.setText("");
		tfDni.setText("");
		dpFechaAlquiler.setValue(null);
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

	public Alquiler getAlquiler() {
		return cancelado ? null
				: new Alquiler(Cliente.getClienteConDni(tfDni.getText()),
						Vehiculo.getVehiculoConMatricula(tfMatricula.getText()), dpFechaAlquiler.getValue());
	}

	@FXML
	void initialize() {
		tfDni.textProperty().addListener((ob, ov, nv) -> Controles.validarCampoTexto(Cliente.ER_DNI, tfDni));

		tfMatricula.textProperty()
				.addListener((ob, ov, nv) -> Controles.validarCampoTexto(Vehiculo.ER_MATRICULA, tfMatricula));

	}
}
