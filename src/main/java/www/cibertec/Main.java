package www.cibertec;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarVenta();
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion inválida, intentalo de nuevo.");
            }

        } while (opcion != 2);
    }

    static void mostrarMenu() {
        System.out.println("\n===== CAJA REGISTRADORA - TIENDA =====");
        System.out.println("1. Registrar venta");
        System.out.println("2. Salir");
        System.out.print("Elige una opción: ");
    }

    static void registrarVenta() {
        int cantidadProductos = leerCantidadProductos();
        double totalCompra = 0;

        for (int i = 1; i <= cantidadProductos; i++) {
            System.out.println("\nProducto " + i + ":");
            totalCompra += procesarProducto();
        }

        boolean esClienteFrecuente = preguntarClienteFrecuente();
        double totalConDescuento = aplicarDescuento(totalCompra, esClienteFrecuente);

        mostrarResumen(totalCompra, totalConDescuento);
    }

    static int leerCantidadProductos() {
        int cantidad;

        do {
            System.out.print("Cuántos productos va a llevar? ");
            cantidad = sc.nextInt();
            sc.nextLine();

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a 0.");
            }
        } while (cantidad <= 0);

        return cantidad;
    }

    static double procesarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.println("Categorías: 1.Abarrotes  2.Limpieza  3.Electrónica  4.Otros");
        System.out.print("Elige la categoría: ");
        int categoria = sc.nextInt();
        sc.nextLine();

        double precioUnitario = obtenerPrecioBase(categoria);

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        double subtotal = precioUnitario * cantidad;
        System.out.println(nombre + " -> Subtotal: S/ " + subtotal);

        return subtotal;
    }

    static double obtenerPrecioBase(int categoria) {
        double precio;

        switch (categoria) {
            case 1:
                precio = 5.0;
                break;
            case 2:
                precio = 8.5;
                break;
            case 3:
                precio = 150.0;
                break;
            case 4:
                precio = 10.0;
                break;
            default:
                System.out.println("Categoría inválida.");
                precio = 0.0;
        }

        return precio;
    }

    static boolean preguntarClienteFrecuente() {
        System.out.print("Es cliente frecuente? (S/N): ");
        String respuesta = sc.nextLine();
        boolean esFrecuente;

        if (respuesta.equals("S") || respuesta.equals("s")) {
            esFrecuente = true;
        } else {
            esFrecuente = false;
        }

        return esFrecuente;
    }

    static double aplicarDescuento(double total, boolean esFrecuente) {
        double descuento;

        if (esFrecuente && total > 100) {
            descuento = total * 0.15;
        } else if (esFrecuente) {
            descuento = total * 0.10;
        } else if (total > 100) {
            descuento = total * 0.05;
        } else {
            descuento = 0;
        }

        return total - descuento;
    }

    static void mostrarResumen(double total, double totalFinal) {
        System.out.println("\n--- RESUMEN DE VENTA ---");
        System.out.println("Total sin descuento: S/ " + total);
        System.out.println("Total a pagar: S/ " + totalFinal);
    }
}
