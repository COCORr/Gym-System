package com.huawei.classroom.context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 用例判定上下文。
 */
public class ClassContext {

    private List<Testcase> testSuit = new ArrayList();

    /**
     * 指定用例判定逻辑。
     * @param predicate 用例判定逻辑。
     * @param <T>
     * @return
     */
    public <T> Testcase testcase(Predicate<T> predicate) {
        Testcase testcase=new Testcase();
        testcase.predicate(predicate);
        testSuit.add(testcase);
        return testcase;
    }

    /**
     * 打印所有的测试结果。
     */
    public void printResult() {
        List<TestResult> results = this.testSuit.stream().map(t -> t.getResult()).collect(Collectors.toList());
        Gson gson = new Gson();
        System.out.println(gson.toJson(results));
    }

}
