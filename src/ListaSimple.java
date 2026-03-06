class ListaSimple {
  NodoLista cabeza;

  public void insertarFinal(Producto p) {
    NodoLista nuevo = new NodoLista(p);
    if (cabeza == null)
      cabeza = nuevo;
    else {
      NodoLista aux = cabeza;
      while (aux.siguiente != null)
        aux = aux.siguiente;
      aux.siguiente = nuevo;
    }
  }

  public void eliminarPorId(int id) {
    if (cabeza == null)
      return;

    if (cabeza.dato.id == id) {
      cabeza = cabeza.siguiente;
      return;
    }
    NodoLista aux = cabeza;
    while (aux.siguiente != null && aux.siguiente.dato.id != id) {
      aux = aux.siguiente;
    }
    if (aux.siguiente != null)
      aux.siguiente = aux.siguiente.siguiente;
  }

  // Ordenamiento por burbuja adaptado (modifica punteros)
  public void ordenarPorPrecio() {
    if (cabeza == null || cabeza.siguiente == null)
      return;
    boolean huboCambio;
    do {
      huboCambio = false;
      NodoLista actual = cabeza;
      NodoLista anterior = null;
      while (actual != null && actual.siguiente != null) {
        if (actual.dato.precio > actual.siguiente.dato.precio) {
          huboCambio = true;
          NodoLista sig = actual.siguiente;
          actual.siguiente = sig.siguiente;
          sig.siguiente = actual;
          if (anterior == null)
            cabeza = sig;
          else
            anterior.siguiente = sig;
          anterior = sig;
        } else {
          anterior = actual;
          actual = actual.siguiente;
        }
      }
    } while (huboCambio);
  }

  public void mostrar() {
    NodoLista aux = cabeza;
    while (aux != null) {
      System.out.println(aux.dato);
      aux = aux.siguiente;
    }
  }
}