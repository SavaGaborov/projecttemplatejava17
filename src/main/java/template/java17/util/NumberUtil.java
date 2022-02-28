package template.java17.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtil {

    private NumberUtil() {
    }

    public static Double round(Double number) {
        if (number == null || number.isInfinite() || number.isNaN())
            return 0D;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.UP);
        return Double.valueOf(decimalFormat.format(number));
    }
}
