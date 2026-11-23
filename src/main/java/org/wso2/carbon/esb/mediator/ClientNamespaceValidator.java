/*
 * Copyright (c) 2026, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.esb.mediator;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.regex.Pattern;

/** Faults the flow when clientNamespace could move the registry path out of its namespace. */
public class ClientNamespaceValidator extends AbstractMediator {

    // Letters, digits, '.', '_', '-' or ':' only, and never a "." or ".." segment.
    private static final Pattern SEGMENT = Pattern.compile("(?!\\.{1,2}$)[A-Za-z0-9._:-]+");

    @Override
    public boolean mediate(MessageContext synCtx) {
        Object clientNamespace = synCtx.getProperty("uri.var.clientNamespace");
        if (clientNamespace == null || !isValid(clientNamespace.toString())) {
            handleException("Invalid clientNamespace: only '/'-separated segments of letters, "
                    + "digits, '.', '_', '-' and ':' are allowed", synCtx);
        }
        return true;
    }

    private static boolean isValid(String clientNamespace) {
        for (String segment : clientNamespace.split("/", -1)) {
            if (!SEGMENT.matcher(segment).matches()) {
                return false;
            }
        }
        return true;
    }
}
