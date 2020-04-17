
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * La clase guarda en una colección List (un ArrayList) la flota de vehículos
 * que una agencia de alquiler posee
 * 
 * Los vehículos se modelan como un interface List que se instanciará como una
 * colección concreta ArrayList
 * Asier Gonzalez Gamboa
 */
public class AgenciaAlquiler {
	private String nombre; // el nombre de la agencia
	private List<Vehiculo> flota; // la lista de vehículos

	/**
	 * Constructor
	 * 
	 * @param nombre el nombre de la agencia
	 */
	public AgenciaAlquiler(String nombre) {
		this.nombre = nombre;
		this.flota = new ArrayList<>();
	}

	/**
	 * añade un nuevo vehículo solo si no existe
	 * 
	 */
	public void addVehiculo(Vehiculo vehiculo) {
		if(!flota.contains(vehiculo)) {
			flota.add(vehiculo);
		}
	}

	/**
	 * Extrae los datos de una línea, crea y devuelve el vehículo
	 * correspondiente
	 * 
	 * Formato de la línea:
	 * C,matricula,marca,modelo,precio,plazas para coches
	 * F,matricula,marca,modelo,precio,volumen para furgonetas
	 * 
	 * 
	 * Asumimos todos los datos correctos. Puede haber espacios antes y después
	 * de cada dato
	 */
	private Vehiculo obtenerVehiculo(String linea) {
		String[] str = linea.split(",");
		for(int i = 0; i < str.length; i++) {
			str[i].trim();
		}
		if(str[0].equalsIgnoreCase("C")) {
			Vehiculo v = new Coche(str[1],str[2],str[3],
					Double.parseDouble(str[4]),Integer.parseInt(str[5]));
			return v;
		}
		else {
			Vehiculo v = new Furgoneta(str[1],str[2],str[3],
					Double.parseDouble(str[4]),Double.parseDouble(str[5]));
			return v;
		}		
	}

	/**
	 * La clase Utilidades nos devuelve un array con las líneas de datos
	 * de la flota de vehículos  
	 */
	public void cargarFlota() {
		String[] datos = Utilidades.obtenerLineasDatos();
		String error = null;
		try {
			for (String linea : datos) {
				error = linea;
				Vehiculo vehiculo = obtenerVehiculo(linea);
				this.addVehiculo(vehiculo);

			}
		}
		catch (Exception e) {
			System.out.println(error);
		}

	}

	/**
	 * Representación textual de la agencia
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Veh�culos en alquiler de la agencia ").append(nombre).append("\n");
		sb.append("Total veh�culos: ").append(flota.size()).append("\n\n");
		for(Vehiculo vehiculo : flota)
		{
			sb.append(vehiculo.toString()).append("\n");
			sb.append("-----------------------------------------------------\n");
		}
		return sb.toString();

	}

	/**
	 * Busca todos los coches de la agencia
	 * Devuelve un String con esta información y lo que
	 * costaría alquilar cada coche el nº de días indicado * 
	 *  
	 */
	public String buscarCoches(int n) {
		StringBuilder sb = new StringBuilder();
		for(Vehiculo v: flota) {
			if(v instanceof Coche) {
				sb.append(v.toString()).append("\n");
				sb.append("Coste alquiler ").append(n).append(" d�as: ");
				sb.append(v.calcularPrecioAlquiler(n));
				sb.append("-----------------").append("\n");
			}
			
		}
		return sb.toString();
	}

	/**
	 * Obtiene y devuelve una lista de coches con más de 4 plazas ordenada por
	 * matrícula - Hay que usar un iterador
	 * 
	 */
	public List<Coche> cochesOrdenadosMatricula() {
		
		ArrayList <Coche> r = new ArrayList<>();
		
		Iterator <Vehiculo> it = flota.iterator();
		
		while(it.hasNext())
		{
			Vehiculo vehiculo = it.next();
			
			if(vehiculo instanceof Coche)
			{
				if(((Coche) vehiculo).getPlazas() > 4)
				{
					r.add((Coche) vehiculo);
					}
			}
		}
		r.sort(Comparator.comparing(Coche::getMatricula));
		return r;
		
	}

	/**
	 * Devuelve la relación de todas las furgonetas ordenadas de
	 * mayor a menor volumen de carga
	 * 
	 */
	public List<Furgoneta> furgonetasOrdenadasPorVolumen() {

		Iterator<Vehiculo> it = flota.iterator();
		List<Furgoneta> furgonetas = new ArrayList<>();
		while(it.hasNext())
		{
			Vehiculo v = it.next();
			if(v instanceof Furgoneta)
			{
				furgonetas.add((Furgoneta) v);
			}
		}
		Collections.sort(furgonetas, new Comparator<Vehiculo>() {
			public int compare(Vehiculo uno, Vehiculo otro) {
				if (((Furgoneta) uno).getVolumen() > ((Furgoneta) otro).getVolumen()) {
					return -1;
				}
				if (((Furgoneta) uno).getVolumen() == ((Furgoneta) otro).getVolumen()) {
					return 0;
				}
				return 1;

			}
		});
		return furgonetas;

	}

	/**
	 * Genera y devuelve un map con las marcas (importa el orden) de todos los
	 * vehículos que hay en la agencia como claves y un conjunto (importa el orden) 
	 * de los modelos en cada marca como valor asociado
	 */
	public Map<String, Set<String>> marcasConModelos() {

		Map<String, Set<String>> marcas = new TreeMap<>();
		for(Vehiculo v: flota) {
			String marca = v.getMarca();
			String modelo = v.getModelo();
			if(marcas.containsKey(marca)) {
				marcas.get(marca).add(modelo);
			}
			else {
				Set<String> set = new TreeSet<>();
				set.add(modelo);
				
				marcas.put(marca, set);
			}
		}
		return marcas;
	}

}
