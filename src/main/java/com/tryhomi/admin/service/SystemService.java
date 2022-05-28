

package com.tryhomi.admin.service;

import org.hibernate.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class SystemService {

	private static final int BATCH_SIZE = 100;

	private static Logger logger = LoggerFactory.getLogger(SystemService.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Async
	@Transactional(propagation = Propagation.SUPPORTS)
	public void reIndex() throws Exception {

	}
}
