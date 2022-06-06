package com;

import journals.BusinessJournal;
import journals.GardenJournal;

import java.util.concurrent.*;

public class Main {
    static int id = 1;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        BusinessJournal businessJournal = new BusinessJournal();
        Thread gardenJournal = new Thread(new GardenJournal());
        System.out.println("Accounting sale journals across in a hour: ");
        businessJournal.start();
        gardenJournal.run();

        System.out.println("\nIncome increasing in a month: ");
        //execution callable interface
        ExecutorService service = Executors.newSingleThreadExecutor();
        NumberDepot myCallable = new NumberDepot(5);
        Future<String> future = service.submit(myCallable);
        String result = future.get();
        System.out.println("\ncurrent income: ");
        System.out.println(result);

        // synchronize thread
        PrintingMachine print = new PrintingMachine();
        Thread businessArticle = new Thread(new Handler(print));
        Thread gardenArticle = new Thread(new Handler(print));
        businessArticle.start();
        gardenArticle.start();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    public static class Handler implements Runnable {
        static String printingArticle = "Beautiful flowers for garden";
        private PrintingMachine action;

        public Handler(PrintingMachine action) {
            this.action = action;
        }

        public void run() {
            System.out.println("sending " + id + " request = " + printingArticle);
            id++;
            System.out.println("Work result: " + action.equals(action, printingArticle));
        }
    }

    public static class PrintingMachine {
        private String value = "Elon Musk drop bitcoin", response = "";

        public synchronized String equals(Object o, String value) {
            if (this.value == "Wait for article") {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
                this.value = value;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
            response = this.value;
            this.value ="Wait for article";
            return this.response;
            //return "";
        }
    }


    static class NumberDepot implements Callable<String> {
        private int num = 0;
        String result="";
        public NumberDepot(int num) {
            this.num = num;
        }

        @Override
        public String call() throws Exception {
            for (int i = 1; i < num; i++) {
                Thread.sleep(1000);
                result = i + "0%";
                System.out.println(i + " week=" + result);
            }
            return result;
        }
    }
}
