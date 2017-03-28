////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////                                        Practica 4 - Problema del viajante - TSP                                               ///////////////////////                     
///////////////////////                              Manuel Moya Ferrer   Jose Manuel Garcia Gimenez                                      ///////////////////////
///////////////////////                      manuelmoya@correo.ugr.es   jgarciagimenez@correo.ugr.es                             ///////////////////////
///////////////////////                                 3º Telecomunicaciones -Especialidad en Telemática                                ////////////////////////          
///////////////////////                                                   Complementos de Programación                                               ////////////////////////      
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package tsp;

import java.util.Scanner;

/**
 * Esta Clase contiene los datos relevantes para la resolucion de la ruta, los cuales se exportaran a la clase Ruta 
 * mediante un objeto problema exportado de esta clase. Contiene datos como el numero de ciudades, un vector 
 * de las ciudades a visitar, y una matriz de Distancias entre las ciudades.
 * @author Manuel Moya Ferrer - Jose Manuel García Giménez
 */
public class Problema {
    private Ciudad vCiudades[];
    private int nCiudades;
    private double mDistancias[][];
    
    /**
     * Mediante este método leemos los datos sobre las ciudades a incluir. El primer dato que se introduce es el numero
     * de ciudades a leer, posteriormente, y para cada ciudad: Se lee primero la etiqueta de la ciudad, 
     * posteriormente la coordenada X, y finalmente la coordenada Y.
     */
    public void leerProblema(){

            Scanner conin = new Scanner(System.in);
            double x,y;
            int etiqueta;
            String dimension=conin.next();
            nCiudades=conin.nextInt();
            vCiudades=new Ciudad[nCiudades];

            for(int i=0;i<nCiudades;i++){ 
                etiqueta=conin.nextInt();
                x=conin.nextDouble();    
                y=conin.nextDouble();
                Ciudad ciudad= new Ciudad(x,y,etiqueta);
                vCiudades[i]= ciudad;
            }

        }

    /**
     * Metodo que calcula la Matriz de distancia entre todas las combinaciones de ciudades posibles.
     * Cada fila de la matriz corresponde a las distancias de una ciudad dada con el resto de ciudades.
     */
    public void calcularMatrizDistancias(){
            mDistancias=new double[nCiudades][nCiudades];

            for(int filas=0;filas<nCiudades;filas++){
                for(int columnas=0;columnas<nCiudades;columnas++){
                mDistancias[filas][columnas]=vCiudades[filas].calculaDistancia(vCiudades[columnas]);
                 }  
            }

        }

    /**
     * Metodo para obtener un objeto Ciudad mediante una etiqueta n.
     * @param n Este parametro es la etiqueta de la ciudad que se quiere obtener
     * @return Objeto Ciudad
     */
    public Ciudad obtenerCiudad(int n){
            return vCiudades[n-1]; //Se le resta 1 para que funcione al pasarle una etiqueta(que empiezan)
        }

    /**
     * Metodo para obtener la matriz de distancias
     * @return Double Matriz de distancias [][] 
     */
    public double [][]getMatriz(){
        return mDistancias;
    } 

    /**
     * Muestra por pantalla la matriz de distancias
     */
    public void VerMatrizDistancia(){
        System.out.println("Toma la matriz");
        for(int filas=0;filas<nCiudades;filas++){
                for(int columnas=0;columnas<nCiudades;columnas++){
                    System.out.print(mDistancias[filas][columnas]);
                    System.out.print(" ");
                 }  
                System.out.print("\n");
            }

    }

    /**
     * 
     * @return Vector de ciudades (vector de objetos ciudad)
     */
    public Ciudad []getVCiudad(){
        return vCiudades;
    }

    /**
     * 
     * @return Int numero de ciudades
     */
    public int obtenerNciudades(){
        return nCiudades;
    }

}
