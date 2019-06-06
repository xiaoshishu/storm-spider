package com.example.annotation.annotation2;

import java.lang.reflect.Field;

public class TestAnnotationInt {
    @AnnotationInt(2)
    private static int value;

    private int a = 1;

    public static void main(String[] args) throws Exception{
        // 获取到所有得变量
        Field[] fields = TestAnnotationInt.class.getDeclaredFields();
        for (Field field : fields) {
            // 允许访问私有变量
            field.setAccessible(true);
            // 判断此变量是否使用了注解
            boolean isUsedAnnotation = field.isAnnotationPresent(AnnotationInt.class);
            if (isUsedAnnotation) {
                AnnotationInt annotationInt = field.getAnnotation(AnnotationInt.class);
                field.set(annotationInt, annotationInt.value());
            }
        }
        System.out.println(value);

    }

}
