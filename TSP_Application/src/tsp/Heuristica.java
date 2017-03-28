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

import java.util.Random;

/**
 * En esta clase se implementan los algoritmos que resolveran la ruta a escoger
 * @author  Manuel Moya Ferrer - Jose Manuel García Giménez
 */
public class Heuristica {

    
    private Ruta RutaActual,mejorRutaMontecarlo=null;
    private double costeAlgoritmo3;
    private int etiquetaCiudadActual;
    private int nciudadesenruta=0;
    private int nIteraciones;
  
    /**
     * Resolucion de la ruta mediante el algoritmo 1
     * @param problema
     * @return Objeto RutaSolucion
     */
    public Ruta resolverRutaAlgoritmo1(Problema problema){

        double mDistancias[][];
        int nciudades,ciudadinicial;
        Ruta RutaAux; //ruta que se calcula en cada iteracion, y la buena se guarda en ruta actual

        nciudades=problema.obtenerNciudades();


        mDistancias=problema.getMatriz();
        boolean visitados[]= new boolean[nciudades];
            int coste = 0;

        for(ciudadinicial=1;ciudadinicial<=nciudades;ciudadinicial++){     //Se empieza por la ciudad con etiqueta 1, para obtener la primera ruta, y así posteriormente comparando las siguientes rutas definidas por las siguientes ciudades
        RutaAux= new Ruta(nciudades);
        double costeaux = 0; // Coste para cada ruta, el coste bueno se guarda en corte
        for(int i=0;i<nciudades;i++){  //Ponemos el vector de ciudades visitadas a false, cuando pasemos por una ciudad le daremos valor true para contarla como visitada
            visitados[i]=false;        
        }

            RutaAux.addCiudad(problema.obtenerCiudad(ciudadinicial));
            visitados[ciudadinicial-1]=true;
            int ciudadactual=ciudadinicial;
            for(int saltos=0;saltos<nciudades-1;saltos++){ 

                double minimo;
                int etiquetavector=1;

                minimo=65000;
                for(int i=0;i<nciudades;i++){          //Sacamos la ciudad a la que estamos mas cerca, la que menor coste tenga
                    if(mDistancias[ciudadactual-1][i]<=minimo && mDistancias[ciudadactual-1][i]!=0 && visitados[i]==false){
                        minimo=mDistancias[ciudadactual-1][i];
                        etiquetavector=i;//Las etiquetas empiezan en 1, y el vector en 0
                    }         
                }
                visitados[etiquetavector]=true;
                costeaux+=mDistancias[ciudadactual-1][etiquetavector];
                RutaAux.addCiudad(problema.obtenerCiudad(etiquetavector+1)); 
                ciudadactual=etiquetavector+1;


            }
            RutaAux.addCiudad(problema.obtenerCiudad(ciudadinicial));
            costeaux+=mDistancias[ciudadactual-1][ciudadinicial-1];

            if(costeaux<coste || coste==0){
                RutaActual= RutaAux;
                RutaActual.coste=costeaux;
            }
        }
            return RutaActual;
        }
    
