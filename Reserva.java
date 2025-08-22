import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reserva(Integer roomNumber, LocalDate checkin, LocalDate checkout) throws DomainException {
        validateDates(checkin, checkout);
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public long duration() {
        return ChronoUnit.DAYS.between(checkin, checkout);
    }

    public void updateDates(LocalDate newCheckin, LocalDate newCheckout) throws DomainException {
        if (newCheckin.isBefore(LocalDate.now()) || newCheckout.isBefore(LocalDate.now())) {
            throw new DomainException("Alterações de reserva só podem ocorrer para datas futuras.");
        }
        validateDates(newCheckin, newCheckout);
        this.checkin = newCheckin;
        this.checkout = newCheckout;
    }

    private void validateDates(LocalDate in, LocalDate out) throws DomainException {
        if (!out.isAfter(in)) {
            throw new DomainException("A data de saída deve ser maior que a data de entrada.");
        }
    }

    @Override
    public String toString() {
        return "Quarto " + roomNumber
                + ", check-in: " + checkin.format(FMT)
                + ", check-out: " + checkout.format(FMT)
                + ", " + duration() + " noite(s)";
    }
}
