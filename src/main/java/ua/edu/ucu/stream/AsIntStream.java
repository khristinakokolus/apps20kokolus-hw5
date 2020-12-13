package ua.edu.ucu.stream;


import lombok.Getter;
import ua.edu.ucu.function.*;

import java.util.ArrayList;

@Getter
public class AsIntStream implements IntStream {
    private ArrayList<Integer> valuesContainer;

    private AsIntStream(int... values) {
        this.valuesContainer = new ArrayList<>();
        for (int value : values) {
            valuesContainer.add(value);
        }
    }

    private AsIntStream(ArrayList<Integer> newValues) {
        this.valuesContainer = new ArrayList<>();
        valuesContainer.addAll(newValues);
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        isEmpty();
        double average;
        int counter = 0;
        int sum = 0;
        for (int value : valuesContainer) {
            sum+= value;
            counter++;
        }
        average = (double) sum / counter;
        return average;
    }

    public int helpfulMinMax(String minMax) {
        int minOrMax = valuesContainer.get(0);
        for (int value : valuesContainer) {
            if (value < minOrMax && minMax.equals("min")) {
                minOrMax = value;
            }
            else if (value > minOrMax && minMax.equals("max")) {
                minOrMax = value;
            }
        }
        return minOrMax;
    }

    @Override
    public Integer max() {
        isEmpty();
        int maxValue;
        maxValue = helpfulMinMax("max");
        return maxValue;
    }

    @Override
    public Integer min() {
        isEmpty();
        int minValue;
        minValue = helpfulMinMax("min");
        return minValue;
    }


    @Override
    public long count() {
        return valuesContainer.size();
    }


    @Override
    public Integer sum() {
        isEmpty();
        int sum = 0;
        for (int value : valuesContainer) {
            sum += value;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int value : valuesContainer) {
            if (predicate.test(value)) {
                newArray.add(value);
            }
        }
        return new AsIntStream(newArray);
    }


    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int value : valuesContainer) {
            newArray.add(mapper.apply(value));
        }
        return new AsIntStream(newArray);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int value : valuesContainer) {
            newArray.addAll(((AsIntStream) func.applyAsIntStream(value)).
                    getValuesContainer());
        }
        return new AsIntStream(newArray);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int value : valuesContainer) {
            action.accept(value);
        }
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int convolution = identity;
        for (int value : valuesContainer) {
            convolution = op.apply(convolution, value);
        }
        return convolution;
    }

    @Override
    public int[] toArray() {
        int arraySize = (int) count();
        int[] resultArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            resultArray[i] = valuesContainer.get(i);
        }
        return resultArray;
    }

    public void isEmpty() {
        if (count() == 0) {
            throw new IllegalArgumentException("Stream is empty!");
        }
    }

}
