package immutable.sealed;

public final class FinalKid extends SpecialAbstractClass {
    @Override
    public String aboutMe() {
        return getClass().getName() + " -> I'm final permitted class";
    }
}