    /**
     * Resolucion de la ruta mediante el algoritmo 2
     * @param problema
     * @return Objeto RutaSolucion
     */
    public Ruta inserEconomica(Problema problema ){  //Algoritmo 2

            int nciudades=problema.obtenerNciudades() ;
            Ciudad vCiudades[]=problema.getVCiudad();
            boolean visitados[]= new boolean[nciudades];
            double costeAlgoritmo2 = 0;

            Ruta rutaInicial = new Ruta(nciudades);
            for(int i=0;i<nciudades;i++){  //Ponemos el vector de ciudades visitadas a false, cuando pasemos por una ciudad le daremos valor true para contarla como visitada
                 visitados[i]=false;        
             }

            double coorX=vCiudades[0].getX();
            double coorY=vCiudades[0].getY();
            int izquierda=vCiudades[0].getEtiqueta(); 
            int derecha=vCiudades[0].getEtiqueta(); 
            int arriba=vCiudades[0].getEtiqueta();

            for (int i=0;i<nciudades;i++){  // buscamos la ciudad más a la izquierda
                if(vCiudades[i].getX() < coorX  && visitados[i]==false){
                    coorX=vCiudades[i].getX();
                    izquierda=vCiudades[i].getEtiqueta();
                }

            }
            rutaInicial.addCiudad(problema.obtenerCiudad(izquierda)); // añadimos a la ruta la ciudad más a la izquierda
            visitados[izquierda-1]=true;

            for (int i=0;i<nciudades;i++){  // buscamos la ciudad más a la derecha
                if(vCiudades[i].getX()>coorX  && visitados[i]==false){
                    coorX=vCiudades[i].getX();
                    derecha=vCiudades[i].getEtiqueta();
                }

            }
            rutaInicial.addCiudad(problema.obtenerCiudad(derecha)); // añadimos a la ruta la ciudad más a la derecha
            visitados[derecha-1]=true;

             for (int i=0;i<nciudades;i++){  // buscamos la ciudad más arriba
                if(vCiudades[i].getY()>coorY  && visitados[i]==false){
                    coorY=vCiudades[i].getY();
                    arriba=vCiudades[i].getEtiqueta();
                }
            }
            rutaInicial.addCiudad(problema.obtenerCiudad(arriba)); // añadimos a la ruta la ciudad más a la arriba
            visitados[arriba-1]=true;

            rutaInicial.addCiudad(problema.obtenerCiudad(izquierda)); // volvemos a añadir la primera para que se forme el triángulo.

            // Ya tenemos la ruta inicial, ahora vamos a calcular lo que hay que insertar.

            double costeInicial;
            Ciudad posibleInsercion = vCiudades[0] ;
            int posicion = 0;

            for(int l=0;l<nciudades;l++){

                for (int i=0;i<nciudades;i++){
                    if(visitados[i]==false)  {//si está sin visitar buscamos la mejor inserción de esta ciudad
                        costeInicial=rutaInicial.getCiudad(0).calcCosteInsercion(rutaInicial.getCiudad(1),vCiudades[i]);
                        posicion=0;
                        posibleInsercion=vCiudades[i];
                        for(int j=0;j<rutaInicial.getnCiudadesVisitadas()-1;j++){

                            if(rutaInicial.getCiudad(j).calcCosteInsercion(rutaInicial.getCiudad(j+1),vCiudades[i])<costeInicial){
                                costeInicial=rutaInicial.getCiudad(j).calcCosteInsercion(rutaInicial.getCiudad(j+1),vCiudades[i]);
                                posibleInsercion=vCiudades[i];

                                posicion=j;
                            }
                        }

                        rutaInicial.addCiudadRuta(posibleInsercion,posicion+1);
                        visitados[i]=true;

                    }            

                }     
            }

            for(int i=0;i<rutaInicial.getnCiudadesVisitadas()-1;i++){
                costeAlgoritmo2+=rutaInicial.getCiudad(i).calculaDistancia(rutaInicial.getCiudad(i+1));
            }
            rutaInicial.coste=costeAlgoritmo2;
            return rutaInicial;

        }

