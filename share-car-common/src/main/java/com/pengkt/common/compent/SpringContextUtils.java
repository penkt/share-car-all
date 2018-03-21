package com.pengkt.common.compent;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jca.context.BootstrapContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtils");
        }
    }

    public static <T> T getBean(String name){
        checkApplicationContext();
        if(StringUtils.isNotBlank(name)){
            Object o=applicationContext.getBean(name);
            return (T)o;
        }
        return null;
    }

    public static <T> T getBean(Class<T> c){
        checkApplicationContext();
        return applicationContext.getBean(c);
    }


}
