package uttugseuja.lucklotteryserver.global.error.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import uttugseuja.lucklotteryserver.global.error.ErrorResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (LuckLotteryException e) {
            writeErrorResponse(response, e.getErrorCode(), request.getRequestURL().toString());
        } catch (Exception e) {
            if (e.getCause() instanceof LuckLotteryException) {
                writeErrorResponse(
                        response,
                        ((LuckLotteryException) e.getCause()).getErrorCode(),
                        request.getRequestURL().toString());
            } else {
                e.printStackTrace();
                writeErrorResponse(
                        response,
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        request.getRequestURL().toString());
            }
        }
    }


    private void writeErrorResponse(HttpServletResponse response, ErrorCode errorCode, String path)
            throws IOException {
        ErrorResponse errorResponse =
                new ErrorResponse(
                        errorCode.getStatus(), errorCode.getReason(), path);

        response.setStatus(errorCode.getStatus());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }


}