package com.muaythaiapp.muaythai.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SenhaUtil {
    private static final int LOG_ROUNDS = 12;

    public static String criptografar(String senha){
        BCrypt.Hasher hasher = BCrypt.withDefaults();
        String hashedSenha = hasher.hashToString(LOG_ROUNDS, senha.toCharArray());
        return hashedSenha;
    }

    public static boolean verificarSenha(String senha , String senhaCriptografada){
        BCrypt.Verifyer verifyer = BCrypt.verifyer();
        BCrypt.Result result = verifyer.verify(senha.toCharArray(), senhaCriptografada);
        return result.verified;
    }
}
