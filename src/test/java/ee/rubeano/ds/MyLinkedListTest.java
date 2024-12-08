package ee.rubeano.ds;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {

    @Test
    public void testMyLinkedList() {
        // empty list
        System.out.printf("=> Testing %s <= %n", "empty list");
        MyLinkedList list = new MyLinkedList(null);
        list.traverse(null);

        // null node addition
//        System.out.printf("=> Testing %s <= %n", "+1 null node");
//        assertFalse(list.addItem(null));
//        list.traverse(list.getRoot());

        // 1 node
        System.out.printf("=> Testing %s <= %n", "+1 node");
        assertTrue(list.addItem(new Node("str1")));
        list.traverse(list.getRoot());

        // 2 nodes
        System.out.printf("=> Testing %s <= %n", "+1 node (2 nodes)");
        assertTrue(list.addItem(new Node("str3")));
        list.traverse(list.getRoot());

        // 3 nodes and order
        System.out.printf("=> Testing %s <= %n", "+1 node and nodes' order (3 nodes)");
        assertTrue(list.addItem(new Node("str0")));
        list.traverse(list.getRoot());

        // 4 nodes and order
        System.out.printf("=> Testing %s <= %n", "+1 node and nodes' order (4 nodes)");
        assertTrue(list.addItem(new Node("str2")));
        list.traverse(list.getRoot());

        // non-existing node removal
        System.out.printf("=> Testing %s <= %n", "-1 non-existing node");
        assertFalse(list.removeItem(new Node("non-existing-node")));

        // null node removal
        System.out.printf("=> Testing %s <= %n", "-1 null node");
        assertFalse(list.removeItem(null));

        // node removal from the middle
        System.out.printf("=> Testing %s <= %n", "-1 existing node from the middle");
        assertTrue(list.removeItem(new Node("str2")));
        list.traverse(list.getRoot());

        // node removal from the start
        System.out.printf("=> Testing %s <= %n", "-1 existing node from the start");
        assertTrue(list.removeItem(new Node("str0")));
        list.traverse(list.getRoot());

        // node removal from the end
        System.out.printf("=> Testing %s <= %n", "-1 existing node from the end");
        assertTrue(list.removeItem(new Node("str3")));
        list.traverse(list.getRoot());

        // last node removal
        System.out.printf("=> Testing %s <= %n", "last node removal");
        assertTrue(list.removeItem(new Node("str1")));
        list.traverse(list.getRoot());
    }

}
