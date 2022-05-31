package com.gatheringdecks.cards.resource;

import java.util.List;

import javax.validation.Valid;

import com.gatheringdecks.cards.model.Card;
import com.gatheringdecks.cards.repository.Cards;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/cards")
public class CardsResource {
	
	@Autowired
	private Cards cards;
	
	@PostMapping
	public Card adicionar(@Valid @RequestBody Card card) {
		return cards.save(card);
	}
	
	@GetMapping
	public List<Card> listar() {
		return cards.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Card> buscar(@PathVariable Long id) {
		Card card = cards.getOne(id);
		
		if (card == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(card);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Card> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Card card) {
		Card existente = cards.getOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(card, existente, "id");
		
		existente = cards.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Card card = cards.getOne(id);
		
		if (card == null) {
			return ResponseEntity.notFound().build();
		}
		
		cards.delete(card);
		
		return ResponseEntity.noContent().build();
	}
}