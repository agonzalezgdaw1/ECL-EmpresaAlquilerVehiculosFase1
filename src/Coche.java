
/**
 * Un coche es un vehículo que añade un nº de plazas
 * 
 * El coste final de alquiler depende no solo del nº de días de alquiler 
 * sino también del nº de plazas. Si el nº de plazas es > 4 se añaden 5€ más por día
 * Asier Gonzalez Gamboa
 */
public class Coche extends Vehiculo {
	
	private int plazas;

	public Coche(String matricula, String marca, String modelo, double precioDia, int plazas) {
		super(matricula, marca, modelo, precioDia);
		this.plazas = plazas;
	}

	public int getPlazas() {
		return plazas;
	}

	public int BonusAsientos() {
		if (plazas > 4) {
			return 5;
		}
		return 0;
	}

	public String toString() {
		return super.toString() + "Plazas: " + plazas;
	}

	@Override
	public int compareTo(Vehiculo o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
