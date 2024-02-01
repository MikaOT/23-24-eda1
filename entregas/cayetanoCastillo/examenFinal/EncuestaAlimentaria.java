package ProyectoFinal;

import java.util.Scanner;

public class EncuestaAlimentaria {
    private Paciente paciente;
    private Scanner scanner;
    private double altura;
    private double peso;
    private String sexo;
    private int edad;
    private String frecuenciaEjercicio;

    public EncuestaAlimentaria(String nombrePaciente, double altura, double peso, String sexo, int edad,
            String frecuenciaEjercicio) {
        this.paciente = new Paciente(nombrePaciente);
        this.altura = altura;
        this.peso = peso;
        this.sexo = sexo;
        this.edad = edad;
        this.frecuenciaEjercicio = frecuenciaEjercicio;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarEncuesta() {
        while (true) {
            int seleccionDia = solicitarDia();
            if (seleccionDia == -1) {
                break;
            }

            DiaAlimentacion dia = obtenerOCrearDia(seleccionDia);
            manejarIngestas(dia);
        }

        mostrarResumen();
    }

    private int solicitarDia() {
        System.out.println("Seleccione día (1-5) o -1 para terminar:");
        int seleccionDia = scanner.nextInt();
        while (seleccionDia < -1 || seleccionDia > 5 || seleccionDia == 0) {
            System.out.println("Por favor, ingrese un número entre 1 y 5 para el día, o -1 para terminar.");
            seleccionDia = scanner.nextInt();
        }
        return seleccionDia;
    }

    private DiaAlimentacion obtenerOCrearDia(int numeroDia) {
        DiaAlimentacion dia = paciente.buscarDiaAlimentacion(numeroDia);
        if (dia == null) {
            dia = new DiaAlimentacion(numeroDia);
            paciente.agregarDiaAlimentacion(dia);
        }
        return dia;
    }

    private void manejarIngestas(DiaAlimentacion dia) {
        while (true) {
            int seleccionIngesta = solicitarIngesta();
            if (seleccionIngesta == -1) {
                break;
            }

            Ingesta ingesta = obtenerOCrearIngesta(dia, seleccionIngesta);
            manejarAlimentos(ingesta);
        }
    }

    private int solicitarIngesta() {
        System.out.println(
                "Seleccione ingesta: 1 (Desayuno), 2 (Media mañana), 3 (Almuerzo), 4 (Merienda), 5 (Cena), -1 (Menú anterior):");
        int seleccionIngesta = scanner.nextInt();
        while (seleccionIngesta < -1 || seleccionIngesta > 5 || seleccionIngesta == 0) {
            System.out.println(
                    "Por favor, ingrese un número entre 1 y 5 para la ingesta, o -1 para volver al menú anterior.");
            seleccionIngesta = scanner.nextInt();
        }
        return seleccionIngesta;
    }

    private Ingesta obtenerOCrearIngesta(DiaAlimentacion dia, int tipoIngesta) {
        Ingesta ingesta = dia.buscarIngesta(tipoIngesta);
        if (ingesta == null) {
            ingesta = new Ingesta(tipoIngesta);
            dia.agregarIngesta(ingesta);
        }
        return ingesta;
    }

    private void manejarAlimentos(Ingesta ingesta) {
        while (true) {
            System.out.println("Ingrese un alimento para la " + tipoIngesta(ingesta.getTipoIngesta())
                    + " o '-1' para finalizar, '-2' para listar alimentos:");
            String seleccionAlimento = scanner.next();

            if (seleccionAlimento.equals("-1")) {
                break;
            } else if (seleccionAlimento.equals("-2")) {
                listarAlimentos(ingesta);
            } else {
                ingesta.agregarAlimento(new Alimento(seleccionAlimento));
            }
        }
    }

    private void listarAlimentos(Ingesta ingesta) {
        System.out.println("Alimentos ingresados en " + ingesta.getTipoIngesta() + ":");
        for (Alimento alimento : ingesta.getAlimentos()) {
            System.out.println(alimento.getNombre());
        }
    }

    private String tipoIngesta(int seleccionIngesta) {
        switch (seleccionIngesta) {
            case 1:
                return "Desayuno";
            case 2:
                return "Media mañana";
            case 3:
                return "Almuerzo";
            case 4:
                return "Merienda";
            case 5:
                return "Cena";
            default:
                return "Desconocido";
        }
    }

    public void mostrarResumen() {
        System.out.println("Resumen de la encuesta alimentaria para: " + paciente.getNombre());
        System.out.println("Edad: " + edad + " años");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " cm");
        System.out.println("Sexo: " + sexo);
        System.out.println("Frecuencia de ejercicio: " + frecuenciaEjercicio);

        Node<DiaAlimentacion> diaNode = paciente.getDiasAlimentacion().getHead();
        while (diaNode != null) {
            DiaAlimentacion dia = diaNode.getData();
            System.out.println("Día: " + dia.getNumeroDia());

            Node<Ingesta> ingestaNode = dia.getIngestas().getHead();
            while (ingestaNode != null) {
                Ingesta ingesta = ingestaNode.getData();
                System.out.println("  Ingesta: " + ingesta.getTipoIngesta());

                for (Alimento alimento : ingesta.getAlimentos()) {
                    System.out.println("    Alimento: " + alimento.getNombre());
                }
                ingestaNode = ingestaNode.getNext();
            }
            diaNode = diaNode.getNext();
        }
    }
}
