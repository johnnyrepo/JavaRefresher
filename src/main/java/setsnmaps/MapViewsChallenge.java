package setsnmaps;

import java.util.*;

public class MapViewsChallenge {

    public static void main(String[] args) {
        Map<String, Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));

        System.out.println("=> Map.keySet() <=");
        // Map.keySet() - returns a set of keys backed by the map
        Set<String> keysView = contacts.keySet();
        System.out.println(keysView);

        // KeySet.remove() - removes an element in corresponding map
        keysView.remove("Daffy Duck");
        contacts.forEach((k, v) -> System.out.println(v));

        // KeySet.retainAll() - to keep only needed elements in corresponding map
        keysView.retainAll(List.of("Linus Van Pelt", "Charlie Brown", "Robin Hood", "Mickey Mouse"));
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));

        // KeySet.clear()
        keysView.clear();
        System.out.println(contacts);

        // changing the Map affects it's KeySet
        ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
        ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
        System.out.println(keysView);
        System.out.println("------------");

        System.out.println("=> Map.values() <=");
        // => Map.values()
        var values = contacts.values();
        values.forEach(System.out::println);

        // Changing the Values affects the KeySet corresponding to the bound Map
        values.retainAll(ContactData.getData("email"));
        System.out.println(keysView);
        contacts.forEach((k, v) -> System.out.println(v));
        System.out.println("------------");

        List<Contact> list = new ArrayList<>(values);
        list.sort(Comparator.comparing(Contact::getNameLastFirst));
        list.forEach(c -> System.out.println(c.getNameLastFirst() + ": " + c));
        System.out.println("------------");

        Contact first = list.get(0);
        contacts.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
        keysView.forEach(System.out::println);
        System.out.println("------------");

        HashSet<Contact> hashSet = new HashSet<>(values);
        hashSet.forEach(System.out::println);
        if (hashSet.size() < contacts.keySet().size()) {
            System.out.println("Duplicate values are in my Map");
        }
        System.out.println("------------");

        // Map.entrySet()
        System.out.println("=> Map.entrySet() <=");
        var nodeSet = contacts.entrySet();
        for (var node : nodeSet) {
            if (!node.getKey().equals(node.getValue().getName())) {
                System.out.println("Key doesn't match name: " + node.getKey() + ": " + node.getValue());
            }
        }
    }

}
