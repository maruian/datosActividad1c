package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar;

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_compararContenido();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la función call_buscarPalabra
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
	}

	private int call_compararContenido() {
		// view.getTextArea().setText("hola");
		// TODO: Llamar a la función compararContenido de GestionDatos
		// TODO: Gestionar excepciones
		if ((view.getFichero1().getText().length()==0)||(view.getFichero2().getText().length()==0)){
			view.showError("Debes seleccionar dos ficheros");
			return 1;
		}
		try {
			if (model.compararContenido(view.getFichero1().getText(), view.getFichero2().getText())){
			  view.getTextArea().setText("Iguales");
			} else {
			  view.getTextArea().setText("Diferentes");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			view.showError("Error de entrada / salida");
		}
		return 1;
	}

	private int call_buscarPalabra() {
		// TODO: Llamar a la función buscarPalabra de GestionDatos
		// TODO: Gestionar excepciones
		int linea;
		if ((view.getFichero1().getText().length() == 0) || (view.getPalabra().getText().length() == 0)) {
			view.showError("Debes rellenar los campos");
			return 1;
		}
		if (view.getPrimera().isSelected()) {
			try {
				if ((linea = model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(),
						true)) != -1) {
					view.getTextArea().setText("Primera Aparición\nPalabra encontrada en la linea: " + linea);
				} else {
					view.getTextArea().setText("Primera Aparición\nPalabra NO encontrada");
				}
			} catch (IOException e) {
				view.showError("Error de entrada / salida");
			}
		} else {
			try {
				if ((linea = model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(),
						false)) != -1) {
					view.getTextArea().setText("Última aparición\nPalabra encontrada en la linea: " + linea);
				} else {
					view.getTextArea().setText("Última aparición\nPalabra NO encontrada");
				}
			} catch (IOException e) {
				view.showError("Error de entrada / salida");
			}
		}
		return 1;
	}

}
