package com.github.monitorgroup.spring.boot;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enable orgalon for spring boot application
 *
 * @author xionghui
 * @author niujunlong
 * @version 1.0.0
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableOrgalonConfiguration {

}
