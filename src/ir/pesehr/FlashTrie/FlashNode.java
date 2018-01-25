package ir.pesehr.FlashTrie;

import java.util.ArrayList;

/**
 * @author: pesehr.
 */
public class FlashNode {
  ArrayList<String> prefixes;
  int level = 0;
  public boolean valid = true;
  public boolean left = false;
  public boolean right = false;
  public FlashNode rightNode;
  public FlashNode leftNode;


  public FlashNode(int level) {
    this.level = level;
    prefixes = new ArrayList<String>();
  }

  public ArrayList<FlashNode> findAll(int start) {
    ArrayList<FlashNode> nodes = new ArrayList<FlashNode>();
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


  public void addPrefix(String prifix) {
    prefixes.add(prifix);
  }

  public void setLeft(boolean left) {
    if (left && right)
      valid = false;
    this.left = left;
  }

  public void setRight(boolean right) {
    if (left && right)
      valid = false;
    this.right = right;
  }

  public int getLevel() {
    return level;
  }

  public ArrayList<String> getPrefix() {
    return prefixes;
  }
}
