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
 * Esta clase da lugar a objetos ruta[], los cuales contienen las ciudades pertenecientes a una ruta dada, así mismo
 * contiene el coste de una ruta.
 * @author Manuel Moya Ferrer - Jose Manuel García Giménez
 */
public class Ruta {
 
    private Ciudad ruta[];
    private int nCiudadesVisitadas;
    double coste;
   
    /**
     * Constructor de la clase Ruta
     * @param n Numero de ciudades de la ruta a crear
     */
    Ruta(int n){
        ruta=new Ciudad[n+1];
        nCiudadesVisitadas=0;
    }
   
    /**
     * Añadir una ciudad a una ruta
     * @param ciudad Objeto Ciudad
     */
    void addCiudad (Ciudad ciudad){
        ruta[nCiudadesVisitadas]=ciudad;
        nCiudadesVisitadas++;
    }
   
    /**
     * 
     * @return Devuelve una ruta, un vector de objetos Ciudad
     */
    Ciudad []getRuta(){      
        return ruta;
    }
     
    /**
     * 
     * @return Devuelve el numero de ciudades que contiene una ruta
     */
    int getnCiudadesVisitadas(){    
        return nCiudadesVisitadas;
    }   
    
    /**
     * Muestra por pantalla las etiquetas de las ciudades de una ruta. Ej: Ruta1.muestraruta();
     */
    void muestraruta(){
        for(int i=0;i<nCiudadesVisitadas;i++){  
            System.out.println(ruta[i].getEtiqueta());      
    }
    }
    
    /**
     * Muestra por pantalla las coordenadas de las ciudades de un objeto ruta.
     */
    void muestraCoordenadasRuta(){
        for(int i=0;i<nCiudadesVisitadas;i++){  
            System.out.print(ruta[i].getX());
            System.out.print(" ");
            System.out.print(ruta[i].getY());
            System.out.print("\n");
       }
    }
    
    /**
     *
     * @param nciudad
     * @return Etiqueta de la siguiente ciudad en la ruta a la ciudad nciudad introducida como parametro.
     */
    int siguiente(int nciudad){//muestra la etiqueta de la siguiente ciudad a nciudad dada una ruta
        return ruta[nciudad+1].getEtiqueta();
    }
    
    /**
     * Metodo para añadir una ciudad a una ruta, dada un Objeto ciudad y un entero Posicion
     * @param ciudad
     * @param pos 
     */
    void addCiudadRuta(Ciudad ciudad, int pos){
        Ciudad aux[]=new Ciudad[ruta.length];
        
        for(int i=0;i<pos;i++){
            aux[i]=ruta[i];
        }
        aux[pos]=ciudad;
        nCiudadesVisitadas++;
        for(int i =pos+1;i<nCiudadesVisitadas;i++){
            aux[i]=ruta[i-1];
        }
        
        ruta=aux;
    }
    
      /**
       * 
       * @param posicion
       * @return Objeto Ciudad para una dicha posicion en la ruta
       */
      Ciudad getCiudad(int posicion){ //devuelve la ciudad de la ruta de la posición posicion. 
        return ruta[posicion];
    }
}