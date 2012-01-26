/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.user.openid;

import com.google.inject.BindingAnnotation;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
public @interface AdminEmail {}