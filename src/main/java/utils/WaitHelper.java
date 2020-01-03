package utils;

import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class WaitHelper {

    public static void pollingWait(int timeoutInMilliseconds, int sleepInMilliseconds, Supplier<Boolean> condition) throws TimeoutException {

        long startTime = System.currentTimeMillis();

        while (true) {

            if (condition.get()) {
                return;
            }

            try {
                Thread.sleep(sleepInMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() > startTime + timeoutInMilliseconds) {
                throw new TimeoutException(
                        String.format("Waited for %s milliseconds without success", timeoutInMilliseconds));
            }
        }
    }
}
