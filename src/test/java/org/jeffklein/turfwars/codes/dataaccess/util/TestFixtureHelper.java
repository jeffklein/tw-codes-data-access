package org.jeffklein.turfwars.codes.dataaccess.util;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Helper methods to make test fixtures.
 */
public class TestFixtureHelper {
    public static final DateTime NOW = DateTimeUtil.nowUtc();

    public static final DateTime PAST = DateTimeUtil.nowUtc().withYear(1997);

    public static final DateTime FUTURE = DateTimeUtil.nowUtc().withYear(2112);

    public static Set<TempCode> makeRandomTempCodeBatch(int batchId, boolean futureExpDate) {
        Set<TempCode> codes = new HashSet<TempCode>();
        for (int i = 0; i < 5; i++) {
            TempCode code = makeRandomTempCode(batchId, futureExpDate);
            codes.add(code);
        }
        return codes;
    }

    public static TempCode makeRandomTempCode(int batchId, boolean futureExpDate) {
        TempCode code = new TempCode();
        code.setCode(randomTempCode());
        if (futureExpDate) {
            code.setExpirationDate(FUTURE);
        }
        else {
            code.setExpirationDate(PAST);
        }
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
