package org.empty.neuralnetwork.matrix;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatrixTest {

    @Test
    public void testEquals() {
        Matrix m1 = new Matrix(3, 4, i -> 0.5 * (i - 6));
        Matrix m2 = new Matrix(3, 4, i -> 0.5 * (i - 6));
        Matrix m3 = new Matrix(3, 4, i -> 0.5 * (i - 6.2));

        assertTrue(m1.equals(m2));
        assertFalse(m1.equals(m3));
    }

    @Test
    public void testMultiplyDouble() {
        Matrix m = new Matrix(3, 4, i -> 0.5 * (i - 6));

        double x = 0.5;
        Matrix expected = new Matrix(3, 4, i ->x *  0.5 * (i - 6));

        Matrix result = m.apply((index, value)->x * value);

        assertTrue(result.equals(expected));
        assertTrue(Math.abs(result.get(1) + 1.25000) < 0.0001);
    }

    @Test
    public void testToString() {
        Matrix m = new Matrix(3, 4, i->i*2);

        String text = m.toString();

        double[] expected = new double[12];

        for (int i = 0; i < expected.length; i++) {
            expected[i] = i * 2;
        }

        var rows= text.split("\n");

        assertTrue(rows.length == 3);

        int index = 0;
        for (var row : rows) {
            var values = row.split("\\s+");

            for (var textValue : values) {
                if (textValue.length() == 0) {
                    continue;
                }
                var doubleValue = Double.valueOf(textValue);

                assertTrue(Math.abs(doubleValue - expected[index]) < 0.0001);

                index++;
            }
        }
    }

}
