package com.sundayteam.backend.interceptor;

import com.sundayteam.backend.annotation.Auth;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    // preHandle sẽ được gọi khi có request (config request url ở dưới)
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        // trích xuất method tương ứng với request mapping trong controller
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // tìm trong method có khai báo anotation Auth?
        Auth roleAnnotation = AnnotationUtils
                .findAnnotation(method, Auth.class);
        // nếu có lấy ra thuộc tính role, không trả về null
        Auth.Role role = roleAnnotation != null ? roleAnnotation.role() : null;

        // lấy các thông tin đăng nhập từ session
        HttpSession session = request.getSession();
        // boolean isLogined = session.getAttribute("isLogin") != null ? (Boolean) session
        //         .getAttribute("isLogin") : false;
        // Auth.Role loginRole = session.getAttribute("role") != null ? (Auth.Role) session
        //         .getAttribute("role") : null;

        String token = request.getHeader("Bearer");
        System.out.println("request.getHeader() = " + request.getHeader("Authentication"));
        System.out.println("token = " + token);

        boolean isLogined = true;
        Auth.Role loginRole = Auth.Role.ADMIN ;
        // - trường hợp role yêu cầu của method = null bỏ qua interceptor này và
        // chạy bình thường
        // - khác null tức request này chỉ được thực hiên khi đã đăng nhập
        if (role != null) {
            // chưa đăng nhập chuyển hướng sang trang login để đăng nhập
            if (!isLogined) {
                // response.sendRedirect("login");
                return true;
            } else {
                // - trường hợp đã login tiến hành kiểm tra role
                // - những trường hợp chỉ yêu cầu login mà không yêu cầu cụ thể
                // role nào thì tất cả các role đều có quyền truy cập
                // - trường hợp yêu cầu cụ thể loại role sau khi đăng nhập thì
                // phải kiểm tra
                // - không thoả mãn điều kiện dưới chuyển hướng sang trang
                // denied
                if (role != Auth.Role.LOGIN && role != loginRole) {
                    // response.sendRedirect("deny?url=\""
                    //         + request.getRequestURL().toString() + "?"
                    //         + request.getQueryString() + "\"&role=" + role);
                    return true;
                }
            }
        }

        return true;
    }
}
