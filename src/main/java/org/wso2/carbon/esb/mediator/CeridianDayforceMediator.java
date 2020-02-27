/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
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
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;

/**
 * Sample method implementation.
 */
public class CeridianDayforceMediator extends AbstractMediator {
    private String uriPath;
    private String clientNameSpaceKey;
    private String serviceUri;

    @Override
    public boolean mediate(MessageContext synCtx) {
        try {
            uriPath = synCtx.getProperty("uri.var.uriPath").toString();
            clientNameSpaceKey = synCtx.getProperty("uri.var.clientNamespace").toString();
            uriPath = uriPath + clientNameSpaceKey;
            serviceUri = synCtx.getProperty("SERVICE_URI").toString() + clientNameSpaceKey.substring(clientNameSpaceKey.lastIndexOf("/"));
            if (serviceUri.startsWith("http://")) {
                serviceUri = serviceUri.substring(7);
            } else if (serviceUri.startsWith("https://")) {
                serviceUri = serviceUri.substring(8);
            }
            synCtx.getConfiguration().getRegistry().newResource(uriPath, false);
            synCtx.getConfiguration().getRegistry().updateResource(uriPath, serviceUri);
            synCtx.setProperty("uri.var.clientNamespace", serviceUri);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return true;
    }
}