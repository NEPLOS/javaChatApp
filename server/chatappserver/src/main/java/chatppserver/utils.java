package chatppserver;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class utils  
{
    public static String getSaltString() { // stolen func from stackoverflow :D
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static String insertBackSlash(String originalString, int index) 
    {  
        StringBuffer newString = new StringBuffer(originalString); 
  
        newString.insert(index, '\\'); 

        return newString.toString(); 
    } 


    public static int amountOFcharInReverseInASequent(String originalString , char character , int starterIndex)
    {
        int result = 0;

        for (int i = starterIndex; i >= 0 ; i--) 
        {
            if(originalString.charAt(i) == character)
                result++;
            else
                break;
        }

        return result;
    }

    public static String checkForInjection(String input)
    {
        System.out.println("input : " + input);
        if(input == null)
            return input;
        for (int i = input.length() - 1; i >= 0; i--) 
        {
            if(input.charAt(i) == '\'' || input.charAt(i) == '\\')
            {
                input = insertBackSlash(input, i);
                //i--;
            }    
        }
        
        System.out.println("final string : " + input);
        return input;
    }

    public static String generateCookie() { // another stolen func from stackoverflow :D
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static int countChar(String text , char c)
    {
        int result = 0;
        for(int i =0; i < text.length(); i++)
        {
            if(text.charAt(i) == c)
                result++;
        }
        return result;
    }

    public static boolean emailFilter(String email)
    {

        if(!(email.endsWith("@gmail.com") && countChar(email,'@') == 1))
        {
            return false;
        }

        return true;
    }
}
