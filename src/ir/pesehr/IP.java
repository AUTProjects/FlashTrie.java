package ir.pesehr;

/**
 * @author: pesehr.
 */
public class IP {

  private String fullIp;
  private String binary = "";
  private String mask;
  private String ip;

  public IP(String ip) {
    this.fullIp = ip;
  }

  public int getMask() {
    return Integer.parseInt(mask);
  }

  public String getBinary() {
    if ("".equals(binary)) {
      ip = fullIp.split("/")[0];
      if (fullIp.split("/").length > 1)
        mask = fullIp.split("/")[1];
      else
        mask = "32";
      String[] splitedIp = ip.split("\\.");
      for (String string : splitedIp) {
        int octet = Integer.parseInt(string);
        String binaryOctet = Integer.toBinaryString(octet);
        while (binaryOctet.length() < 8) {
          binaryOctet = '0' + binaryOctet;
        }
        binary += binaryOctet;
      }
    }
    return binary;
  }

  @Override
  public String toString() {
    return fullIp;
  }
}
