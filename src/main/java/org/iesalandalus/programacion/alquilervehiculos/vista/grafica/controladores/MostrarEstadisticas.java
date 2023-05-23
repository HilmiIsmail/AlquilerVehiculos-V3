package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MostrarEstadisticas extends Controlador {

	@FXML
	private Button btCancelar;
	@FXML
	private Button btGenerar;
	@FXML
	private ComboBox<Month> cbMes;
	@FXML
	private ComboBox<Integer> cbAnio;
	@FXML
	private PieChart pieChart;
	@FXML
	private Label lblTotalTurismos;
	@FXML
	private Label lblTotalFurgonetas;
	@FXML
	private Label lblTotalAutobuses;

	@FXML
	void initialize() {
		cbMes.getItems().addAll(Month.values());
		cbAnio.getItems().addAll(2019, 2020, 2021, 2022, 2023); // ponemos por ejemplo los utlimos 5 años
	}

	@FXML
	void generarEstadisticas() {
		Month mes = cbMes.getValue();
		int anio = cbAnio.getValue();

		List<Alquiler> alquileres = obtenerAlquileresMesYAnio(mes, anio);
		int totalTurismos = contarTurismos(alquileres);
		int totalFurgonetas = contarFurgonetas(alquileres);
		int totalAutobuses = contarAutobuses(alquileres);

		lblTotalTurismos.setText(String.valueOf(totalTurismos));
		lblTotalFurgonetas.setText(String.valueOf(totalFurgonetas));
		lblTotalAutobuses.setText(String.valueOf(totalAutobuses));

		actualizarPieChart(totalTurismos, totalFurgonetas, totalAutobuses);
	}

	private List<Alquiler> obtenerAlquileresMesYAnio(Month mes, int anio) {
		List<Alquiler> alquileresFiltrados = new ArrayList<>();

		// Obtener la lista de alquileres
		List<Alquiler> alquileres = VistaGrafica.getInstancia().getControlador().getAlquileres();

		// Filtramos los alquileres que correspondan al mes y año especificados
		for (Alquiler alquiler : alquileres) {
			LocalDate fechaAlquiler = alquiler.getFechaAlquiler();
			if (fechaAlquiler.getMonth() == mes && fechaAlquiler.getYear() == anio) {
				alquileresFiltrados.add(alquiler);
			}
		}

		return alquileresFiltrados;
	}

	/*********************
	 * EL METODO PARA CONTAR EL NUMERO DE TURISMOS
	 *********************************/
	private int contarTurismos(List<Alquiler> alquileres) {
		int contador = 0;
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getVehiculo() instanceof Turismo) {
				contador++;
			}
		}
		return contador;
	}

	/*********************
	 * EL METODO PARA CONTAR EL NUMERO DE FURGONETAS
	 *********************************/
	private int contarFurgonetas(List<Alquiler> alquileres) {
		int contador = 0;
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getVehiculo() instanceof Furgoneta) {
				contador++;
			}
		}
		return contador;
	}

	/*********************
	 * EL METODO PARA CONTAR EL NUMERO DE AUTOBUSES
	 *********************************/
	private int contarAutobuses(List<Alquiler> alquileres) {
		int contador = 0;
		for (Alquiler alquiler : alquileres) {
			if (alquiler.getVehiculo() instanceof Autobus) {
				contador++;
			}
		}
		return contador;
	}

	private void actualizarPieChart(int totalTurismos, int totalFurgonetas, int totalAutobuses) {
		pieChart.getData().clear();
		pieChart.getData().add(new PieChart.Data("Turismos", totalTurismos));
		pieChart.getData().add(new PieChart.Data("Furgonetas", totalFurgonetas));
		pieChart.getData().add(new PieChart.Data("Autobuses", totalAutobuses));
	}

	@FXML
	void cancelar() {
		getEscenario().close();
	}
}
