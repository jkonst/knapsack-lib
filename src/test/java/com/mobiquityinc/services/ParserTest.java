package com.mobiquityinc.services;

import com.mobiquityinc.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


public class ParserTest {

    private static String validFileName;
    private static String invalidFileName;
    private static List<Double> maxWeights;
    private static List<Double[]> weights;
    private static List<Double[]> values;

    @BeforeAll
    public static void beforeAllTests() {
        validFileName = "/knapsack.txt";
        invalidFileName = "/knapsackINV.txt";
        maxWeights = Arrays.asList(new Double(81), new Double(8), new Double(75), new Double(56));
        values = Arrays.asList(new Double[]{new Double(45), new Double(98), new Double(3), new Double(76), new Double(9), new Double(48)},
                new Double[]{new Double(34)},
                new Double[]{new Double(29), new Double(74), new Double(16), new Double(55), new Double(52),
                        new Double(75), new Double(74), new Double(35), new Double(78)},
                new Double[]{new Double(13), new Double(40), new Double(10), new Double(16), new Double(36),
                new Double(79), new Double(45), new Double(79), new Double(64)});
        weights = Arrays.asList(new Double[]{53.38, 88.62, 78.48, 72.3, 30.18, 46.34},
                new Double[]{15.3},
                new Double[]{85.31, 14.55, 3.98, 26.24, 63.69, 76.25, 60.02, 93.18, 89.95},
                new Double[]{90.72, 33.8, 43.15, 37.97, 46.81, 48.77, 81.8, 19.36, 6.76});
    }

    @Test
    public void parseValidFile() throws APIException {
        KnapsackParser parser = mock(KnapsackParser.class);
        String path = KnapsackParser.class.getResource(validFileName).getPath();

        doAnswer((i) -> {
            Assertions.assertEquals(path, i.getArguments()[0]);
            return null;
        }).when(parser).parse(anyString());

        when(parser.getMaxWeights()).thenReturn(maxWeights);
        when(parser.getValues()).thenReturn(values);
        when(parser.getWeights()).thenReturn(weights);

        parser.parse(path);
        Assertions.assertEquals(parser.getMaxWeights().size(), parser.getValues().size());
        Assertions.assertEquals(parser.getMaxWeights().size(), parser.getWeights().size());
    }

    @Test
    public void parseInvalidFile() throws APIException {
        KnapsackParser parser = mock(KnapsackParser.class);
        String path = KnapsackParser.class.getResource(invalidFileName).getPath();
        doThrow(APIException.class).when(parser).parse(path);

        Assertions.assertThrows(APIException.class, () -> parser.parse(path));

    }

    @Test
    public void parseValidFileAfterMock() throws APIException {
        KnapsackParser parser = new KnapsackParser();
        File testFile = new File("src/test/resources" + validFileName);
        parser.parse(testFile.getAbsolutePath());
        Assertions.assertEquals(parser.getMaxWeights().size(), parser.getValues().size());
        Assertions.assertEquals(parser.getMaxWeights().size(), parser.getWeights().size());
    }


    @Test
    public void parseInvalidFileAfterMock() throws APIException {
        KnapsackParser parser = new KnapsackParser();
        File testFile = new File("src/test/resources" + invalidFileName);

        Assertions.assertThrows(APIException.class, () -> parser.parse(testFile.getAbsolutePath()));
        Assertions.assertEquals(parser.getMaxWeights().size(), 0);
        Assertions.assertEquals(parser.getValues().size(), 0);
        Assertions.assertEquals(parser.getWeights().size(), 0);
    }

}
