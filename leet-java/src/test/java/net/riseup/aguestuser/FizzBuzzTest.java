package net.riseup.aguestuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.IntConsumer;


class FizzBuzzTest {


    private final FizzBuzz mFizzBuzz = new FizzBuzz(15);
    private final List<String> mPrintedLines = new ArrayList<>();
    private final List<Runnable> mStrPrinters = List.of(
            () -> {
                System.out.println("fizz");
                mPrintedLines.add("fizz");
            },
            () -> {
                System.out.println("buzz");
                mPrintedLines.add("buzz");
            },
            () -> {
                System.out.println("fizzbuzz");
                mPrintedLines.add("fizzbuzz");
            }
    );
    private final IntConsumer mIntPrinter = (int n) -> {
        System.out.println(n);
        mPrintedLines.add(Integer.toString(n));
    };


    @AfterEach
    void setup(){
        mPrintedLines.clear();
    }

    @Test
    void fizzBuzz_printsCorrectValuesConcurrently() throws InterruptedException, ExecutionException {
        String[] expectedOutput = {
                "1", "2", "fizz", "4", "buzz", "fizz", "7", "8", "fizz", "buzz", "11", "fizz", "13", "14", "fizzbuzz"
        };
        mFizzBuzz.run(mStrPrinters, mIntPrinter).join();
        // TODO: use hamcrest!
        assert(Arrays.equals(expectedOutput, mPrintedLines.toArray()));
    }
}