package ru.house13.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Загрузчик и хранилище текстур. Nearest-фильтрация обязательна:
 * дробная билинейная фильтрация размывает пиксель-арт.
 * Ассеты ищутся через Gdx.files.internal (classpath / assets).
 */
public final class Assets implements Disposable {

    private final ObjectMap<String, Texture> textures = new ObjectMap<>();

    /** Текстура по имени файла в assets (кэшируется; первая загрузка — из internal). */
    public Texture tex(String name) {
        Texture t = textures.get(name);
        if (t == null) {
            t = new Texture(Gdx.files.internal(name));
            t.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
            textures.put(name, t);
        }
        return t;
    }

    @Override
    public void dispose() {
        for (Texture t : textures.values()) {
            t.dispose();
        }
        textures.clear();
    }
}
