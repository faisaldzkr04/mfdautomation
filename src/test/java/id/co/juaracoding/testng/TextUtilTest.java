package id.co.juaracoding.testng;

import id.co.juaracoding.util.TextUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test untuk TextUtil.
 */
public class TextUtilTest {

    private TextUtil textUtil;

    /**
     * Membuat object TextUtil sebelum setiap test dijalankan.
     */
    @BeforeMethod
    public void setUp() {
        textUtil = new TextUtil();
    }

    @Test
    public void is_palindrome_returns_true() {
        boolean result = textUtil.isPalindrome("Kasur Rusak");

        Assert.assertTrue(
                result,
                "Text 'Kasur Rusak' seharusnya terdeteksi sebagai palindrome"
        );
    }

    @Test
    public void is_palindrome_returns_false() {
        boolean result = textUtil.isPalindrome("Automation");

        Assert.assertTrue(
                !result,
                "Text 'Automation' seharusnya bukan palindrome"
        );
    }

    @Test
    public void count_vowels_returns_correct_number() {
        int result = textUtil.countVowels("Automation Testing");

        Assert.assertEquals(
                result,
                7,
                "Jumlah huruf vokal pada 'Automation Testing' seharusnya 7"
        );
    }
}