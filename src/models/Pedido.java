package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pedido {
  private String cliente;
  private String codigoPostal;
  private List<Integer> prioridades;
  public Pedido(String cliente, String codigoPostal, List<Integer> prioridades) {
    this.cliente = cliente;
    this.codigoPostal = codigoPostal;
    this.prioridades = prioridades;
  }
  public String getCliente() {
    return cliente;
  }
  public void setCliente(String cliente) {
    this.cliente = cliente;
  }
  public String getCodigoPostal() {
    return codigoPostal;
  }
  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }
  public List<Integer> getPrioridades() {
    return prioridades;
  }
  public void setPrioridades(List<Integer> prioridades) {
    this.prioridades = prioridades;
  }

  public int getZona(){
    String[] partes=codigoPostal.split("-");
    return Integer.parseInt(partes[1]);
  }

  public int getUrgencias(){
    int suma=prioridades.stream().filter(p -> p % 3==0).mapToInt(Integer::intValue).sum();
    Set<Character> vocales=new HashSet<>();
    String nombre=cliente.toLowerCase();
    for (char c : nombre.toCharArray()) {
      if ("aeiou".indexOf(c)>=0) {
        vocales.add(c);
      }
    }
    return suma*vocales.size();
  }
  @Override
  public String toString() {
    return "Pedido [getZona()=" + getZona() + ", getUrgencias()=" + getUrgencias() + "]";
  }
  public int getUrgencia() {
     int suma=prioridades.stream().filter(p -> p % 3==0).mapToInt(Integer::intValue).sum();
    Set<Character> vocales=new HashSet<>();
    String nombre=cliente.toLowerCase();
    for (char c : nombre.toCharArray()) {
      if ("aeiou".indexOf(c)>=0) {
        vocales.add(c);
      }
    }
    return suma*vocales.size();
  }

  
}
