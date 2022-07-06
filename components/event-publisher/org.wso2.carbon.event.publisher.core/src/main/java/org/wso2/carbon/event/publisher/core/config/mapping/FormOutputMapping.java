/*
 * Copyright (c) 2022, WSO2 Inc. (http://www.wso2.com).
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
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.event.publisher.core.config.mapping;

import org.wso2.carbon.event.publisher.core.config.EventPublisherConstants;
import org.wso2.carbon.event.publisher.core.config.OutputMapping;

public class FormOutputMapping extends OutputMapping {

    private String mappingText;
    private boolean registryResource;
    private long cacheTimeoutDuration;

    public String getMappingText() {

        return mappingText;
    }

    public void setMappingText(String mappingText) {

        this.mappingText = mappingText;
    }

    @Override
    public String getMappingType() {

        return EventPublisherConstants.EF_FORM_MAPPING_TYPE;
    }

    public boolean isRegistryResource() {

        return registryResource;
    }

    public void setRegistryResource(boolean registryResource) {

        this.registryResource = registryResource;
    }

    public long getCacheTimeoutDuration() {

        return cacheTimeoutDuration;
    }

    public void setCacheTimeoutDuration(long cacheTimeoutDuration) {

        this.cacheTimeoutDuration = cacheTimeoutDuration;
    }
}
