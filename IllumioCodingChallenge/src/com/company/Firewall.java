package com.company;
import java.io.*;
import java.util.*;

public class Firewall {

    ArrayList<Rule> rules;

    public Firewall(String path){
        File file = new File(path);
        rules = new ArrayList<>();
        try {
            Scanner read = new Scanner(file);
            String line = "";
            Rule temp;
            while (read.hasNextLine()) {
                line = read.nextLine();
                temp = new Rule(line);
                rules.add(temp);
            }
            read.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
    }

    public boolean accept_packet(String dir, String pro, int port, String ip){
        for(Rule r : rules){
            if(r.checkIfMatch(dir, pro, port, ip)){
                return true;
            }
        }
        return false;
    }



}
