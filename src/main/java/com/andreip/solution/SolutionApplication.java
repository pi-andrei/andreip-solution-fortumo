package com.andreip.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutionApplication.class, args);
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/sum")
	public Integer calculateNumbers(@RequestBody String request) {
		// Assuming this will not happen according to task description
		if (request == null ) return 0;

        Calculator calculator = Calculator.getInstance();
        // stop the calculation and return results
	    if (request.toLowerCase().equals("end")) {
	    	calculator.setInProgress(false);
	    	return calculator.getSum();
        }

	    // Assume request can be parsed, in other case will return code 500
	    int num = Integer.parseInt(request);
	    calculator.setInProgress(true);
	    calculator.addToSum(num);

	    // To prevent dead locks, release request after 10 seconds
	    int iterationLimit = 1000;
	    int currentIteration = 1;
	    while (calculator.isInProgress()) {
			try {
				//noinspection BusyWait
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentIteration++;
			if (currentIteration == iterationLimit) break;
		}

	    return calculator.getSum();
	}
}
