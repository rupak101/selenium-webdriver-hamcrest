package com.api.framework;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.apache.commons.validator.routines.EmailValidator;

public class utils {
    //Method to validate the email address format.
    public static boolean validateEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    //Method to get json schema factory
    public static JsonSchemaFactory getJsonSchemaFactory() {
        return JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
                .freeze();
    }
}
