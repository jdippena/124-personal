package edu.macalester.comp124.palindrome;

import acm.program.ConsoleProgram;

/**
 * Created by Jaco on 5/27/15.
 */

public class Palindrome extends ConsoleProgram {

    @Override
    public void run() {
        String input = readLine("Enter word that might be a palindrome (enter quit to stop): ");
        if (input.equals("quit")) println("Thanks for playing!");
        else {
            if (isPalindrome(input)) println(input + " is a palindrome.");
            else println(input + " is not a palindrome.");
            run();
        }
    }

    public static boolean isPalindrome(String input) {
        String processed = "";
        for (char c : input.toCharArray()) if (Character.isLetterOrDigit(c)) processed += Character.toLowerCase(c);
        String reversed = "";
        for (char c : processed.toCharArray()) reversed = c + reversed;
        return processed.equals(reversed);
    }
}
