import java.util.*;

public class MalSistema {
    static Student[] listaEstudiantes = new Student[5];
    static Course[] listaCursos = new Course[3];
    static int contadorEstudiantes = 0;
    static int contadorCursos = 0;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Sistema de Gestión Estudiantil");

        while (true) {
            System.out.println("1-Agregar estudiante  2-Inscribir  3-Listar  4-Promedios  5-Salir");
            String opcionMenu = teclado.nextLine();

            if (opcionMenu.equals("1")) {
                agregarEstudiante();
            } else if (opcionMenu.equals("2")) {
                inscribir(teclado);
            } else if (opcionMenu.equals("3")) {
                listarTodo();
            } else if (opcionMenu.equals("4")) {
                mostrarPromedios();
            } else if (opcionMenu.equals("5")) {
                break;
            }
        }
    }

    static void agregarEstudiante() {
        Scanner entrada = new Scanner(System.in);
        // SEGURIDAD: Validar que haya espacio en el arreglo
        if (contadorEstudiantes < 5) {
            System.out.println("Nombre:");
            String nombre = entrada.nextLine();
            System.out.println("Edad:");
            int edad = Integer.parseInt(entrada.nextLine());
            
            listaEstudiantes[contadorEstudiantes] = new Student(nombre, edad);
            contadorEstudiantes++;
        } else {
            System.out.println("Error: No se pueden agregar más de 5 estudiantes.");
        }
    }

    static void inscribir(Scanner teclado) {
        System.out.println("Posición del estudiante (0-4):");
        int pos = Integer.parseInt(teclado.nextLine());
        
        Student estudiante = null;
        if (pos >= 0 && pos < contadorEstudiantes) {
            estudiante = listaEstudiantes[pos];
        }

        if (estudiante == null) {
            System.out.println("Error: Estudiante no encontrado.");
            return;
        }

        System.out.println("Curso:");
        String cursoNombre = teclado.nextLine();
        Course cursoEncontrado = null;
        for (int i = 0; i < contadorCursos; i++) {
            if (listaCursos[i].nombre.equals(cursoNombre)) {
                cursoEncontrado = listaCursos[i];
            }
        }

        if (cursoEncontrado == null && contadorCursos < 3) {
            cursoEncontrado = new Course(cursoNombre, 10);
            listaCursos[contadorCursos] = cursoEncontrado;
            contadorCursos++;
        }

        if (cursoEncontrado != null) {
            estudiante.addCourse(cursoEncontrado);
            System.out.println("Inscripción exitosa.");
        } else {
            System.out.println("Error: Límite de cursos alcanzado.");
        }
    }

    static void listarTodo() {
        for (int i = 0; i < contadorEstudiantes; i++) {
            System.out.println("Estudiante: " + listaEstudiantes[i].nombre + " (" + listaEstudiantes[i].edad + " años)");
            for (int j = 0; j < listaEstudiantes[i].cursoCount; j++) {
                System.out.println("  - Curso: " + listaEstudiantes[i].cursos[j].nombre + " | Nota: " + listaEstudiantes[i].cursos[j].nota);
            }
        }
    }

    static void mostrarPromedios() {
        for (int i = 0; i < contadorEstudiantes; i++) {
            double suma = 0.0; // CAMBIO: Usamos double para precisión
            int totalCursos = listaEstudiantes[i].cursoCount;

            // SEGURIDAD: Evitar división por cero
            if (totalCursos > 0) {
                for (int j = 0; j < totalCursos; j++) {
                    suma += listaEstudiantes[i].cursos[j].nota;
                }
                double promedio = suma / totalCursos;
                System.out.println(listaEstudiantes[i].nombre + " - Promedio: " + promedio);
            } else {
                System.out.println(listaEstudiantes[i].nombre + " - Sin cursos inscritos.");
            }
        }
    }
}

class Student {
    String nombre;
    int edad;
    Course[] cursos = new Course[10];
    int cursoCount = 0;

    Student(String n, int e) {
        this.nombre = n;
        this.edad = e;
    }

    void addCourse(Course c) {
        if (cursoCount < 10) {
            cursos[cursoCount] = c;
            cursoCount++;
        }
    }
}

class Course {
    String nombre;
    int capacidad;
    double nota;

    Course(String n, int cap) {
        this.nombre = n;
        this.capacidad = cap;
        // Se cambió a un valor fijo para ser más realista que un random
        this.nota = 4.0; 
    }
}