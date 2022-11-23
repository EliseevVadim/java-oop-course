package utils;

public class ComboBoxFriendlyPair {
    private final long value;
    private final String text;

    public ComboBoxFriendlyPair(long value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String toString() {
        return value + " - " + text;
    }
}
