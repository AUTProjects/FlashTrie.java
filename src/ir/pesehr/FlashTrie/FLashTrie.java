package ir.pesehr.FlashTrie;

import ir.pesehr.Trie.Node;
import ir.pesehr.Trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: pesehr.
 */
public class FLashTrie {
  Trie trie;
  FlashNode root;

  public FLashTrie(HashMap<ir.pesehr.IP, String> ips) {
    trie = new Trie(ips);
    makePCTrie(32, null, 4);
   // trie.print();
  }

  public void makePCTrie(int level, Node root, int compSize) {
    for (int c = 0; c < (int) (Math.log(compSize) / Math.log(2) + 1e-10); c++) {
      ArrayList<Node> nodes = trie.findAll(c);
      for (Node node : nodes) {
        node.complete();
      }
    }

    ArrayList<FlashNode> flashNodes = new ArrayList<FlashNode>();
    FlashNode newFlashNode;
    ArrayList<FlashNode> newFlashNodes = new ArrayList<FlashNode>();
    for (int c = (int) (Math.log(compSize) / Math.log(2) + 1e-10); c < level; c++) {
      ArrayList<Node> nodes = trie.findAll(c);

      for (int compIndex = 0; compIndex < nodes.size(); compIndex += compSize) {
        newFlashNode = new FlashNode(c);
        for (int prefixIndex = 0; prefixIndex < compSize; prefixIndex++)
          newFlashNode.addPrefix(nodes.get(compIndex + prefixIndex).getPrefix());
        newFlashNodes.add(newFlashNode);

        if (this.root == null)
          this.root = newFlashNode;

        int halfCompIndex = compIndex;
        boolean isCompleate = false;
        for (int j = 0; j < compSize / 2; j++)
          if (nodes.get(j + halfCompIndex).getLeftNode() != null || nodes.get(j + halfCompIndex).getRightNode() != null)
            isCompleate = true;
        if (isCompleate) {
          for (int j = 0; j < compSize / 2; j++)
            nodes.get(halfCompIndex + j).complete();
          newFlashNode.setLeft(true);
        }

        halfCompIndex = compIndex + (compSize / 2);
        isCompleate = false;
        for (int j = 0; j < compSize / 2; j++)
          if (nodes.get(j + halfCompIndex).getLeftNode() != null || nodes.get(j + halfCompIndex).getRightNode() != null)
            isCompleate = true;
        if (isCompleate) {
          for (int j = 0; j < compSize / 2; j++)
            nodes.get(halfCompIndex + j).complete();
          newFlashNode.setRight(true);
        }
      }
      if (flashNodes.isEmpty()) {
        flashNodes.addAll(newFlashNodes);
        newFlashNodes = new ArrayList<FlashNode>();
      } else {
        for (FlashNode child : newFlashNodes)
          for (FlashNode parent : flashNodes) {
            if (parent.leftNode == null && parent.left) {
              parent.leftNode = child;
              break;
            } else if (parent.rightNode == null && parent.right) {
              parent.rightNode = child;
              break;
            }
          }
        flashNodes = new ArrayList<FlashNode>();
        flashNodes.addAll(newFlashNodes);
        newFlashNodes = new ArrayList<FlashNode>();
      }
    }

    print();
  }


  public void print() {
    ArrayList<FlashNode> temp = new ArrayList<FlashNode>();
    temp.add(root);
    while (temp.size() != 0) {
      ArrayList<FlashNode> temp2 = new ArrayList<FlashNode>();
      for (FlashNode node : temp) {
        System.out.print("level " + node.getLevel() + " prefix:" + node.getPrefix() + "valid: " + node.valid + " --- ");
        if (node.leftNode != null)
          temp2.add(node.leftNode);
        if (node.rightNode != null)
          temp2.add(node.rightNode);
      }
      temp = temp2;
      System.out.print("\n");
    }
  }
}