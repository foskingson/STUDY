
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Stu4_2 implements Comparable<Stu4_2> {

    private final long amount;

    public Stu4_2(long amount) {
        this.amount = amount;
    }

    public Stu4_2 plus(Stu4_2 other) {
        return new Stu4_2(this.amount + other.amount);
    }

    @Override
    public int compareTo(@NotNull Stu4_2 o) {
        return Long.compare(this.amount, o.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stu4_2 javaMoney = (Stu4_2) o;
        return amount == javaMoney.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Java Money {" +
                "amount=" + amount +
                '}';
    }

}