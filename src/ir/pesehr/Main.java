package ir.pesehr;

import ir.pesehr.Trie.Node;
import ir.pesehr.Trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    HashMap<IP, String> ips = Reader.read(2);
    Trie trie = new Trie(ips);
    trie.print();
    System.out.println(trie.find((new IP("3.0.0.0")).getBinary()).getPrefix());
  }

}
