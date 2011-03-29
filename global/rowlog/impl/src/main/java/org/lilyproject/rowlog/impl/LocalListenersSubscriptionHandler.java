/*
 * Copyright 2010 Outerthought bvba
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
package org.lilyproject.rowlog.impl;

import org.lilyproject.rowlog.api.*;

public class LocalListenersSubscriptionHandler extends AbstractListenersSubscriptionHandler {
    
    public LocalListenersSubscriptionHandler(String subscriptionId, MessagesWorkQueue messagesWorkQueue, RowLog rowLog,
            RowLogConfigurationManager rowLogConfigurationManager) {
        super(subscriptionId, messagesWorkQueue, rowLog, rowLogConfigurationManager);
    }

    @Override
    protected WorkerDelegate createWorkerDelegate(String context) {
        return new LocalWorkerDelegate();
    }

    private class LocalWorkerDelegate implements WorkerDelegate {
        @Override
        public boolean processMessage(RowLogMessage message) throws RowLogException, InterruptedException {
            RowLogMessageListener listener = RowLogMessageListenerMapping.INSTANCE.get(subscriptionId);
            if (listener == null)
                return false;
            return listener.processMessage(message);
        }

        @Override
        public void close() {
        }
    }
}
