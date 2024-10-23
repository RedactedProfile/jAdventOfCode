package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Day4 extends AOCDay {


    MessageDigest digest = MessageDigest.getInstance("MD5");

    public Day4() throws NoSuchAlgorithmException {
    }

    private void findLeadingPattern(String in, String pattern) {
        digest.reset();
        for(;;) {
            var hash = digest.digest(in.getBytes()).toString();

            break;
        }
    }

    @Override
    public void assertTests() {
        var keyAnswer = (HashMap<String, String>) Map.of(
                "abcdef", "609043",
                "pqrstuv", "1048970"
        );

        keyAnswer.forEach((key, value) -> {
            findLeadingPattern(key, value);
        });


    }

}
