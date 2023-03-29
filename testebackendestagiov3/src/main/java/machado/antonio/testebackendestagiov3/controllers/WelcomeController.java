package machado.antonio.testebackendestagiov3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

	/**
	 * First API {@code GetMapping} method.
	 * 
	 * @return a welcome message
	 */
	@GetMapping
	public ResponseEntity<String> welcomeMessage() {
		return ResponseEntity.status(HttpStatus.OK).body(
				" Hi and Welcome! This /welcome address is not the path to the others addresses, but it is just a quick guide."

						+ " There are 6 REST Controllers for this API Project (to find all elements from the tables you can type  /all  in front of all of them using the REST GET request):"
						+ " They are at the controllers package, and they can be accessed by this addresses:  1. /equipment ; 2. /equipment/state ; 3. /equipment/model ;"
						+ " 4. /equipment/model-state-hourly-earnings ; 5. /equipment/position-history ; 6. /equipment/state-history ."

						+ " Thank you for your time!");
	}

}
