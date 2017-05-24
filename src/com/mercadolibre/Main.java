package com.mercadolibre;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String version = "0.1.1";

    public static void main(String[] args) throws IOException {

        if(args.length == 0) {
            System.out.println("/**** Sell Your Item Mock Generator v" + Main.version + " ****/");
            return;
        }

        ProcessBuilder pbItem = new ProcessBuilder("curl", "https://api.mercadolibre.com/items/" + args[0]);
        Process p = pbItem.start();

        String item_get = curlToString(p);

        ProcessBuilder pbCategory = new ProcessBuilder("curl", "https://api.mercadolibre.com/categories/" + args[1]);
        p = pbCategory.start();

        String category_get = curlToString(p);

        ProcessBuilder pbCategoryAtts = new ProcessBuilder("curl", "https://api.mercadolibre.com/categories/" + args[1] + "/attributes");
        p = pbCategoryAtts.start();

        String category_atts_get = curlToString(p);

        //byte[] encoded = Files.readAllBytes(Paths.get(new Main().getClass().getResource("mock_template").getPath()));
        //String fileContent = new String(encoded, Charset.defaultCharset());

        InputStream mockStream = new Main().getClass().getResourceAsStream("/resources/mock_template");
        String fileContent = inputStreamToString(mockStream);

        fileContent = fileContent.replace("<TOK1>", item_get);
        fileContent = fileContent.replace("<TOK2>", category_get);
        fileContent = fileContent.replace("<TOK3>", category_atts_get);

        File f = new File("mock_" + args[0]);
        f.createNewFile();
        PrintWriter out = new PrintWriter(f);
        out.println(fileContent);
        out.close();

        System.out.println("Mock created at: " + f.getAbsolutePath());

    }

    private static String curlToString(Process p) throws IOException{
        InputStream is = p.getInputStream();

        /*String newLine = System.getProperty("line.separator");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder result = new StringBuilder();
        String line; boolean flag = false;
        while ((line = reader.readLine()) != null) {
            result.append(flag? newLine: "").append(line);
            flag = true;
        }*/

        return inputStreamToString(is).replaceAll("\"", "\\\\\"");
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        String newLine = System.getProperty("line.separator");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder result = new StringBuilder();
        String line; boolean flag = false;
        while ((line = reader.readLine()) != null) {
            result.append(flag? newLine: "").append(line);
            flag = true;
        }

        return result.toString();
    }
}
