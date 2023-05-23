package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarVehiculos extends Controlador {

	@FXML
	private Button btnAceptar;
	@FXML
	private TableView<Vehiculo> tvVehiculos;
	@FXML
	private TableColumn<Vehiculo, String> tcMatricula;
	@FXML
	private TableColumn<Vehiculo, String> tcMarca;
	@FXML
	private TableColumn<Vehiculo, String> tcModelo;
	@FXML
	private TableColumn<Vehiculo, Integer> tcCilindrada;
	@FXML
	private TableColumn<Vehiculo, Integer> tcPlazas;
	@FXML
	private TableColumn<Vehiculo, Integer> tcPma;
	

	@FXML
	void aceptar(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	public void initialize() {
	    // Configuración de las propiedades de las columnas
	    tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
	    tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
	    tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

	    // Configuración de la propiedad de cilindrada
	    tcCilindrada.setCellValueFactory(fila -> {
	        Vehiculo vehiculo = fila.getValue();
	        if (vehiculo instanceof Turismo) {
	            // Si es un Turismo, se muestra la cilindrada
	            Turismo turismo = (Turismo) vehiculo;
	            return new SimpleIntegerProperty(turismo.getCilindrada()).asObject();
	        } else {
	            // Si no es un Turismo, se establece un valor de cero
	            return new SimpleIntegerProperty(0).asObject();
	        }
	    });

	    // Configuración de la propiedad de plazas
	    tcPlazas.setCellValueFactory(fila -> {
	        Vehiculo vehiculo = fila.getValue();
	        if (vehiculo instanceof Autobus) {
	            // Si es un Autobus, se muestra el número de plazas
	            Autobus autobus = (Autobus) vehiculo;
	            return new SimpleIntegerProperty(autobus.getPlazas()).asObject();
	        } else {
	            // Si no es un Autobus, se establece un valor de cero
	            return new SimpleIntegerProperty(0).asObject();
	        }
	    });

	    // Configuración de la propiedad de PMA
	    tcPma.setCellValueFactory(fila -> {
	        Vehiculo vehiculo = fila.getValue();
	        if (vehiculo instanceof Furgoneta) {
	            // Si es una Furgoneta, se muestra el PMA
	            Furgoneta furgoneta = (Furgoneta) vehiculo;
	            return new SimpleIntegerProperty(furgoneta.getPma()).asObject();
	        } else {
	            // Si no es una Furgoneta, se establece un valor de cero
	            return new SimpleIntegerProperty(0).asObject();
	        }
	    });
	}



	public void cargarVehiculos(List<Vehiculo> vehiculos) {
		tvVehiculos.setItems(FXCollections.observableArrayList(vehiculos));
	}
}
