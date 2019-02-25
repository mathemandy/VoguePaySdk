package android.voguepay.ng.voguepaysdk;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by numb3rs on 2/20/18.
 */


@Singleton
public class AppExecutors {

    private Executor mainThreadExecutor, backgroundThreadExecutor;

    public Executor getMainThreadExecutor(){
        return mainThreadExecutor;
    }

    public Executor getDiskExecutor() {
        return  backgroundThreadExecutor;
    }

    @Inject
    public AppExecutors() {
        mainThreadExecutor = new MainThreadExecutor();
        backgroundThreadExecutor = Executors.newSingleThreadExecutor();

    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());


        @Override
        public void execute(@NonNull Runnable command) {

            mainThreadHandler.post(command);
        }

    }
}
