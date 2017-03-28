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

/**
 *  Clase Ciudad
 * @author Manuel Moya Ferrer - Jose Manuel García Giménez
 */
public class Ciudad {
    private double x,y;
    private int etiqueta;
            
            /**
             * Constructor, se le pasan las coordenadas a una ciudad y su respectivo identificador.
             * @param coordenadax
             * @param coordenaday
             * @param identificador 
             */
           public Ciudad(double coordenadax,double coordenaday, int identificador){
                x= coordenadax;
                y=coordenaday;
                etiqueta=identificador;  
            }
            
           /**
            * 
            * @param ciudad Objeto Ciudad 
            * @return  Devuelve la distancia entre dos ciudades, por ejemplo distancia=Ciudad1.calculaDistancia(Ciudad2) -Double
            */
            public double calculaDistancia(Ciudad ciudad){
                return Math.sqrt((x-ciudad.x)*(x-ciudad.x)+(y-ciudad.y)*(y-ciudad.y));  
            }
            
            /**
             * 
             * @return Etiqueta de una ciudad dada. -Int
             */
            public int getEtiqueta(){
                return etiqueta;
            }
            
            /**
             * 
             * @return Coordenada X de una ciudad -Double
             */
            public double getX(){
                return x;
            }  
            
            /**
             * 
             * @return Coordenada Y de una ciudad dada -Double
             */
            public double getY(){
                return y;
            }
            
            /**
             * 
             * @param b
             * @param c
             * @return Coste Insercion dadas dos ciudades  -Double
             */
           public double calcCosteInsercion( Ciudad b, Ciudad c){


                double distanciaAB,distanciaBC,distanciaAC;
                distanciaAB=calculaDistancia(b);
                distanciaBC=b.calculaDistancia(c);
                distanciaAC=calculaDistancia(c);

                return (distanciaAC+distanciaBC-distanciaAB);

            }
            
}