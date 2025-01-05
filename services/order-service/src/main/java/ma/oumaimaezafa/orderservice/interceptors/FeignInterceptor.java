package ma.oumaimaezafa.orderservice.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;


@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //recuperer Jwt et envoie comme un header

        // Get the authentication from SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if authentication exists and is a JwtAuthenticationToken
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            // on recupere le token
            String jwtAccessToken = jwtAuthenticationToken.getToken().getTokenValue();

            // Add the token to the request header
            requestTemplate.header("Authorization", "Bearer " + jwtAccessToken);

            System.out.println("Access Token => ");
            System.out.println(jwtAccessToken);
        } else {
            // Log or handle the case when no JWT token is available
            System.out.println("No JWT Authentication found");
        }
    }

}
