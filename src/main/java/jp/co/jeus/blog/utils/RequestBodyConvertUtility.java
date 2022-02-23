package jp.co.jeus.blog.utils;

public class RequestBodyConvertUtility {

    private RequestBodyConvertUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static String convertSpaceNBlank(String content) {
        return content.replaceAll(System.lineSeparator(), "<br>")
                .replaceAll(" ","&nbsp;");
    }
}
