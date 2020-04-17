
/**
 * Una furgoneta es un vehículo que añade la característica del volumen de carga
 * (valor de tipo double)
 * 
 * El coste de alquiler de una furgoneta no solo depende del nº de días de
 * alquiler
 * Tendrá un incremento que dependerá de su volumen de carga: hasta 5 m3
 * exclusive ( metros cúbicos) de volumen el incremento sobre el precio
 * final será de 10€, entre 5 y 10 (inclusive) el incremento sobre el precio
 * final será de 15€, si volumen > 10 el incremento sobre el precio final será de
 * 25€
 * Asier Gonzalez Gamboa
 */
public class Furgoneta extends Vehiculo{
	double volumen;
	
	public Furgoneta(String matricula, String marca, String modelo, double precio, double carga) {
		super(matricula, marca, modelo, precioDia);
		this.volumen = volumen;
	}

	public double precioAlquiler(int dias) {
		double precio = super.getPrecioDia();
		if (this.volumen < 5) {
			return (precio + 10) * dias;
		} else if (this.volumen >= 5 && this.volumen <= 10) {
			return (precio + 15) * dias;
		} else if (this.volumen > 10) {
			return (precio + 25) * dias;
		}
		return precio;
	}
public double getVolumen() {
	return volumen;
}
public void setVolumen(double volumen) {
	this.volumen = volumen;
}

	public String toString() {
		return "FURGONETA\n"+super.toString()+" | Volumen : "+ volumen +" (m3)";
	}

	public int compareTo(Vehiculo o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
