
public class Producto {
    
    private String nombre;
    private double precio;
    private int  stock;

    // trim() elimina espacios, toUpperCase() normaliza el texto
    // Ambos devuelven un nuevo String — si no reasignamos, no cambia nada (inmutabilidad)
    public Producto(String nombre,double precio, int stock){
        this.nombre = nombre.trim().toUpperCase();
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int    getStock() { return stock; }

    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString() {
        return  getNombre()
                + " | $" + getPrecio()
                + " | Stock: " + getStock();
    }


}
