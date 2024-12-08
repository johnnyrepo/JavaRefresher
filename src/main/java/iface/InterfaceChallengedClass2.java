package iface;

public class InterfaceChallengedClass2 implements InterfaceChallenged {

    public void newMethod() {

    }

    @Override
    public void defaultMethod() {
        System.out.println("I am 'defaultMethod()' of InterfaceChallengedClass2");
        InterfaceChallenged.super.defaultMethod();
    }
}
