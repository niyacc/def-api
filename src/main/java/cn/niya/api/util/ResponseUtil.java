package cn.niya.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Date 2022.05.30
 * response call back
 */
@Slf4j
public class ResponseUtil {

    public static void responseOut(HttpServletResponse response, JsonResult result) {
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = null;
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            writer = response.getWriter();
            objectMapper.writeValue(writer, result);
            writer.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
