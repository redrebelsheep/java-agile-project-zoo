package de.volkswagen.f73.model;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class "DoubleTextField"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public class DoubleTextField extends TextField {

    // Attributes

    /**
     * Replace text.
     *
     * @param start the start
     * @param end   the end
     * @param text  the text
     */
    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            if (validate(this.getText())) {
                if (text.equals(".") && this.getText().contains(".")) {
                    super.replaceText(start, end, "");
                } else {
                    super.replaceText(start, end, text);
                }
            } else {
                super.replaceText(start, end, "");
            }
        }
    }

    /**
     * Replace selection.
     *
     * @param text the text
     */
    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    /**
     * Validate.
     *
     * @param text the text
     *
     * @return true, if successful
     */
    private boolean validate(String text) {
        Pattern decimalPattern = Pattern.compile("^([0-9]*)((\\.){1}([0-9]{0,1}))?");
        Matcher matcher = decimalPattern.matcher(text);
        return matcher.matches();
    }
}
