package nl.adacademie.drankbuddy.repository.interfaces;

import nl.adacademie.drankbuddy.entity.Account;
import nl.adacademie.drankbuddy.entity.mutation.StockMutation;

import java.util.List;

public interface StockMutationDaoInterface {

    List<StockMutation> findAllByAccount(Account account);

    void save(StockMutation stockMutation);

}
