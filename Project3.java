import java.io.*;
import java.util.*;

public class Project3
{
  public static void main(String[] args) throws IOException
  {
    int count = 0;
    if (args.length < 1)
    {
      System.out.println("Error: Directory name is missing");
      System.out.println("Usage: java scoreProcess directory_name");
      return;
    }
    File directory = new File(args[0]);
    File[] files = directory.listFiles();

    File file;
    Scanner input;

    HashTableImp<String, Double> hashTable = new HashTableImp<String, Double>();
    HashTableImp<String, Integer> hashCount = new HashTableImp<String, Integer>();
    for (int i = 0; i < files.length; i++)
    {
      input = new Scanner(files[i]);

      String name;
      Double score;
      while (input.hasNext())
      {
      	name = "";
      	while (!input.hasNextDouble())
        {
      	  name += input.next() + " ";
      	}
        score = new Double(input.next());
        if(Double.isNaN(score) == false)
        {
          count = 0;
          if(hashTable.get(name) == null)
          {
            hashTable.put(name, score);
            count++;
            hashCount.put(name, count);
          }
          else
          {
            score  += hashTable.get(name);
            hashTable.put(name, score);
            count++;
            count += hashCount.get(name);
            hashCount.put(name, count);
          }
        }
        else
        {
          throw new RuntimeException("Not a number.");
        }
      }
    }

    Scanner keyName = new Scanner(System.in);
    System.out.println("Type Q to Quit.");
    System.out.println("Enter name: ");
    String key = keyName.nextLine() + " ";
    while(key.equals("Q ") == false)
    {
      if(hashTable.get(key) != null)
      {
        System.out.println(key + ": Avg: " + (hashTable.get(key)/hashCount.get(key)) + " #Scores: " + hashCount.get(key));
      }
      else
      {
        System.out.println(key + " not found.");
      }
      System.out.println("Enter name: ");
      key = keyName.nextLine() + " ";
    }
  }
}
