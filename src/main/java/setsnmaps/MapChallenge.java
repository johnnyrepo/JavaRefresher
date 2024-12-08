package setsnmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapChallenge {

    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");

        List<Contact> fullList = new ArrayList<>(phones);
        fullList.addAll(emails);
        fullList.forEach(System.out::println);
        System.out.println("------------");

        // HashMap
        hashMap(fullList);
    }

    private static void hashMap(List<Contact> fullList) {
        System.out.println("=> HashMap <=");

        Map<String, Contact> contacts = new HashMap<>();
        for (Contact contact : fullList) {
            contacts.put(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.get()
        System.out.println("=> Map.get()");
        System.out.println(contacts.get("Charlie Brown"));
        System.out.println("------------");

        // Map.getOrDefault()
        System.out.println("=> Map.getOrDefault()");
        Contact defaultContact = new Contact("Chuck Brown");
        System.out.println(contacts.getOrDefault("Chuck Brown", defaultContact));
        System.out.println("------------");

        // Map.put() - and duplicates handling
        System.out.println("=> Map.put()");
        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.put(contact.getName(), contact);
            if (duplicate != null) {
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.putIfAbsent()
        System.out.println("=> Map.putIfAbsent()");
        contacts.clear();
        for (Contact contact : fullList) {
            contacts.putIfAbsent(contact.getName(), contact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        contacts.clear();
        for (Contact contact : fullList) {
            Contact duplicate = contacts.putIfAbsent(contact.getName(), contact);
            if (duplicate != null) {
                contacts.put(contact.getName(), contact.mergeContactData(duplicate));
            }
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.merge()
        System.out.println("=> Map.merge()");
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergeContactData));
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.compute() & Map.computeIfAbsent()
        System.out.println("=> Map.compute() & Map.computeIfAbsent()");
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            // contacts.compute(contactName, (k, v) -> new Contact(k));
            contacts.computeIfAbsent(contactName, k -> new Contact(k));
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.computeIfPresent()
        System.out.println("=> Map.computeIfPresent()");
        for (String contactName : new String[] {"Daisy Duck", "Daffy Duck", "Scrooge McDuck"}) {
            contacts.computeIfPresent(contactName, (k, v) -> {
                v.addEmail("Fun Place");
                return v;
            });
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.replaceAll()
        System.out.println("=> Map.replaceAll()");
        contacts.replaceAll((k, v) -> {
            String newEmail = k.replaceAll(" ", "") + "@funplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com", newEmail);
            return v;
        });
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.replace()
        System.out.println("=> Map.replace(k, v)");
        Contact daisy = new Contact("Daisy Jane Duck");
        Contact replacedContact = contacts.replace("Daisy Duck", daisy);
        System.out.println("daisy= " + daisy);
        System.out.println("replacedContact= " + replacedContact);
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.replace()
        System.out.println("=> Map.replace(k, v, v)");
        Contact updatedDaisy = replacedContact.mergeContactData(daisy);
        System.out.println("updatedDaisy= " + updatedDaisy);
        boolean success = contacts.replace("Daisy Duck", replacedContact, updatedDaisy);
        if (success) {
            System.out.println("Successfully replaced element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n", "Daisy Duck", replacedContact);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");

        // Map.remove(k, v)
        System.out.println("=> Map.remove(k, v)");
        success = contacts.remove("Daisy Duck", daisy);
        if (success) {
            System.out.println("Successfully removed element");
        } else {
            System.out.printf("Did not match on both key: %s and value: %s %n", "Daisy Duck", daisy);
        }
        contacts.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
        System.out.println("------------");
    }

}
