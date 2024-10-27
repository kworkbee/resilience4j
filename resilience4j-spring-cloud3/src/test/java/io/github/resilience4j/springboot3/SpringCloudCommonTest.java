/*
 *
 * Copyright 2024: Jiwon Jeon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.resilience4j.springboot3;

import io.github.resilience4j.springboot3.bulkhead.autoconfigure.RefreshScopedBulkheadAutoConfiguration;
import io.github.resilience4j.springboot3.circuitbreaker.autoconfigure.RefreshScopedCircuitBreakerAutoConfiguration;
import io.github.resilience4j.springboot3.ratelimiter.autoconfigure.RefreshScopedRateLimiterAutoConfiguration;
import io.github.resilience4j.springboot3.retry.autoconfigure.RefreshScopedRetryAutoConfiguration;
import io.github.resilience4j.springboot3.timelimiter.autoconfigure.RefreshScopedTimeLimiterAutoConfiguration;
import org.junit.Test;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringCloudCommonTest {

    @Test
    public void testRefreshScopedBulkheadConfig() {
        Stream.of(RefreshScopedBulkheadAutoConfiguration.class.getMethods())
            .filter(method -> method.isAnnotationPresent(Bean.class))
            .forEach(method -> assertThat(method.isAnnotationPresent(RefreshScope.class)).isTrue());
    }

    @Test
    public void testRefreshScopedCircuitBreakerConfig() {
        Stream.of(RefreshScopedCircuitBreakerAutoConfiguration.class.getMethods())
            .filter(method -> method.isAnnotationPresent(Bean.class))
            .forEach(method -> assertThat(method.isAnnotationPresent(RefreshScope.class)).isTrue());
    }

    @Test
    public void testRefreshScopedRetryConfig() {
        Stream.of(RefreshScopedRetryAutoConfiguration.class.getMethods())
            .filter(method -> method.isAnnotationPresent(Bean.class))
            .forEach(method -> assertThat(method.isAnnotationPresent(RefreshScope.class)).isTrue());
    }

    @Test
    public void testRefreshScopedRateLimiterConfig() {
        Stream.of(RefreshScopedRateLimiterAutoConfiguration.class.getMethods())
            .filter(method -> method.isAnnotationPresent(Bean.class))
            .forEach(method -> assertThat(method.isAnnotationPresent(RefreshScope.class)).isTrue());
    }

    @Test
    public void testRefreshScopedTimeLimiterConfig() {
        Stream.of(RefreshScopedTimeLimiterAutoConfiguration.class.getMethods())
            .filter(method -> method.isAnnotationPresent(Bean.class))
            .forEach(method -> assertThat(method.isAnnotationPresent(RefreshScope.class)).isTrue());
    }
}
