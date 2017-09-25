package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import view.LaunchView;

public class GestionDatos {

	public GestionDatos() {

	}

	//TODO: Implementa una función para abrir ficheros
	public BufferedReader abrirFichero(String nombre) throws FileNotFoundException {
		File f = new File(nombre);
		BufferedReader br = new BufferedReader(new FileReader(f));
		return br;
	}
	
	//TODO: Implementa una función para cerrar ficheros
	public boolean cerrarFichero(BufferedReader br) throws IOException {
		br.close();
		return true;
	}
	
	public boolean compararContenido (String fichero1, String fichero2) throws IOException {
		//TODO: Implementa la función
        // Si los ficheros tienen un tamaño distinto
		// nos salimos del metodo indicando que no son iguales
		// con false
		if (!compararTamano(fichero1,fichero2)) {
        	throw new FileNotFoundException();
        }
		BufferedReader br1 = abrirFichero(fichero1);
		BufferedReader br2 = abrirFichero(fichero2);
		String cadena="";
		//Si los ficheros son iguales
        //Comparamos linea a linea
	    //hasta encontrar una linea distinta
	    //del primer fichero con el segundo fichero
    	while ((cadena=br1.readLine())!=null) {
		  if (!cadena.equals(br2.readLine())) {
	        cerrarFichero(br1);
		    cerrarFichero(br2);
		    return false;
		  }
		}
	
	    //Si pasa todas las comprobaciones
		//cerramos los ficheros y devolvemos true
		//indicando que los dos ficheros son iguales
		cerrarFichero(br1);
		cerrarFichero(br2);
		return true;
	}
	
	public int buscarPalabra(String fichero1, String palabra, boolean primera_aparicion) throws IOException {
		// TODO: Implementa la función
		BufferedReader br = abrirFichero(fichero1);
		String cadena = "";
		int linea = 1;
		int posicion = -1;
		if (primera_aparicion) {
			// Primero tenemos que recorrer el fichero linea a linea
			while ((cadena = br.readLine()) != null) {
				String[] palabras = cadena.split(" ");
				// luego comprobamos si la palabra que buscamos esta en la linea actual
				// con un bucle for comparando la palabra dada con cada palabra de la linea
				// actual
				// si encuentra la palabra devuelve la linea donde la encontro
				for (String palabra2 : palabras) {
					if (palabra.equals(palabra2)) {
						cerrarFichero(br);
						return linea;
					}
				}
				linea++;
			}
		} else {
			// Primero tenemos que recorrer el fichero linea a linea
			while ((cadena = br.readLine()) != null) {
				String[] palabras = cadena.split(" ");
				// luego comprobamos si la palabra que buscamos esta en la linea actual
				// con un bucle for comparando la palabra dada con cada palabra de la linea
				// actual
				// si encuentra la palabra devuelve la linea donde la encontro
				for (String palabra2 : palabras) {
					if (palabra.equals(palabra2)) {
						posicion=linea;
					}
				}
				linea++;
			}
			return posicion;
		}
		cerrarFichero(br);
		// Si no encuentra la palabra devuelve -1 como resultado
		return -1;
	}
	
    public boolean compararTamano (String fichero1, String fichero2) {
    	//Comparamos los tamaños de los ficheros, si son ficheros iguales devuelve true
    	//si son ficheros distintos devuelve false
    	File f1 = new File(fichero1);
    	File f2 = new File(fichero2);
    	if (!f1.exists() || !f2.exists())
    		return false;
    	if (f1.length()!=f2.length())
       		 return false;
    	return true;
    }

}
