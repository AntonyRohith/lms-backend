package org.capestart.repository;

import javax.transaction.Transactional;

import org.capestart.entity.Publisher;

@Transactional
public interface PublisherRepo extends BaseRepository<Publisher> {

}
