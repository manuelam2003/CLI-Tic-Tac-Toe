package tictactoe;

public enum FieldValues {

    CROSS("X"), ZERO("O"), EMPTY("_");

    private final String value;

    FieldValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}