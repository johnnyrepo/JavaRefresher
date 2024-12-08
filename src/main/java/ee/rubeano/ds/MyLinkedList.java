package ee.rubeano.ds;

public class MyLinkedList implements NodeList {

    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return root;
    }

    @Override
    public boolean addItem(ListItem item) {
        if (root == null) {
            root = item;
            return true;
        }

        var currentItem = root;
        while (currentItem != null) {
            if (currentItem.compareTo(item) > 0) {
                if (currentItem.previous() != null) {
                    currentItem.previous().setNext(item);
                    item.setNext(currentItem);
                    item.setPrevious(currentItem.previous());
                    currentItem.setPrevious(item);
                } else {
                    root = item;
                    item.setNext(currentItem);
                    currentItem.setPrevious(item);
                }

                return true;
            } else if (currentItem.compareTo(item) < 0) {
                if (currentItem.next() == null) {
                    currentItem.setNext(item);
                    item.setPrevious(currentItem);

                    return true;
                }
            }
            currentItem = currentItem.next();
        }

        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (root == null || item == null) {
            return false;
        }

        var currentItem = root;
        while (currentItem != null) {
            if (currentItem.compareTo(item) == 0) {
                var prev = currentItem.previous();
                var next = currentItem.next();
                if (prev == null) {
                    root = next;
                    if (next != null) {
                        next.setPrevious(null);
                    }

                    return true;
                } else {
                    prev.setNext(next);
                    if (next != null) {
                        next.setPrevious(prev);
                    }

                    return true;
                }
            }

            currentItem = currentItem.next();
        }

        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
        }

        var currentItem = root;
        while (currentItem != null) {
            System.out.println(currentItem.value);
            currentItem = currentItem.next();
        }
    }

}