    /**
     * Resolucion de la ruta mediante el algoritmo 3
     * @param problema
     * @return Objeto RutaSolucion
     */
    public Ruta inserMasLejano(Problema problema){

            double mDistancias[][];
            int nciudades=problema.obtenerNciudades() ;
            Ciudad vCiudades[]=problema.getVCiudad();
            boolean visitados[]= new boolean[nciudades];
            mDistancias=problema.getMatriz();

            Ruta rutaInicial = new Ruta(nciudades);
            for(int i=0;i<nciudades;i++){  //Ponemos el vector de ciudades visitadas a false, cuando pasemos por una ciudad le daremos valor true para contarla como visitada
                 visitados[i]=false;        
             }

            double coorX=vCiudades[0].getX();
            double coorY=vCiudades[0].getY();
            int izquierda=vCiudades[0].getEtiqueta(); 
            int derecha=vCiudades[0].getEtiqueta(); 
            int arriba=vCiudades[0].getEtiqueta();

            for (int i=0;i<nciudades;i++){  // buscamos la ciudad más a la izquierda
                if(vCiudades[i].getX() < coorX  && visitados[i]==false){
                    coorX=vCiudades[i].getX();
                    izquierda=vCiudades[i].getEtiqueta();
                }

            }
            rutaInicial.addCiudad(problema.obtenerCiudad(izquierda)); // añadimos a la ruta la ciudad más a la izquierda
            visitados[izquierda-1]=true;

            for (int i=0;i<nciudades;i++){  // buscamos la ciudad más a la derecha
                if(vCiudades[i].getX()>coorX  && visitados[i]==false){
                    coorX=vCiudades[i].getX();
                    derecha=vCiudades[i].getEtiqueta();
                }

            }
            rutaInicial.addCiudad(problema.obtenerCiudad(derecha)); // añadimos a la ruta la ciudad más a la derecha
            visitados[derecha-1]=true;

             for (int i=0;i<nciudades;i++){  // buscamos la ciudad más arriba
                if(vCiudades[i].getY()>coorY  && visitados[i]==false){
                    coorY=vCiudades[i].getY();
                    arriba=vCiudades[i].getEtiqueta();
                }
            }
            rutaInicial.addCiudad(problema.obtenerCiudad(arriba)); // añadimos a la ruta la ciudad más a la arriba
            visitados[arriba-1]=true;



            // Ya tenemos la ruta inicial, ahora vamos a calcular lo que hay que insertar.


            Ciudad posibleInsercion = vCiudades[0] ;

            for(int i=0;i<nciudades-3;i++){ //hacemos una iteración por cada inserción que neceistamos
                double distanciaminima=0;
                for(int j=0;j<nciudades;j++){  //recorremos todas las ciudades posibles para insertar

                    if(visitados[j]==false) {   //de entredistanciaminima=buscarDistanciaMinima(mDistancias, vCiudades[j], rutaInicial) todas las ciudades tomamos las que no están visitadas

                        if(buscarDistanciaMinima(mDistancias, vCiudades[j], rutaInicial)>distanciaminima ){
                             distanciaminima=buscarDistanciaMinima(mDistancias, vCiudades[j], rutaInicial);
                             posibleInsercion=vCiudades[j]; 
                        }


                    }
                }//ahora que sabemos el que vamos a insertar, tenemos que ver donde insertarlo

            int posicion = 1;
                double costeInicial=rutaInicial.getCiudad(0).calcCosteInsercion(rutaInicial.getCiudad(1),posibleInsercion);
                        for(int j=0;j<rutaInicial.getnCiudadesVisitadas()-1;j++){

                            if(rutaInicial.getCiudad(j).calcCosteInsercion(rutaInicial.getCiudad(j+1),posibleInsercion)<costeInicial){
                                costeInicial=rutaInicial.getCiudad(j).calcCosteInsercion(rutaInicial.getCiudad(j+1),posibleInsercion);


                                posicion=j;
                            }
                        }     


            rutaInicial.addCiudadRuta(posibleInsercion,posicion+1);
            visitados[posibleInsercion.getEtiqueta()-1]=true;

            }


            rutaInicial.addCiudad(problema.obtenerCiudad(izquierda)); // volvemos a añadir la primera para que se forme el triángulo.

            for(int i=0;i<rutaInicial.getnCiudadesVisitadas()-1;i++){
                costeAlgoritmo3+=rutaInicial.getCiudad(i).calculaDistancia(rutaInicial.getCiudad(i+1));
            }
            rutaInicial.coste=costeAlgoritmo3;
            return rutaInicial;
        }   //Algoritmo 

