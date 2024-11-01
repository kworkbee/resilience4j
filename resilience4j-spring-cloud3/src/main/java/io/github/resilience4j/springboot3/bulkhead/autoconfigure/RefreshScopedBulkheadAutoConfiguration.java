package io.github.resilience4j.springboot3.bulkhead.autoconfigure;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import io.github.resilience4j.bulkhead.event.BulkheadEvent;
import io.github.resilience4j.common.CompositeCustomizer;
import io.github.resilience4j.common.bulkhead.configuration.BulkheadConfigCustomizer;
import io.github.resilience4j.common.bulkhead.configuration.CommonThreadPoolBulkheadConfigurationProperties;
import io.github.resilience4j.common.bulkhead.configuration.ThreadPoolBulkheadConfigCustomizer;
import io.github.resilience4j.consumer.EventConsumerRegistry;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.spring6.bulkhead.configure.BulkheadConfiguration;
import io.github.resilience4j.spring6.bulkhead.configure.BulkheadConfigurationProperties;
import io.github.resilience4j.spring6.bulkhead.configure.threadpool.ThreadPoolBulkheadConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Bulkhead.class, RefreshScope.class})
@AutoConfigureAfter(RefreshAutoConfiguration.class)
@AutoConfigureBefore(BulkheadAutoConfiguration.class)
public class RefreshScopedBulkheadAutoConfiguration {

    protected final BulkheadConfiguration bulkheadConfiguration;
    protected final ThreadPoolBulkheadConfiguration threadPoolBulkheadConfiguration;

    protected RefreshScopedBulkheadAutoConfiguration() {
        this.threadPoolBulkheadConfiguration = new ThreadPoolBulkheadConfiguration();
        this.bulkheadConfiguration = new BulkheadConfiguration();
    }

    /**
     * @param bulkheadConfigurationProperties ThreadPool Bulkhead Spring Configuration Properties
     * @param bulkheadEventConsumerRegistry   The Bulkhead Event Consumer Registry
     * @return the RefreshScoped BulkheadRegistry
     */
    @Bean
    @org.springframework.cloud.context.config.annotation.RefreshScope
    @ConditionalOnMissingBean
    public BulkheadRegistry bulkheadRegistry(
            BulkheadConfigurationProperties bulkheadConfigurationProperties,
            EventConsumerRegistry<BulkheadEvent> bulkheadEventConsumerRegistry,
            RegistryEventConsumer<Bulkhead> bulkheadRegistryEventConsumer,
            @Qualifier("compositeBulkheadCustomizer") CompositeCustomizer<BulkheadConfigCustomizer> compositeBulkheadCustomizer) {
        return bulkheadConfiguration
                .bulkheadRegistry(bulkheadConfigurationProperties, bulkheadEventConsumerRegistry,
                        bulkheadRegistryEventConsumer, compositeBulkheadCustomizer);
    }

    /**
     * @param threadPoolBulkheadConfigurationProperties ThreadPool Bulkhead Spring Configuration Properties
     * @param bulkheadEventConsumerRegistry             The Bulkhead Event Consumer Registry
     * @return the RefreshScoped ThreadPoolBulkheadRegistry
     */
    @Bean
    @org.springframework.cloud.context.config.annotation.RefreshScope
    @ConditionalOnMissingBean
    public ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry(
            CommonThreadPoolBulkheadConfigurationProperties threadPoolBulkheadConfigurationProperties,
            EventConsumerRegistry<BulkheadEvent> bulkheadEventConsumerRegistry,
            RegistryEventConsumer<ThreadPoolBulkhead> threadPoolBulkheadRegistryEventConsumer,
            @Qualifier("compositeThreadPoolBulkheadCustomizer") CompositeCustomizer<ThreadPoolBulkheadConfigCustomizer> compositeThreadPoolBulkheadCustomizer) {

        return threadPoolBulkheadConfiguration.threadPoolBulkheadRegistry(
                threadPoolBulkheadConfigurationProperties, bulkheadEventConsumerRegistry,
                threadPoolBulkheadRegistryEventConsumer, compositeThreadPoolBulkheadCustomizer);
    }
}
