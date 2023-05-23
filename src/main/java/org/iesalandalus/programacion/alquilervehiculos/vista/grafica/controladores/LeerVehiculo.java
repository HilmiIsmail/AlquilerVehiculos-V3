package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LeerVehiculo extends Controlador {

	@FXML
	private ComboBox<String> cbTipo;
	@FXML
	private TextField tfCilindrada;
	@FXML
	private TextField tfMarca;
	@FXML
	private TextField tfMatricula;
	@FXML
	private TextField tfModelo;
	@FXML
	private TextField tfPlazas;
	@FXML
	private TextField tfPma;

	private boolean cancelado;

	@FXML
	void initialize() {
		cbTipo.getItems().addAll(TipoVehiculo.TURISMO.toString(), TipoVehiculo.AUTOBUS.toString(),
				TipoVehiculo.FURGONETA.toString());

		cbTipo.valueProperty().addListener((observable, oldValue, newValue) -> {
			habilitarCampos(newValue);
		});
		cbTipo.setValue(TipoVehiculo.TURISMO.toString());
		tfMarca.textProperty().addListener((ob, ov, nv) -> Controles.validarCampoTexto(Vehiculo.ER_MARCA, tfMarca));
		tfMatricula.textProperty()
				.addListener((ob, ov, nv) -> Controles.validarCampoTexto(Vehiculo.ER_MATRICULA, tfMatricula));

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

	public void limpiar() {
		tfCilindrada.setText("");
		tfMarca.setText("");
		tfMatricula.setText("");
		tfModelo.setText("");
		tfPlazas.setText("");
		tfPma.setText("");
		cancelado = true;

	}

	public Vehiculo getVehiculo() {
		String tipoVehiculo = cbTipo.getValue();
		String matricula = tfMatricula.getText();
		String marca = tfMarca.getText();
		String modelo = tfModelo.getText();

		Vehiculo vehiculo = null;
		if (tipoVehiculo.equals(TipoVehiculo.TURISMO.toString())) {
			int cilindrada = 0;
			if (!tfCilindrada.getText().isEmpty()) {
				cilindrada = Integer.parseInt(tfCilindrada.getText());
			}
			vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
			
		} else if (tipoVehiculo.equals(TipoVehiculo.FURGONETA.toString())) {
			int plazas = 0;
			int pma = 0;
			if (!tfPlazas.getText().isEmpty()) {
				plazas = Integer.parseInt(tfPlazas.getText());
			}
			if (!tfPma.getText().isEmpty()) {
				pma = Integer.parseInt(tfPma.getText());
			}
			vehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);
			
		} else {
			int plazas = 0;
			if (!tfPlazas.getText().isEmpty()) {
				plazas = Integer.parseInt(tfPlazas.getText());
			}
			vehiculo = new Autobus(marca, modelo, plazas, matricula);
		}
		return cancelado ? null : vehiculo;
	}

	private void habilitarCampos(String tipo) {

		if (tipo.equals(TipoVehiculo.TURISMO.toString())) {
			Controles.deshabilitarCamposTexto(tfPlazas, tfPma);
			Controles.habilitarCamposTexto(tfCilindrada);
		} else if (tipo.equals(TipoVehiculo.AUTOBUS.toString())) {
			Controles.deshabilitarCamposTexto(tfCilindrada, tfPma);
			Controles.habilitarCamposTexto(tfPlazas);
		} else if (tipo.equals(TipoVehiculo.FURGONETA.toString())) {
			Controles.deshabilitarCamposTexto(tfCilindrada);
			Controles.habilitarCamposTexto(tfPlazas, tfPma);
		}

	}
}
