package org.ka.apples;


import java.util.List;
import java.util.stream.Collectors;

public class AppleProcessor {

    private final AppleFunction appleFunction;

    public AppleProcessor(AppleFunction appleFunction) {
        this.appleFunction = appleFunction;
    }

    public List<AppleCore> process(List<Apple> apples) {
        return apples.stream()
                .map(appleFunction::apply)
                .collect(Collectors.toList());
    }
}
