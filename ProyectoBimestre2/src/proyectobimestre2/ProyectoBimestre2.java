package proyectobimestre2;
import java.io.File;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
public class ProyectoBimestre2 {
    public static void main(String[] args) {
        int limPostulantes=100, limDatos=7,diaLimite;
        String matrizDatosPostulantes[][] = new String [limPostulantes][limDatos];
        String datosSalidaQuimica[][]= new String[limPostulantes][limDatos];
        String datosSalidaFisio[][]= new String[limPostulantes][limDatos];
        String datosSalidaMedicina[][]= new String[limPostulantes][limDatos];
        generarDatosPostulantes(matrizDatosPostulantes);
        leerNotas(matrizDatosPostulantes);
        // Llamar al método generarFechaInscripcion
        int diaInscripcion = generarFechaInscripcion();
        inscrpExamenAdmision(matrizDatosPostulantes, diaInscripcion);
        ResultadoExamenQuimica(matrizDatosPostulantes, datosSalidaQuimica); 
        ResultadoExamenFisio(matrizDatosPostulantes, datosSalidaFisio); 
        ResultadoExamenMedicina(matrizDatosPostulantes, datosSalidaMedicina);
        escribirArchivo(datosSalidaQuimica, datosSalidaFisio, datosSalidaMedicina, matrizDatosPostulantes);
    }
    
    public static int generarFechaInscripcion() {
        // fecha aleatoria entre 1 y 28 de marzo
        int fechaInscripcion=(int)(Math.random()*(28-1+1)+28);
        return (int) (Math.random() * 28) + 1;
    }
    
    public static void inscrpExamenAdmision(String matrizDatosPostulantes[][], int diaInscripcion) {
        int diaLimite = 20;
        if (diaInscripcion <= diaLimite) {
            // Presentar la parte de la fecha en la consola
            System.out.println("Su inscripción fue el " + diaInscripcion + " de marzo. "
                                + (" Se asignará su fecha para el examen.") + "\n");
        } else {
        System.out.println("La fecha para las inscripciones terminaron");
        }
    }
    
