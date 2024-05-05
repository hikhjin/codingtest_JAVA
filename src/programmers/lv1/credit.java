package programmers.lv1;

import java.util.Arrays;

class Solution_credit {
    public int[] solution(String[] card_numbers) {
        int[] answer = new int[card_numbers.length];

        for (int i = 0; i < card_numbers.length; i++) {
            if (isValidFormat(card_numbers[i])) {
                if (isValidCreditCardNumber(card_numbers[i])) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }
            } else {
                answer[i] = 0;
            }


        }

        return answer;
    }

    private boolean isValidFormat(String cardNumber) {
        return cardNumber.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$") || cardNumber.matches("^\\d{16}$");
    }

    private boolean isValidCreditCardNumber(String cardNumber) {
        // 하이픈 제거
        cardNumber = cardNumber.replaceAll("-", "");

        // 16자리인지 확인
        if (cardNumber.length() != 16) {
            return false;
        }

        int sum = 0;
        boolean isEven = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (isEven) {
                digit *= 2;
                if (digit >= 10) {
                    digit = digit % 10 + digit / 10;
                }
            }

            sum += digit;
            isEven = !isEven;
        }

        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        Solution_credit solution = new Solution_credit();
        String[] card_numbers = {"3285-3764-9934-2453", "3285376499342453", "3285-3764-99342453",
                "3285-3764-9934-245", "3285-3764-9934-2459", "3285-3764-9934-2452", "3-2853764-9934-2453", "3"};
        int[] result = solution.solution(card_numbers);
        System.out.println(Arrays.toString(result)); // 예상 출력: [1, 1, 0, 0, 0, 0, 0, 0]
    }
}
