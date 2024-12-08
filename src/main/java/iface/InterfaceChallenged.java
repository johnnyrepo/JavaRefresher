package iface;

public interface InterfaceChallenged {

    /**
     * Extension method.
     * It's a lot like method on superclass, as it can be overridden
     * Since Java 8
     */
    default void defaultMethod() {
        System.out.println("I am 'defaultMethod()' of InterfaceChallenged");
        privateMethod();
    }

    /**
     * Example: Comparator.naturalOrder();
     * Since Java 8
     */
    static void staticMethod() {
        System.out.println("I am a 'staticMethod()' of InterfaceChallenged");
        privateStaticMethod();
    }

    /**
     * Addresses problem of re-use of code within concrete methods on an interface
     * Since Java 9
     */
    private void privateMethod() {
        System.out.println("I am 'privateMethod()' of InterfaceChallenged");
    }

    /**
     * Addresses problem of re-use of code within concrete methods on an interface
     * Since Java 9
     */
    private static void privateStaticMethod() {
        System.out.println("I am 'privateStaticMethod()' of InterfaceChallenged");
    }

}
