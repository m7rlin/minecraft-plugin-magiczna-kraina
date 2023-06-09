package pl.mgtm.magicznakraina.api.economy;

import java.text.DecimalFormat;

public enum DecimalFormats {
    FORMAT_1("#,##0.00"),
    FORMAT_2("#,##0.000"),
    FORMAT_3("#,##0.0000"),
    FORMAT_4("#,###0.00"),
    FORMAT_5("0.00"),
    FORMAT_6("0.000"),
    FORMAT_7("#,###,###.00"),
    FORMAT_8("###0.00"),
    FORMAT_9("0.00%"),
    FORMAT_10("0.##E0"),
    EUROPEAN("#,##0.00");

    private final String pattern;

    DecimalFormats(String pattern) {
        this.pattern = pattern;
    }

    public DecimalFormat getDecimalFormat() {
        return new DecimalFormat(pattern);
    }
}