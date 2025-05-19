/*package com.b2x2.competition.N2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public interface SuperMapper {
    Map<Class<?>, Set<Field>> sourceFeldsByClass = new ConcurrentHashMap<>();
    Map<Class<?>, Map<String, Field>> targetFieldsByClass = new ConcurrentHashMap<>();

    static void main(String[] args) {
        System.out.println();
        Entity entity = Entity.builder().integer(42).build();
        Dog dog = toDto(entity, Dog.class);
    }

    static <T> T toDto(Object entity, Class<T> dtoClass) {
        try {
            var sourceFields = sourceFeldsByClass.computeIfAbsent(entity.getClass(), clazz -> Arrays.stream(clazz.getDeclaredFields()).peek(field -> field.setAccessible(true)).collect(Collectors.toUnmodifiableSet()));
            var targetFields = targetFieldsByClass.computeIfAbsent(dtoClass, clazz -> Arrays.stream(clazz.getDeclaredFields()).peek(field -> field.setAccessible(true)).collect(Collectors.toMap(Field::getName, f -> f)));
            T dto = dtoClass.get

            for (var sourceField : sourceFields) {
                Field targetField = targetFields.get(sourceField.getName());

                if (targetField != null && targetField.getType().equals(sourceField.getType())) {
                    targetField.set(target, sourceField.get(source));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
*/