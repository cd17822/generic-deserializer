package genericDeser.tree;

class RBNode<T extends Comparable<T>> { // package private
    private RBNode parent;
    private RBNode left;
    private RBNode right;
    private T data;
    private int freq;
    private Boolean red;
    private Boolean leftChild;

    public RBNode(T dataIn, RBNode parentIn, Boolean leftChildIn) {
        parent = parentIn;
        left = null;
        right = null;
        data = dataIn;
        freq = 1;
        red = true;
        leftChild = leftChildIn;
    }

    public String toString() {
        String l = (left == null) ? null : Integer.toString(left.hashCode());
        String r = (right == null) ? null : Integer.toString(right.hashCode());
        String p = (parent == null) ? null : Integer.toString(parent.hashCode());
        String c = (leftChild == null) ? null : Boolean.toString(leftChild);
        String string = "NODE(" + hashCode() + ")" +
                        "\ndata: " + data +
                        "\nfreq: " + freq +
                        "\nparent: " + p +
                        "\nred: " + red +
                        "\nleft: " + l +
                        "\nright: " + r +
                        "\nleftChild: " + c + "\n";
        return string;
    }

    public int compareTo(RBNode<T> other) {
        return data.compareTo(other.getData());
    }

    public T getData() {
        return data;
    }

    public void setRight(RBNode node) {
        right = node;
    }

    public void setLeft(RBNode node) {
        left = node;
    }

    public RBNode getLeft() {
        return left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setFreq(int freqIn) {
        freq = freqIn;
    }

    public int getFreq() {
        return freq;
    }

    public void setParent(RBNode node) {
        parent = node;
    }

    public RBNode getParent() {
        return parent;
    }

    public Boolean getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Boolean isLeftChild) {
        leftChild = isLeftChild;
    }

    public Boolean getRed() {
        return red;
    }

    public void setRed(Boolean isRed) {
        red = isRed;
    }
}
