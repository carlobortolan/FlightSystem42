/*
 * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Music;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Music {


    private String artist;
    private String title;
    private String icon;
    private String source;

    private String lyrik;

    private List<Music> musicLibrary;

    public Music() {
    }

    public Music(String artist, String title, String icon, String source, String lyrik) {
        this.artist = artist;
        this.title = title;
        this.icon = icon;
        this.source = source;
        this.lyrik = lyrik;
    }


    public void initialize() throws IOException {
        Path music = Path.of("src/main/java/Music/MusicLocation");
        String content = Files.readString(music);
        String[] musicTitles = content.split("\n");
        musicLibrary = new LinkedList<>();

        Path lyriks = Path.of("src/main/java/Music/Lyrik");
        String contentLyriks = Files.readString(lyriks);
        String[] musicLyriks = contentLyriks.split("\\|");


        for (int i = 0; i < musicTitles.length; i++) {
            String[] details = musicTitles[i].split("\\|");
            if (details.length > 3) {
                String artist = details[0].trim();
                String title = details[1].trim();
                String icon = details[2].trim();
                String source = details[3].trim();
                String lyrik = musicLyriks[i];
                musicLibrary.add(new Music(artist, title, icon, source, lyrik));
            }
        }
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public String getSource() {
        return source;
    }

    public List<Music> getMusicLibrary() {
        return musicLibrary;
    }

    public String getLyrik() {
        return lyrik;
    }
}
