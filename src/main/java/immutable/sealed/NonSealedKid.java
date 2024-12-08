package immutable.sealed;

public non-sealed class NonSealedKid extends SpecialAbstractClass {
    @Override
    public String aboutMe() {
        return getClass().getName() + " -> I'm non-sealed permitted class";
    }
}
