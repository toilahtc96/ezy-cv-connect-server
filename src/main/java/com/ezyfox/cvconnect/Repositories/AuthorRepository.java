package com.ezyfox.cvconnect.Repositories;

import com.ezyfox.cvconnect.entities.Author;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface AuthorRepository extends EzyDatabaseRepository<Long, Author> {
}
