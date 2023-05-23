package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarClientes extends Controlador {
	
	@FXML
    private Button btAceptar;

	@FXML
	private TableColumn<Cliente, String> tcDni;

	@FXML
	private TableColumn<Cliente, String> tcNombre;

	@FXML
	private TableColumn<Cliente, String> tcTelefono;
	
	@FXML
	private TableView<Cliente> tvClientes;
	
	@FXML
	void aceptar(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void initialize() {
		tcDni.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getDni()));
		tcNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombre()));
		tcTelefono.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getTelefono()));
	}

	public void actualizar(List<Cliente> clientes) {
		tvClientes.setItems(FXCollections.observableArrayList(clientes));
	}
	
}
