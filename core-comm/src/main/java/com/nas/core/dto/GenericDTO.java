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
    public <T> E getEntityInstance(T e) throws ClassNotFoundException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException {
        final Class<?> eclass = Class.forName(this.entity.getClass().getName());
        Constructor<?>[] constructors = eclass.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            Class<?>[] params = constructor.getParameterTypes();
            for (Class<?> param : params) {
                Field[] fields = param.getDeclaredFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    System.out.println(fieldName);
                }
            }
        }
        List<Class<?>[]> objects = new ArrayList<>();

        for(Constructor<?> ctor: constructors){
            Class<?>[] type = ctor.getParameterTypes();
            objects.add(type);
        }
        for(Object obj: objects){
            System.out.println(obj.toString());
        }
        return null;//(T) eclass.newInstance();
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
        final GenericDTO<E, D> genericDTO = new GenericDTO<>(entity, dto);
        final E ent = genericDTO.getEntityInstance(entity);
        Method[] methods = ent.getClass().getDeclaredMethods();

        List<String> list = genericDTO.getParametersName(methods);
        return null;
    }
    /*public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        EntityExample entity = new EntityExample("1", "Anas");
        DtoEntity dtoEntity = new DtoEntity();
        GenericDTO.toDto(entity, dtoEntity);

        /*EntityExample entityExample = genericDTO.getEntityInstance();

        System.out.println("---------------");
        System.out.printf(entityExample.toString());
        System.out.println("---------------");
        Method[] declaredMethods = EntityExample.class.getDeclaredMethods();

        List<String> list = genericDTO.getParametersName(declaredMethods);

        for(String str: list)
            System.out.println(str);
    }*/

    public D getDto() {
        return dto;
    }
}
