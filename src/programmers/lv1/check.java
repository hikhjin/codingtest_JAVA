import java.util.Arrays;

class Solution_check {
    public int[] solution(String[] words) {
        int[] answer = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            if (checkRule(words[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean checkRule(String word) {
        if (!hasMoreThanFourConsonantsInRow(word) || !hasMoreThanFourVowelsInRow(word)
                || !hasMoreThanFourSequentialCharacters(word) || !hasMoreThanThreeSameCharactersInRow(word)) {
            return false;
        }
        return true;
    }

    private boolean hasMoreThanFourVowelsInRow(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (!isVowel(word.charAt(i))) {
                count++;
                if (count >= 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean hasMoreThanFourConsonantsInRow(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                count++;
                if (count >= 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private boolean hasMoreThanThreeSameCharactersInRow(String word) {
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
                if (count >= 3) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }

    private boolean hasMoreThanFourSequentialCharacters(String word) {
        for (int i = 0; i < word.length() - 3; i++) {
            if (word.charAt(i) + 1 == word.charAt(i + 1) &&
                    word.charAt(i + 1) + 1 == word.charAt(i + 2) &&
                    word.charAt(i + 2) + 1 == word.charAt(i + 3)) {
                return true;
            } else if (word.charAt(i) - 1 == word.charAt(i + 1) &&
                    word.charAt(i + 1) - 1 == word.charAt(i + 2) &&
                    word.charAt(i + 2) - 1 == word.charAt(i + 3)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_check solution = new Solution_check();
        String[] words = {"aeiou", "asdfgh", "yakkke", "abfedc", "xyzabc", "qwertyuiop"};
        int[] result = solution.solution(words);
        System.out.println(Arrays.toString(result));
    }
}
