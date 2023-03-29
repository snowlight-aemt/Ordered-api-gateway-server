package me.snowlight.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class LoggingGlobalFilter {
    @Bean
    public org.springframework.cloud.gateway.filter.GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("[Gateway pre filter]");
            log.info(request.getHeaders().toString());

            return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("[Gateway post filter]");
                    log.info(response.getHeaders().toString());
                }));
        };
    }
}
