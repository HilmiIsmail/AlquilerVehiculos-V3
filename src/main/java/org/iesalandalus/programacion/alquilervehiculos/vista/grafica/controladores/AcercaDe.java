package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.ventanas.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AcercaDe extends Controlador {

	@FXML
	private ImageView ivImagen;

	@FXML
	private Label lbAutor;

	@FXML
	private Label lbTitulo;

	@FXML
	private Label lbVersion;

	@FXML
	private void initialize() {
		System.out.println("Ventana Acerca de");

	}
}
