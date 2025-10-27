package dev.luis.goes.spring.actuator.studies.monitoring.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.Include;
import org.springframework.boot.actuate.web.exchanges.servlet.HttpExchangesFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class HttpTraceRequestFilter extends HttpExchangesFilter {

    public HttpTraceRequestFilter(HttpExchangeRepository repository, Set<Include> includes) {
        super(repository, includes);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
    }


    //  Método para remover os filtros do trace. Um bom exemplo é o swagger
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("swagger") || request.getServletPath().contains("api-docs") || request.getServletPath().contains("/actuator");
    }

}