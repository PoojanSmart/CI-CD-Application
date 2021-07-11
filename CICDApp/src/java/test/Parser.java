/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author smart
 */
@Stateless
@LocalBean
public class Parser {

    public static TreeMap<String,ArrayList<String>> commandParser(String commands)
    {
        TreeMap<String,ArrayList<String>> stage_commands = new TreeMap<String,ArrayList<String>>() {};
                
        int from=0;
        int to=0;

                while((from = commands.indexOf("(", to)) !=-1 )
                {
                    ArrayList<String> arr = new ArrayList<String>();
                    to =  commands.indexOf("}", to)+1;
                    String c = (String) commands.subSequence(from, to);
                    String stageName = c.split("\'")[1];
                    String command = c.split("\\{")[1].split("\\}")[0];
                    
                    arr.add(command);
                    stage_commands.put(stageName,arr);
                }
        return stage_commands;
    }

}
