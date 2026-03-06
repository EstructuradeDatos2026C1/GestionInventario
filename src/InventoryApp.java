import java.io.*;
import java.util.Scanner;

public class InventoryApp {
  private static final String FILE_NAME = "inventario.csv";

  public static void main(String[] args) {
    ListaSimple lista = new ListaSimple();
    cargarDatos(lista);

    lista.insertarFinal(new Producto(101, "Laptop", 1200.0));
    lista.insertarFinal(new Producto(102, "Mouse", 25.0));
    lista.insertarFinal(new Producto(103, "Monitor", 300.0));

    System.out.println("Inventario Original:");
    lista.mostrar();

    System.out.println("\nOrdenando por precio...");
    lista.ordenarPorPrecio();
    lista.mostrar();

    guardarDatos(lista);
  }

  public static void guardarDatos(ListaSimple lista) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
      NodoLista aux = lista.cabeza;
      while (aux != null) {
        pw.println(aux.dato.id + "," + aux.dato.nombre + "," + aux.dato.precio);
        aux = aux.siguiente;
      }
    } catch (IOException e) {
      System.out.println("Error al guardar.");
    }
  }

  public static void cargarDatos(ListaSimple lista) {
    File file = new File(FILE_NAME);
    if (!file.exists())
      return;
    try (Scanner sc = new Scanner(file)) {
      while (sc.hasNextLine()) {
        String[] parts = sc.nextLine().split(",");
        lista.insertarFinal(new Producto(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2])));
      }
    } catch (Exception e) {
      System.out.println("Error al cargar.");
    }
  }
}