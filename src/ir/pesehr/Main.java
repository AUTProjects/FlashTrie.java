package ir.pesehr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
//       IP ip = new IP("3.0.0.0/8");
//      System.out.println(ip.getBinary());
    HashMap<IP, String> ips = Reader.read(100);
    Node root = new Node(0, null, null, null);

    for (Map.Entry<IP, String> entry : ips.entrySet()) {
      IP key = entry.getKey();
      String value = entry.getValue();
      root.find(key.getBinary().substring(0, key.getMask()), true).setPrefix(value);
    }

    printer(root);
  }


  static void printer(Node root) {
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
}
