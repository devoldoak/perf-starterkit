package org.perfstarterkit.config;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.actuate.metrics.statsd.StatsdMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rpigeyre on 16/03/2017.
 */
@Configuration
@EnableMetrics
public class MetricsConfiguration {

    @Value("${spring.application.name:application}.${random.value:0000}")
    private String prefix = "metrics";

    @Bean
    public MetricsEndpointMetricReader metricsEndpointMetricReader(final MetricsEndpoint metricsEndpoint) {
        return new MetricsEndpointMetricReader(metricsEndpoint);
    }

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter(MetricExportProperties export) {
        return new StatsdMetricWriter(prefix, export.getStatsd().getHost(), export.getStatsd().getPort());
    }
}
