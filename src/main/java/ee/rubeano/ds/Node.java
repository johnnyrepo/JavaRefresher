package ee.rubeano.ds;

public class Node extends ListItem {

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        return rightLink;
    }

    @Override
    ListItem previous() {
        return leftLink;
    }

    @Override
    ListItem setNext(ListItem next) {
        rightLink = next;

        return rightLink;
    }

    @Override
    ListItem setPrevious(ListItem previous) {
        leftLink = previous;

        return leftLink;
    }

    @Override
    int compareTo(ListItem item) {
        if (item == null) {
            return 1;
        }
        if (value == null && item.value == null) {
            return 0;
        } else {
            if (value == null) {
                return -1;
            } else if (item.value == null) {
                return 1;
            }

            if (value.hashCode() > item.value.hashCode()) {
                return 1;
            } else if (value.hashCode() < item.value.hashCode()) {
                return -1;
            }
        }

        return 0;
    }

}
