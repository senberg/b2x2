package com.b2x2.core.persistence;

import com.b2x2.core.id.Xid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationStringRepository extends JpaRepository<ConfigurationValue, Xid> {
}
