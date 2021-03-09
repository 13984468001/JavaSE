package com.example.simple;

import java.lang.annotation.*;

/**
 * 定义自己的一个注解
 * <p>
 * 一个类的某些字段上被注解标识， 在读取该属性时， 将注解中的默认值赋给这些属性，
 * 没有标记的属性不赋值
 *
 * @author PL
 */

/*
@Retention 用于指定被修饰的注解被保留多长时间， 分别 SOURCE（ 注解仅存在于源码中， 在
class 字节码文件中不包含） ,CLASS（ 默认的保留策略， 注解会在 class 字节码文件中存
在， 但运行时无法获取） ,RUNTIME（ 注解会在 class 字节码文件中存在， 在运行时可以
通过反射获取到） 三种类型， 如果想要在程序运行过程中通过反射来获取注解的信息需要将
Retention 设置为 RUNTIME。
*/
@Retention(RetentionPolicy.RUNTIME)
//@Target 用于指定被修饰的注解修饰哪些程序单元， 也就是上面说的类， 方法， 字段。
@Target(ElementType.FIELD)
//@Documented 用于指定被修饰的注解类将被 javadoc 工具提取成文档。
@Documented
//@Inherited 用于指定被修饰的注解类将被 javadoc 工具提取成文档。
@Inherited
public @interface MyAnnotation {
    String value() default "注解的默认值";
}
