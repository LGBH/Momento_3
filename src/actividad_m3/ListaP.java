package actividad_m3;

import javax.swing.JOptionPane;

public class ListaP {

    Producto cab;

    ListaP() {

        cab = null;

    }

    Producto getUltimo() {
        if (cab == null) {
            return null;
        } else {
            Producto aux = cab;
            while (aux.sig != cab) {
                aux = aux.sig;
            }
            return aux;
        }
    }

    public boolean getIsEmpty() {
        return cab == null ? true : false;

    }

    Producto getReferencia(String id) {
        if (cab == null) {
            return null;
        } else {
            Producto p = cab;
            do {
                if ((p.idProducto).equalsIgnoreCase(id)) {
                    return p;
                } else {
                    p = p.sig;
                }
            } while (p != cab);
            return null;
        }
    }

    int getLongLista() {
        int cont = 0;
        if (cab == null) {
            return cont;
        } else {
            Producto p = cab;
            do {
                cont++;
                p = p.sig;
            } while (p != cab);
            return cont;
        }
    }

    void setAddInicio(Producto q) {
        if (cab == null) {
            cab = q;
            cab.sig = cab;
            JOptionPane.showMessageDialog(null,
                    "Producto agregado al inicio...");
        } else {
            Producto p = getReferencia(q.idProducto);
            if (p != null) {
                JOptionPane.showMessageDialog(null,
                        "La referencia existe!!!");
            } else {
                p = getUltimo();
                q.sig = cab;
                p.sig = q;
                cab = q;
                q = p = null;
                JOptionPane.showMessageDialog(null,
                        "Producto agregado al inicio...");
            }
        }
    }

    void setAddFinal(Producto q) {
        Producto p, aux;
        if (cab == null) {
            cab = q;
            cab.sig = cab;
            JOptionPane.showMessageDialog(null, "Agregado al final...");
        } else {
            aux = getReferencia(q.idProducto);
            if (aux == null) {
                p = getUltimo();
                p.sig = q;
                q.sig = cab;
                JOptionPane.showMessageDialog(null,
                        "Agregado al final...");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Elemento existe...");
            }
        }
    }

    void eliminarProductoPorId(String id) {
        if (cab == null) {
            JOptionPane.showMessageDialog(null, "La lista está vacía");
            return;
        }

        Producto p = getReferencia(id);

        if (p == null) {
            JOptionPane.showMessageDialog(null, "El producto con ID " + id + " no existe");
            return;
        }

        if (p == cab) {
            // Si el producto a eliminar es el primero
            if (cab.sig == cab) {
                // Si hay un solo elemento en la lista
                cab = null;
            } else {
                // Si hay más de un elemento en la lista
                Producto ultimo = getUltimo();
                cab = cab.sig;
                ultimo.sig = cab;
            }
        } else {
            // Si el producto a eliminar no es el primero
            Producto anterior = null;
            Producto current = cab;

            while (current != p) {
                anterior = current;
                current = current.sig;
            }

            anterior.sig = p.sig;
        }

        JOptionPane.showMessageDialog(null, "Producto con ID " + id + " eliminado");
    }

}
