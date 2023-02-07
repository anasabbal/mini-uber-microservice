package com.nas.core.util;

import java.util.UUID;

public class GenratorId extends DS{
    public String createSalt() {

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < 3; i++){
            String ts = String.valueOf(System.currentTimeMillis());
            String rand = UUID.randomUUID().toString();
            //stringBuilder.append(DigestUtils.sha1Hex(ts + rand));
        }

        return String.valueOf(stringBuilder);
    }
}
