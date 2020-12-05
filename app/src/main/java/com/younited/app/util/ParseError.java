package com.younited.app.util;

import java.util.HashMap;

public class ParseError {

    private static HashMap<Integer, String> errorMap;

    public ParseError(){
        ParseError.errorMap = new HashMap<>();
        ParseError.errorMap.put(201, "Favor informe uma senha");
        ParseError.errorMap.put(202, "Usuário não disponível");
    }

    public static String getError(int errorCode) {
        return ParseError.errorMap.get(errorCode);
    }
}
