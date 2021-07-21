package com.api.mutants.mutantsconsumer.repository;


import com.api.mutants.mutantsconsumer.entity.DnaVerified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaVerifiedRepository extends JpaRepository<DnaVerified, Long> {


}
