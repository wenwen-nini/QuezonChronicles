package Main.styles.animationHub;

import Main.styles.clearScreen.ClearScreen;

public class LoadingDots {
    
    public LoadingDots() { }

    public void loadingDotsAnimation(String message) {
        int dotCount = 3;
        int delay = 500;

        long duration = 3000; // animation runs for 3 seconds
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < duration) {
            for (int i = 0; i <= dotCount; i++) {
                System.out.print("\r" + message + ".".repeat(i));
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public void customLoadingDotsAnimation(String message, int dotCount, int delay, long duration) {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < duration) {
            for (int i = 0; i <= dotCount; i++) {
                System.out.print("\r" + message + ".".repeat(i));
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}