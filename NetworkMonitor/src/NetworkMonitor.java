import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
//purpose of the code:
/*
 * The code defines a class called NetworkMonitor that can be used to monitor network activity and block access to unauthorized websites or files. 
 * The NetworkMonitor class contains a set of authorized sites and files, which the user can add to. 
 * When the NetworkMonitor is run, it prompts the user to enter a website or file to access.
 * If the entered site or file is not in the set of authorized sites, the NetworkMonitor blocks access to the site by adding an entry to the /etc/hosts file that maps the site to an invalid IP address.
 * The NetworkMonitor class could be used to monitor network activity on a computer or network and prevent access to malicious or unauthorized websites or files.
 * This could help to protect against security threats such as malware or phishing attacks.
 */


public class NetworkMonitor {
  // Set of authorized websites and files
  private static final Set<String> AUTHORIZED_SITES = new HashSet<>();
  static {
    AUTHORIZED_SITES.add("www.google.com");
    AUTHORIZED_SITES.add("www.gmail.com");
    AUTHORIZED_SITES.add("www.youtube.com");
    // Add more authorized sites here
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a website or file to access: ");
    String input = scanner.nextLine();

    // Check if the input is an authorized site or file
    if (AUTHORIZED_SITES.contains(input)) {
      System.out.println("Access granted to authorized site: " + input);
    } else {
      System.out.println("Access denied to unauthorized site: " + input);

      // Optionally, take protective measures such as alerting the user or blocking access to the site
      alertUser();
      blockAccess(input);
    }
  }

  private static void alertUser() {
    System.out.println("ALERT: Suspicious network activity detected. Please check your security settings.");
  }

  private static void blockAccess(String site) {
	  try {
	    // Check if the user has permission to write to the hosts file
	    Path hostsPath = Paths.get("/etc/hosts");
	    if (java.nio.file.Files.isWritable(hostsPath)) {
	      // Read the contents of the hosts file
	      List<String> hostsLines = java.nio.file.Files.readAllLines(hostsPath);

	      // Add a new line that maps the site to the invalid IP address
	      hostsLines.add("0.0.0.0 " + site);

	      // Write the updated contents back to the hosts file
	      java.nio.file.Files.write(hostsPath, hostsLines);
	    } else {
	      // Handle the case where the user does not have permission to write to the hosts file
	      System.out.println("ERROR: Permission denied to write to /etc/hosts");
	    }
	  } catch (IOException e) {
	    e.printStackTrace();
	  }
	}

}

