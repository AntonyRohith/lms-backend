package org.capestart.repository;

import javax.transaction.Transactional;

import org.capestart.entity.Author;


@Transactional
public interface AuthorRepo extends BaseRepository<Author>{

}