    public static void generarDatosPostulantes(String matrizDatosPostulantes[][]){
        try {
            Formatter objetoEntrada = new Formatter("OperaEntrada.csv");
            matrizDatosPostulantes[0][0]= "NOMBRE";
            matrizDatosPostulantes[0][1]= "APELLIDO";
            matrizDatosPostulantes[0][2]= "CARRERA";
            matrizDatosPostulantes[0][3]= "MERITO";
            matrizDatosPostulantes[0][4]= "NOTA";
            matrizDatosPostulantes[0][5]= "FECHA EXAMEN-ABRIL";
            String nombPostulantes[]={"Maria", "Juan", "Pedro", "Dario", "Milka", "Ana", "Sofia", "Pablo", "Lili", "Arlet"};
            String nombCarrera[]={"Quimica", "Fisio", "Medicina"};
            String nombMerito[]={"Abanderado", "Bachillerato Afin", "Capacidad Especial", "Sin Merito"};
            String apelPostulantes[]={"Carrion", "Vazquez", "Ruiz", "Garcia", "Quinde", "Andrade", "Sanchez", "Paez", "Ramirez", "Valarezo"};
            objetoEntrada.format("%s;%s;%s;%s;%s;%s\n", matrizDatosPostulantes[0][0],matrizDatosPostulantes[0][1],matrizDatosPostulantes[0][2],matrizDatosPostulantes[0][3],matrizDatosPostulantes[0][5],matrizDatosPostulantes[0][4]);
            for (int i = 1; i < matrizDatosPostulantes.length; i++) {
                int indiceAleatNomb=(int)(Math.random()*((nombPostulantes.length-1)-0+1)+0);
                int indiceAleatapel=(int)(Math.random()*((apelPostulantes.length-1)-0+1)+0);
                int indiceAleatCarrera=(int)(Math.random()*((nombCarrera.length-1)-0+1)+0);
                int indiceAleatMerito=(int)(Math.random()*((nombMerito.length-1)-0+1)+0);
                int indiceAleatFecha=(int)(Math.random()*(23-19+1)+19); 
                int indiceAleatNota=(int)(Math.random()*(100-30+1)+30);
                objetoEntrada.format("%s;%s;%s;%s;%s;%s\n",nombPostulantes[indiceAleatNomb],apelPostulantes[indiceAleatapel] ,nombCarrera[indiceAleatCarrera],nombMerito[indiceAleatMerito],indiceAleatFecha,indiceAleatNota);
            }
            
        objetoEntrada.close();   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
    
    public static void leerNotas(String matrizDatosPostulantes[][]){
        try {
            Scanner leer = new Scanner(new File("OperaEntrada.csv"));
            int i=0;
            while(leer.hasNext()){
                String datos[]=leer.nextLine().split(";");
                if (i > 0) { // Sirve ignorar la primera línea (encabezados)
                    matrizDatosPostulantes[i][0]=datos[0];
                    matrizDatosPostulantes[i][1]=datos[1];
                    matrizDatosPostulantes[i][2]=datos[2];
                    matrizDatosPostulantes[i][3]=datos[3];
                    matrizDatosPostulantes[i][4]=datos[4];
                    matrizDatosPostulantes[i][5]=datos[5];
                }
                i++;
            }
        } catch (Exception e) {
        }   
    } 
    
    public static void ResultadoExamenQuimica(String matrizDatosPostulantes[][], String datosSalidaQuimica[][]){
        int cupoQuimica = 80;
        int limPostulantesQuim = 0;
        for (int i = 1; i < matrizDatosPostulantes.length; i++) { 
            double nota = Double.valueOf(matrizDatosPostulantes[i][5]);
            datosSalidaQuimica[i][0] = matrizDatosPostulantes[i][0]; // Nombre
            datosSalidaQuimica[i][1] = matrizDatosPostulantes[i][1]; // Apellido
            datosSalidaQuimica[i][2] = matrizDatosPostulantes[i][5]; // Nota
            if (nota >= 80 && nota <= 100) {
                datosSalidaQuimica[i][3] = "Admitido";
                limPostulantesQuim++;
            }else{
                datosSalidaQuimica[i][3] = "No Admitido";
            } 
        }
        // Ordenar los arreglos en orden descendente según los puntajes
        Arrays.sort(datosSalidaQuimica, 1, datosSalidaQuimica.length,
        (notaMayor, notaMenor) -> Double.compare(Double.valueOf(notaMenor[2]), Double.valueOf(notaMayor[2])));
        // Seleccionar a los primeros 80 estudiantes y marcarlos como "Admitidos"
        for (int i = 1; i <= limPostulantesQuim && i <= cupoQuimica; i++) {
            datosSalidaQuimica[i][3] = "Admitido";
        }
    }
    
    public static void ResultadoExamenFisio(String matrizDatosPostulantes[][], String datosSalidaFisio[][]){
        for (int i = 1; i < matrizDatosPostulantes.length; i++) { 
            double nota = Double.valueOf(matrizDatosPostulantes[i][5]);
            datosSalidaFisio[i][0] = matrizDatosPostulantes[i][0]; // Nombre
            datosSalidaFisio[i][1] = matrizDatosPostulantes[i][1]; // Apellido
            datosSalidaFisio[i][2] = matrizDatosPostulantes[i][5]; // Nota
            if (nota >= 90 && nota <= 100) {
                datosSalidaFisio[i][3] = "Admitido";
            }else{
                datosSalidaFisio[i][3] = "No Admitido";
            }    
        }
        Arrays.sort(datosSalidaFisio, 1, datosSalidaFisio.length,
        (notaMayor, notaMenor) -> Double.compare(Double.valueOf(notaMenor[2]), Double.valueOf(notaMayor[2])));
    }
    
    public static void ResultadoExamenMedicina(String matrizDatosPostulantes[][], String datosSalidaMedicina[][]){
        int cupoMedicina = 80;
        int limPostulantesMedi = 0;
        for (int i = 1; i < matrizDatosPostulantes.length; i++) { 
            double nota = Double.valueOf(matrizDatosPostulantes[i][5]);
            Arrays.sort(matrizDatosPostulantes, 1, matrizDatosPostulantes.length,
            (notaMayor, notaMenor) -> Double.compare(Double.valueOf(notaMenor[5]), Double.valueOf(notaMayor[5])));
            int puntosMerito = 0;
            if ("Abanderado".equalsIgnoreCase(matrizDatosPostulantes[i][3])) 
                puntosMerito = 5;
            else if ("Bachillerato Afin".equalsIgnoreCase(matrizDatosPostulantes[i][3])) 
                    puntosMerito = 2;
            else if ("Capacidad Especial".equalsIgnoreCase(matrizDatosPostulantes[i][3])) 
                        puntosMerito = 1;
            double notaPonderada = Math.min(nota + puntosMerito, 100);
            double notaNormal = nota - puntosMerito;
            datosSalidaMedicina[i][0] = matrizDatosPostulantes[i][0]; // Nombre
            datosSalidaMedicina[i][1] = matrizDatosPostulantes[i][1]; // Apellido
            datosSalidaMedicina[i][2] = String.valueOf(notaPonderada); // Nota Ponderada
            datosSalidaMedicina[i][4] = matrizDatosPostulantes[i][3]; // Mérito
            if (notaPonderada >= 85 && notaPonderada <= 100) {
                datosSalidaMedicina[i][3] = "Admitido";
                limPostulantesMedi++;
            }else{
                datosSalidaMedicina[i][3] = "No Admitido";
            } 
            
        }
        Arrays.sort(datosSalidaMedicina, 1, datosSalidaMedicina.length,
        (notaMayor, notaMenor) -> Double.compare(Double.valueOf(notaMenor[2]), Double.valueOf(notaMayor[2])));
        for (int i = 1; i <= limPostulantesMedi && i <= cupoMedicina; i++) {
            datosSalidaMedicina[i][3] = "Admitido";
        }
    }
    
    public static void escribirArchivo(String datosSalidaQuimica[][], String datosSalidaFisio[][], String datosSalidaMedicina[][], String matrizDatosPostulantes[][]){
        try {
            Formatter objetoSalida = new Formatter("OperaSalida.csv");
            matrizDatosPostulantes[0][0]= "NOMBRE";
            matrizDatosPostulantes[0][1]= "APELLIDO";
            matrizDatosPostulantes[0][2]= "CARRERA";
            matrizDatosPostulantes[0][3]= "NOTA";
            matrizDatosPostulantes[0][6]= "RESULTADO";
            objetoSalida.format("%s;%s;%s;%s;%s\n", matrizDatosPostulantes[0][0],matrizDatosPostulantes[0][1], matrizDatosPostulantes[0][2],matrizDatosPostulantes[0][3],matrizDatosPostulantes[0][6]);
            for (int i = 1; i < datosSalidaQuimica.length; i++) {
                if ("Quimica".equalsIgnoreCase(matrizDatosPostulantes[i][2])) {
                objetoSalida.format("%s;%s;%s;%s;%s;%s;%s\n", datosSalidaQuimica[i][0], // Nombre
                                                                    datosSalidaQuimica[i][1], // Apellido
                                                                    matrizDatosPostulantes[i][2],//Carrera
                                                                    datosSalidaQuimica[i][2], // Nota
                                                                    datosSalidaQuimica[i][3],"",""); // Admitido/No Admitido
                }
            }
            objetoSalida.format("\n");
            for (int i = 1; i < datosSalidaFisio.length; i++) {
                if ("Fisio".equalsIgnoreCase(matrizDatosPostulantes[i][2])) {
                objetoSalida.format("%s;%s;%s;%s;%s;%s;%s\n", datosSalidaFisio[i][0], 
                                                              datosSalidaFisio[i][1],
                                                              matrizDatosPostulantes[i][2],
                                                              datosSalidaFisio[i][2], 
                                                              datosSalidaFisio[i][3],"",""); 
                }
            }
            objetoSalida.format("\n");
            matrizDatosPostulantes[0][4]= "NOTA PONDERADA";
            matrizDatosPostulantes[0][5]= "MERITO";
            objetoSalida.format("%s;%s;%s;%s;%s;%s;%s\n", matrizDatosPostulantes[0][0],matrizDatosPostulantes[0][1], matrizDatosPostulantes[0][2],matrizDatosPostulantes[0][3],matrizDatosPostulantes[0][4],matrizDatosPostulantes[0][5],matrizDatosPostulantes[0][6]);
            for (int i = 1; i < datosSalidaMedicina.length; i++) {
                if ("Medicina".equalsIgnoreCase(matrizDatosPostulantes[i][2])) {
                objetoSalida.format("%s;%s;%s;%s;%s;%s;%s\n", datosSalidaMedicina[i][0], 
                                                                    datosSalidaMedicina[i][1],
                                                                    matrizDatosPostulantes[i][2],
                                                                    matrizDatosPostulantes[i][5],
                                                                    datosSalidaMedicina[i][2], 
                                                                    datosSalidaMedicina[i][4],
                                                                    datosSalidaMedicina[i][3]); 
                }
            }
            objetoSalida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }       
}
