package com.sundayteam.backend.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import com.sundayteam.backend.model.DataResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ResponseInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Xử lý trước khi controller xử lý request
        return true;
    }

    // @Override
    // public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    //                        ModelAndView modelAndView) throws Exception {
    //     // Xử lý sau khi controller xử lý request
    // }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  Object handler, ModelAndView modelAndView) throws Exception {
        // HandlerInterceptor.super.postHandle(request, response, new DataResponse(handler), modelAndView);
        if (response.getStatus() == HttpStatus.OK.value()) {
            // Nếu response có mã 200 thì thêm trường dữ liệu vào response
            // DataResponse responseData = new DataResponse();
            // responseData.setStatusCode(HttpStatus.OK.value());
            // responseData.setMessage("Success");

            // String data = objectMapper.writeValueAsString(modelAndView.getModel());
            // responseData.setData(data);

            // Chuyển đổi response thành đối tượng JSON
            // String responseBody = objectMapper.writeValueAsString(responseData);
            // response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            // response.getWriter().write(responseBody);
        }
    }


    // @Override
    // public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
    //                             Exception ex) throws Exception {
    //     // Xử lý sau khi response được trả về cho client
    //     // Tạo một đối tượng Java
    //     ResponseData responseData = new ResponseData();
    //     responseData.setStatusCode(200);
    //     responseData.setMessage("Success");
    //     // responseData.setData(handler);
    //
    //     // Sử dụng ObjectMapper để chuyển đối tượng Java thành chuỗi JSON
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     String jsonString = objectMapper.writeValueAsString(responseData);
    //
    //     // Ghi chuỗi JSON vào response
    //     // response.setStatus(HttpServletResponse.SC_OK);
    //     // response.setCharacterEncoding("UTF-8");
    //     // response.setContentType("application/json");
    //     // PrintWriter out = response.getWriter();
    //     // out.print(jsonString);
    //     // out.flush();
    //     System.out.println("jsonString = " + jsonString);
    //     System.out.println("handler = " + handler);
    // }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // if(ex != null) {
        //     System.out.println("response = " + response.getStatus());
        // }
        // HandlerInterceptor.super.afterCompletion(request, response, new DataResponse(handler), ex);

    }
}
