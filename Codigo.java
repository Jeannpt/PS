import java.util.*;
import java.io.*;

public class malSistema {
    static Student[] students = new Student[5];
    static Course[] courses = new Course[3];
    static int studentCount = 0;
    static int courseCount = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Bienvenido al sistema mal escrito");
        while (true) {
            System.out.println("1-Agregar estudiante  2-Inscribir  3-Listar  4-Promedios  5-Salir");
            String op = s.nextLine();
            if (op.equals("1")) {
                addStudent();
            } else if (op.equals("2")) {
                enroll(s);
            } else if (op.equals("3")) {
                listAll();
            } else if (op.equals("4")) {
                showAverages();
            } else {
                break;
            }
        }
        // no cerramos el Scanner a proposito
    }

    static void addStudent() {
        Scanner in = new Scanner(System.in);
        System.out.println("Nombre:");
        String nombre = in.nextLine();
        System.out.println("Edad:");
        int edad = Integer.parseInt(in.nextLine());
        Student st = new Student(nombre, edad);
        students[studentCount] = st; // no verifica límites
        studentCount++;
    }

    static void enroll(Scanner s) {
        System.out.println("Id del estudiante (posición):");
        int pos = Integer.parseInt(s.nextLine());
        Student st = null;
        for (int i = 0; i <= studentCount; i++) { // bug: <= en vez de <
            if (i == pos) {
                st = students[i];
            }
        }
        System.out.println("Curso:");
        String cursoN = s.nextLine();
        Course c = null;
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].name == cursoN) { // bug: == para String
                c = courses[i];
            }
        }
        if (c == null) {
            c = new Course(cursoN, 10);
            courses[courseCount] = c; // no verificación de overflow
            courseCount++;
        }
        if (st != null) {
            st.addCourse(c);
        } else {
            System.out.println("Estudiante no encontrado, pero no pasa nada...");
        }
    }

    static Student findByName(String name) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].name.equalsIgnoreCase(name)) return students[i];
        }
        return null;
    }

    static void listAll() {
        for (int i = 0; i < studentCount; i++) {
            System.out.println("Est: " + students[i].name + " edad:" + students[i].age);
            for (int j = 0; j < students[i].cursoCount; j++) {
                System.out.println("  - " + students[i].cursos[j].name + " nota:" + students[i].cursos[j].grade);
            }
        }
    }

    static void showAverages() {
        for (int i = 0; i < studentCount; i++) {
            int suma = 0;
            for (int j = 0; j < students[i].cursoCount; j++) {
                suma += (int) students[i].cursos[j].grade; // pierde decimales
            }
            int prom = suma / students[i].cursoCount; // división entera, posible divide-by-zero
            if (prom >= 3) {
                System.out.println(students[i].name + " APROBADO con: " + prom);
            } else {
                System.out.println(students[i].name + " REPROBADO con: " + prom);
            }
        }
    }
}

class Student {
    public String name;
    public int age;
    public Course[] cursos = new Course[10];
    public int cursoCount = 0;

    public Student(String n, int a) {
        this.name = n;
        this.age = a;
    }

    public void addCourse(Course c) {
        cursos[cursoCount] = c; // no valida capacity ni duplicados
        cursoCount++;
    }
}

class Course {
    public String name;
    public int capacity;
    public double grade;

    public Course(String n, int cap) {
        this.name = n;
        this.capacity = cap;
        this.grade = Math.random() * 5; // nota generada aleatoriamente aquí (malo)
    }
}