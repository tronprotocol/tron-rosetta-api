package org.tron.common;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.tron.model.AccountBalanceResponse;

public class ApiUtil {
    public static ResponseEntity<AccountBalanceResponse> sendError(NativeWebRequest req, String message) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", "application/json");
            res.getWriter().print(message);
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
