/*
 * Copyright 2008-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.griffon.runtime.javafx;

import griffon.core.GriffonExceptionHandler;
import javafx.application.Platform;
import org.codehaus.griffon.runtime.core.threading.AbstractUIThreadManager;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.util.Objects.requireNonNull;

/**
 * @author Dean Iverson
 */
public class JavaFXUIThreadManager extends AbstractUIThreadManager {
    private static final Thread.UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER = new GriffonExceptionHandler();

    /**
     * True if the current thread is the UI thread.
     */
    public boolean isUIThread() {
        return Platform.isFxApplicationThread();
    }

    @Override
    public void runInsideUIAsync(@Nonnull Runnable runnable) {
        requireNonNull(runnable, ERROR_RUNNABLE_NULL);
        Platform.runLater(runnable);
    }

    @Override
    public void runInsideUISync(final @Nonnull Runnable runnable) {
        requireNonNull(runnable, ERROR_RUNNABLE_NULL);
        if (isUIThread()) {
            runnable.run();
        } else {
            FutureTask<Void> task = new FutureTask<>(new Runnable() {
                @Override
                public void run() {
                    try {
                        runnable.run();
                    } catch (Throwable throwable) {
                        UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(Thread.currentThread(), throwable);
                    }
                }
            }, null);

            Platform.runLater(task);
            try {
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(Thread.currentThread(), e);
            }
        }
    }
}
