
package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class VentanaPrincipal extends Controlador {

	@FXML
	private MenuItem salir;

	@FXML
	private void initialize() {

		System.out.println("Ventana Principal");
	}

	/**********************************************
	 * CLIENTES
	 ****************************************************************/
	@FXML
	void listarClientes(ActionEvent event) {
		// System.out.println("Boton pulsado");
		ListarClientes listarClientes = (ListarClientes) Controladores.get("vistas/ListarClientes.fxml",
				"ListarClientes", getEscenario());
		listarClientes.actualizar(VistaGrafica.getInstancia().getControlador().getClientes());
		listarClientes.getEscenario().showAndWait();
	}

	@FXML
	void leerCliente(ActionEvent event) {
		LeerCliente leerCliente = (LeerCliente) Controladores.get("vistas/LeerCliente.fxml", "Leer Cliente",
				getEscenario());
		leerCliente.limpiar();
		leerCliente.getEscenario().showAndWait();
		try {
			Cliente cliente = leerCliente.getCliente();
			if (cliente != null) {
				VistaGrafica.getInstancia().getControlador().insertar(cliente);
				Dialogos.mostrarDialogoAdvertencia("Insertar Clliente", "Cliente insertado correctamente",
						getEscenario());
			}
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Insertar Cliente", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void buscarCliente(ActionEvent event) {
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
					MostrarCliente mostrarCliente = (MostrarCliente) Controladores.get("vistas/MostrarCliente.fxml",
							"Mostrar Cliente", getEscenario());
					mostrarCliente.mostrar(clienteEncontrado);
					mostrarCliente.getEscenario().showAndWait();
				} else {
					Dialogos.mostrarDialogoInformacion("Buscar Cliente",
							"No se encontró ningún cliente con el DNI " + dni, getEscenario());
				}
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Buscar Cliente", e.getMessage(), getEscenario());
		}
	}

	/**********************************************
	 * VEHICULOS
	 ****************************************************************/
	@FXML
	void listarVehiculos(ActionEvent event) {
		ListarVehiculos listarVehiculos = (ListarVehiculos) Controladores.get("vistas/ListarVehiculos.fxml",
				"Listar Vehiculos", getEscenario());
		listarVehiculos.cargarVehiculos(VistaGrafica.getInstancia().getControlador().getVehiculos());
		listarVehiculos.getEscenario().showAndWait();
	}

	@FXML
	void leerVehiculo(ActionEvent event) {
		LeerVehiculo leerVehiculo = (LeerVehiculo) Controladores.get("vistas/LeerVehiculo.fxml", "Leer Vehículo",
				getEscenario());
		leerVehiculo.limpiar();
		leerVehiculo.getEscenario().showAndWait();
		try {
			Vehiculo vehiculo = leerVehiculo.getVehiculo();
			if (vehiculo != null) {
				VistaGrafica.getInstancia().getControlador().insertar(vehiculo);
				Dialogos.mostrarDialogoAdvertencia("Insertar Vehículo", "Vehículo insertado correctamente",
						getEscenario());
			}
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Insertar Vehículo", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void buscarVehiculo(ActionEvent event) {
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
					MostrarVehiculoInfo mostrarVehiculo = (MostrarVehiculoInfo) Controladores
							.get("vistas/MostrarVehiculoInfo.fxml", "Mostrar Vehículo", getEscenario());
					mostrarVehiculo.mostrar(vehiculoEncontrado);
					mostrarVehiculo.getEscenario().showAndWait();
				} else {
					Dialogos.mostrarDialogoInformacion("Buscar Vehiculo",
							"No se encontró ningún vehículo con la matrícula " + matricula, getEscenario());
				}
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Buscar Cliente", e.getMessage(), getEscenario());
		}

	}

	/**********************************************
	 * ALQUILERES
	 ****************************************************************/

	@FXML
	void listarAlquileres(ActionEvent event) {
		ListarAlquileres listarAlquileres = (ListarAlquileres) Controladores.get("vistas/ListarAlquileres.fxml",
				"Listar Alquileres", getEscenario());
		listarAlquileres.cargarAlquileres(VistaGrafica.getInstancia().getControlador().getAlquileres());
		listarAlquileres.getEscenario().showAndWait();
	}

	@FXML
	void leerAlquiler(ActionEvent event) {

		LeerAlquiler leerAlquiler = (LeerAlquiler) Controladores.get("vistas/LeerAlquiler.fxml", "Leer Alquiler",
				getEscenario());
		leerAlquiler.limpiar();
		leerAlquiler.getEscenario().showAndWait();
		try {
			Alquiler alquiler = leerAlquiler.getAlquiler();
			if (alquiler != null) {
				VistaGrafica.getInstancia().getControlador().insertar(alquiler);
				Dialogos.mostrarDialogoAdvertencia("Insertar Alquiler", "Alquiler insertado correctamente",
						getEscenario());
			}
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			Dialogos.mostrarDialogoError("Insertar Alquiler", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void buscarAlquiler(ActionEvent event) {
	    BuscarAlquiler buscarAlquiler = (BuscarAlquiler) Controladores.get("vistas/BuscadorAlquiler.fxml",
	            "Buscar Alquiler", getEscenario());

	    buscarAlquiler.limpiar();
	    buscarAlquiler.getEscenario().showAndWait();

	    try {
	        Alquiler alquilerBuscado = buscarAlquiler.getAlquiler();
	        if (alquilerBuscado != null) {
	            Alquiler alquilerEncontrado = VistaGrafica.getInstancia().getControlador().buscar(alquilerBuscado);

	            if (alquilerEncontrado != null) {
	                MostrarAlquilerInfo mostrarAlquiler = (MostrarAlquilerInfo) Controladores
	                        .get("vistas/MostrarAlquilerInfo.fxml", "Mostrar Alquiler", getEscenario());
	                mostrarAlquiler.mostrar(alquilerEncontrado);
	                mostrarAlquiler.getEscenario().showAndWait();
	            } else {
	                Dialogos.mostrarDialogoInformacion("Buscar Alquiler", "No se encontró ningún alquiler",
	                        getEscenario());
	            }
	        }
	    } catch (IllegalArgumentException | NullPointerException e) {
	        Dialogos.mostrarDialogoError("Buscar Alquiler", e.getMessage(), getEscenario());
	    }
	}


	/***********************************************************************************************************************/
	

	/***********************************************************************************************************************/

	@FXML
	void confirmarSalida() {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				getEscenario())) {
			getEscenario().close();
		}
	}

	@FXML
	void acercaDe(ActionEvent event) {
		AcercaDe acercaDe = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Acerca de", getEscenario());
		acercaDe.getEscenario().showAndWait();
	}

}
