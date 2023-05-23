package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarAlquileres extends Controlador {
	@FXML
	private Button btAceptar;
	@FXML
	private TableView<Alquiler> tvAlquileres;
	@FXML
	private TableColumn<Alquiler, Cliente> tcCliente;
	@FXML
	private TableColumn<Alquiler, Vehiculo> tcVehiculo;
	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaAlquiler;
	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaDevolucion;
	@FXML
	private TableColumn<Alquiler, Integer> tcPrecio;

	@FXML
	void aceptar(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void initialize() {
		tcCliente.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getCliente()));
		tcVehiculo.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getVehiculo()));
		tcFechaAlquiler.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getFechaAlquiler()));
		tcFechaDevolucion.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getFechaDevolucion()));
		tcPrecio.setCellValueFactory(fila -> new SimpleIntegerProperty(fila.getValue().getPrecio()).asObject());
	}

	public void cargarAlquileres(List<Alquiler> alquileres) {
		tvAlquileres.setItems(FXCollections.observableArrayList(alquileres));
	}

	@FXML
	void mostrarEstadisticasMensuales(ActionEvent event) {
		// Abrir la ventana de MostrarEstadisticasMensuales
		MostrarEstadisticas estadisticas = (MostrarEstadisticas) Controladores.get("vistas/EstadisticasMensuales.fxml",
				"Estadísticas Mensuales", getEscenario());
		estadisticas.getEscenario().showAndWait();
	}
	@FXML
    void listarAlquileresCliente(ActionEvent event) {
		BuscarCliente buscarCliente = (BuscarCliente) Controladores.get("vistas/BuscadorCliente.fxml", "Buscar Cliente",
				getEscenario());
		buscarCliente.limpiar();
		buscarCliente.getEscenario().showAndWait();
		
		try {
			String dni = buscarCliente.getDni();
			if (dni != null) {
				Cliente clienteEncontrado = VistaGrafica.getInstancia().getControlador()
						.buscar(Cliente.getClienteConDni(dni));
				if (clienteEncontrado != null) {
					ListarAlquileresCliente listarAlquileresCliente = (ListarAlquileresCliente) Controladores
							.get("vistas/ListarAlquileresCliente.fxml", "Listar Alquileres del Cliente", getEscenario());
					listarAlquileresCliente.actualizar(
							VistaGrafica.getInstancia().getControlador().getAlquileres(Cliente.getClienteConDni(dni)));
					listarAlquileresCliente.getEscenario().showAndWait();
				} else {
					Dialogos.mostrarDialogoInformacion("Listar Alquileres Cliente",
							"No se encontró ningún alquiler con el DNI " + dni, getEscenario());
				}
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Listar Alquileres Cliente", e.getMessage(), getEscenario());
		}
		
    }

    @FXML
    void listarAlquileresVehiculo(ActionEvent event) {
		BuscarVehiculo buscarVehiculo = (BuscarVehiculo) Controladores.get("vistas/BuscadorVehiculo.fxml",
				"Buscar Vehículo", getEscenario());

		buscarVehiculo.limpiar();
		buscarVehiculo.getEscenario().showAndWait();

		try {
			String matricula = buscarVehiculo.getMatricula();
			if (matricula != null) {
				Vehiculo vehiculoEncontrado = VistaGrafica.getInstancia().getControlador()
						.buscar(Vehiculo.getVehiculoConMatricula(matricula));
				if (vehiculoEncontrado != null) {
					ListarAlquileresVehiculo listarAlquileresVehiculo = (ListarAlquileresVehiculo) Controladores
							.get("vistas/ListarAlquileresVehiculo.fxml", "Listar Alquileres del Vehículo", getEscenario());
					listarAlquileresVehiculo.actualizar(VistaGrafica.getInstancia().getControlador()
							.getAlquileres(Vehiculo.getVehiculoConMatricula(matricula)));
					listarAlquileresVehiculo.getEscenario().showAndWait();

				} else {
					Dialogos.mostrarDialogoInformacion("Listar Alquileres Vehículo",
							"No se encontró ningún alquileres por la matrícula " + matricula, getEscenario());
				}
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Listar Alquileres Cliente", e.getMessage(), getEscenario());
		}

	

    }
}
