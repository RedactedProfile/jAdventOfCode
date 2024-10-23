package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Day4 extends AOCDay {


    MessageDigest digest = MessageDigest.getInstance("MD5");

    public Day4() throws NoSuchAlgorithmException {
    }

    private String md5(String in) {
        StringBuilder md5Hex =
                new StringBuilder(
                    new BigInteger(1,
                            digest.digest(in.getBytes()))
                                .toString(16));

        // Pad with leading zeros if necessary
        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    /**
     *
     * @param in
     * @param pattern
     * @return number suffix or -1 if not found
     */
    private int findLeadingPattern(String in, String pattern) {
        digest.reset();
        int num = 0;
        for(;;) {
            var value = md5( in + num);
            if (value.startsWith(pattern)) {
                return num;
            }

            if(num >= Integer.MAX_VALUE) {
                return -1;
            }

            num++;
        }
    }

    @Override
    public void assertTests() {
        var keyAnswer = Map.of(
                "abcdef", "609043",
                "pqrstuv", "1048970"
        );

        keyAnswer.forEach((key, value) -> {
            int r = findLeadingPattern(key, "00000");
            if(r == -1) throw new AssertionError("Key " + key + " not found");
            if(r != Integer.parseInt(value)) throw new AssertionError("Key " + key + " not equal to value " + value);
        });
    }

}
