package com.epam.izh.rd.online.service;

import java.util.regex.Pattern;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        return Pattern.compile(remove).matcher(base).replaceAll("");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        StringBuilder con = new StringBuilder();
        for (String s: elements) {
            con.append(s);
        }
        return con.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {
        if (text.isEmpty()) {
            return "";
        }

        char[] chars = text.toCharArray();
        if (isUpperCase(chars[0])) {
            chars[0] = (char) (chars[0] + 32);
        }

        for (int i = 1; i < chars.length; i++) {
            if (i % 2 != 0) {
                if (!isUpperCase(chars[i]) && !isSpace(chars[i])) {
                    chars[i] = (char) (chars[i] - 32);
                }
            } else {
                if (isUpperCase(chars[i]) && !isSpace(chars[i])) {
                    chars[i] = (char) (chars[i] + 32);
                }
            }
        }
        return new String(chars); //TODO
    }

    private boolean isUpperCase(char c) {
        return c >= 65 && c <= 90;
    }

    private boolean isSpace(char c) {
        return c == 32;
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        if (string.isEmpty()) {
            return false;
        }
        return new StringBuilder(string).reverse().toString().replaceAll("\\s", "")
               .equalsIgnoreCase(string.replaceAll("\\s", ""));
    }

    public static void main(String[] args) {
        String str = "Hello, hello, hello, how low!";
        String remove = ", he";
        String pali = "а роза упала на лапу Азора";
        String jump = "Load Up On Guns And Bring Your Friends";
        SimpleTextService text = new SimpleTextService();

        System.out.println(text.toJumpCase(jump));
    }
}
