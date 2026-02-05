package controllers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import models.Pedido;

public class PedidoController {
  
  public Stack<Pedido> filtrarPorZona(List<Pedido> pedidos, int umbral){
    Stack<Pedido> stack =new Stack<>();
    for (Pedido p : pedidos) {
    if (p.getZona() > umbral) {
        stack.push(p);
    }
}
    return stack;
  }
public TreeSet<Pedido> ordenarPorZona(Stack<Pedido> pila) {

    TreeSet<Pedido> set = new TreeSet<>(new Comparator<Pedido>() {
        @Override
        public int compare(Pedido a, Pedido b) {

            int cmpZona = Integer.compare(b.getZona(), a.getZona());

            if (cmpZona != 0) {
                return cmpZona;
            }

            int cmpCliente = a.getCliente().compareTo(b.getCliente());

            if (cmpCliente != 0) {
                return cmpCliente;
            }

            return 0;
        }
    });

    set.addAll(pila);
    return set;
}



  public TreeMap<Integer, Queue<Pedido>> agruparPorUrgencia(List<Pedido> pedidos){
    TreeMap<Integer , Queue<Pedido>> mapa=new TreeMap<>();
    for (Pedido p : pedidos) {
      int urg=p.getUrgencias();
      mapa.putIfAbsent(urg, new LinkedList<>());
      mapa.get(urg).add(p);
    }
    return mapa;
  }

    public Stack<Pedido> explotarGrupo(Map<Integer, Queue<Pedido>> mapa) {

        int mejorClave = -1;
        int maxCantidad = -1;

        // buscar grupo mayor
        for (Map.Entry<Integer, Queue<Pedido>> entry : mapa.entrySet()) {

            int urg = entry.getKey();
            int size = entry.getValue().size();

            if (size > maxCantidad ||
               (size == maxCantidad && urg > mejorClave)) {

                maxCantidad = size;
                mejorClave = urg;
            }
        }

        // convertir a stack LIFO
        Stack<Pedido> stack = new Stack<>();
        Queue<Pedido> cola = mapa.get(mejorClave);

        for (Pedido p : cola) {
            stack.push(p);
        }

        return stack;
    }
}
