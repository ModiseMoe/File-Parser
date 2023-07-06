
package com.enviro.assessment.grad001.ModiseMoerane.FileParser;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountProfileRepository extends JpaRepository<AccountProfile, Long> {

    public AccountProfile findByAccountHolderNameAndAccountHolderSurname(String accountHolderName, String accountHolderSurname);

    
}
