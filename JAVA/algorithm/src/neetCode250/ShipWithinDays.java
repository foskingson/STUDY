package neetCode250;

public class ShipWithinDays {
    public int shipWithinDays(int[] weights, int days) {
        int mw = -1, tw = 0;
        for (int w : weights) {
            mw = Math.max(mw, w);
            tw+=w;
        }

        int left = mw, right = tw;
        while (left<right) {
            int mid = (left + right) / 2;
            int dn = 1;
            int cw = 0;

            for (int w : weights) {
                if (cw+w>mid) {
                    dn++;
                    cw=0;
                }
                cw+=w;
            }

            if (dn > days) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        ShipWithinDays solution = new ShipWithinDays();

        // 테스트용 데이터
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        // 메서드 호출
        int result = solution.shipWithinDays(weights, days);

        // 결과 출력
        System.out.println("최소 선박의 최대 무게: " + result);
    }
}
