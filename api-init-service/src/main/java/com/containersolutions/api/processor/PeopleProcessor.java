package com.containersolutions.api.processor;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import com.containersolutions.api.models.PeopleDTO;

public class PeopleProcessor implements ItemProcessor<PeopleDTO, PeopleDTO> {
	@Override
	public PeopleDTO process(PeopleDTO item) throws Exception {
		System.out.println("Transforming People(s).." + item.getName());
		item.setUuid(UUID.randomUUID().toString());
		return item;
	}
}
