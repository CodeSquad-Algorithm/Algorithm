package 아더;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pro_84512_모음사전 {

    static List<String> words = new ArrayList<>();

    public static void main(String[] args) {
        Pro_84512_모음사전 pro = new Pro_84512_모음사전();

        System.out.println(pro.solution("AAAAE"));
        System.out.println(pro.solution("AAAE"));
        System.out.println(pro.solution("I"));
        System.out.println(pro.solution("EIO"));
    }

    public int solution(String word) {
        int answer = 0;
        String[] candidates = {"A", "E", "I", "O", "U"};

        // 리스트에 A E I O U를 순서대로 완전탐색
        rec_func(0, "", candidates);

        // 리스트를 정렬
        words = words.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        answer = words.indexOf(word) + 1;

        return answer;
    }

    public void rec_func(int k, String word, String[] candidates) {
        if (k >= 5) {
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            words.add(word + candidates[i]);
            rec_func(k + 1, word + candidates[i], candidates);
        }
    }
}
