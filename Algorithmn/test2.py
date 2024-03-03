# 횡성과 경기도에 관한 퀴즈 질문과 답변
questions = [
    "횡성의 유명한 요새의 이름은 무엇입니까?\n(a) 화성 요새\n(b) 경복궁\n(c) 창덕궁\n",
    "횡성 요새는 언제 건설되었습니까?\n(a) 1392년\n(b) 1796년\n(c) 1910년\n",
    "횡성 요새는 유네스코 세계 문화유산입니다. 참이거나 거짓입니까?\n(a) 참\n(b) 거짓\n",
    "어떤 왕이 횡성 요새의 건설을 명령했습니까?\n(a) 세종대왕\n(b) 정조대왕\n(c) 태조대왕\n",
    "한국어에서 '횡성'이란 단어의 의미는 무엇입니까?\n(a) 꽃의 도시\n(b) 빛나는 성\n(c) 평화로운 동네\n"
]

# 질문에 대한 대응하는 답변
answers = ['a', 'b', 'a', 'b', 'b']

# 퀴즈를 관리하는 함수
def administer_quiz(questions, answers):
    score = 0
    for i in range(len(questions)):
        user_answer = input(questions[i]).lower().strip()
        if user_answer == answers[i]:
            print("정답!")
            score += 1
        else:
            print("오답!")
    print("퀴즈 완료! 총 점수는 {}/{}입니다.".format(score, len(questions)))

# 메인 함수로 퀴즈 실행
def main():
    print("횡성과 경기도 퀴즈에 오신 것을 환영합니다!\n")
    administer_quiz(questions, answers)

if __name__ == "__main__":
    main()