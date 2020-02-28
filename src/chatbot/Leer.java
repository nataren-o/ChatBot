
package chatbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author CAMILO
 */
public class Leer {
    //lee toda la información del txt
    File file = new File("datos.txt");
    //método público para ir a la dirección que está la respusta de la pregunta que hace el usuario
    public String leertxt(String direccion){
        String texto="";
        try {
            //lectura de los carateres tal y como están en el txt
            BufferedReader bf=new BufferedReader(new FileReader(direccion));
            
            String temporal="";
            String bfread;
            //mientras que la variable bfread sea diferente de null
            //irá leyendo hasta encontrar la respuesta correcta
            while ((bfread=bf.readLine()) !=null){
                //salto de línea y lo agrega a la variable temporal
                temporal=temporal+bfread+"\r\n";
            }
            texto=temporal;
        } catch (Exception e) {
        }
        return texto;
    }
    public String preguntanueva(String pregunta,String respuesta){
        
        //String myword="";
       // String respuesta=""; 
        String nuevaPalabra="";
        String[] tupla3=pregunta.split(" ");
        //System.out.println(tupla3[0]);
        
        for (int i=0;i<=tupla3.length-1;i++){
            if(i==tupla3.length-1){
                 nuevaPalabra=nuevaPalabra+tupla3[i]+"#"+tupla3[i]+"?"+"#"+respuesta;
                 //compara si lo que se ingresó es igual a todo lo que se ingresó en esta línea es igual
                 //y no es igual entonces procesa la pregunta nueva
            }else if (tupla3[i].equalsIgnoreCase("que")|| tupla3[i].equalsIgnoreCase("es")||tupla3[i].equalsIgnoreCase("un")||tupla3[i].equalsIgnoreCase("de")||tupla3[i].equalsIgnoreCase("hijo")){
                continue;
            }else {
                //se guarda en el txt, y se separa con # junto con su repuesta
            nuevaPalabra=nuevaPalabra+tupla3[i]+"#";}
        }
        //se imprime por consola lo que se guardó
        System.out.println(nuevaPalabra);
       // palabra.guardar(palabra.leertxt("fileName1.txt"), nuevaPalabra);
        //la retorna.
       return nuevaPalabra;
    }
    public void guardar(String contenidoAnteriorTxt, String nuevapalabra){
        try {
            //compara si existe la pregunta en el txt
		if(!file.exists()) {
                    //si no existe entonces crea una nueva pregunta con su resputa y separada por #
				file.createNewFile();	
		}
		//nos imprime las representaciones que se eliminarón
		PrintWriter pw = new PrintWriter(file);
               // pw.write(leertxt("fileName1.txt"));
                //String palabra=leertxt("fileName1.txt");
                //guarda la nueva palabra
                pw.append(contenidoAnteriorTxt + nuevapalabra);
                
               // pw.append(leertxt("fileName1.txt"))
		
                //cierra la lectura d
		pw.close();
		System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
