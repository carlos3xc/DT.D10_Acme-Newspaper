
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
import domain.Newspaper;

import repositories.ArticleRepository;
import repositories.NewspaperRepository;
import security.Authority;
import security.LoginService;

@Service
@Transactional
public class NewspaperService {

	@Autowired
	private NewspaperRepository	newspaperRepository;

	@Autowired
	private Validator				validator;


	public Newspaper create() {
		Assert.isTrue(this.checkUser());
		Newspaper result;
		result = new Newspaper();
		result.setArticles(new ArrayList<Article>());
		Assert.notNull(result, "The newspaper must not be null.");
		return result;
	}

	public Newspaper save(final Newspaper newspaper) {
		Assert.notNull(newspaper, "The newspaper must not be null.");
		final Newspaper aSave = this.newspaperRepository.save(newspaper);
		return aSave;
	}

	public void delete(final Newspaper newspaper) {
		Assert.isTrue(this.checkAdmin());
		Assert.notNull(newspaper, "The newspaper must not be null.");
		final Newspaper aDelete = this.newspaperRepository.findOne(newspaper.getId());
		this.newspaperRepository.delete(aDelete);
	}

/*	public Newspaper reconstruct(final ArticleForm articleForm, final BindingResult binding) {
		Newspaper result;
		if (articleForm.getId() == 0) {
			result = this.create();

			result.setTitle(articleForm.getTitle());
			result.setDescription(articleForm.getSummary());
			result.setText(articleForm.getText());
			result.setPictures(articleForm.getPictures());
			result.setIsDraft(articleForm.getIsDraft());

		} else {
			result = this.newspaperRepository.findOne(articleForm.getId());

			result.setTitle(articleForm.getTitle());
			result.setDescription(articleForm.getSummary());
			result.setText(articleForm.getText());
			result.setPictures(articleForm.getPictures());
			result.setIsDraft(articleForm.getIsDraft());

			this.validator.validate(result, binding);
		}
		return result;
	}*/

	public Newspaper findOne(final int newspaperId) {
		Newspaper result;
		result = this.newspaperRepository.findOne(newspaperId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Newspaper> findAll() {
		Collection<Newspaper> result;
		result = this.newspaperRepository.findAll();
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
