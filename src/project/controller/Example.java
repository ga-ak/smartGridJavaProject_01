package project.controller;

public class Example {

    // 비밀번호 찾기
    public void findPW() {
        String a = "[%s]님 안녕하세요 변경된 비밀번호는 [%s]입니다";
        CreatePassword cp = new CreatePassword();

        String formatedtext = String.format(a, "김철호", cp.createPW());
        System.out.println(formatedtext);
    }
}
