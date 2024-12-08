package setsnmaps;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetChallenge {

    public static void main(String[] args) {

        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails =  ContactData.getData("email");
        printData("Phone List", phones);
        printData("Email List", emails);

        Set<Contact> phoneContacts = new HashSet<>(phones);
        Set<Contact> emailContacts = new HashSet<>(emails);
        printData("Phone Contacts", phoneContacts);
        printData("Email Contacts", emailContacts);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        robinHood.addEmail("Sherwood Forest");
        robinHood.replaceEmailIfExists("RHood@sherwoodforest.com",
        "RHood@sherwoodforest.org");
        System.out.println(robinHood);


        // Set.addAll() - UNION
        Set<Contact> unionAB = new HashSet<>();
        unionAB.addAll(emailContacts);
        unionAB.addAll(phoneContacts);
        printData("(A \u222A B) Union of emails (A) with phones (B)", unionAB);

        // Set.retainAll() - INTERSECT
        Set<Contact> intersectAB = new HashSet<>(emailContacts);
        intersectAB.retainAll(phoneContacts);
        printData("(A \u229A B) Intersect emails (A) and phones (B)", intersectAB);

        // Set.removeAll()
        Set<Contact> AMinusB = new HashSet<>(emailContacts);
        AMinusB.removeAll(phoneContacts);
        printData("(A - B) emails (A) - phones (B)", AMinusB);

        Set<Contact> BMinusA = new HashSet<>(phoneContacts);
        BMinusA.removeAll(emailContacts);
        printData("(B - A) phones (B) - emails (a)", BMinusA);
    }

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}
