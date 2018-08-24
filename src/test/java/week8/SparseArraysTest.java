package week8;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SparseArraysTest {

    @Test
    public void matchingStrings_ReturnsCorrectResult_IfInputIsCorrect() {
        // given
        String[] strings = new String[]{"ab", "abc", "abc"};
        String[] queries = new String[]{"a", "ab", "abc"};

        // when
        int[] array = SparseArrays.matchingStrings(strings, queries);

        // then
        assertEquals(Arrays.toString(new int[]{0, 1, 2}), Arrays.toString(array));
    }

    @Test
    public void matchingStrings_ReturnsZero_IfQueriesIsNull() {
        // given
        String[] strings = new String[]{"ab", "abc", "abc"};
        String[] queries = new String[]{null};

        // when
        int[] array = SparseArrays.matchingStrings(strings, queries);

        // then
        assertEquals(Arrays.toString(new int[]{0}), Arrays.toString(array));
    }

    @Test
    public void matchingStrings_ReturnsArrayOfZeros_IfStringsIsNull() {
        // given
        String[] strings = new String[]{null};
        String[] queries = new String[]{"a", "ab", "abc"};

        // when
        int[] array = SparseArrays.matchingStrings(strings, queries);

        // then
        assertEquals(Arrays.toString(new int[]{0, 0, 0}), Arrays.toString(array));
    }

}