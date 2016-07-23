package org.ka.apples;


import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class AppleProcessor {

    private final AppleFunction appleFunction;

    public AppleProcessor(AppleFunction appleFunction) {
        this.appleFunction = appleFunction;
    }

    public List<AppleCore> process(List<Apple> apples) {
        return apples.stream()
                .map(apple -> appleFunction.apply(apple))
                .collect(Collectors.toList());
    }
}
