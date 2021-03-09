package com.example.simple;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 利用反射解析注解并赋值
 */
public class DemoReflect {
    public static void main(String[] args) {
        //初始化对象赋默认值
        DemoMyAnnotation dma = new DemoMyAnnotation("Object default value 1", "Object default value 2", "Object default value 3");
        //解析注解，将注解的属性值赋给对象使用了注解的属性
        doAnnoDemo(dma);
        System.out.println(dma.toString());
    }

    private static void doAnnoDemo(Object o) {
        Class<?> clz = o.getClass();
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field : declaredFields) {
            //测试过程中竟然出现不进入if。原来发现写错了，如下这行。MyAnnotation这个注解作用范围：ElementType.FIELD，只能作用在字段上
            //if (clz.isAnnotationPresent(MyAnnotation.class)) {
            if (clz.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
                if (null != annotation) {
                    String fieldName = field.getName();
                    try {
                        /*获取到字段的set方法*/
                        Method fieldSetMethod = clz.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
                        /*获取到注解的属性值*/
                        String av = annotation.value();
                        /*将注解的属性值赋给对象的属性*/
                        fieldSetMethod.invoke(o, av);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
