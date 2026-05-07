import java.util.ArrayList;
import java.util.Scanner;

public class GestorProductos {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ArrayList<Producto> — lista dinámica, crece sola con cada add()
        // <Producto> permite guardar cualquier subclase gracias al polimorfismo
        // Métodos que vamos a usar: add(), get(), size()
        ArrayList<Producto> productos = new ArrayList<>();
        String busqueda = null;
        Producto encontrado = null;
        int stock;
        String nombre;
        double precio;

        int opcion = 0;

        do {
            System.out.println("\n----------------------------");
            System.out.println("     GESTOR DE PRODUCTOS      ");
            System.out.println("-------------------------------");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Actualizar Producto");
            System.out.println("3. Mostrar todos los productos");
            System.out.println("4. Buscar producto por nombre");
            System.out.println("5. Eliminar Producto");
            System.out.println("6. Hacer pedido");
            System.out.println("7. Salir");
            System.out.print("Opción: ");

            // Leemos la opción como String y la validamos en Validador
            // Repasa: los Strings leídos desde Scanner nunca vienen del pool
            opcion = Validador.leerOpcion(scanner);

            switch (opcion) {

                case 1:
                    // ── AGREGAR PRODUCTO FÍSICO ──
                    System.out.println("\n── Nuevo producto ──");

                    nombre = Validador.leerTexto(scanner, "Nombre:  ");
                    precio = Validador.leerPrecio(scanner, "Precio:  ");
                    stock     = Validador.leerEnteroPositivo(scanner, "Stock:   ");

                    // add() agrega al final de la lista
                    // Polimorfismo: guardamos ProductoFisico en lista de tipo Producto
                    productos.add(0,new Producto(nombre, precio, stock));

                    // size() devuelve la cantidad actual de elementos
                    System.out.println("Producto agregado. Total: " + productos.size());
                    break;


                case 2:
                    // ── ACTUALIZAR PRODUCTO──
                    System.out.println("\n── Buscar producto a actualizar──");

                    if (productos.size() == 0) {
                        System.out.println("No hay productos cargados aún.");
                        break;
                    }

                    System.out.print("Nombre a buscar: ");
                    busqueda = scanner.nextLine();

                    // Normalizamos igual que en el constructor de Producto
                    // trim() y toUpperCase() devuelven nuevos Strings → reasignamos
                    // Si no lo hacemos, "teclado" nunca encontraría "TECLADO"
                    busqueda = busqueda.trim().toUpperCase();

                    encontrado = null;

                    for (int i = 0; i < productos.size(); i++) {
                        // equals() para comparar Strings — NUNCA ==
                        // Los datos vienen de Scanner → nunca del String Pool
                        // == siempre daría false aunque el texto sea idéntico
                        if (productos.get(i).getNombre().equals(busqueda)) {
                            encontrado = productos.get(i);
                            break;
                        }
                    }

                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                        nombre = Validador.leerTexto(scanner, "Nuevo Nombre:  ");
                        encontrado.setNombre(nombre);
                        precio = Validador.leerPrecio(scanner, "NuevoPrecio:  ");
                        encontrado.setPrecio(precio);
                        stock  = Validador.leerEnteroPositivo(scanner, "Nuevo Stock:   ");
                        encontrado.setStock(stock);
                    } else {
                        System.out.println("No se encontró: " + busqueda);
                    }
                    break;
                


                case 3:
                    // ── MOSTRAR TODOS ──
                    System.out.println("\n── Productos registrados ──");

                    if (productos.size() == 0) {
                        System.out.println("No hay productos cargados aún.");
                    } else {
                        // get(i) accede al elemento en la posición i
                        // toString() se llama automáticamente en println()
                        // Java decide en ejecución qué versión usar → polimorfismo
                        for (int i = 0; i < productos.size(); i++) {
                            System.out.println((i + 1) + ". " + productos.get(i));
                        }
                    }
                    break;


                case 4:
                    // ── BUSCAR POR NOMBRE ──
                    System.out.println("\n── Buscar producto ──");

                    if (productos.size() == 0) {
                        System.out.println("No hay productos cargados aún.");
                        break;
                    }

                    System.out.print("Nombre a buscar: ");
                    busqueda = scanner.nextLine();

                    // Normalizamos igual que en el constructor de Producto
                    // trim() y toUpperCase() devuelven nuevos Strings → reasignamos
                    // Si no lo hacemos, "teclado" nunca encontraría "TECLADO"
                    busqueda = busqueda.trim().toUpperCase();

                    encontrado = null;

                    for (int i = 0; i < productos.size(); i++) {
                        // equals() para comparar Strings — NUNCA ==
                        // Los datos vienen de Scanner → nunca del String Pool
                        // == siempre daría false aunque el texto sea idéntico
                        if (productos.get(i).getNombre().equals(busqueda)) {
                            encontrado = productos.get(i);
                            break;
                        }
                    }

                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró: " + busqueda);
                    }
                    break;

                case 5:
                    System.out.println("\n── Buscar producto a eliminar──");

                    if (productos.size() == 0) {
                        System.out.println("No hay productos cargados aún.");
                        break;
                    }

                    System.out.print("Nombre a buscar: ");
                    busqueda = scanner.nextLine();

                    // Normalizamos igual que en el constructor de Producto
                    // trim() y toUpperCase() devuelven nuevos Strings → reasignamos
                    // Si no lo hacemos, "teclado" nunca encontraría "TECLADO"
                    busqueda = busqueda.trim().toUpperCase();

                    encontrado = null;

                    for (int i = 0; i < productos.size(); i++) {
                        // equals() para comparar Strings — NUNCA ==
                        // Los datos vienen de Scanner → nunca del String Pool
                        // == siempre daría false aunque el texto sea idéntico
                        if (productos.get(i).getNombre().equals(busqueda)) {
                            encontrado = productos.get(i);
                            productos.remove(i);
                            break;
                        }
                    }

                    if (encontrado != null) {
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró: " + busqueda);
                    }

                    break;
                
                case 6:
                    break;

                case 7:
                    System.out.println("Saliendo...");
                    break;


                default:
                    System.out.println("Opción inválida. Elegí entre 1 y 5.");
            }

        } while (opcion != 7);

        scanner.close();
    }
}