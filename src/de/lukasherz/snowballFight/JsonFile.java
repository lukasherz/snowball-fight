/*
 * Copyright (c) 2018. Lukas Herz
 */

package de.lukasherz.snowballFight;

import de.maltekp.json.JsonArray;
import de.maltekp.json.JsonObject;

import java.io.*;

public class JsonFile {
    private String path;

    public JsonFile(String path) {
        this.path = path;
    }

    public JsonObject readJsonObject() {
        String fileContent = read();
        if (fileContent != null) {
            return JsonObject.parseJsonObject(fileContent);
        } else {
            return null;
        }
    }

    public JsonArray readJsonArray() {
        String fileContent = read();
        if (fileContent != null) {
            return JsonArray.parseJsonArray(fileContent);
        } else {
            return null;
        }
    }

    public String read() {
        if (new File(path).exists()) {
            try {
                BufferedReader reader = new BufferedReader((new FileReader(path)));

                String line, content = "";
                while ((line = reader.readLine()) != null) {
                    content += line;
                }

                reader.close();
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public boolean saveJsonObject(JsonObject jsonObject) {
        return save(jsonObject.toString());
    }

    public boolean saveJsonArray(JsonArray jsonArray) {
        return save(jsonArray.toString());
    }

    private boolean save(String string) {
        File file = new File(path.substring(0, path.lastIndexOf(File.separator)));
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(string);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
