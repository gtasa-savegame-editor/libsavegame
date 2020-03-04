package nl.paulinternet.libsavegame.variables;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class VariableTests {

    @Test
    public void testBooleanVariable() {
        Variable<Boolean> boolVar = new Variable<>(true);
        Assert.assertTrue(boolVar.getValue());
        Assert.assertEquals(String.valueOf(true), boolVar.getText());

        boolVar.setText(String.valueOf(false));
        Assert.assertFalse(boolVar.getValue());
        Assert.assertEquals(String.valueOf(false), boolVar.getText());
    }

    @Test
    public void testIntVariable() {
        Integer updatedValue = 128;
        Integer initialValue = 0;

        Variable<Integer> intVar = new Variable<>(initialValue);
        Assert.assertEquals(initialValue, intVar.getValue());
        Assert.assertEquals(String.valueOf(initialValue), intVar.getText());

        AtomicBoolean updateCalled = new AtomicBoolean(false);

        intVar.addOnChangeListener(i -> {
            Assert.assertEquals(updatedValue, i);
            Assert.assertEquals(updatedValue, intVar.getValue());
            Assert.assertEquals(String.valueOf(updatedValue), intVar.getText());
            updateCalled.set(true);
        });

        intVar.setValue(updatedValue);
        Assert.assertEquals(updatedValue, intVar.getValue());
        Assert.assertEquals(String.valueOf(updatedValue), intVar.getText());
        Assert.assertTrue(updateCalled.get());
    }

    @Test
    public void testFloatVariable() {
        Float updatedValue = Double.valueOf(Math.PI).floatValue();
        Float updatedValue2 = 2f;
        Float initialValue = 0.5f;

        Variable<Float> floatVar = new Variable<>(initialValue);
        Assert.assertEquals(initialValue, floatVar.getValue());
        Assert.assertEquals(String.valueOf(initialValue), floatVar.getText());

        AtomicBoolean updateCalled = new AtomicBoolean(false);

        floatVar.addOnChangeListener(f -> {
            updateCalled.set(true);
        });

        floatVar.setValue(updatedValue);
        Assert.assertEquals(updatedValue, floatVar.getValue());
        Assert.assertEquals(String.valueOf(updatedValue), floatVar.getText());
        Assert.assertTrue(updateCalled.get());

        updateCalled.set(false);
        Assert.assertFalse(updateCalled.get());

        floatVar.setText(String.valueOf(updatedValue2));
        Assert.assertEquals(updatedValue2, floatVar.getValue());
        Assert.assertEquals(String.valueOf(updatedValue2), floatVar.getText());
        Assert.assertTrue(updateCalled.get());
    }

    @Test
    public void testStringVariable() {
        final String initialValue = "foo";
        final String updatedValue = "bar";
        final String updatedValue2 = "baz";

        Variable<String> strVar = new Variable<>(initialValue);
        Assert.assertEquals(initialValue, strVar.getValue());
        Assert.assertEquals(initialValue, strVar.getText());

        AtomicBoolean updateCalled = new AtomicBoolean(false);

        strVar.addOnChangeListener(i -> {
            switch (i) {
                case updatedValue:
                    Assert.assertEquals(updatedValue, i);
                    Assert.assertEquals(updatedValue, strVar.getValue());
                    Assert.assertEquals(updatedValue, strVar.getText());
                    break;
                case updatedValue2:
                    Assert.assertEquals(updatedValue2, i);
                    Assert.assertEquals(updatedValue2, strVar.getValue());
                    Assert.assertEquals(updatedValue2, strVar.getText());
                    break;
                default:
                    break;
            }
            updateCalled.set(true);
        });

        strVar.setValue(updatedValue);
        Assert.assertEquals(updatedValue, strVar.getValue());
        Assert.assertEquals(updatedValue, strVar.getText());
        Assert.assertTrue(updateCalled.get());

        updateCalled.set(false);
        Assert.assertFalse(updateCalled.get());

        strVar.setText(updatedValue2);
        Assert.assertEquals(updatedValue2, strVar.getValue());
        Assert.assertEquals(updatedValue2, strVar.getText());
        Assert.assertTrue(updateCalled.get());
    }
}