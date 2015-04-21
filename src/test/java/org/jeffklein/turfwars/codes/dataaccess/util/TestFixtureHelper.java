package org.jeffklein.turfwars.codes.dataaccess.util;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Helper methods to make test fixtures.
 */
public class TestFixtureHelper {
    public static final Date NOW = new Date(System.currentTimeMillis());

    public static TempCodeApiResponse createTempCodeApiResponse() {
        TempCodeApiResponse toPersist = new TempCodeApiResponse();
        toPersist.setNextUpdate(NOW);
        toPersist.setTimestamp(NOW);
        toPersist.setTempCodes(makeTestTempCodeData(toPersist));
        return toPersist;
    }

    public  static Set<TempCode> makeTestTempCodeData(TempCodeApiResponse apiResponse) {
        Set<TempCode> codes = new HashSet<TempCode>();
        for (int i = 0 ; i < 5 ; i++) {
            TempCode code = new TempCode();
            code.setCode(randomTempCode());
            code.setExpires(NOW);
            code.setTempCodeApiResponse(apiResponse);
            codes.add(code);
        }
        return codes;
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
