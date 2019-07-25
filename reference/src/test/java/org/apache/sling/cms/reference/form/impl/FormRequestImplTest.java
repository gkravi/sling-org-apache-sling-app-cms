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
package org.apache.sling.cms.reference.form.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.cms.reference.forms.FormException;
import org.apache.sling.cms.reference.forms.impl.FormRequestImpl;
import org.apache.sling.cms.reference.forms.impl.fields.SelectionHandler;
import org.apache.sling.cms.reference.forms.impl.fields.TextareaHandler;
import org.apache.sling.cms.reference.forms.impl.fields.TextfieldHandler;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.internal.util.reflection.FieldSetter;

public class FormRequestImplTest {
    @Rule
    public final SlingContext context = new SlingContext();
    private FormRequestImpl formRequest;

    @Before
    public void init() throws NoSuchFieldException, SecurityException, FormException {
        SlingContextHelper.initContext(context);
        context.request().setResource(context.resourceResolver().getResource("/form/jcr:content/container/form"));

        context.request().getParameterMap().put("requiredtextarea", new String[] { "Hello World!" });
        context.request().getParameterMap().put("singleselect", new String[] { "Hello World!" });
        context.request().getParameterMap().put("anotherkey", new String[] { "Hello World!" });
        context.request().getParameterMap().put("money", new String[] { "123" });
        context.request().getParameterMap().put("patternfield", new String[] { "123" });
        context.request().getParameterMap().put("double", new String[] { "2.7" });
        context.request().getParameterMap().put("integer", new String[] { "2" });
        context.request().getParameterMap().put("datefield", new String[] { "2019-02-02" });

        formRequest = new FormRequestImpl(context.request());

        FieldSetter.setField(formRequest, formRequest.getClass().getDeclaredField("fieldHandlers"),
                Arrays.asList(new SelectionHandler(), new TextareaHandler(), new TextfieldHandler()));

        formRequest.init();
    }

    @Test
    public void testValueMap() throws FormException {
        ValueMap formData = formRequest.getFormData();
        assertNotNull(formData);
        assertTrue(formData.containsKey("requiredtextarea"));
        assertFalse(formData.containsKey("textarea"));
        assertFalse(formData.containsKey("anotherkey"));
    }

    @Test
    public void testRequest() throws FormException {
        assertNotNull(formRequest.getOriginalRequest());
        assertEquals(context.request(), formRequest.getOriginalRequest());
    }
}
