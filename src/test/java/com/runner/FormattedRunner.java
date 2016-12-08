package com.runner;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * Created by Main Server on 05.12.2016.
 */
public class FormattedRunner extends BlockJUnit4ClassRunner {

    public FormattedRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            notifier.addListener(new CustomListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.run(notifier);
    }
}
