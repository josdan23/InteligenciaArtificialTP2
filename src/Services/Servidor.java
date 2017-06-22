/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josdan
 */
public class Servidor {
    
    public int puerto = 5001;
    private ServerSocket serverSocket;
    private Socket socket;
    
    
    public Servidor() {
    }
    
    
    public void iniciarServer(){
    
    try {
            //Socket de servidor para esperar peticiones de la red
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor> Servidor iniciado");    
            System.out.println("Servidor> En espera de cliente...");
            
            socket = new Socket();
            socket = serverSocket.accept();

            
            /*Socket clientSocket; //Socket de cliente
            while(true){
            //en espera de conexion, si existe la acepta
            clientSocket = serverSocket.accept();
            
            String mensaje = procesarPeticion(clientSocket);
            
            responderCliente(clientSocket, mensaje);
            
            //cierra el socket del cliente para que no consuma recursos
            clientSocket.close();
            
            }    */
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public String procesarPeticion() {

        try {

            
            //obtiene el flujo del cliente socket ara leer lo que envie el cliente
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            

            //obtiene la cadena string del flujo. Input es un stream o flujo
            String solicitud = input.readLine();
            System.out.println(solicitud);

            return  solicitud;
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
     
   public void responderCliente(String mensaje){
        //se crea un flujo de salida enviar la respuesta del servidor. al celular
        
        try {
            
            PrintStream respuesta;
            respuesta = new PrintStream(socket.getOutputStream());
            
            //fuerza la escritura en el stream
            respuesta.flush();

            //imprimi el flujo. Esto hace que se envie a la aplicacion
            respuesta.println(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void cerrarConexion() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
