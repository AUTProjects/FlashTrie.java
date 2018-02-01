package ir.pesehr;

import ir.pesehr.FlashTrie.FLashTrie;
import ir.pesehr.Trie.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Reader {

  static HashMap<IP, String> read() {
    return read(100);
  }

  static HashMap<IP, String> read(int n) {
    HashMap<IP, String> data = new HashMap<IP, String>();
    try {
      File file = new File("Data.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      int c = 0;
      while ((line = bufferedReader.readLine()) != null && c < n) {
//        if (c % 100 == 36) {
          String[] lineSplit = line.split(" ");
          data.put(new IP(lineSplit[2]), lineSplit[lineSplit.length - 1]);
          c++;
//        }
      }
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return data;
  }

  static HashMap<IP, String> test(int n, FLashTrie trie) {
    HashMap<IP, String> data = new HashMap<IP, String>();
    try {
      File file = new File("Data.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      int c = 0;
      while ((line = bufferedReader.readLine()) != null && c < n) {
//        if (c % 100 == 36) {
          String[] lineSplit = line.split(" ");
          IP ip = new IP(lineSplit[2]);
          System.out.println("for ip" + lineSplit[2].split("/")[0] + " expected: " + lineSplit[lineSplit.length - 1]
                  + " found " + trie.find(ip.getBinary()));

//        }
        c++;
      }
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return data;
  }
}