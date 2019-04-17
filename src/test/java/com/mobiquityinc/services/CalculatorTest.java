package com.mobiquityinc.services;

import com.mobiquityinc.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CalculatorTest {

    private static Double maxWeight;
    private static Double[] values;
    private static Double[] weights;

    @BeforeAll
    public static void beforeAllTests() {
        maxWeight = 75.0;
        values = new Double[]{29.0, 74.0, 16.0, 55.0, 52.0, 75.0, 74.0, 35.0, 78.0};
        weights = new Double[]{85.31, 14.55, 3.98, 26.24, 63.69, 76.25, 60.02, 93.18, 89.95};
//        maxWeight = 81.0;
//        values = new Double[]{45.0, 98.0, 3.0, 76.0, 9.0, 48.0};
//        weights = new Double[]{53.38, 88.62, 78.48, 72.3, 30.18, 46.34};
    }

    @Test
    public void getCorrectlyCalculatedItemsMock() throws APIException {
        KnapsackCalculator calculator = mock(KnapsackCalculator.class);

        calculatorSetup(calculator);

        calculator.setMaxWeight(maxWeight);
        calculator.setValues(values);
        calculator.setWeights(weights);

        when(calculator.getItems()).thenReturn("2,7");

        Assertions.assertEquals("2,7", calculator.getItems());
    }

    @Test
    public void getIncorrectlyCalculatedItemsMock() throws APIException{
        KnapsackCalculator calculator = mock(KnapsackCalculator.class);

        calculatorSetup(calculator);

        calculator.setMaxWeight(maxWeight);
        calculator.setValues(values);
        calculator.setWeights(weights);

        when(calculator.getItems()).thenReturn("1,2");
        Assertions.assertNotEquals("2,7", calculator.getItems());

        doThrow(APIException.class).when(calculator).getItems();
        Assertions.assertThrows(APIException.class, () -> calculator.getItems());
    }

    @Test
    public void getCorrectlyCalculatedItems() throws APIException {
        KnapsackCalculator calculator = new KnapsackCalculator();

        calculator.setMaxWeight(maxWeight);
        calculator.setValues(values);
        calculator.setWeights(weights);

        Assertions.assertEquals("2,7", calculator.getItems());
    }

    private void calculatorSetup(KnapsackCalculator calculator) {
        doAnswer((i) -> {
            Assertions.assertEquals(75.0, i.getArguments()[0]);
            return null;
        }).when(calculator).setMaxWeight(maxWeight);

        doAnswer((i) -> {
            Assertions.assertEquals(values, i.getArguments()[0]);
            return null;
        }).when(calculator).setValues(values);

        doAnswer((i) -> {
            Assertions.assertEquals(weights, i.getArguments()[0]);
            return null;
        }).when(calculator).setWeights(weights);
    }

}
