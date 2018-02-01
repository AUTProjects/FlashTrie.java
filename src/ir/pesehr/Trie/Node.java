package ir.pesehr.Trie;

import java.util.ArrayList;

/**
 * @author: pesehr.
 */
public class Node {

  private int level = 0;
  private String prefix;
  private Node leftNode;
  private Node rightNode;

  public Node(int level, String prefix) {
    this.level = level;
    this.prefix = prefix;
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
          rightNode = new Node(level + 1, prefix);
          return rightNode.find(binary.substring(1), add);
        } else
          return this;
      else return rightNode.find(binary.substring(1), add);
    else if (leftNode == null)
      if (add) {
        leftNode = new Node(level + 1, prefix);
        return leftNode.find(binary.substring(1), add);
      } else
        return this;
    else return leftNode.find(binary.substring(1), add);
  }

  @Override
  public String toString() {
    return "level " + level + " prefix: " + prefix;
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


  boolean isFull = false;

  public boolean isFullChild(int end) {

    return isFull || end == level || (rightNode.isFullChild(end) || rightNode.prefix != null)
            && (leftNode.isFullChild(end) || leftNode.prefix != null);
  }


  public ArrayList<Node> findAll(int start) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    if (level == start) {
      nodes.add(this);
      return nodes;
    }
    if (rightNode != null)
      nodes.addAll(rightNode.findAll(start));
    if (leftNode != null)
      nodes.addAll(leftNode.findAll(start));
    return nodes;
  }

  public void compress(int end) {
    if (end == level)
      return;
    if (rightNode != null && leftNode == null)
      leftNode = new Node(level + 1, prefix);
    if (leftNode != null && rightNode == null)
      rightNode = new Node(level + 1, prefix);

    if (rightNode != null && leftNode != null)
      if (leftNode.isFullChild(end) && rightNode.isFullChild(end))
        isFull = true;

    if (isFullChild(end))
      prefix = null;

  }

  public void complete() {
    if(leftNode == null)
      leftNode = new Node(level+1,prefix);
    if(rightNode == null)
      rightNode = new Node(level+1,prefix);
  }
}
