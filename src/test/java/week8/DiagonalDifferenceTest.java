package week8;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class DiagonalDifferenceTest {

    private static DiagonalDifference diagDiff;

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Run before the first test method");
        diagDiff = new DiagonalDifference();
    }

    @AfterClass
    public static void tearDownBeforeClass() {
        System.out.println("Run after the last test method");
    }

    @Before
    public void setUp() {
        System.out.println("Run before each test");
    }

    @After
    public void tearDown() {
        System.out.println("Run after each test");
        System.out.println("---------------------------");
    }

    @Test
    public void diagonalDifference_Returns0_IfArrayHasOnly1Element() {
        // given
        int[][] array = new int[][]{{10}};
        // when
        int result = diagDiff.diagonalDifference(array);
        // then
        assertEquals(0, result);
    }

    @Test
    public void diagonalDifference_Returns0_IfInputNotSquareMatrix() {
        // given
        int[][] array = new int[3][5];
        // when
        int result = diagDiff.diagonalDifference(array);
        // then
        assertEquals(0, result);
    }

    @Test
    public void diagonalDifference_ReturnsAbsoluteSum_IfInputIsCorrectSquareIntArray() {
        // given
        int[][] array = new int[][]{
                {11, 2, 4}, {4, 5, 6}, {10, 8, -12}};
        // when
        int result = diagDiff.diagonalDifference(array);
        // then
        assertEquals(15, result);
    }
}