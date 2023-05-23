package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class DevolverAlquilerCliente extends Controlador {
	@FXML
    private DatePicker dpFechaDevolucion;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    private Cliente cliente;

    public void mostrar(Cliente cliente) {
        this.cliente = cliente;
    }


    @FXML
     void initialize() {
       System.out.println("Ventana de devolver alquiler de cliente");
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
					VistaGrafica.getInstancia().getControlador().devolver(cliente, fechaDevolucion);
					Dialogos.mostrarDialogoConfirmacion("Devolver Alquiler Cliente", "El alquiler de cliente ha sido vuelto correctamente.",
							getEscenario());
				} catch (OperationNotSupportedException e) {
					Dialogos.mostrarDialogoError("Devolver alquiler cliente", e.getMessage(), getEscenario());
				}
        }
        getEscenario().close();
    }

}
