package me.snowlight.gateway;

import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/order-service/**")
                        .filters(u -> u.rewritePath("order-service/(?<segment>.*)", "/$\\{segment}"))
                        .uri("http://localhost:8080"))
                .route(r -> r.path("/gift-service/**")
                        .filters(u -> u.rewritePath("gift-service/(?<segment>.*)", "/$\\{segment}"))
                        .uri("http://localhost:18080"))
                .build();
    }
}
