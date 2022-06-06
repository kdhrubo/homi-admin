package com.tryhomi.admin.core.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ControllerErrorHandlerAdvice {

    private final ErrorConfigProperties errorConfigProperties;


    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception e) throws Exception {
        log.info("errorConfigProperties - {}", errorConfigProperties);
        log.info("Url - {}",  req.getRequestURI());
        log.info("Exception - {}", e.getClass().getSimpleName());

        Optional<ErrorConfigProperties.ErrorConfig> errorConfig =
        errorConfigProperties.getErrorConfigList()
                .stream()
                .filter(ec ->
                    StringUtils.equals(req.getRequestURI(), ec.getUrl())
                            &&
                            StringUtils.equals(e.getClass().getSimpleName(), ec.getException())
                ).findAny();

        ModelAndView mav = new ModelAndView();
        if(errorConfig.isPresent()) {

            mav.addObject("errorKey", errorConfig.get().getMsgKey());

            if(errorConfig.get().getAttributes() != null && !errorConfig.get().getAttributes().isEmpty()) {
                for(ErrorConfigProperties.ErrorConfig.Attribute attribute : errorConfig.get().getAttributes()){
                    mav.addObject(attribute.getKey(), attribute.getValue());
                }
            }

            mav.setViewName(errorConfig.get().getView());
        }
        else{
            mav.addObject("errorCode", "ERR - 001 : User Already exists");
            mav.setViewName("500");
        }

        return mav;
    }




}
