package com.team.car.util;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * Created by ysw on 14-12-14.
 */
public class MessageSource extends ResourceBundleMessageSource {
    public MessageSource() {
        setBasenames("export");
    }

    public static MessageSourceAccessor getAccessor() {
        //只读取   中文  语言包
        return new MessageSourceAccessor(new MessageSource(), Locale.CHINA);
    }

    public static String message(String s) {
        return getAccessor().getMessage(s);
    }
}
