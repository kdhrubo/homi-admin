package com.tryhomi.admin.core.multitenancy;


import com.tryhomi.admin.core.domain.SimpleUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Slf4j
@Component
public class TenantRequestInterceptor implements AsyncHandlerInterceptor {
        
      
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

           Optional<SimpleUser> u = SecurityUtil.getUser();

           if(u.isPresent()) {
               this.setTenantContext(u.get().getUser().getTenant().getTenantId());
           }

           return true;
       }
 
       @Override
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
           TenantContext.clear();
       }
         
       private boolean setTenantContext(String tenant) {
         TenantContext.setCurrentTenant(tenant);
         return true;
       }
}