package br.gov.sp.fatec.projetoia.utils;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class StringGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$*";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

    private static final SecureRandom random = new SecureRandom();

    public String generateRandomPassword(int length) {        
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALL_CHARACTERS.length());
            password.append(ALL_CHARACTERS.charAt(index));
        }

        return new String(password);
    }

    public String generateRandomRecoverCode(){
        StringBuilder code = new StringBuilder(6);
        for(int i = 0; i < 6; i++){
            int index = random.nextInt(DIGITS.length());
            code.append(DIGITS.charAt(index));
        }

        return code.toString();
    }
}
