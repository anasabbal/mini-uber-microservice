package com.nas.gateway.swagger;

import com.nas.core.constants.ResourcePath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;




@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";

    private final RouteDefinitionLocator routeLocator;

    @Override
    public List<SwaggerResource> get() {
        String gatewayPredicate = "/" + ResourcePath.API_GATEWAY_PREDICATE;
        List<SwaggerResource> resources = new ArrayList<>();
        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            log.info("Discovered route definition: {}", routeDefinition.getId());
            String resourceName = routeDefinition.getId();
            String location = routeDefinition.getPredicates().get(0).getArgs().get("_genkey_0").replace("/**", API_URI);

            if (location.contains(gatewayPredicate)) {
                location = location.replace(gatewayPredicate, "");
            }

            log.info("Adding swagger resource: {} with location {}", resourceName, location);
            resources.add(swaggerResource(resourceName, location));
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
