import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesoresEstudiantes {
    ArrayList<String> listaProfesores;
    ArrayList<String> listEstudiantes;
    ArrayList<ArrayList<String>> listaGeneralEstudiantes;

    public ProfesoresEstudiantes() {
        listaProfesores = new ArrayList<>();
        listaGeneralEstudiantes = new ArrayList<>();
    }

    public void iniciar() {
        int opcion = 0;
        do {
            String menu = "<<<< MENÚ DE OPCIONES >>>>\n" +
                    "1. Registrar profesores\n" +
                    "2. Registrar estudiantes asociados al profesor\n" +
                    "3. Consultar lista total de profesores y sus estudiantes asociados\n" +
                    "4. Consultar un profesor e imprimir la lista de estudiantes asociados\n" +
                    "5. Consultar un profesor e indicar la cantidad de estudiantes asociados\n" +
                    "6. Consultar un estudiante e indicar cual es su director de grupo\n" +
                    "7. Salir\n\n" +
                    "Elija una opción:";

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                break;
            }

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarProfesores();
                    break;
                case 2:
                    registrarEstudiantes();
                    break;
                case 3:
                    consultarListaProfesoresYEstudiantes();
                    break;
                case 4:
                    consultaProfesorPorNombre();
                    break;
                case 5:
                    consultaCantidadEstudiantesPorProfesor();
                    break;
                case 6:
                    consultarEstudiante();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private void registrarProfesores() {
        int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores a registrar:"));
        for (int i = 0; i < cant; i++) {
            String nombreProfesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor " + (i + 1) + ":");
            if (nombreProfesor != null && !nombreProfesor.trim().isEmpty()) {
                if (listaProfesores.contains(nombreProfesor)) {
                    JOptionPane.showMessageDialog(null, "El profesor " + nombreProfesor + " ya existe. No se puede registrar dos veces.");
                    i--;
                } else {
                    listaProfesores.add(nombreProfesor);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Registro de profesores exitoso.");
    }

    private void registrarEstudiantes() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores antes de registrar estudiantes.");
            return;
        }

        listaGeneralEstudiantes.clear();
        for (int i = 0; i < listaProfesores.size(); i++) {
            listEstudiantes = new ArrayList<>();
            int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de estudiantes para el profesor " + listaProfesores.get(i) + ":"));
            for (int j = 0; j < cant; j++) {
                String nombreEst = JOptionPane.showInputDialog("Ingrese el nombre del estudiante " + (j + 1) + " para el profesor " + listaProfesores.get(i) + ":");
                if (nombreEst != null && !nombreEst.trim().isEmpty()) {
                    if (listEstudiantes.contains(nombreEst)) {
                        JOptionPane.showMessageDialog(null, "El estudiante " + nombreEst + " ya está registrado en este grupo.");
                        j--;
                    } else {
                        listEstudiantes.add(nombreEst);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre inválido. Intente de nuevo.");
                    j--;
                }
            }
            listaGeneralEstudiantes.add(listEstudiantes);
        }
        JOptionPane.showMessageDialog(null, "Registro de estudiantes exitoso.");
    }

    private void consultarListaProfesoresYEstudiantes() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados.");
            return;
        }

        StringBuilder reporte = new StringBuilder("Lista total de profesores y sus estudiantes:\n\n");
        for (int i = 0; i < listaProfesores.size(); i++) {
            reporte.append("Profesor: ").append(listaProfesores.get(i)).append("\n");
            ArrayList<String> estudiantes = listaGeneralEstudiantes.get(i);
            if (estudiantes.isEmpty()) {
                reporte.append("  - No tiene estudiantes asociados.\n");
            } else {
                for (String est : estudiantes) {
                    reporte.append("  - ").append(est).append("\n");
                }
            }
            reporte.append("\n");
        }
        JOptionPane.showMessageDialog(null, reporte.toString());
    }

    private void consultaProfesorPorNombre() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados.");
            return;
        }

        String nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre del profesor a consultar:");
        if (nombreBuscado != null && !nombreBuscado.trim().isEmpty()) {
            int index = listaProfesores.indexOf(nombreBuscado);
            if (index != -1) {
                StringBuilder reporte = new StringBuilder("Estudiantes del profesor " + nombreBuscado + ":\n\n");
                ArrayList<String> estudiantes = listaGeneralEstudiantes.get(index);
                if (estudiantes.isEmpty()) {
                    reporte.append("No tiene estudiantes asociados.");
                } else {
                    for (String est : estudiantes) {
                        reporte.append("- ").append(est).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(null, reporte.toString());
            } else {
                JOptionPane.showMessageDialog(null, "El profesor no se encuentra registrado.");
            }
        }
    }

    private void consultaCantidadEstudiantesPorProfesor() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados.");
            return;
        }

        String nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre del profesor:");
        if (nombreBuscado != null && !nombreBuscado.trim().isEmpty()) {
            int index = listaProfesores.indexOf(nombreBuscado);
            if (index != -1) {
                int cantidad = listaGeneralEstudiantes.get(index).size();
                JOptionPane.showMessageDialog(null, "El profesor " + nombreBuscado + " tiene " + cantidad + " estudiante(s) asociado(s).");
            } else {
                JOptionPane.showMessageDialog(null, "El profesor no se encuentra registrado.");
            }
        }
    }

    private void consultarEstudiante() {
        if (listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.");
            return;
        }

        String estudianteBuscado = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar:");
        if (estudianteBuscado != null && !estudianteBuscado.trim().isEmpty()) {
            boolean encontrado = false;
            for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
                if (listaGeneralEstudiantes.get(i).contains(estudianteBuscado)) {
                    String profesorDirector = listaProfesores.get(i);
                    JOptionPane.showMessageDialog(null, "El estudiante " + estudianteBuscado + " pertenece al grupo del profesor: " + profesorDirector);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "El estudiante no se encuentra registrado en ningún grupo.");
            }
        }
    }

    public static void main(String[] args) {
        ProfesoresEstudiantes sistema = new ProfesoresEstudiantes();
        sistema.iniciar();
    }
}