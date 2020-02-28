
package chatbot;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

public class FileController
{
    //variable tipo archivo
    public File file;
    public FileController(String fileName)
    {
        file = new File(fileName);
    }
    public FileController(File file)
    {
        this.file = file;
    }

     public String getFileContent()
    {
        StringBuilder fileContent = new StringBuilder();
        try
        {
            // constructor que accede al txt de forma aleatoria 
            //con la "r" lee, es de read!
            RandomAccessFile fileReader = new RandomAccessFile(file, "r");
           //lee la línea a la que se accedió
            String line = fileReader.readLine();
            //sí la línea diferente de null
            while (line != null)
            {
                //se agregan los datos encontrados con la variable line
                //los va agregando al final siempre
                fileContent.append("\n").append(line);
                line = fileReader.readLine();
            }

        }
        catch (FileNotFoundException fne)
        {
            JOptionPane.showMessageDialog(
                null,
                "El archivo no existe",
                "ERROR EN ARCHIVO",
                JOptionPane.ERROR_MESSAGE);
            fne.printStackTrace();
        }
        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(
                null,
                "Error al leer el archivo",
                "ERROR EN ARCHIVO",
                JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }

        //actualiza el txt y lo convierte a string si loque se ingresó fueron números
        return fileContent.toString();
    }

     //método para guardar recive la variable de arriba
    public void save(String fileContent)
    {
        try
        {
            //printstream para recuperar información del txt y los muestra al usuario
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.println(fileContent);
            printStream.flush();
            printStream.close();
        }
        catch (FileNotFoundException fne)
        {
            JOptionPane.showMessageDialog(
                null,
                "El archivo no existe",
                "ERROR EN ARCHIVO",
                JOptionPane.ERROR_MESSAGE);
            fne.printStackTrace();
        }
    }
}