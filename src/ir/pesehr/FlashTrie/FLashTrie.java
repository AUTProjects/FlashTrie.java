package ir.pesehr.FlashTrie;

import ir.pesehr.IP;
import ir.pesehr.Trie.Node;
import ir.pesehr.Trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author: pesehr.
 */
public class FLashTrie {
  Trie trie;
  HashMap<Node, FlashNode> tries;

  public FLashTrie(HashMap<ir.pesehr.IP, String> ips) {
    trie = new Trie(ips);
    tries = new HashMap<>();
    IP ip = new IP("0.0.0.0");
    makePCTrie(16, 32, 4);
    find(ip.getBinary());

    // trie.print();
  }

  public void find(String ip){

    Node level1 = trie.find(ip.substring(0,16));
    FlashNode level2 = tries.get(level1);

  }

  public void makePCTrie(int from, int to, int compSize) {

    ArrayList<Node> levetTries = trie.findAll(from, null);
    for (Node rootNode : levetTries) {
      FlashNode rootFlash = null;

      for (int c = 0; c < (int) (Math.log(compSize) / Math.log(2) + 1e-10); c++) {
        ArrayList<Node> nodes = trie.findAll(c + rootNode.getLevel() + 1, rootNode);
        for (Node node : nodes) {
          node.complete();
        }
      }

      ArrayList<FlashNode> flashNodes = new ArrayList<FlashNode>();
      FlashNode newFlashNode;
      ArrayList<FlashNode> newFlashNodes = new ArrayList<FlashNode>();
      for (int c = (int) (Math.log(compSize) / Math.log(2) + 1e-10); c < to; c++) {
        ArrayList<Node> nodes = trie.findAll(c+ rootNode.getLevel() + 1, rootNode);

        for (int compIndex = 0; compIndex < nodes.size(); compIndex += compSize) {
          newFlashNode = new FlashNode(c);
          for (int prefixIndex = 0; prefixIndex < compSize; prefixIndex++)
            newFlashNode.addPrefix(nodes.get(compIndex + prefixIndex).getPrefix());
          newFlashNodes.add(newFlashNode);

          if (rootFlash == null)
            rootFlash = newFlashNode;

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

      tries.put(rootNode, rootFlash);

    }
    print();

  }


  public void print() {
    tries.entrySet().stream().forEach((entry) -> {
      FlashNode currentValue = entry.getValue();
      ArrayList<FlashNode> temp = new ArrayList<FlashNode>();
      System.out.println("");
      temp.add(currentValue);
      while (temp.size() != 0) {
        ArrayList<FlashNode> temp2 = new ArrayList<FlashNode>();
        for (FlashNode node : temp) {
          if(node == null)
            break;
          System.out.print("level " + node.getLevel() + " prefix:" + node.getPrefix() + "valid: " + node.valid + " --- ");
          if (node.leftNode != null)
            temp2.add(node.leftNode);
          if (node.rightNode != null)
            temp2.add(node.rightNode);
        }
        temp = temp2;
        System.out.print("\n");
      }
    });
  }
}