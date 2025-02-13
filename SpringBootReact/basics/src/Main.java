import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double nota1, nota2, nota3, media;

        System.out.print("Introduce la primera nota: ");
        nota1 = scanner.nextDouble();

        System.out.print("Introduce la segunda nota: ");
        nota2 = scanner.nextDouble();

        System.out.print("Introduce la tercera nota: ");
        nota3 = scanner.nextDouble();

        // Corrección: cálculo correcto de la media
        media = (nota1 + nota2 + nota3) / 3;

        // Mostrar la media con dos decimales
        System.out.printf("Tu media es: %.2f%n", media);

        // Corrección en la condición de aprobado
        if (media >= 5) {
            System.out.println("Estás aprobado.");
        } else {
            System.out.println("Estás suspenso.");
        }

        scanner.close();
    }
}
