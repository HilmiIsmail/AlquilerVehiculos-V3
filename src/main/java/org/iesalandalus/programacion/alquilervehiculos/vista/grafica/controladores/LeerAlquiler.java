package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;

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

public class LeerAlquiler extends Controlador {

	@FXML
	private Button btCancelar;

	@FXML
	private Button btInsertar;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private TextField tfDniCliente;

	@FXML
	private TextField tfMatriculaVehiculo;

	private boolean cancelado;

	@FXML
	void cancelar(ActionEvent event) {
		cancelado = true;
		getEscenario().close();
	}

	@FXML
	void insertar(ActionEvent event) {
		cancelado = false;
		getEscenario().close();
	}

	public void limpiar() {
		tfDniCliente.setText("");
		tfMatriculaVehiculo.setText("");
		dpFechaAlquiler.setValue(null);
		cancelado = true;

	}

	@FXML
	void initialize() {
		tfDniCliente.textProperty()
				.addListener((ob, ov, nv) -> Controles.validarCampoTexto(Cliente.ER_DNI, tfDniCliente));
		tfMatriculaVehiculo.textProperty()
				.addListener((ob, ov, nv) -> Controles.validarCampoTexto(Vehiculo.ER_MATRICULA, tfMatriculaVehiculo));

	}

	public Alquiler getAlquiler() {
		String dniCliente = tfDniCliente.getText();
		String matriculaVehiculo = tfMatriculaVehiculo.getText();

		Cliente cliente = Cliente.getClienteConDni(dniCliente);
		Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(matriculaVehiculo);
		LocalDate fechaAlquiler = dpFechaAlquiler.getValue();
		
		return cancelado ? null : new Alquiler(cliente, vehiculo, fechaAlquiler);
	}
}
