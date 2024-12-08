import iface.InterfaceChallenged;
import iface.InterfaceChallengedClass;
import iface.InterfaceChallengedClass2;

public class InterfaceChallenge {

    public static void main(String[] args) {
        System.out.println("InterfaceChallenged:");
        InterfaceChallenged.staticMethod();
        System.out.println();

        InterfaceChallenged icc = new InterfaceChallengedClass();
        System.out.println("InterfaceChallengedClass:");
        icc.defaultMethod();
        System.out.println();

        InterfaceChallenged icc2 = new InterfaceChallengedClass2();
        System.out.println("InterfaceChallengedClass2:");
        icc2.defaultMethod();
        System.out.println();
    }

}