    /**
     * Resolucion de la ruta mediante el algoritmo 3
     * @param problema
     * @return Objeto RutaSolucion
     */    
    public Ruta resolverAlgoritmo4(Problema problema){
            Random randomGenerator=new Random();
            int numeroaleatorio, numerociudadanterior=0,etiquetaPrimeraCiudad=0,etiquetaUltimaCiudad;

            Ruta mejorRuta=null,rutaAux=null;
            int k=1000; // k=numero de permutaciones del conjunto de ciudades
            int nciudades=problema.obtenerNciudades();
            boolean visitados[]= new boolean[nciudades];
            double costeaux=0,menorcoste=1000000;
            double mDistancias[][]=problema.getMatriz();

            rutaAux=new Ruta(nciudades);

            for(k=0;k<10000;k++){               ///SE GENERAN 10000 RUTAS Y SE COGE LA DE MENOR COSTE
                    for(int i=0;i<nciudades;i++){  //Ponemos el vector de ciudades visitadas a false, cuando pasemos por una ciudad le daremos valor true para contarla como visitada
                     visitados[i]=false;        
                     }
                costeaux=0;
                nciudadesenruta=0;

                for(int i=0;i<nciudades;i++){
                    numeroaleatorio=randomGenerator.nextInt(nciudades)+1;
                    if(i==0){
                        etiquetaPrimeraCiudad=numeroaleatorio;
                        numerociudadanterior=numeroaleatorio;
                    }
                        if(visitados[numeroaleatorio-1]==false){
                                rutaAux.addCiudad(problema.obtenerCiudad(numeroaleatorio));   //le resta 1 en obtenerciudad
                                visitados[numeroaleatorio-1]=true;                                              
                                costeaux=costeaux+mDistancias[numeroaleatorio-1][numerociudadanterior-1];
                                nciudadesenruta++;
                                numerociudadanterior=numeroaleatorio;
                        if(nciudades==nciudadesenruta){
                            i=nciudades;
                            rutaAux.addCiudad(problema.obtenerCiudad(etiquetaPrimeraCiudad));
                            etiquetaUltimaCiudad=numeroaleatorio;
                            costeaux=costeaux+mDistancias[etiquetaPrimeraCiudad-1][etiquetaUltimaCiudad-1];
                        }
                        }else{
                         i--;
                        }

                }
                 if(costeaux<menorcoste){
                            menorcoste=costeaux;
                            mejorRuta=rutaAux;
                 }
                 rutaAux=null;
                 rutaAux=new Ruta(nciudades);

            }
            mejorRuta.coste=costeaux;
            return mejorRuta;
        }
    
    /**
     * Busca la distancia minima entre una ciudad y todas las ciudades de una Ruta que se le pasa, pasandole 
     * también la matriz de Distancias.
     * @param mDistancias
     * @param ciudad
     * @param rutaInicial
     * @return double Distancia minima
     */
   public double buscarDistanciaMinima(double mDistancias[][],Ciudad ciudad,Ruta rutaInicial){
        
        double  distanciaMinima=mDistancias[(ciudad.getEtiqueta()-1)][rutaInicial.getCiudad(0).getEtiqueta()-1];
        
        for(int i =0;i<rutaInicial.getnCiudadesVisitadas();i++){
            if(mDistancias[rutaInicial.getCiudad(i).getEtiqueta()-1][ciudad.getEtiqueta()-1]<distanciaMinima && mDistancias[rutaInicial.getCiudad(i).getEtiqueta()-1][ciudad.getEtiqueta()-1] != 0) {
                distanciaMinima=mDistancias[rutaInicial.getCiudad(i).getEtiqueta()-1][ciudad.getEtiqueta()-1];
                
            }
        
        }
        return distanciaMinima;        
    }


   
  
    

}
    
    
 
