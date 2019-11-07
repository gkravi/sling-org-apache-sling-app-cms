/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sling.cms.transformer.internal.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.cms.transformer.Transformation;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, adapters = Transformation.class)
public class TransformationImpl implements Transformation {

    @ChildResource
    private List<Resource> handlers;

    @ValueMapValue
    private String name;

    @Override
    public List<Resource> getHandlers() {
        return handlers;
    }

    @Override
    public String getName() {
        return name;
    }

    public void sethandlers(List<Resource> handlers) {
        this.handlers = handlers;
    }

    public void setName(String name) {
        this.name = name;
    }

}
