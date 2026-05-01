import java.util.*;

public class MalSistema { // Corregido: Empieza con Mayúscula
    static Student[] listaEstudiantes = new Student[5];
    static Course[] listaCursos = new Course[3];
    static int contadorEstudiantes = 0;
    static int contadorCursos = 0;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido al sistema");

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
        System.out.println("Nombre:");
        String nombre = entrada.nextLine();
        System.out.println("Edad:");
        int edad = Integer.parseInt(entrada.nextLine());
        
        listaEstudiantes[contadorEstudiantes] = new Student(nombre, edad);
        contadorEstudiantes++;
    }

    static void inscribir(Scanner teclado) {
        System.out.println("Posición del estudiante:");
        int pos = Integer.parseInt(teclado.nextLine());
        
        Student estudiante = null;
        for (int i = 0; i <= contadorEstudiantes; i++) {
            if (i == pos) {
                estudiante = listaEstudiantes[i];
            }
        }

        System.out.println("Curso:");
        String cursoNombre = teclado.nextLine();
        Course cursoEncontrado = null;
        for (int i = 0; i < contadorCursos; i++) {
            if (listaCursos[i].nombre == cursoNombre) {
                cursoEncontrado = listaCursos[i];
            }
        }

        if (cursoEncontrado == null) {
            cursoEncontrado = new Course(cursoNombre, 10);
            listaCursos[contadorCursos] = cursoEncontrado;
            contadorCursos++;
        }

        if (estudiante != null) {
            estudiante.addCourse(cursoEncontrado);
        }
    }

    static void listarTodo() {
        for (int i = 0; i < contadorEstudiantes; i++) {
            System.out.println("Est: " + listaEstudiantes[i].nombre);
            for (int j = 0; j < listaEstudiantes[i].cursoCount; j++) {
                System.out.println("  - " + listaEstudiantes[i].cursos[j].nombre);
            }
        }
    }

    static void mostrarPromedios() {
        for (int i = 0; i < contadorEstudiantes; i++) {
            int suma = 0;
            for (int j = 0; j < listaEstudiantes[i].cursoCount; j++) {
                suma += (int) listaEstudiantes[i].cursos[j].nota;
            }
            int prom = suma / listaEstudiantes[i].cursoCount;
            System.out.println(listaEstudiantes[i].nombre + " promedio: " + prom);
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
        cursos[cursoCount] = c;
        cursoCount++;
    }
}

class Course {
    String nombre;
    int capacidad;
    double nota;

    Course(String n, int cap) {
        this.nombre = n;
        this.capacidad = cap;
        this.nota = Math.random() * 5;
    }
}