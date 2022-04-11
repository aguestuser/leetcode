package net.riseup.aguestuser;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

class FizzBuzz {
    private static final int NUM_THREADS = 4;

    private final int mTotalNums;
    private final ExecutorService mExecutor = Executors.newFixedThreadPool(NUM_THREADS);
    private final AtomicInteger mCurrentNum = new AtomicInteger(1);
    private final CyclicBarrier mIterationBarrier = new CyclicBarrier(NUM_THREADS, mCurrentNum::incrementAndGet);


    public FizzBuzz(int n) {
        this.mTotalNums = n;
    }

    public CompletableFuture<Void> run(
            List<Runnable> strPrinters,
            IntConsumer intPrinter
    ){
        return CompletableFuture.allOf(
                CompletableFuture.runAsync(restoreInterrupt(() -> fizz(strPrinters.get(0))), mExecutor),
                CompletableFuture.runAsync(restoreInterrupt(() -> buzz(strPrinters.get(1))), mExecutor),
                CompletableFuture.runAsync(restoreInterrupt(() -> fizzbuzz(strPrinters.get(2))), mExecutor),
                CompletableFuture.runAsync(restoreInterrupt(() -> number(intPrinter)), mExecutor)
        );
    }


    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException, BrokenBarrierException {
        printIf(printFizz, x -> x % 3 == 0 && x % 5 != 0);
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException, BrokenBarrierException {
        printIf(printBuzz, x -> x % 3 != 0 && x % 5 == 0);
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException, BrokenBarrierException {
        printIf(printFizzBuzz, x -> x % 3 == 0 && x % 5 == 0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException, BrokenBarrierException {
        Runnable printX = () -> printNumber.accept(mCurrentNum.get());
        printIf(printX, x -> x % 3 != 0 && x % 5 != 0);
    }

    private void printIf(Runnable printer, Predicate<Integer> predicate) throws InterruptedException, BrokenBarrierException {
        int x;
        while (true) {
            x = mCurrentNum.get();
            if(x > mTotalNums) return;

            if (predicate.test(x)) try {
                printer.run();
            } catch (Throwable throwable){
                throwable.printStackTrace();
                Thread.currentThread().interrupt();
            }
            mIterationBarrier.await();
        }
    }

    private interface InterruptibleRunnable {
        void run() throws InterruptedException, BrokenBarrierException;
    }

    private Runnable restoreInterrupt(InterruptibleRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        };
    }

}
