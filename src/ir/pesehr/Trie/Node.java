package ir.pesehr.Trie;

/**
 * @author: pesehr.
 */
public class Node {

  private int level = 0;
  private String prefix;
  private Node leftNode;
  private Node rightNode;

  public Node(int level, String prefix, Node leftNode, Node rightNode) {
    this.level = level;
    this.prefix = prefix;
    this.leftNode = leftNode;
    this.rightNode = rightNode;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public Node find(String binary, boolean add) {
    if ("".equals(binary) || binary == null)
      return this;
    else if (binary.charAt(0) == '1')
      if (rightNode == null)
        if (add) {
          rightNode = new Node(level + 1, prefix, null, null);
          return rightNode.find(binary.substring(1), true);
        } else
          return this;
      else return rightNode.find(binary.substring(1), true);
    else if (leftNode == null)
      if (add) {
        leftNode = new Node(level + 1, prefix, null, null);
        return leftNode.find(binary.substring(1), true);
      } else
        return this;
    else return leftNode.find(binary.substring(1), true);
  }

  @Override
  public String toString() {
    return "level " + level + " prefix: " + prefix + " left: " + leftNode + " right: " + rightNode;
  }

  public int getLevel() {
    return level;
  }

  public String getPrefix() {
    return prefix;
  }

  public Node getLeftNode() {
    return leftNode;
  }

  public Node getRightNode() {
    return rightNode;
  }
}
