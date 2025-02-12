package yeo.nuel.lix.aspect;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import yeo.nuel.lix.annotation.PasswordEncryption;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordEncryptionAspect {

    private final PasswordEncoder passwordEncoder;

    @Around("execution(* yeo.nuel.lix.controller..*.*(..))")
    public Object passwordEncryptionAspect(ProceedingJoinPoint pjp) throws Throwable {
        Arrays.stream(pjp.getArgs()).forEach(this::fieldEncryption);
        return pjp.proceed();
    }

    public void fieldEncryption(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return;
        }

        FieldUtils.getAllFieldsList(object.getClass())
                .stream()
                .filter(filter -> !(Modifier.isFinal(filter.getModifiers())) && !(Modifier.isStatic(filter.getModifiers())))
                .forEach(field -> {
                    try {
                        boolean encryptionTarget = field.isAnnotationPresent(PasswordEncryption.class);
                        if (!encryptionTarget) {
                            return;
                        }

                        Object encryptionFiled = FieldUtils.readField(field, object, true);
                        if (!(encryptionFiled instanceof String)) {
                            return;
                        }

                        String encryptedPassword = passwordEncoder.encode((String) encryptionFiled);
                        FieldUtils.writeField(field, object, encryptedPassword, true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
