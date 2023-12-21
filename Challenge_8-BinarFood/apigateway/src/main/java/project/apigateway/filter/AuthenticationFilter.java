package project.apigateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import project.apigateway.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = null;
            if (routeValidator.isSecure.test(exchange.getRequest())){
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization header");
                }
                String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeaders != null && authHeaders.startsWith("Bearer ")){
                    authHeaders=authHeaders.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeaders);
                    request = exchange.getRequest()
                            .mutate()
                            .header("logUser", jwtUtil.extractUsername(authHeaders))
                            .build();

                }catch (Exception e){
                    System.out.println("invalid access !!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            //nothing
            return chain.filter(exchange.mutate().request(request).build());
        }));
    }

    public static class Config{

    }
}
