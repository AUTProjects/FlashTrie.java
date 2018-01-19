package ir.pesehr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Reader {

  static HashMap<IP, String> read(){
    return read(100);
  }

  static HashMap<IP,String> read(int n){
    HashMap<IP, String> data = new HashMap<IP, String>();
    try {
      File file = new File("Data.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;

      while ((line = bufferedReader.readLine()) != null && n >= 0) {
        String[] lineSplit = line.split(" ");
        data.put(new IP(lineSplit[2]),lineSplit[lineSplit.length-1]);
        n--;
      }
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return data;
  }
}