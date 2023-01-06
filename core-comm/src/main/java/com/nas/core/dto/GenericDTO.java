package com.nas.core.dto;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class GenericDTO<E, D> {
    private final E entity;
    private final D dto;

    public GenericDTO(E entity, D dto){
        this.entity = entity;
        this.dto = dto;
    }
    public <E> Class<E> getEntityInstance() throws ClassNotFoundException, SecurityException, IllegalArgumentException {
        return (Class<E>) Class.forName(this.entity.getClass().getName());
    }
    public <E> Constructor<E>[] getAllConstructorFromGenericClass(Class<E> eClass) throws NoSuchMethodException {
        return new Constructor[]{eClass.getDeclaredConstructor()};
    }
    public <E> List<Class<E>[]> getTypeConstructors(Constructor<E>[] constructors){
        final List<Class<E>[]> objs = new ArrayList<>();

        for(Constructor<E> ctr: constructors){
            Class<E>[] type = (Class<E>[]) ctr.getParameterTypes();
            objs.add(type);
            System.out.println(type.toString());
        }
        return objs;
    }
    public static List<String> merge(List<String> list){
        final List<String> newList = new ArrayList<>();

        for(String str: list){
            if(str.charAt(0) == 's')
                newList.add(str.replaceAll("set", "").toLowerCase());
            else
                newList.add(str.replaceAll("get", "").toLowerCase());
        }
        return newList;
    }
    @SneakyThrows
    public List<String> getParametersName(Method[] method){
        final List<String> list = new ArrayList<>();

        for(Method method1: method){
            Parameter[] parameters = method1.getParameters();

            for (Parameter parameter : parameters) {
                final String executable = parameter.getDeclaringExecutable().getName();
                list.add(executable);
            }
        }
        return merge(list);
    }
    public static <E, D> D toDto(E entity, D dto) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return null;
    }
    public D getDto() {
        return dto;
    }
}
