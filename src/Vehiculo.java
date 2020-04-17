
/**
 * Representa a un vehículo en alquiler
 * De esta clase no se crearán instancias
 * 
 * De un vehículo se conoce su matrícula, marca, modelo y el precio a pagar por
 * día de alquiler
 * 
 * Para todo vehículo se puede calcular su coste de alquiler que depende del nº
 * de días que se alquile (llamaremos a esta operación calcularPrecioAlquiler() )
 * 
 * Dos vehículos pueden compararse por su matrícula (es su orden natural)
 * 
 * Dos vehículos son iguales si además de pertenecer a la misma clase tienen la
 * misma matrícula
 * Asier Gonzalez Gamboa
 * 
 */
public abstract class Vehiculo implements Comparable<Vehiculo>{
	private String matricula;
	private String marca;
	private String modelo;
	private double precioDia;

	/**
	 * Constructor
	 */
	public Vehiculo(String matricula, String marca, String modelo,
	        double precioDia) {
		this.matricula = matricula.toUpperCase();
		this.marca = marca.toUpperCase();
		this.modelo = modelo.toUpperCase();
		this.precioDia = precioDia;

	}
public String getMarca() {
	return marca;
}
public String getMatricula() {
	return matricula;
}
public String getModelo() {
	return modelo;
}
public double getPrecioDia() {
	return precioDia;
}
	public double calcularPrecioAlquiler(int diasTotal) {
		return diasTotal * precioDia;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Vehiculo v = (Vehiculo) obj;
			return v.getMatricula() == matricula;
	}
	public String toString() {
		String str = "Matricula: " + matricula + 
				"| Marca: " + marca + 
				"| Modelo: " + modelo + 
				"\nPrecio d�a alquiler: " + precioDia;
		return str;
	}
	/**
	 * Redefinición de hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		return matricula.hashCode() * 13;
	}
	
}