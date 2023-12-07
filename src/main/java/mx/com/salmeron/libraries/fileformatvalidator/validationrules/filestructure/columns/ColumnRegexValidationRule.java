package mx.com.salmeron.libraries.fileformatvalidator.validationrules.filestructure.columns;

import lombok.AllArgsConstructor;
import lombok.ToString;
import mx.com.salmeron.libraries.fileformatvalidator.validationrules.errors.TextDoesNotMatchRegexException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
@AllArgsConstructor
public class ColumnRegexValidationRule implements ColumnValidationRule {

    private static final Logger logger = LogManager.getLogger(ColumnRegexValidationRule.class);

    private Pattern pattern;

    @Override
    public boolean validate(String text) throws Exception {

        Matcher matcher = pattern.matcher(text);
        boolean matchFound = matcher.find();
        if (!matchFound) {
            logger.atError().log("Regex validation failed: " + text);
            throw new TextDoesNotMatchRegexException(text,pattern);

        }
        return true;
    }


}
