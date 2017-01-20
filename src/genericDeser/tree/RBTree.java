package genericDeser.tree;

import genericDeser.tree.RBNode;
import genericDeser.fileOperations.Logger;
import java.util.ArrayList;

public class RBTree<T extends Comparable<T>> {
    private RBNode<T> root;

    public RBTree() {
        root = null;
    }

    public RBNode<T> getRoot() {
        return root;
    }

    public void setRoot(RBNode<T> node) {
        root = node;
    }

    public void insert(T data) {
        RBNode<T> inserted;

        if (root == null) {
            root = new RBNode<T>(data, null, null);
            inserted = root;
        } else {
            inserted = insertHelper(data, root);
        }

        fixViolations(inserted);
    }

    public int getTotalCount() {
        return getTotalCountHelper(root);
    }

    public int getUniqueCount() {
        return getUniqueCountHelper(root);
    }

    private RBNode<T> insertHelper(T data, RBNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new RBNode<T>(data, node, true));
                return node.getLeft();
            } else {
                return insertHelper(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) == 0) { // equal
            node.setFreq(node.getFreq() + 1);
            return null;
        } else {
            if (node.getRight() == null) {
                node.setRight(new RBNode<T>(data, node, false));
                return node.getRight();
            } else {
                return insertHelper(data, node.getRight());
            }
        }
    }

    private void fixViolations(RBNode<T> node) {
        if (node == null) return;

        if (node.getParent() == null) {
            node.setRed(false);
        } else if (node.getParent().getRed()) {
            if (uncleIsRed(node)) {                                    // case 1
                Logger.writeMessage("Case 1", Logger.DebugLevel.TREE_STEPS);
                swapGrandpaParentUncleColors(node);
                fixViolations(node.getParent().getParent()); // if grandpa's the root, it should be black
            } else {
                if (node.getLeftChild() && !node.getParent().getLeftChild()) {        // case 2
                    Logger.writeMessage("Case 2 Right", Logger.DebugLevel.TREE_STEPS);
                    swapRight(node);
                    fixViolations(node.getRight()); // since it leads to case 3 violation
                } else if (!node.getLeftChild() && node.getParent().getLeftChild()) {  // case 2
                    Logger.writeMessage("Case 2 Left", Logger.DebugLevel.TREE_STEPS);
                    swapLeft(node);
                    fixViolations(node.getLeft()); // since it leads to case 3 violation
                } else if (node.getLeftChild() && node.getParent().getLeftChild()) {   // case 3
                    Logger.writeMessage("Case 3 Right", Logger.DebugLevel.TREE_STEPS);
                    rotateRight(node);
                } else {                                                 // case 3
                    Logger.writeMessage("Case 3 Left", Logger.DebugLevel.TREE_STEPS);
                    rotateLeft(node);
                }
            }
        }
    }

    private Boolean uncleIsRed(RBNode<T> node) {
        RBNode<T> g = node.getParent().getParent();
        return g != null &&
               g.getLeft() != null && g.getRight() != null &&
               g.getLeft().getRed() && g.getRight().getRed();
    }

    private void swapGrandpaParentUncleColors(RBNode<T> node) {
        RBNode<T> g = node.getParent().getParent();

        g.getLeft().setRed(false);
        g.getLeft().setRed(false);
        g.setRed(true);
    }

    private void swapRight(RBNode<T> node) {
        node.getParent().getParent().setRight(node);
        node.getParent().setLeft(node.getRight());
        if (node.getRight() != null) {
            node.getRight().setParent(node.getParent());
            node.getRight().setLeftChild(true);
        }
        node.setRight(node.getParent());
        node.setParent(node.getRight().getParent());
        node.getRight().setParent(node);
        node.setLeftChild(false);
        node.getRight().setLeftChild(false);
    }

    private void swapLeft(RBNode<T> node) {
        node.getParent().getParent().setLeft(node);
        node.getParent().setRight(node.getLeft());
        if (node.getLeft() != null) {
            node.getLeft().setParent(node.getParent());
            node.getLeft().setLeftChild(false);
        }
        node.setLeft(node.getParent());
        node.setParent(node.getLeft().getParent());
        node.getLeft().setParent(node);
        node.setLeftChild(true);
        node.getLeft().setLeftChild(true);
    }

    private void rotateRight(RBNode<T> node) {
        RBNode<T> x = node;
        RBNode<T> p = node.getParent();
        RBNode<T> b = node.getParent().getRight();
        RBNode<T> g = node.getParent().getParent();
        RBNode<T> gg = node.getParent().getParent().getParent();

        if (gg != null) {
            if (g.getLeftChild()) {
                gg.setLeft(p);
            } else {
                gg.setRight(p);
            }
        }

        g.setLeft(p.getRight());
        if (b != null) {
            b.setLeftChild(true);
            b.setParent(g);
        }
        p.setRight(g);
        p.setParent(gg);
        g.setParent(p);
        p.setLeftChild(g.getLeftChild());
        if (p.getLeftChild() == null) {
            root = p;
        }
        g.setLeftChild(false);

        p.setRed(false);
        g.setRed(true);
    }

    private void rotateLeft(RBNode<T> node) {
        RBNode<T> x = node;
        RBNode<T> p = node.getParent();
        RBNode<T> b = node.getParent().getLeft();
        RBNode<T> g = node.getParent().getParent();
        RBNode<T> gg = node.getParent().getParent().getParent();

        if (gg != null) {
            if (g.getLeftChild()) {
                gg.setLeft(p);
            } else {
                gg.setRight(p);
            }
        }

        g.setRight(p.getLeft());
        if (b != null) {
            b.setLeftChild(false);
            b.setParent(g);
        }
        p.setLeft(g);
        p.setParent(gg);
        g.setParent(p);
        p.setLeftChild(g.getLeftChild());
        if (p.getLeftChild() == null) {
            root = p;
        }
        g.setLeftChild(true);

        p.setRed(false);
        g.setRed(true);
    }

    private int getTotalCountHelper(RBNode<T> node) {
        int count = 0;
        if (node == null) return count;

        count += getTotalCountHelper(node.getLeft());
        count += node.getFreq();
        count += getTotalCountHelper(node.getRight());

        return count;
    }

    private int getUniqueCountHelper(RBNode<T> node) {
        int count = 0;
        if (node == null) return count;

        count += getUniqueCountHelper(node.getLeft());
        count += 1;
        count += getUniqueCountHelper(node.getRight());

        return count;
    }
}
