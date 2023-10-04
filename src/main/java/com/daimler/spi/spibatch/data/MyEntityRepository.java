package com.daimler.spi.spibatch.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEntityRepository extends JpaRepository<EntityObj, Long> {
}