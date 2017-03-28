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
 * Esta clase es la ejecutora de los diferentes metodos de las otras clases para mostrar una solucion final.
 * Dependiendo de los argumentos de entrada, mostrara cierta informacion de la ruta o ejecutará un determinado
 * algoritmo. Tal como podemos ver en la documentación, en el archivo "Manual de usuario".
 * @author Manuel Moya Ferrer - Jose Manuel García Giménez
 * 
 */
public class TSP {

    public static void main(String[] args) {
       long time_start, time_end;
        
       Ruta ruta=null;
       Heuristica solucionHeuristica=new Heuristica();
       Problema problema=new Problema();
       problema.leerProblema();
       problema.calcularMatrizDistancias();
       
       boolean argsolucion=false,argruta=false,argcoste=false,argmontecarlo=false,argAlg1=false,argAlg4=false,argAlg2=false,argAlg3=false;
       
                     for (int i=0; i<args.length;i++){	
                         
		if(args[i].equals("solucion")){
			argsolucion=true;
		}		
		if(args[i].equals("ruta")){
			argruta=true;
		}		
		if(args[i].equals("coste")){
			argcoste=true;			
		}	
                                        if(args[i].equals("solAlg1")){
                                                argAlg1=true;			
                                        }
                                        if(args[i].equals("solAlg2")){
                                                argAlg2=true;			
                                        }
                                        if(args[i].equals("solAlg3")){
                                                argAlg3=true;			
                                        }
                                        if(args[i].equals("solAlg4")){
                                                argAlg4=true;			
                                        }
     
	}
            
         time_start=System.currentTimeMillis();         
       
                
        if(argAlg1){ 
           ruta=solucionHeuristica.resolverRutaAlgoritmo1(problema);   //Resolver una ruta
         } 
        if(argAlg2){ 
           ruta=solucionHeuristica.inserEconomica(problema); //Resolver una ruta
         }
        if(argAlg3){ 
           ruta=solucionHeuristica.inserMasLejano(problema);   //Resolver una ruta
         }
         if(argAlg4){ 
           ruta=solucionHeuristica.resolverAlgoritmo4(problema);   //k=10000
         }

        time_end=System.currentTimeMillis();
       
        if(argcoste){
            double coste=ruta.coste;
            System.out.println("El coste de la ruta es "+"  =  "+coste);
        }
        if(argruta){ 
           ruta.muestraruta();
        }
        if(argsolucion){
            ruta.muestraCoordenadasRuta();
        }
       
        System.out.println("El tiempo de ejecución del algoritmo seleccionado es: "+(time_end-time_start)+" milisegundos");
 
    }
    
}
