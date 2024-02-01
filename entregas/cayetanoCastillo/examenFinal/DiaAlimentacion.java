package ProyectoFinal;

public class DiaAlimentacion {
    private int numeroDia;
    private List<Ingesta> ingestas;

    public DiaAlimentacion(int numeroDia) {
        this.numeroDia = numeroDia;
        this.ingestas = new List<>();
    }

    public void agregarIngesta(Ingesta ingesta) {
        this.ingestas.add(ingesta);
    }

    public Ingesta buscarIngesta(int tipoIngesta) {
        for (Ingesta ingesta : ingestas) {
            if (ingesta.getTipoIngesta() == tipoIngesta) {
                return ingesta;
            }
        }
        return null;
    }

    public int getNumeroDia() {
        return numeroDia;
    }

    public void setNumeroDia(int numeroDia) {
        this.numeroDia = numeroDia;
    }

    public List<Ingesta> getIngestas() {
        return ingestas;
    }

}
