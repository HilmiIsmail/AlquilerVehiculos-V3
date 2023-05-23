package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class DevolverAlquilerVehiculo extends Controlador {
	@FXML
    private DatePicker dpFechaDevolucion;
	
	private Vehiculo vehiculo;
	
	public void mostrar(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
	
	@FXML
    void initialize() {
      System.out.println("Ventana de devolver alquiler de vehículo");
   }

	@FXML
    void cancelar(ActionEvent event) {
    	getEscenario().close();
    }

    @FXML
    void devolver(ActionEvent event) {
    	LocalDate fechaDevolucion = dpFechaDevolucion.getValue();
        if (fechaDevolucion != null) {
				try {
					VistaGrafica.getInstancia().getControlador().devolver(vehiculo, fechaDevolucion);
					Dialogos.mostrarDialogoConfirmacion("Devolver Alquiler Vehículo", "El alquiler de vehículo ha sido vuelto correctamente.",
							getEscenario());
				} catch (OperationNotSupportedException e) {
					Dialogos.mostrarDialogoError("Devolver alquiler vehiculo", e.getMessage(), getEscenario());
				}
        }
        getEscenario().close();
    

    }
}
