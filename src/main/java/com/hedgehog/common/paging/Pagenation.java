package com.hedgehog.common.paging;

/*페이지 처리를 위함.*/
public class Pagenation {

    /*
     * SelectCriteria는 sql쪽에서 계산을 편하게 하기 위한 DTO.
     * 그래서 최소한의 매개변수가 들어가고 이를 계산하여 SelectCriteria가 출력되도록 한다.
     * */
    public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String searchCondition, String searchValue, String orderBy) {
        /*
         * pageNo은 현재 몇페이지인지
         * totalCount는 전체 게시물 수가 얼마인지. 이 두가지는 이미 넘어온 상태다. 그래서 나머지 변수만 선언해준다.
         * limit은 일반적으로 10이긴 하겠지만. 상황에 따라 다를 수 있어서 매개변수로 지정했다.
         * */
        int maxPage; // 전체 페이지에서 가장 마지막 페이지
        int startPage; // 한번에 표시될 페이지 버튼의 시작할 페이지
        int endPage; // 한번에 표시될 페이지 버튼의 끝나는 페이지
        /*1페이지라면 예를 들어 << 1 2 3 4 5 6 7 8 9 10 >> 이런 느낌일테니깐. startPage는 1, endPage는 10*/
        int startRow;
        int endRow;

        /*
         * 총 페이지 수를 계산해보자. 10페이지씩 보여준다고 할 때
         * 총 목록 수가 99개인 경우 10페이지 -> 9.9 의 올림
         * 총 목록 수가 100개인 경우 10페이지 -> 10.0 의 올림
         * 총 목록 수가 101개인 경우 11페이지. -> 10.1 의 올림
         * */
        maxPage = (int) Math.ceil(1.0 * totalCount / limit);
        /*
         * 현재 페이지에서 10개의 버튼을 보여준다고 하면. -> buttonAmount
         * 1 ... 10
         * 11 ... 20
         * 21 ... 30
         * 다시한번 말하면
         * 1 페이지 ~ 10페이지 까지는 startPage 가 1
         * -> -1 하고. /10 내림.하고 *10 + 1
         * 11페이지 ~ 20페이지 까지는 startPage 가 11
         * -> -1 하고. /10 내림.하고 *10 + 1
         * 21페이지 ~ 30페이지 까지는 startPage 가 21
         * -> -1 하고. /10 내림.하고 *10 + 1
         * */
        startPage = (int) ((pageNo - 1) / buttonAmount) * buttonAmount + 1;
        endPage = startPage + buttonAmount - 1;
        /*
         * 이와중에 maxPage보다 endPage가 더 작은 경우가 있다.
         * startPage는 의미가 없다. 1페이지가 최소값일것이기 때문
         * */
        if (maxPage < endPage) {
            endPage = maxPage;
        }
        /*
         * 게시물이 아무것도 존재하지 않을 경우도 생각해야 한다.
         * 게시물이 없다고 하더라도 1페이지라고 표시해야할 것이다.
         * maxPage가 분명 0으로 나올 가능성이 있고. endPage는 원래 10이어야 겠지만.
         * 위의 연산식 때문에 자동으로 0이 된다 그래서 아래와 같은 조건식은 문제가 없다.
         * */
        if (maxPage == 0 && endPage == 0) {
            maxPage = startPage;
            endPage = startPage;
        }
        /*
         * 조회할 시작번호와 마지막 행 번호를 계산한다.
         * 즉, 내가 어떤 페이지를 보고 있을때 거기의 맨 위 항목의 행번호와
         * 맨 아래 항목의 행번호를 말한다.
         * */
        startRow = (pageNo - 1) * limit; // 0부터 센다.
        endRow = (startRow) + limit - 1; // 예를 들어 limit 이 10이면. 9를 말한다. 0~9 -> 10개 = limit

        System.out.println("startRow = " + startRow);
        System.out.println("endRow = " + endRow);

        SelectCriteria selectCriteria = new SelectCriteria(pageNo, totalCount, limit, buttonAmount, maxPage, startPage, endPage, startRow, endRow, searchCondition, searchValue, orderBy);

        return selectCriteria;
    }

    /*
     * 한편, 제일 기본적인 검색이 있을 수 있다. 즉, 검색조건과 검색어가 없는 경우를 말한다.
     * 이는 오버로딩으로 처리한다.
     * */
    public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount) {
        return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null, null, null);
    }

    public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String orderBy) {
        return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null, null, orderBy);
    }
    public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String searchCondition, String searchValue) {
        return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue, null);
    }
}
