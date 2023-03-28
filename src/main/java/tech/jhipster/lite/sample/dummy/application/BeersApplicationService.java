package tech.jhipster.lite.sample.dummy.application;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.sample.dummy.domain.BeerId;
import tech.jhipster.lite.sample.dummy.domain.beer.Beer;
import tech.jhipster.lite.sample.dummy.domain.beer.BeerToCreate;
import tech.jhipster.lite.sample.dummy.domain.beer.Beers;
import tech.jhipster.lite.sample.dummy.domain.beer.BeersCreator;
import tech.jhipster.lite.sample.dummy.domain.beer.BeersRemover;
import tech.jhipster.lite.sample.dummy.domain.beer.BeersRepository;

@Service
public class BeersApplicationService {

  private final BeersRepository beers;
  private final BeersCreator creator;
  private final BeersRemover remover;

  public BeersApplicationService(BeersRepository beers) {
    this.beers = beers;

    creator = new BeersCreator(beers);
    remover = new BeersRemover(beers);
  }

  @PreAuthorize("can('create', #beerToCreate)")
  public Beer create(BeerToCreate beerToCreate) {
    return creator.create(beerToCreate);
  }

  @PreAuthorize("can('remove', #beer)")
  public void remove(BeerId beer) {
    remover.remove(beer);
  }

  public Beers catalog() {
    return beers.catalog();
  }
}
