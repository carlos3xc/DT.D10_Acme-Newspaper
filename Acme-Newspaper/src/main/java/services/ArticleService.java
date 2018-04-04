
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Article;

import repositories.ArticleRepository;
import security.Authority;
import security.LoginService;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository	articleRepository;

	@Autowired
	private Validator				validator;


	public Article create() {
		Assert.isTrue(this.checkUser());
		Article result;
		result = new Article();
		result.setPictures(new ArrayList<String>());
		Assert.notNull(result, "The article must not be null.");
		return result;
	}

	public Article save(final Article article) {
		Assert.notNull(article, "The article must not be null.");
		final Article aSave = this.articleRepository.save(article);
		return aSave;
	}

	public void delete(final Article article) {
		Assert.isTrue(this.checkAdmin());
		Assert.notNull(article, "The article must not be null.");
		final Article aDelete = this.articleRepository.findOne(article.getId());
		this.articleRepository.delete(aDelete);
	}

/*	public Article reconstruct(final ArticleForm articleForm, final BindingResult binding) {
		Article result;
		if (articleForm.getId() == 0) {
			result = this.create();

			result.setTitle(articleForm.getTitle());
			result.setDescription(articleForm.getSummary());
			result.setText(articleForm.getText());
			result.setPictures(articleForm.getPictures());
			result.setIsDraft(articleForm.getIsDraft());

		} else {
			result = this.articleRepository.findOne(articleForm.getId());

			result.setTitle(articleForm.getTitle());
			result.setDescription(articleForm.getSummary());
			result.setText(articleForm.getText());
			result.setPictures(articleForm.getPictures());
			result.setIsDraft(articleForm.getIsDraft());

			this.validator.validate(result, binding);
		}
		return result;
	}*/

	public Article findOne(final int articleId) {
		Article result;
		result = this.articleRepository.findOne(articleId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Article> findAll() {
		Collection<Article> result;
		result = this.articleRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	private Boolean checkUser() {
		final Collection<Authority> a = LoginService.getPrincipal().getAuthorities();
		Boolean res = false;
		for (final Authority b : a)
			if (b.getAuthority().equals(Authority.USER))
				res = true;
		return res;
	}

	private Boolean checkAdmin() {
		final Collection<Authority> a = LoginService.getPrincipal().getAuthorities();
		Boolean res = false;
		for (final Authority b : a)
			if (b.getAuthority().equals(Authority.ADMIN)) {
				res = true;
				break;
			}
		return res;
	}

}
