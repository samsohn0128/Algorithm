import java.util.*;

class Solution {
    Map<String, Integer> genrePlayCount = new HashMap<>();
    List<Genre> genreList = new ArrayList<>();
    List<Music> musicList = new ArrayList<>();

    public int[] solution(String[] genres, int[] plays) {
        countGenreAndAddMusic(genres, plays);
        List<Integer> answerList = new ArrayList<>();
        for (Genre genre : genreList) {
            int[] tempAnswer = findTwoMaxMusic(genre);
            for (int i = 0; i < tempAnswer.length; i++) {
                answerList.add(tempAnswer[i]);
            }
        }
        return answerList.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    void countGenreAndAddMusic(String[] genres, int[] plays) {
        for (int i = 0; i < genres.length; i++) {
            final int finalI = i;
            genrePlayCount.computeIfPresent(genres[i], (k, v) -> v += plays[finalI]);
            genrePlayCount.putIfAbsent(genres[i], plays[i]);
            musicList.add(new Music(i, genres[i], plays[i]));
        }
        genrePlayCount.forEach((k, v) -> genreList.add(new Genre(k, v)));
        Collections.sort(genreList, (g1, g2) -> g2.playCount - g1.playCount);
    }

    int[] findTwoMaxMusic(Genre genre) {
        int[] musicIds = musicList.stream()
                .filter(music -> music.genre.equals(genre.genre))
                .sorted((m1, m2) -> m2.playCount - m1.playCount)
                .mapToInt(music -> music.id)
                .toArray();
        if (musicIds.length >= 2) {
            return new int[]{musicIds[0], musicIds[1]};
        } else {
            return new int[]{musicIds[0]};
        }
    }

    class Genre {
        String genre;
        int playCount;

        Genre(String genre, int playCount) {
            this.genre = genre;
            this.playCount = playCount;
        }

        @Override
        public String toString() {
            return "{genre = " + genre
                    + ", playCount = " + playCount
                    + "}";
        }
    }

    class Music {
        int id;
        String genre;
        int playCount;

        Music(int id, String genre, int playCount) {
            this.id = id;
            this.genre = genre;
            this.playCount = playCount;
        }

        @Override
        public String toString() {
            return "{id = " + id
                    + ", genre = " + genre
                    + ", playCount = " + playCount
                    + "}";
        }
    }
}