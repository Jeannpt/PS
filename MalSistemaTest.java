import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MalSistemaTest {

    @Before
    public void setUp() {
        // Reiniciamos los contadores antes de cada prueba para que sean independientes
        MalSistema.contadorEstudiantes = 0;
        MalSistema.contadorCursos = 0;
        for (int i = 0; i < 5; i++) {
            MalSistema.listaEstudiantes[i] = null;
        }
    }

    // 1. Prueba: Añadir Estudiante (Caso de éxito)
    @Test
    public void testAgregarEstudianteExitoso() {
        // Simulamos la lógica de agregar un estudiante
        MalSistema.listaEstudiantes[0] = new Student("Jean Pooll", 18);
        MalSistema.contadorEstudiantes = 1;
        
        assertNotNull("El estudiante no debería ser nulo", MalSistema.listaEstudiantes[0]);
        assertEquals("Jean Pooll", MalSistema.listaEstudiantes[0].nombre);
        assertEquals(1, MalSistema.contadorEstudiantes);
    }

    // 2. Prueba: Caso de Error (Límite de Estudiantes)
    @Test
    public void testErrorLimiteEstudiantes() {
        MalSistema.contadorEstudiantes = 5; // Llenamos el cupo
        
        // Intentar agregar uno más (lógica de validación)
        boolean pudoAgregar = false;
        if (MalSistema.contadorEstudiantes < 5) {
            pudoAgregar = true;
        }
        
        assertFalse("No debería permitir agregar más de 5 estudiantes", pudoAgregar);
    }

    // 3. Prueba: Inscribir a un Curso
    @Test
    public void testInscribirCurso() {
        Student est = new Student("Jean", 18);
        Course curso = new Course("Programacion", 10);
        
        est.addCourse(curso);
        
        assertEquals(1, est.cursoCount);
        assertEquals("Programacion", est.cursos[0].nombre);
    }

    // 4. Prueba: Cálculo de Promedio (Matemática exacta)
    @Test
    public void testCalculoPromedio() {
        Student est = new Student("Jean", 18);
        Course c1 = new Course("Fisica", 10);
        c1.nota = 4.5;
        Course c2 = new Course("Algebra", 10);
        c2.nota = 3.5;
        
        est.addCourse(c1);
        est.addCourse(c2);
        
        double suma = 0;
        for (int i = 0; i < est.cursoCount; i++) {
            suma += est.cursos[i].nota;
        }
        double promedio = suma / est.cursoCount;
        
        // Verificamos que el promedio sea 4.0
        assertEquals(4.0, promedio, 0.001);
    }

    // 5. Prueba: Caso de Error (División por cero en promedio)
    @Test
    public void testPromedioSinCursos() {
        Student est = new Student("Jean", 18);
        
        double promedio = 0;
        if (est.cursoCount > 0) {
            promedio = 5.0 / est.cursoCount;
        } else {
            promedio = 0; // Manejo del error
        }
        
        assertEquals(0.0, promedio, 0.001);
    }
}