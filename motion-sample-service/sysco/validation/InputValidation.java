package com.sysco.sampleService.Stas.validation;

import com.sysco.sampleService.Kirill.validation.InputValidator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Max;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {InputValidation.HomeValidation.class})
public @interface InputValidation {

    String message() default "Calories and quantity must consists of numbers";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

   class HomeValidation implements ConstraintValidator <InputValidation, String>{

       private static final Pattern VALID_PATTERN = Pattern.compile("^[0-9]+$");

       @Override
       public boolean isValid(String value, ConstraintValidatorContext context) {
           if (value == null){
               return false;
           };
           return VALID_PATTERN.matcher(value).matches();
       }
   }

}
