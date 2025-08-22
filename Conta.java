public class Conta {
    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;

    public Conta(Integer number, String holder, Double balance, Double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public Integer getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void deposit(Double amount) {
        if (amount == null || amount <= 0.0) throw new Dominio("Valor de depósito inválido");
        balance += amount;
    }

    public void withdraw(Double amount) {
        if (amount == null || amount <= 0.0) throw new Dominio("Valor de saque inválido");
        if (amount > withdrawLimit) throw new Dominio("O valor do saque excede o limite");
        if (amount > balance) throw new Dominio("Saldo insuficiente");
        balance -= amount;
    }
}
