package ee.rubeano.ds;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class SearchTreeTest {

    @Test
    public void testSearchTree() {
        // empty tree
        System.out.printf("=> Testing %s <= %n", "empty list");
        SearchTree tree = new SearchTree(null);
        tree.traverse(tree.getRoot());

        // 1 node
        System.out.printf("=> Testing %s <= %n", "+1 node");
        assertTrue(tree.addItem(new Node("1")));
        tree.traverse(tree.getRoot());

        // 2 nodes
        System.out.printf("=> Testing %s <= %n", "+1 node");
        assertTrue(tree.addItem(new Node("4")));
        tree.traverse(tree.getRoot());

        // 3 nodes
        System.out.printf("=> Testing %s <= %n", "+1 node");
        assertTrue(tree.addItem(new Node("5")));
        tree.traverse(tree.getRoot());

        // 4 nodes
        System.out.printf("=> Testing %s <= %n", "+1 node");
        assertTrue(tree.addItem(new Node("2")));
        tree.traverse(tree.getRoot());

        // 5 nodes
        System.out.printf("=> Testing %s <= %n", "+1 node");
        assertTrue(tree.addItem(new Node("3")));
        tree.traverse(tree.getRoot());

        // +1 node when it's already added
        System.out.printf("=> Testing %s <= %n", "+1 node when it's already added");
        assertFalse(tree.addItem(new Node("2")));

        // non-existing node removal
        System.out.printf("=> Testing %s <= %n", "-1 non-existing node");
        assertFalse(tree.removeItem(new Node("non-existing-node")));

        // null node removal
        System.out.printf("=> Testing %s <= %n", "-1 null node");
        assertFalse(tree.removeItem(null));

        // node removal from the middle
        System.out.printf("=> Testing %s <= %n", "-1 existing node from the middle");
        assertTrue(tree.removeItem(new Node("3")));
        tree.traverse(tree.getRoot());

        // node removal from the top
        System.out.printf("=> Testing %s <= %n", "-1 existing node from the top");
        assertTrue(tree.removeItem(new Node("1")));
        tree.traverse(tree.getRoot());

        // node removal from the bottom
        System.out.printf("=> Testing %s <= %n", "-1 existing node from the top");
        assertTrue(tree.removeItem(new Node("5")));
        tree.traverse(tree.getRoot());

        // last node removal
        System.out.printf("=> Testing %s <= %n", "-2 existing nodes to empty the tree");
        assertTrue(tree.removeItem(new Node("2")));
        tree.traverse(tree.getRoot());
        assertTrue(tree.removeItem(new Node("4")));
        tree.traverse(tree.getRoot());

        // from empty tree removal
        System.out.printf("=> Testing %s <= %n", "removal from empty tree");
        assertFalse(tree.removeItem(null));
        tree.traverse(tree.getRoot());
    }

    public final int sum(final int[] nums) {
        final int boo;
        boo = 1;
        int sum = 0;
        for (var i : nums) {
            sum += i;
        }
        return sum;
    }

}
