package by.calculator.postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

@Component
public class SqlBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {
            SqlDataField sqlAnnotation = field.getAnnotation(SqlDataField.class);
            if (sqlAnnotation == null) continue;

            try {
                handleField(bean, field);
            } catch (IOException e) {
                throw new BeanCreationException("Error created bean", e);
            }
        }
        return bean;
    }

    private void handleField(Object bean, Field field) throws IOException {
        String sqlFileName = field.getAnnotation(SqlDataField.class).value();
        String sqlData = readSqlFromFile(sqlFileName);

        boolean isPrivate = !field.canAccess(bean);

        field.setAccessible(true);
        ReflectionUtils.setField(field, bean, sqlData);
        if (isPrivate) {
            field.setAccessible(false);
        }
    }

    public String readSqlFromFile(String fileName) throws IOException {
        InputStream resource = new ClassPathResource(
                fileName).getInputStream();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource))) {
            String sql = reader.lines()
                    .collect(Collectors.joining("\n"));
            return sql;
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
