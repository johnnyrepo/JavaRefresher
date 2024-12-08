package ee.rubeano.ds;

public class SearchTree implements NodeList{

    private ListItem root;

    public SearchTree(ListItem root) {
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

        var currentNode = root;
        while (currentNode != null) {
            if (currentNode.compareTo(item) == 0) {
                return false;
            } else if (currentNode.compareTo(item) > 0) {
                if (currentNode.leftLink == null) {
                    currentNode.leftLink = item;

                    return true;
                }
                currentNode = currentNode.leftLink;
            } else {
                if (currentNode.rightLink == null) {
                    currentNode.rightLink = item;

                    return true;
                }
                currentNode = currentNode.rightLink;
            }
        }

        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (root == null || item == null) {
            return false;
        }

        var currentParent = root;
        var currentNode = root;
        while (currentNode != null) {
            if (currentNode.compareTo(item) == 0) {
                performRemoval(currentNode, currentParent);

                return true;
            } else if (currentNode.compareTo(item) > 0) {
                currentParent = currentNode;
                currentNode = currentNode.leftLink;
            } else {
                currentParent = currentNode;
                currentNode = currentNode.rightLink;
            }
        }

        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            return;
        }

        traverse(root.leftLink);
        System.out.println(root.value);
        traverse(root.rightLink);
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (parent.equals(item)) {
            root = (root.leftLink != null) ? root.leftLink : root.rightLink;
            return;
        }

        if (item.equals(parent.leftLink)) {
            parent.leftLink = item.leftLink;
        } else {
            parent.rightLink = item.rightLink;
        }
    }

}
