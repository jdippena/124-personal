package edu.macalester.comp124.palindrome;

import org.junit.Test;
import static org.junit.Assert.*;

public class PalindromeTest {

    @Test
    public void testPalindrome1() {
        // After you finish, you should be able to uncomment these lines and pass the test.
        assertTrue(Palindrome.isPalindrome("racecar"));
        assertFalse(Palindrome.isPalindrome("racecarz"));
    }

	@Test
    public void testPalindrome2() {
        assertTrue(Palindrome.isPalindrome("A man. A plan. A canal. Panama!"));
        assertTrue(Palindrome.isPalindrome("1234567890987654321"));
        assertTrue(Palindrome.isPalindrome("TaTtArRaTtAt"));
        assertTrue(Palindrome.isPalindrome("1234@#567(@#8909*&876&543!2#1"));
        assertFalse(Palindrome.isPalindrome("This is definitely not a palindrome."));
    }

}