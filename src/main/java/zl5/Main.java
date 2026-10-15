package zl5;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OperationResult<String> userOperationResult = OperationResult.success("Pawel");
        OperationResult<List<String>> namesList = OperationResult.success(List.of("bla", "bla"));
        OperationResult<Double> doubleOperationResult = OperationResult.failure("zzzzz");

        String user = userOperationResult.getValue();
        List<String> names = namesList.getValue();
        Double duble = doubleOperationResult.getValue();
        OperationResult<Integer> failedResult = OperationResult.failure("Failed");

        System.out.println(user);
        System.out.println(duble);
        System.out.println(names);
        System.out.println(failedResult.getMessage());
    }
}
