package ir.pesehr;

import ir.pesehr.FlashTrie.FLashTrie;

import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    HashMap<IP, String> ips = Reader.read(10000);
    FLashTrie trie = new FLashTrie(ips);


  }

}
