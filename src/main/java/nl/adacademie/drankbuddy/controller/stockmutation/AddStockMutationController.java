package nl.adacademie.drankbuddy.controller.stockmutation;

import nl.adacademie.drankbuddy.dto.AddStockMutationRequestDto;
import nl.adacademie.drankbuddy.entity.mutation.CorrectionMutation;
import nl.adacademie.drankbuddy.entity.mutation.DeliveryMutation;
import nl.adacademie.drankbuddy.entity.mutation.SaleMutation;
import nl.adacademie.drankbuddy.entity.mutation.StockMutation;
import nl.adacademie.drankbuddy.repository.interfaces.StockMutationDaoInterface;
import nl.adacademie.drankbuddy.view.type.AddStockMutationPageStatus;

public class AddStockMutationController {

    private final StockMutationDaoInterface stockMutationDao;

    public AddStockMutationController(StockMutationDaoInterface stockMutationDao) {
        this.stockMutationDao = stockMutationDao;
    }

    public AddStockMutationPageStatus addStockMutation(AddStockMutationRequestDto addStockMutationRequestDto) {

        if (addStockMutationRequestDto.product() == null) {
            return AddStockMutationPageStatus.EMPTY_FIELDS;
        }

        if (addStockMutationRequestDto.mutationType() == null) {
            return AddStockMutationPageStatus.EMPTY_FIELDS;
        }

        if (addStockMutationRequestDto.amount() == 0) {
            return AddStockMutationPageStatus.NO_ZERO;
        }

        StockMutation stockMutation = switch (addStockMutationRequestDto.mutationType()) {
            case SALE -> new SaleMutation(
                addStockMutationRequestDto.amount(),
                addStockMutationRequestDto.product()
            );
            case DELIVERY -> new DeliveryMutation(
                addStockMutationRequestDto.amount(),
                addStockMutationRequestDto.product()
            );
            case CORRECTION -> new CorrectionMutation(
                addStockMutationRequestDto.amount(),
                addStockMutationRequestDto.product()
            );
        };

        stockMutationDao.save(stockMutation);

        return AddStockMutationPageStatus.SUCCESS;
    }

}
