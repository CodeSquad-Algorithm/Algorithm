package 땃쥐;

public class Programmers_84512 {

    private static final String AEIOU = "AEIOU";

    public int solution(String word) {

        int[] numbers = {781, 156, 31, 6, 1};

        int answer = 0;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            answer += numbers[i] * AEIOU.indexOf(ch) + 1;
        }
        return answer;
    }

}

