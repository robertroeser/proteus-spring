/**
 * Copyright 2018 Netifi Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netifi.proteus.springboot.config;

import io.netifi.proteus.annotations.ProteusClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class ProteusClientFieldCallback implements ReflectionUtils.FieldCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProteusClientFieldCallback.class);

    private ConfigurableListableBeanFactory beanFactory;
    private Object bean;

    public ProteusClientFieldCallback(final ConfigurableListableBeanFactory beanFactory, final Object bean) {
        this.beanFactory = beanFactory;
        this.bean = bean;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        if (!field.isAnnotationPresent(ProteusClient.class)) {
            return;
        }

        ReflectionUtils.makeAccessible(field);


        boolean test = true;
    }
}
