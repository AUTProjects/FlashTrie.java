package ir.pesehr.Trie;

import ir.pesehr.IP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: pesehr.
 */
public class Trie {

  Node root ;

  public Trie() {
    this.root = new Node(0,null);
  }


  public Trie(HashMap<IP, String> ips) {
    this.root = new Node(0,"255.255.255.255");
    makeTria(ips);
  }

  public void makeTria(HashMap<IP, String> ips){
    for (Map.Entry<IP, String> entry : ips.entrySet()) {
      IP key = entry.getKey();
      String value = entry.getValue();
      root.find(key.getBinary().substring(0, key.getMask()), true).setPrefix(value);
    }
  }

  public void print() {
    ArrayList<Node> temp = new ArrayList<Node>();
    temp.add(root);
    while (temp.size() != 0) {
      ArrayList<Node> temp2 = new ArrayList<Node>();
      for (Node node : temp) {
        System.out.print("level " + node.getLevel() + " prefix:" + node.getPrefix() + " --- ");
        if (node.getLeftNode() != null)
          temp2.add(node.getLeftNode());
        if (node.getRightNode() != null)
          temp2.add(node.getRightNode());
      }
      temp = temp2;
      System.out.print("\n");
    }
  }

  public Node find(String binary) {
    return root.find(binary,false);
  }


  public Node add(String binary) {
    return root.find(binary,true);
  }


  public ArrayList<Node> findAll(int start) {
    ArrayList<Node> nodes = new ArrayList<Node>();
     nodes.addAll(root.findAll(start));
     return  nodes;
  }
}
