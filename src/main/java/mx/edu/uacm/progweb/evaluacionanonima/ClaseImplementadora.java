package mx.edu.uacm.progweb.evaluacionanonima;

import java.util.ArrayList;
import java.util.List;

public class ClaseImplementadora implements Interface {

	@Override
	public List<String> obtenerListado(int entero) {
		String s = "wewer";
		List<String> lista = new ArrayList<>();
		lista.add(s);
		
		return lista;
	}

}
