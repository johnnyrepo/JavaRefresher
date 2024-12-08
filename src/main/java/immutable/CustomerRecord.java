package immutable;

public record CustomerRecord(String name, String dob) {

    public CustomerRecord(String name) {
        this(name, null);
    }
}
