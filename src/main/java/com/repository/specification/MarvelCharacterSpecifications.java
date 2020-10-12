package com.repository.specification;

import java.text.MessageFormat;

import org.springframework.data.jpa.domain.Specification;

import com.entity.MarvelCharacter;

public final class MarvelCharacterSpecifications {

	public static Specification<MarvelCharacter> firstNameContains(String expression) {
	    return (root, query, builder) -> builder.like(root.get("firstName"), contains(expression));
	}

	public static Specification<MarvelCharacter> lastNameContains(String expression) {
	    return (root, query, builder) -> builder.like(root.get("lastName"), contains(expression));
	}

	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}
}
