package com.pengkt.customer.biz.handler;


import com.pengkt.customer.biz.compent.IdGenerator;
import com.pengkt.customer.biz.constant.CommonConstant;
import com.pengkt.customer.biz.exception.GenerBizException;
import com.pengkt.customer.biz.response.BaseResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Aspect
@Component
public class GeneralHandler {
    private static final Logger logger = LoggerFactory.getLogger(GeneralHandler.class);

    @Autowired
    private IdGenerator generalIdWorks;
    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(* com.pengkt.*.controller..*(..))")
    public void controllerPointcut(){

    }

    @Before("controllerPointcut()")
    public void preProcess(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        logger.info("----------------------------------preProcess start--------------------------");
        HttpSession session=request.getSession();
/*        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        request.setAttribute("userId",userDetails.getUsername());*/
        request.setAttribute("requestNo",generalIdWorks.nextId());
        request.setAttribute("startTime",new Date().getTime());
        if(request.getAttribute("channel")==null){
            request.setAttribute("channel","01");
        }
        logger.info("----------------------------------preProcess finished--------------------------");
    }
    @Around("controllerPointcut()")
    public BaseResponse doAround(ProceedingJoinPoint proceedingJoinPoint){
        BaseResponse result;
        logger.info("----------------------------------doProcess start--------------------------");
        try {
            result =(BaseResponse)proceedingJoinPoint.proceed();
            result.setMsg(CommonConstant.OPERATION_SUCCESS);
            result.setCode(CommonConstant.SUCCESS);
        } catch (Throwable throwable) {
            result= (BaseResponse)processException(proceedingJoinPoint, throwable);
        }
        logger.info("----------------------------------doProcess finished--------------------------");
        return  result;
    }

    private BaseResponse processException(ProceedingJoinPoint proceedingJoinPoint,Throwable throwable){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        BaseResponse result=new BaseResponse();
        if(throwable instanceof GenerBizException){
            GenerBizException generBizException=(GenerBizException)throwable;
            result.setCode(generBizException.getCode());
            result.setMsg(generBizException.getMessage());
        }else{
            logger.error("other exception");
            result.setCode(CommonConstant.FAILED);
            result.setMsg(CommonConstant.OPERATION_FAILED);
            throwable.printStackTrace();
        }
        return result;

    }
}
