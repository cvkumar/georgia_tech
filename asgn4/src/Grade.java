/**
 * Created by kumarcal on 10/3/17.
 */
public enum Grade {
    A, B, C, D, F;

    public boolean isPassingGrade() {
        return this == Grade.A || this == Grade.B || this == Grade.C;
    }
}
