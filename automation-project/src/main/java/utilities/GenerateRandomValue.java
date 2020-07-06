package utilities;

import java.util.HashSet;
import java.util.Set;

public class GenerateRandomValue {
    final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    final static java.util.Random rand = new java.util.Random();
    final static Set<String> identifiers = new HashSet<String>();
        
   public String randomIdentifiers() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        System.out.println(builder.toString());
       return builder.toString();
    }
    
    
}