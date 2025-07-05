package umc.study.validation.validator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.study.apipayload.code.status.ErrorStatus;
import umc.study.apipayload.exception.GeneralException;
import umc.study.apipayload.exception.InvalidPageException;
import umc.study.validation.annotation.ValidPage;

public class ValidPageArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class);
    }

    @Override
    public Integer resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String pageStr = webRequest.getParameter("page");
        int page;

        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidPageException("페이지 번호가 유효하지 않습니다.");
        }

        // 0 이하일 때 예외 발생 (b번 조건)
        if (page <= 0) {
            throw new InvalidPageException("페이지 번호는 1 이상이어야 합니다.");
        }

        // 1-based를 0-based로 변환하여 반환 (a번 조건)
        return page - 1;
    }
}
