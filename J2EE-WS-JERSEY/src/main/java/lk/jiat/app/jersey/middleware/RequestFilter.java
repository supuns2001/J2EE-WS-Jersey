package lk.jiat.app.jersey.middleware;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import java.io.IOException;

public class RequestFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("Request Filter call");
    }
}
