package chatbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class BuscaDatos extends FileController{
     /** Ruta del archivo que contiene la base de datos de palabras. */
    private static final String DATABASE = "./datos.txt";
    
    /** Lector utilizado para consultar la base de datos de palabras. */
    private RandomAccessFile fileReader;
    

    
    public BuscaDatos() {
        super(DATABASE);
        try {
            fileReader = new RandomAccessFile(file, "r");
           
        } catch (FileNotFoundException e) {
             JOptionPane.showMessageDialog(
                null,
                "La base de datos de traduccion no fue encontrada",
                "Error Base Datos Traduccion",
                JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
        }
    }
    
    public String translate(String word)
    {
        String translation = "ok";
        String palabraGuardada="";
        String palabra=word;
        int contador=0, contador2=0;
 
        try
        {
            String line = fileReader.readLine();
            boolean encontro=false;
            while(line != null && encontro != true)
            {
                //se crea array string y se separa por medio de #
                String[] tupla = line.split("#");
                //se crea array string y se separa por mdio de espacios
                String[] tuplados =palabra.split(" ");
                
                //for que obtiene la medidad de las palabras
                for (int i=0;i<=tuplados.length-1;i++){ 
                    //for que valida que tiene que ser igual o menor a la cantidad de tuplados
                for(int j=0;j<=tupla.length-1;j++){
                    //if que compara si son iguales los estrings buscados
                    //retorna true si son iguales y false si no son iguales
                if (tuplados[i].equalsIgnoreCase(tupla[j]))
                {
                    //muestra los datos encontrados que estén en la misma línea encontrada como su repuesta
                translation = tupla[tupla.length-1];
                contador++;
                }
                }
                }
                //toma la respuesta correcta, esta es la que se ingresa en el txt si está en la misma línea la 
                //muestra
               if (contador>=contador2){
                   contador2=contador;
                   palabraGuardada=translation;
               }
                contador=0;
                //lee la línea para el txt
                line = fileReader.readLine();
            }
           //  System.out.println("palbra acumulada : "+ palabraGuardada);
        }

        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(
                null,
                "Error al leer la base de datos de palabras",
                "Error Base Datos Traduccion",
                JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }

        finally
        {
            try
            {
                //termina el evento de leer lo que se ingresa
                fileReader.close();
            }

            catch (IOException ioe)
            {
                JOptionPane.showMessageDialog(
                    null,
                    "Error al cerrar la base de datos de palabras",
                    "Error Base Datos Traduccion",
                    JOptionPane.ERROR_MESSAGE);
                ioe.printStackTrace();
            }
        }

        return palabraGuardada;
    }
   
    
}
