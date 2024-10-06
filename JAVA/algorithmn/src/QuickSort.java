import java.util.Scanner;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] n = {42, 32, 24, 60, 15, 5, 90, 45};

        quickSort(n, 0, n.length - 1);

        // 결과 출력
        System.out.println(Arrays.toString(n));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 피벗을 기준으로 나눈 후 피벗의 위치를 받음
            int pivotIndex = partition(arr, low, high);

            // 피벗을 기준으로 좌우 부분 배열을 다시 퀵정렬
            quickSort(arr, low, pivotIndex - 1); // 피벗 왼쪽 부분
            quickSort(arr, pivotIndex + 1, high); // 피벗 오른쪽 부분
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 피벗을 배열의 마지막 요소로 선택
        int pivot = arr[high];
        int i = low - 1; // i는 피벗보다 작은 값이 위치할 인덱스

        for (int j = low; j < high; j++) {
            // 현재 값이 피벗보다 작거나 같으면 i를 증가시키고 교환
            if (arr[j] <= pivot) {
                i++;
                // arr[i]와 arr[j] 교환
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 피벗을 올바른 위치로 이동
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // 피벗의 최종 위치 반환
    }
}
