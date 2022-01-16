package com.codev.foodimpact.repositories;

import com.codev.foodimpact.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
