import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Número do quarto: ");
            int room = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Data de check-in (dd/MM/yyyy): ");
            LocalDate checkin = LocalDate.parse(sc.nextLine().trim(), fmt);

            System.out.print("Data de check-out (dd/MM/yyyy): ");
            LocalDate checkout = LocalDate.parse(sc.nextLine().trim(), fmt);

            Reserva reservation = new Reserva(room, checkin, checkout);
            System.out.println("Reserva: " + reservation);

            System.out.println();
            System.out.println("Entre com as novas datas da reserva:");
            System.out.print("Novo check-in (dd/MM/yyyy): ");
            LocalDate newCheckin = LocalDate.parse(sc.nextLine().trim(), fmt);

            System.out.print("Novo check-out (dd/MM/yyyy): ");
            LocalDate newCheckout = LocalDate.parse(sc.nextLine().trim(), fmt);

            reservation.updateDates(newCheckin, newCheckout);
            System.out.println("Reserva atualizada: " + reservation);
        } catch (DomainException e) {
            System.out.println("Erro na reserva: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Erro: data inválida. Use o formato dd/MM/yyyy.");
        } catch (RuntimeException e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
