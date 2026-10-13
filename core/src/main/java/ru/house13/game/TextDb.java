package ru.house13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Загрузчик JSON-паков текстов (assets/text/*.json).
 *
 * Формат — плоский объект: "ИМЯ_ПОЛЯ": значение. Строки — как есть,
 * числа — для int-полей (HELP_WRAP и т.п.), массивы — для String[]
 * (GHOST_STRINGS, INFO_LINES). Ключи с префиксом "_" (например "_meta")
 * и вложенные объекты игнорируются.
 *
 * Чтение: Gdx.files.internal (десктоп/Android); в headless-тестах
 * Gdx.files == null — тогда файл ищется в classpath (assets попадают
 * туда через sourceSets) и оборачивается в FileHandle(File).
 */
final class TextDb {

    private TextDb() {
    }

    /**
     * @return плоская карта значений или null, если файл не найден/битый
     *         (null => вызывающий Texts.reload оставит вшитые значения).
     */
    static Map<String, Object> load(String internalPath) {
        FileHandle h = open(internalPath);
        if (h == null || !h.exists()) {
            return null;
        }
        try {
            JsonValue root = new JsonReader().parse(h);
            Map<String, Object> out = new LinkedHashMap<>();
            for (JsonValue v = root.child; v != null; v = v.next) {
                if (v.name == null || v.name.startsWith("_")) {
                    continue; // _meta и прочие служебные ключи
                }
                switch (v.type()) {
                    case stringValue:
                        out.put(v.name, v.asString());
                        break;
                    case doubleValue:
                    case longValue:
                        out.put(v.name, v.asDouble());
                        break;
                    case array:
                        String[] a = new String[v.size];
                        int i = 0;
                        for (JsonValue c = v.child; c != null; c = c.next) {
                            a[i++] = c.asString();
                        }
                        out.put(v.name, a);
                        break;
                    default:
                        break; // объекты/булевы — текстами не являются
                }
            }
            return out;
        } catch (Exception e) {
            String msg = "TextDb: не удалось разобрать " + internalPath + ": " + e;
            if (Gdx.app != null) {
                Gdx.app.error("House13", msg);
            } else {
                System.err.println("House13: " + msg);
            }
            return null;
        }
    }

    private static FileHandle open(String path) {
        if (Gdx.files != null) {
            FileHandle h = Gdx.files.internal(path);
            if (h.exists()) {
                return h;
            }
        }
        try {
            URL url = TextDb.class.getResource("/" + path);
            if (url != null) {
                return new FileHandle(new java.io.File(url.toURI()));
            }
        } catch (Exception ignored) {
            // classpath-фолбэк недоступен — вернём что есть
        }
        return Gdx.files != null ? Gdx.files.internal(path) : null;
    }
}
