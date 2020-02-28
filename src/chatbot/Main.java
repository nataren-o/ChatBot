
package chatbot;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.applet.AudioClip;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends javax.swing.JFrame {
    //variables 
    String pregunta, respuesta,preguntagenerada;
    boolean reproducciendo=false;
    //variable tió audio
     AudioClip sonido1, sonido2, alive;
     
    
    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enviar = new javax.swing.JButton();
        texto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pantalla = new javax.swing.JTextArea();
        online = new javax.swing.JLabel();
        Namebot = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enviar.setBackground(new java.awt.Color(255, 255, 255));
        enviar.setText("enviar");
        enviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoActionPerformed(evt);
            }
        });

        pantalla.setEditable(false);
        pantalla.setColumns(20);
        pantalla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 12)); // NOI18N
        pantalla.setRows(5);
        jScrollPane1.setViewportView(pantalla);

        online.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        Namebot.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Namebot.setText("ChatBot");

        jButton1.setText("Enseñar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(texto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enviar))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Namebot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(online, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(online, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Namebot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviar)
                    .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        // TODO add your handling code here:
        //es el contexto de la ejecución
        //con el Runnable se procesa los objetos como los mensajes
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //toma lo que tiene el txt
        pregunta=texto.getText();
        //se busca lo que se ingresó en el txt, y se llama a la clase buscaDatos
        respuesta=(new BuscaDatos().translate(texto.getText()));
        //con esto se genera la pregunta
        preguntagenerada=(new BuscaDatos().translate(generarpregunta()));
        //se muestra lo que yo pregunté en el cuadro
        pantalla.append("Usuario: "+texto.getText() +"\n");
        //si lo que se preguntó no existe en el txt entonces
        if (respuesta.equalsIgnoreCase("ok")){
            //le pregunta al programa que le enseñe.
        pantalla.append("AI: Podrias enseñarme que debo responder si me dicen: '"+pregunta+"' por favor (si/no)\n");
        //pregunta al usuario si quiere enseñarle lo que le preguntó.
        String respuestUsuario= JOptionPane.showInputDialog("Deseas enseñarle?(si/no)");
        //si responde sí entonces abre un input dialog
        if (respuestUsuario.equalsIgnoreCase("si")){
            //le pregunta qué quiere que responda cuando le haga dicha pregunta
             String respuestUsuarioPregunta= JOptionPane.showInputDialog("Que responder a '"+pregunta+"'");
             //llama a la case aprender
             Leer aprender =new Leer();
             //y entonces la guarda 
             String nuevapalabra=aprender.preguntanueva(pregunta,respuestUsuarioPregunta);
             //lee los datos y los agrega al txt
             aprender.guardar(aprender.leertxt("datos.txt"), nuevapalabra);   
        }
    }
        texto.setText("");
        //se muestra la resputa como una animación
        animacionEscribir(respuesta);
        
        int probabilidad=mitadProbabilidad();
        //System.out.println(probabilidad);
        //si la probalidad está mayor que 5
        if(probabilidad>5){
        //  System.out.println("entra");
            //es cuando uno ya no le escribe o está aburrida la cosa entocnes ella pregunta 
            //algo de lo que está en el txt
             animacionpregunta(preguntagenerada);
             //compara si lo que se le preguntó está en el txt
             //reproduce la música establecida 
                         if (preguntagenerada.equalsIgnoreCase("pongamos musica") && reproducciendo != true){
                             
                 alive =java.applet.Applet.newAudioClip(getClass().getResource("./alive.wav"));
                 alive.play();
             }
             
        }   
        } catch (Exception e) {
        }
        }
        });
        hilo.start();

    }//GEN-LAST:event_enviarActionPerformed
    //con esto se genera, establecidadas en el txt
    public String generarpregunta(){
        int numero;
        //escoje una pregunta de las que están en el txt
        numero = (int) (Math.random() * 9) + 1;
        String preguntaAleatoria = Integer.toString(numero);
        String preguntacompletada= preguntaAleatoria+"p";
        return preguntacompletada;
    }
    //metodo para la probalidad de las resputsa que está en un rango de 9 + 1
    public int mitadProbabilidad(){
         int numero;
        numero = (int) (Math.random() * 9) + 1;
        return numero;
    }
    public void animacionEscribir(String respuestaxd) throws InterruptedException, URISyntaxException, IOException{
        //variables para el sonido
        sonido1 =java.applet.Applet.newAudioClip(getClass().getResource("./recibido.mp3"));
        sonido2 =java.applet.Applet.newAudioClip(getClass().getResource("./enviado.mp3"));
        //se llama a la calse de ramdom, es el tiempo que se tarda en responder
        Thread.sleep(generarRandom());
        online.setForeground(Color.blue);
        sonido2.play();
        online.setText("Visto_");
        Thread.sleep(generarRandom());
        online.setText("Escribiendo_"); 
        Thread.sleep(generarRandom());
        online.setText("");
        sonido1.play();
        pantalla.append("AI: "+ respuestaxd+"\n");
          if(pregunta.equalsIgnoreCase("reproducir musica")){
            reproducciendo=true;
              Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=I_izvAbhExY"));
          //  alive =java.applet.Applet.newAudioClip(getClass().getResource("./alive.wav"));
           // alive.play();
        }
        //  System.out.println(generarRandom());
    }
    //método que muestra cuando está escribiendo
      public void animacionpregunta(String respuestaxd) throws InterruptedException{
        sonido1 =java.applet.Applet.newAudioClip(getClass().getResource("./recibido.mp3"));
        //tiempo que se lleva escribiendo o en responder
        Thread.sleep(1500);
        online.setText("Escribiendo"); 
        Thread.sleep(1000);
        online.setText("");
        sonido1.play();
        pantalla.append("AI: "+ respuestaxd+"\n");
    }
    //generador del tiempo que se lleva en ver el mensaje
    public int generarRandom(){
        int numero;
        numero = (int) (Math.random() * 3000) + 1000;
        return numero;
        
    }
    
    public void fijarTexto(){
         pantalla.append("Maquina: "+ respuesta+"\n");
        texto.setText("");
    }
    private void textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_textoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //botón de aprender 
         String UsuarioPregunta= JOptionPane.showInputDialog("Ingrese pregunta");
            //muestra el imput junto con lo que escribió
         String respuestUsuarioPregunta= JOptionPane.showInputDialog("Que responder a '"+ UsuarioPregunta+"'");
         //lee lo que se ingresó en el input
         Leer aprender =new Leer();
         //junta todo
         String nuevapalabra=aprender.preguntanueva(UsuarioPregunta,respuestUsuarioPregunta);
         //guarda en el txt lo que aprendió
         aprender.guardar(aprender.leertxt("datos.txt"), nuevapalabra);   
    }//GEN-LAST:event_jButton1ActionPerformed

    public JButton enviarPresionado(){
        return enviar;
    }
    public void setPanatalla(){
        pantalla.append("Maquina: "+ respuesta+"\n");
    }
    public JTextArea regresaPantalla(){
        return pantalla;
    }
    

    public JTextField regresaTexto(){
        return texto;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Namebot;
    private javax.swing.JButton enviar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel online;
    private javax.swing.JTextArea pantalla;
    private javax.swing.JTextField texto;
    // End of variables declaration//GEN-END:variables
}
