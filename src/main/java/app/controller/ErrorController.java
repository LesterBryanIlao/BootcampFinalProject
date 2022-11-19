package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping(method = RequestMethod.GET)
	public String showError(@RequestParam(name = "error", required = false, defaultValue = "") String error,
			ModelMap map) {
		map.addAttribute("error", error);
		return "error";
	}
}
