package org.jeffklein.turfwars.codes.dataaccess.util;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Helper methods to make test fixtures.
 */
public class TestFixtureHelper {
    public static final DateTime NOW = new DateTime(DateTimeZone.forID("UTC"));

    public static Set<TempCode> makeRandomTempCodeBatch(int batchId) {
        Set<TempCode> codes = new HashSet<TempCode>();
        for (int i = 0; i < 5; i++) {
            TempCode code = makeRandomTempCode(batchId);
            codes.add(code);
        }
        return codes;
    }

    public static TempCode makeRandomTempCode() {
        return makeRandomTempCode(1);
    }

    public static TempCode makeRandomTempCode(int batchId) {
        TempCode code = new TempCode();
        code.setCode(randomTempCode());
        code.setExpirationDate(NOW);
        code.setNextUpdateTimestamp(NOW);
        code.setServerTimestamp(NOW);
        code.setBatchId(batchId);
        return code;
    }

    private static int randomInt() {
        Random random = new Random();
        return random.nextInt(10);
    }

    private static String randomTempCode() {
        StringBuilder sb = new StringBuilder(8);
        sb.append('-');
        sb.append(randomInt());
        sb.append(randomInt());
        sb.append(randomInt());
        sb.append('-');
        sb.append(randomInt());
        sb.append(randomInt());
        sb.append(randomInt());
        return sb.toString();
    }
}
