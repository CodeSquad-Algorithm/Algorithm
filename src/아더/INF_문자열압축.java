package 아더;

public class INF_문자열압축 {

    public static void main(String[] args) {
        String test1 = "KKHSSSSSSSE";
        String test2 = "KSTTTSEEKFKKKDJJGG";

        INF_문자열압축 inf_문자열압축 = new INF_문자열압축();
        System.out.println(inf_문자열압축.solution(test1)); // K2HS7E
        System.out.println(inf_문자열압축.solution(test2)); // KST3SE2KFK3DJ2G2
    }

    private String solution(String test) {
        int count = 1;
        StringBuilder sb = new StringBuilder();

        char target = test.charAt(0);
        sb.append(target);

        for (int i = 1; i < test.length(); i++) {
             if (target == test.charAt(i)) {
                 count++;
             } else {
                 target = test.charAt(i);
                 if (count != 1) {
                     sb.append(count);
                 }
                 sb.append(target);
                 count = 1;
             }
        }
        if (count != 1) {
            sb.append(count);
        }

        return sb.toString();
    }

}
