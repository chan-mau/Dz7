package org.example;

class TrafficLight implements Runnable {
    private String color;
    private int duration;

    public TrafficLight(String color, int duration) {
        this.color = color;
        this.duration = duration;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(color + " світлофор");
            try {
                Thread.sleep(duration * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class TrafficLightSimulator {
    public static void main(String[] args) {
        Thread redThread = new Thread(new TrafficLight("Зелений", 2));
        Thread yellowThread = new Thread(new TrafficLight("Жовтий", 1));
        Thread greenThread = new Thread(new TrafficLight("Червоний", 2));

        redThread.start();
        yellowThread.start();
        greenThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            redThread.interrupt();
            yellowThread.interrupt();
            greenThread.interrupt();
        }));
    }
}

