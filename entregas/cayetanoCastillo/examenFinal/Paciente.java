package ProyectoFinal;

public class Paciente {
    private String nombre;
    private List<DiaAlimentacion> diasAlimentacion;

    public Paciente(String nombre) {
        this.nombre = nombre;
        this.diasAlimentacion = new List<>();
    }

    public void agregarDiaAlimentacion(DiaAlimentacion dia) {
        this.diasAlimentacion.add(dia);
    }

    public DiaAlimentacion buscarDiaAlimentacion(int numeroDia) {
        for (DiaAlimentacion dia : diasAlimentacion) {
            if (dia.getNumeroDia() == numeroDia) {
                return dia;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DiaAlimentacion> getDiasAlimentacion() {
        return diasAlimentacion;
    }

}
