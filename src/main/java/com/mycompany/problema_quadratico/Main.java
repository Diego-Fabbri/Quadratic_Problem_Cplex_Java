package com.mycompany.problema_quadratico;

import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author diego
 */
// NOTA: riproduciamo quanto fatto in: https://www.youtube.com/watch?v=Ro5-DjQibnk
public class Main {

    public static void main(String[] args) throws IloException, FileNotFoundException {
        System.setOut(new PrintStream("Problema_Quadratico.log"));
        IloCplex cplex = new IloCplex();
        
        IloNumVar x1 = cplex.numVar(0, 40, "x1");
        IloNumVar x2 = cplex.numVar(0, Double.POSITIVE_INFINITY, "x2");
        IloNumVar x3 = cplex.numVar(0, Double.POSITIVE_INFINITY, "x3");
        //FUNZIONE OBIETTIVO
        cplex.addMaximize(
                cplex.sum(// sommatoria
                        x1,
                        cplex.prod(2, x2),
                        cplex.prod(3, x3),
                        cplex.prod(-16.5, x1, x1),
                        cplex.prod(-11, x2, x2),
                        cplex.prod(-5.5, x3, x3),
                        cplex.prod(6, x1, x2),
                        cplex.prod(11.5, x2, x3)));
cplex.addLe(cplex.sum(cplex.prod(-1,x1),x2,x3),20);// vincolo 1
cplex.addLe(cplex.sum(cplex.prod(-3,x2),x1,x3),30);// vincolo 2
if(cplex.solve())
{
cplex.exportModel("Problema_Quadratico.lp");
System.out.println("Il valore di funzione obiettivo e':  "+cplex.getObjValue());
System.out.println("Il valore della variabile  "+x1.getName()+ "  e'  :"+cplex.getValue(x1));
System.out.println("Il valore della variabile  "+x2.getName()+ "  e'  :"+cplex.getValue(x2));
System.out.println("Il valore della variabile  "+x3.getName()+ "  e'  :"+cplex.getValue(x3));}
else{System.out.println("Problema non risolvibile");
}
    }
}
