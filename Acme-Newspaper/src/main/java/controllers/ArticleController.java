package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ArticleService;
import domain.Article;


@Controller
@RequestMapping("/article")
public class ArticleController extends AbstractController {

	@Autowired
	private ArticleService articleService;
	
	public ArticleController() {
		super();
	}

	//Display -----------------------------------------------------------------
	
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display(final Integer articleId) {
			ModelAndView result;

			Article article = articleService.findOne(articleId);

			result = new ModelAndView("rendezvous/display");
			result.addObject("article", article);
			result.addObject("requestURI", "article/display.do");
			return result;
		}
}
